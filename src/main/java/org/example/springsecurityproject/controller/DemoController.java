package org.example.springsecurityproject.controller;

import org.example.springsecurityproject.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class DemoController {
    @Autowired
    SampleService sampleService;



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

    @GetMapping("/sample/{name}")
    public String sample(@PathVariable String name){
       return sampleService.sampleMethod(name);
    }

}
