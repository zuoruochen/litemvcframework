package com.zrc.demo;

import com.zrc.annotation.Service;

@Service
public class ServiceDemo {
    private String serviceName = ServiceDemo.class.getName();

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}
