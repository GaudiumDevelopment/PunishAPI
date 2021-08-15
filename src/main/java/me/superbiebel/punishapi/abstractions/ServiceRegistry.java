package me.superbiebel.punishapi.abstractions;

import java.util.concurrent.ConcurrentMap;
import me.superbiebel.punishapi.exceptions.ServiceAlreadyRegisteredException;
import me.superbiebel.punishapi.exceptions.ServiceNotFoundException;
import me.superbiebel.punishapi.exceptions.ShutDownException;
import me.superbiebel.punishapi.exceptions.StartupException;

public abstract class ServiceRegistry<T> extends System {

    protected final ConcurrentMap<T, Service> serviceRegistryMap;

    protected ServiceRegistry(ConcurrentMap<T, Service> serviceRegistryMap) {
        this.serviceRegistryMap = serviceRegistryMap;
    }

    @Override
    protected void onStartup(boolean force) {
        onServiceRegistryStartup(force);
    }

    @Override
    protected void onShutdown() {
        onServiceRegistryShutdown();
    }

    @Override
    protected void onKill() {
        onServiceRegistryKill();
    }

    protected abstract void onServiceRegistryStartup(boolean force);

    protected abstract void onServiceRegistryShutdown();

    protected abstract void onServiceRegistryKill();

    public void addService(T serviceType, Service service) throws StartupException, ServiceAlreadyRegisteredException {
        if (serviceRegistryMap.containsKey(serviceType)) {
            throw new ServiceAlreadyRegisteredException("Service was already registered");
        }
        serviceRegistryMap.put(serviceType, service);
        service.startup(false);
    }

    public Service getService(T serviceType) throws ServiceNotFoundException {
        Service returnedService = serviceRegistryMap.get(serviceType);
        if (returnedService == null) {
            throw new ServiceNotFoundException();
        }
        return returnedService;
    }

    public Service removeService(T serviceType, boolean kill) throws ShutDownException, ServiceNotFoundException {
        if (!serviceRegistryMap.containsKey(serviceType)) {
            throw new ServiceNotFoundException("Servicetype not found");
        }
        Service service = serviceRegistryMap.remove(serviceType);
        if (kill) {
            service.kill();
        } else {
            service.shutdown();
        }
        return service;
    }

    public void emptyServiceRegistry(boolean kill) throws ShutDownException, ServiceNotFoundException {
        for (T serviceType : serviceRegistryMap.keySet()) {
            this.removeService(serviceType, kill);
        }
    }
}
