package me.superbiebel.punishapi.data;


import me.superbiebel.punishapi.System;
import me.superbiebel.punishapi.api.DataAPI;
import me.superbiebel.punishapi.data.services.Service;

import java.util.concurrent.ConcurrentHashMap;

public class Datamanager extends System {
    
    private ConcurrentHashMap<DataAPI.ServiceType, Service> serviceRegistry;
    
    @Override
    public void onStartup(boolean force) {
    
    }
    
    @Override
    public void onShutdown() {
    
    }
    
    @Override
    public void onKill() {
    
    }
    public void addService(DataAPI.ServiceType serviceType, Service service) {
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
    public void removeService(DataAPI.ServiceType serviceType) {
    
    }
    public void getService(DataAPI.ServiceType serviceType) {
    
    }
}
