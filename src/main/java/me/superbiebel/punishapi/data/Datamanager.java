package me.superbiebel.punishapi.data;


import lombok.Getter;
import me.superbiebel.punishapi.System;
import me.superbiebel.punishapi.exceptions.ServiceAlreadyRegisteredException;
import me.superbiebel.punishapi.exceptions.ServiceNotFoundException;
import me.superbiebel.punishapi.exceptions.ShutDownException;
import me.superbiebel.punishapi.exceptions.StartupException;
import me.superbiebel.punishapi.services.Service;
import org.apache.logging.log4j.LogManager;

import java.util.concurrent.ConcurrentHashMap;

/**
 * FOR INTERNAL USE ONLY!!!!
 */
public class Datamanager extends System {
    
    private ConcurrentHashMap<Datamanager.DataServiceType, Service> serviceRegistry;
    @Getter
    private static final int MAXSERVICECOUNT = 1;
    
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
                removeDataService(dataServiceType, false);
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
                removeDataService(dataServiceType, true);
            } catch (Exception e) {
                LogManager.getLogger().error("Could not unregister and shutdown servicetype: {}", dataServiceType, e);
            }
        });
    }
    //thread safe becuz of specialised datatype
    public void addDataService(Datamanager.DataServiceType dataServiceType, Service service) throws IllegalStateException, StartupException, ServiceAlreadyRegisteredException {
        if(serviceRegistry.containsKey(dataServiceType)) {
            throw new ServiceAlreadyRegisteredException("Service was already registered");
        }
        serviceRegistry.put(dataServiceType,service);
        service.startup(false);
    }
    //thread safe becuz of specialised datatype
    public void removeDataService(Datamanager.DataServiceType dataServiceType, boolean kill) throws ShutDownException, ServiceNotFoundException {
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
    public Service getDataService(Datamanager.DataServiceType dataServiceType) {
        Service service = serviceRegistry.get(dataServiceType);
        if (service == null) {
            throw new IllegalArgumentException("Servicetype not found");
        }
        return service;
    }
    public int serviceCount() {
        return serviceRegistry.size();
    }
    public enum DataServiceType {
        TEST
    }
}
