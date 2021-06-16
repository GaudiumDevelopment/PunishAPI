package me.superbiebel.punishapi.data;


import me.superbiebel.punishapi.System;
import me.superbiebel.punishapi.api.PunishAPI;
import me.superbiebel.punishapi.data.services.Service;
import me.superbiebel.punishapi.exceptions.ServiceAlreadyRegisteredException;
import me.superbiebel.punishapi.exceptions.ServiceNotFoundException;
import me.superbiebel.punishapi.exceptions.ShutDownException;
import me.superbiebel.punishapi.exceptions.StartupException;
import org.apache.logging.log4j.LogManager;

import java.util.concurrent.ConcurrentHashMap;

/**
 * FOR INTERNAL USE ONLY!!!!
 */
public class Datamanager extends System {
    
    private ConcurrentHashMap<PunishAPI.ServiceType, Service> serviceRegistry;
    //locked so threadsafe
    @Override
    public void onStartup(boolean force) {
        serviceRegistry = new ConcurrentHashMap<>();
    }
    //locked so threadsafe
    @Override
    public void onShutdown() {
        serviceRegistry.keys().asIterator().forEachRemaining(serviceType -> {
            try {
                removeService(serviceType, false);
            } catch (Exception e) {
                LogManager.getLogger().error("Could not unregister and shutdown servicetype: {}", serviceType, e);
            }
        });
    }
    //locked so threadsafe
    @Override
    public void onKill() {
        serviceRegistry.keys().asIterator().forEachRemaining(serviceType -> {
            try {
                removeService(serviceType, true);
            } catch (Exception e) {
                LogManager.getLogger().error("Could not unregister and shutdown servicetype: {}", serviceType, e);
            }
        });
    }
    //thread safe becuz of specialised datatype
    public void addService(PunishAPI.ServiceType serviceType, Service service) throws IllegalStateException, StartupException, ServiceAlreadyRegisteredException {
        if(serviceRegistry.containsKey(serviceType)) {
            throw new ServiceAlreadyRegisteredException("Service was already registered");
        }
        serviceRegistry.put(serviceType,service);
        service.startup(false);
    }
    //thread safe becuz of specialised datatype
    public void removeService(PunishAPI.ServiceType serviceType, boolean kill) throws ShutDownException, ServiceNotFoundException {
        Service service = serviceRegistry.remove(serviceType);
        if (service == null) {
            throw new ServiceNotFoundException("Servicetype not found");
        }
        if (kill) {
            service.kill();
        } else {
            service.shutdown();
        }
    }
    //thread safe becuz of specialised datatype
    public Service getService(PunishAPI.ServiceType serviceType) {
        Service service = serviceRegistry.get(serviceType);
        if (service == null) {
            throw new IllegalArgumentException("Servicetype not found");
        }
        return service;
    }
}
