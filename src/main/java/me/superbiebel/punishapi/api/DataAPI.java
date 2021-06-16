package me.superbiebel.punishapi.api;

import me.superbiebel.punishapi.PunishCore;
import me.superbiebel.punishapi.data.services.Service;

public class DataAPI {
    
    private final PunishCore punishCore;
    
    public DataAPI(PunishCore punishCore) {
        this.punishCore = punishCore;
    }
    //thread safe
    public void addService(ServiceType serviceType, Service service) throws Exception {
        punishCore.getDatamanager().addService(serviceType, service);
    }
    //thread safe
    public void removeService(ServiceType serviceType,boolean kill) throws Exception {
        punishCore.getDatamanager().removeService(serviceType,kill);
    }
    
    
    public enum ServiceType {
        TEST
    }
}
