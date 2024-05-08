package com.backend.ecommerce.security.controller;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author balde
 */
@Getter @Setter
public class Message {
    private String message;

    public Message() {
    }

    public Message(String message) {
        this.message = message;
    }       
}
