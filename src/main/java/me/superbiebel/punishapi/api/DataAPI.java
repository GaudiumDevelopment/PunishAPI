package me.superbiebel.punishapi.api;

import me.superbiebel.punishapi.data.services.Service;

public class DataAPI {
    
    private final PunishAPI punishAPI;
    
    public DataAPI(PunishAPI punishAPI) {
        this.punishAPI = punishAPI;
    }
    //thread safe
    public void addService(ServiceType serviceType, Service service) throws Exception {
        punishAPI.getDatamanager().addService(serviceType, service);
    }
    //thread safe
    public void removeService(ServiceType serviceType,boolean kill) throws Exception {
        punishAPI.getDatamanager().removeService(serviceType,kill);
    }
    
    
    public enum ServiceType {
        TEST
    }
}
