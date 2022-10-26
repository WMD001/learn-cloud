package com.cloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * user controller
 *
 * @author Qiq
 */
@RestController
public class AdminController {

    @GetMapping("/signIn")
    public String hello() {
        return "Sign In";
    }

}
