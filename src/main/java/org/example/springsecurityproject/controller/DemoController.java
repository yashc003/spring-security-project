package org.example.springsecurityproject.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {



    @GetMapping("/public")
    String home(){
        return "public unauthenticated homepage";
    }

    @GetMapping("/home")
    String hello(){
        return "secured page";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin only";
    }

    @PreAuthorize("hasAuthority('BLOG_WRITE')")
    @GetMapping("/blog/create")
    public String createBlog(){
        return "blog create allowed";
    }

    @GetMapping("/me")
    public  String me(Authentication authentication){
        return "logged in as: "+ authentication.getName();
    }


}
