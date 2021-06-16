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
    
    private ConcurrentHashMap<PunishAPI.DataServiceType, Service> serviceRegistry;
    //locked so threadsafe
    @Override
    public void onStartup(boolean force) {
        serviceRegistry = new ConcurrentHashMap<>();
    }
    //locked so threadsafe
    @Override
    public void onShutdown() {
        serviceRegistry.keys().asIterator().forEachRemaining(dataServiceType -> {
            try {
                removeService(dataServiceType, false);
            } catch (Exception e) {
                LogManager.getLogger().error("Could not unregister and shutdown servicetype: {}", dataServiceType, e);
            }
        });
    }
    //locked so threadsafe
    @Override
    public void onKill() {
        serviceRegistry.keys().asIterator().forEachRemaining(dataServiceType -> {
            try {
                removeService(dataServiceType, true);
            } catch (Exception e) {
                LogManager.getLogger().error("Could not unregister and shutdown servicetype: {}", dataServiceType, e);
            }
        });
    }
    //thread safe becuz of specialised datatype
    public void addService(PunishAPI.DataServiceType dataServiceType, Service service) throws IllegalStateException, StartupException, ServiceAlreadyRegisteredException {
        if(serviceRegistry.containsKey(dataServiceType)) {
            throw new ServiceAlreadyRegisteredException("Service was already registered");
        }
        serviceRegistry.put(dataServiceType,service);
        service.startup(false);
    }
    //thread safe becuz of specialised datatype
    public void removeService(PunishAPI.DataServiceType dataServiceType, boolean kill) throws ShutDownException, ServiceNotFoundException {
        Service service = serviceRegistry.remove(dataServiceType);
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
    public Service getService(PunishAPI.DataServiceType dataServiceType) {
        Service service = serviceRegistry.get(dataServiceType);
        if (service == null) {
            throw new IllegalArgumentException("Servicetype not found");
        }
        return service;
    }
}
