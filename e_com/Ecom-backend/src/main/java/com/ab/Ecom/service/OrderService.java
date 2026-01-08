package com.ab.Ecom.service;

import com.ab.Ecom.model.Order;
import com.ab.Ecom.model.OrderItem;
import com.ab.Ecom.model.Product;
import com.ab.Ecom.model.dto.OrderItemResponse;
import com.ab.Ecom.model.dto.OrderRequest;
import com.ab.Ecom.model.dto.OrderResponse;
import com.ab.Ecom.repo.OrderRepository;
import com.ab.Ecom.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;

    public OrderResponse placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        String orderId = "ORD" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        order.setOrderId(orderId);
        order.setCustomerName(orderRequest.customerName());
        order.setEmail(orderRequest.email());
        order.setStatus("PLACED");
        order.setOrderDate(LocalDate.now());
        List<OrderItem> orderItems = new ArrayList<>();
        orderRequest.items().forEach(
                item -> {
                    Product product = productRepository.findById(item.productId()).orElseThrow(() -> new RuntimeException("Product not found"));
                    product.setStockQuantity(product.getStockQuantity() - item.quantity());
                    productRepository.save(product);
                    OrderItem orderItem = OrderItem.builder()
                            .product(product)
                            .quantity(item.quantity())
                            .totalPrice(product.getPrice().multiply(BigDecimal.valueOf(item.quantity())))
                            .order(order)
                            .build();
                    orderItems.add(orderItem);
                }
        );
        order.setOrderItems(orderItems);
        Order savedOrder = orderRepository.save(order);
        List<OrderItemResponse> itemResponses = new ArrayList<>();
        order.getOrderItems().forEach(
                item -> {
                    OrderItemResponse itemResponse = new OrderItemResponse(
                            item.getProduct().getName(),
                            item.getQuantity(),
                            item.getTotalPrice()
                    );
                    itemResponses.add(itemResponse);
                }
        );
        OrderResponse orderResponse = new OrderResponse(
                savedOrder.getOrderId(),
                savedOrder.getCustomerName(),
                savedOrder.getEmail(),
                savedOrder.getStatus(),
                savedOrder.getOrderDate(),
                itemResponses
        );
        return orderResponse;
    }

    public List<OrderResponse> getAllOrderResponses() {
        List<OrderResponse> orderResponses = new ArrayList<>();
        List<Order> orders = orderRepository.findAll();
        orders.forEach(
                order -> {
                    List<OrderItemResponse> itemResponses = new ArrayList<>();
                    order.getOrderItems().forEach(
                            item -> {
                                OrderItemResponse itemResponse = new OrderItemResponse(
                                        item.getProduct().getName(),
                                        item.getQuantity(),
                                        item.getTotalPrice()
                                );
                                itemResponses.add(itemResponse);
                            }
                    );
                    OrderResponse orderResponse = new OrderResponse(
                            order.getOrderId(),
                            order.getCustomerName(),
                            order.getEmail(),
                            order.getStatus(),
                            order.getOrderDate(),
                            itemResponses
                    );
                    orderResponses.add(orderResponse);
                }
        );
        return orderResponses;
    }
}
