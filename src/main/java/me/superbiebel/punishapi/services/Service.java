package me.superbiebel.punishapi.services;

import me.superbiebel.punishapi.SystemStatus;
import me.superbiebel.punishapi.exceptions.ShutDownException;
import me.superbiebel.punishapi.exceptions.StartupException;
import org.jetbrains.annotations.NotNull;

public interface Service<T> {
    void serviceStartup(boolean force) throws StartupException;
    void serviceShutdown() throws ShutDownException;
    //Will shut down without throwing any exception, force closing and killing anything that is in its way.
    void serviceKill();
    @NotNull
    SystemStatus serviceStatus();
    T serviceType();
    
}
