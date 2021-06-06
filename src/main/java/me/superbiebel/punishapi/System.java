package me.superbiebel.punishapi;

import lombok.Getter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public abstract class System {
    
    private final Lock startupShutdownLock = new ReentrantLock(true);
    
    @Getter
    private boolean ready = false;
    @Getter
    private boolean killed = false;
    @Getter
    private boolean forcedStartup = false;
    //locked so threadsafe
    public void startup(boolean force) {
        try {
            startupShutdownLock.lock();
            if (ready || force) {
                if (force) {
                    forcedStartup = true;
                } else {
                    throw new IllegalStateException("Api already started up!");
                }
            }
            if (force) {
                forcedStartup = true;
            }
            onStartup(force);
            ready = true;
        } finally {
            startupShutdownLock.unlock();
        }
    }
    //locked so threadsafe
    public void startup() {
        startup(false);
    }
    //locked so threadsafe
    public void shutdown() {
        try {
            startupShutdownLock.lock();
            if (!ready) {
                throw new IllegalStateException("Api already shut down!");
            }
            onShutdown();
            ready = false;
        } finally {
            startupShutdownLock.unlock();
        }
    }
    //locked so threadsafe
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
                onShutdown();
            }
        
        } finally {
            startupShutdownLock.unlock();
        }
    }
    
    
    protected abstract void onStartup(boolean force);
    protected abstract void onShutdown();
    protected abstract void onKill();
}
