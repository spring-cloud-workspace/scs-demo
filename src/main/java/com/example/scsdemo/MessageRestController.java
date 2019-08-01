package com.example.scsdemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
class MessageRestController {

    @Value("${message:Hello default}")
    private String message;

    @Value("${secretMessage:No secrets here}")
    private String secretMessage;

    @Value("${spring.cloud.config.uri: No Value}")
    private String uri;

    @RequestMapping("/message")
    String getMessage() {
        return this.message;
    }

    @RequestMapping("/secretMessage")
    String getSecretMessage() {
        return this.secretMessage;
    }

    @RequestMapping("/configUrl")
    String getUri(){
        return this.uri;
    }
}