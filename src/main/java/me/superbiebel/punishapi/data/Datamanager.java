package me.superbiebel.punishapi.data;


import lombok.Getter;
import me.superbiebel.punishapi.abstractions.ServiceRegistry;
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
public class Datamanager extends ServiceRegistry<Datamanager.DataServiceType> {
    
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
                this.removeService(dataServiceType, false);
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
                this.removeService(dataServiceType, true);
            } catch (Exception e) {
                LogManager.getLogger().error("Could not unregister and shutdown servicetype: {}", dataServiceType, e);
            }
        });
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
    
    @Override
    protected void onServiceAddedBegin(DataServiceType serviceType, Service service) throws StartupException, ServiceAlreadyRegisteredException {
        //to be implemented if needed
    }
    
    @Override
    protected void onServiceAddedEnd(DataServiceType serviceType, Service service) throws StartupException, ServiceAlreadyRegisteredException {
        //to be implemented if needed
    }
    
    @Override
    protected void onServiceRemovedBegin(DataServiceType serviceType, boolean kill) throws ShutDownException, ServiceNotFoundException {
        //to be implemented if needed
    }
    
    @Override
    protected void onServiceRemovedEnd(DataServiceType serviceType, boolean kill) throws ShutDownException, ServiceNotFoundException {
        //to be implemented if needed
    }
    
    @Override
    protected void onServiceRegistryEmptyingBegin(boolean kill) throws ShutDownException, ServiceNotFoundException {
        //to be implemented if needed
    }
    
    @Override
    protected void onServiceRegistryEmptyingEnd(boolean kill) throws ShutDownException, ServiceNotFoundException {
        //to be implemented if needed
    }
    
    public enum DataServiceType {
        TEST
    }
}
