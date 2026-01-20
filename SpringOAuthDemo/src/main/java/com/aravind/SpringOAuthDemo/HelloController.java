package com.aravind.SpringOAuthDemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("o-auth-greet")
    public String greet() {
        return "Hello OAuth";
    }
}
