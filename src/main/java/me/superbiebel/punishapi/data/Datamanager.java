package me.superbiebel.punishapi.data;


import me.superbiebel.punishapi.System;
import me.superbiebel.punishapi.api.DataAPI;
import me.superbiebel.punishapi.data.services.Service;
import org.apache.logging.log4j.LogManager;

import java.util.concurrent.ConcurrentHashMap;

public class Datamanager extends System {
    
    private ConcurrentHashMap<DataAPI.ServiceType, Service> serviceRegistry;
    //locked so threadsafe
    @Override
    public void onStartup(boolean force) {
    
    }
    //locked so threadsafe
    @Override
    public void onShutdown() {
        serviceRegistry.keys().asIterator().forEachRemaining(serviceType -> {
            try {
                removeService(serviceType);
            } catch (Exception e) {
                LogManager.getLogger().error("Could not unregister and shutdown servicetype: " + serviceType, e);
            }
        });
    }
    //locked so threadsafe
    @Override
    public void onKill() {
    
    }
    //thread safe becuz of specialised datatype
    public void addService(DataAPI.ServiceType serviceType, Service service) throws Exception {
        if(serviceRegistry.containsKey(serviceType)) {
            throw new IllegalStateException("Service was already registered");
        }
        serviceRegistry.put(serviceType,service);
            service.startup(false);
    }
    //thread safe becuz of specialised datatype
    public void removeService(DataAPI.ServiceType serviceType) throws Exception {
        Service service = serviceRegistry.remove(serviceType);
        if (service == null) {
            throw new IllegalArgumentException("Servicetype not found");
        }
        service.shutdown();
    }
    //thread safe becuz of specialised datatype
    public void getService(DataAPI.ServiceType serviceType) {
        if (serviceRegistry.get(serviceType) == null) {
            throw new IllegalArgumentException("Servicetype not found");
        }
    }
}
