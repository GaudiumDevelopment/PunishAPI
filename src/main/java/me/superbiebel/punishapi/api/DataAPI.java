package me.superbiebel.punishapi.api;

import me.superbiebel.punishapi.data.services.Service;

import java.util.concurrent.ConcurrentHashMap;

public class DataAPI {
    
    private ConcurrentHashMap<ServiceType, Service> serviceRegistry;
    private final PunishAPI punishAPI;
    
    public DataAPI(PunishAPI punishAPI) {
        this.punishAPI = punishAPI;
    }
    //thread safe
    public void addService(ServiceType serviceType, Service service) throws Exception {
        punishAPI.getDatamanager().addService(serviceType, service);
    }
    //thread safe
    public void removeService(ServiceType serviceType) throws Exception {
        punishAPI.getDatamanager().removeService(serviceType);
    }
    
    
    public enum ServiceType {
        TEST
    }
}
