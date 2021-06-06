package me.superbiebel.punishapi.api;

import me.superbiebel.punishapi.data.services.Service;

import java.util.concurrent.ConcurrentHashMap;

public class DataAPI {
    
    private ConcurrentHashMap<ServiceType, Service> serviceRegistry;
    
    public void addService(ServiceType serviceType, Service service) {
        if(serviceRegistry.containsKey(serviceType)) {
            throw new IllegalStateException("Service was already registered");
        }
        serviceRegistry.put(serviceType,service);
        try {
            service.startup(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void removeService() {
    
    }
    
    
    public enum ServiceType {
        TEST
    }
}
