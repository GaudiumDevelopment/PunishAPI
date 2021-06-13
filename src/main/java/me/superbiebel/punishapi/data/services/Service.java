package me.superbiebel.punishapi.data.services;

import me.superbiebel.punishapi.SystemStatus;
import me.superbiebel.punishapi.exceptions.ShutDownException;
import me.superbiebel.punishapi.exceptions.StartupException;

public interface Service {
    void startup(boolean force) throws StartupException;
    void shutdown() throws ShutDownException;
    void kill();
    SystemStatus status();
    
}
