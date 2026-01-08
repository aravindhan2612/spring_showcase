package com.ab.Ecom.model.dto;

public record OrderItemRequest (
    int productId,
    int quantity
){}
