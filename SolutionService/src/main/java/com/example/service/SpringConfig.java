package com.example.service;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.example.service.services")
@ComponentScan("com.example.service.stats")
@ComponentScan("com.example.service.db")
public class SpringConfig {
}