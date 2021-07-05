package me.superbiebel.punishapi.abstractions;

import me.superbiebel.punishapi.exceptions.ShutDownException;
import me.superbiebel.punishapi.exceptions.StartupException;
import me.superbiebel.punishapi.services.Service;

public abstract class ServiceSystem<T> extends System implements Service<T>  {
    
    @Override
    protected void onStartup(boolean force) throws StartupException {
       serviceStartup(force);
    }
    
    @Override
    protected void onShutdown() throws ShutDownException {
        serviceShutdown();
    }
    
    @Override
    protected void onKill() throws ShutDownException {
        serviceKill();
    }
}
