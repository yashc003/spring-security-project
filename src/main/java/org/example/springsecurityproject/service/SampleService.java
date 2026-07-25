package org.example.springsecurityproject.service;

import org.springframework.stereotype.Service;

@Service
public class SampleService {

    public String sampleMethod(String name){

        return name;
    }

    private String privateSampleMethod(String name){
        return name;
    }

}
