package me.superbiebel.punishapi.data.services;

import me.superbiebel.punishapi.SystemStatus;

public interface Service {
    void startup(boolean force) throws Exception;
    void shutdown() throws Exception;
    void kill() throws Exception;
    SystemStatus status();
    
}
