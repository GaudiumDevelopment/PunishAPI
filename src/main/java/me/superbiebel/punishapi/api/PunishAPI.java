package me.superbiebel.punishapi.api;


import lombok.Getter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//multiple instances of this class can be created!
public class PunishAPI {
    
    private final Lock startupShutdownLock = new ReentrantLock(true);
    
    @Getter
    private boolean ready = false;
    @Getter
    private boolean killed = false;
    @Getter
    private boolean forcedStartup = false;
    
    public void startup() {
        startup(false);
    }
    
    public void startup(boolean forcedInit) {
        try {
            startupShutdownLock.lock();
            if (ready || forcedInit) {
                if (forcedInit) {
                    forcedStartup = true;
                } else {
                    throw new IllegalStateException("Api already started up!");
                }
            }
            if (forcedInit) {
                forcedStartup = true;
            }
            ready = true;
        } finally {
            startupShutdownLock.unlock();
        }
        
    }
    public void shutdown() {
        try {
            startupShutdownLock.lock();
            if (!ready) {
                throw new IllegalStateException("Api already shut down!");
            }
            ready = false;
        } finally {
            startupShutdownLock.unlock();
        }
        
    }
    public void kill() {
        try {
            startupShutdownLock.lock();
            if (killed) {
                throw new IllegalStateException("Api already killed!");
            } else if (!ready) {
                throw new IllegalStateException("Api already shut down!");
            } else {
                killed = true;
                ready = false;
            }
            
        } finally {
            startupShutdownLock.unlock();
        }
    }
}
