package com.project.code.service;

import org.springframework.stereotype.Service;

@Service
public class ServiceClass {

    public String getWelcomeMessage() {
        return "Welcome to the Store Management System!";
    }

    public String processRequest() {
        return "Request processed successfully!";
    }

    public String getApplicationStatus() {
        return "Application is running successfully!";
    }
}
