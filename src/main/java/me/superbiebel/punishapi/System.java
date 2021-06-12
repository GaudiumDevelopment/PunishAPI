package me.superbiebel.punishapi;

import lombok.Getter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public abstract class System {
    
    private final Lock startupShutdownLock = new ReentrantLock(true);
    
    @Getter
    private SystemStatus status = SystemStatus.DOWN;
    
    //locked so threadsafe
    public void startup(boolean force) {
        try {
            startupShutdownLock.lock();
            if (status == SystemStatus.STARTING_UP) {
                throw new IllegalStateException("Cannot start up while already starting up!");
            }
            if ((status == SystemStatus.READY || status == SystemStatus.FORCED_READY)&&!force) {
                    throw new IllegalStateException("System already started up!");
            }
            status = SystemStatus.STARTING_UP;
            onStartup(force);
            if (force) {
                status = SystemStatus.FORCED_READY;
            } else {
                status = SystemStatus.READY;
            }
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
            if (status==SystemStatus.DOWN || status==SystemStatus.KILLED) {
                throw new IllegalStateException("System already shut down!");
            }
            onShutdown();
            status = SystemStatus.DOWN;
        } finally {
            startupShutdownLock.unlock();
        }
    }
    //locked so threadsafe
    public void kill() {
        try {
            startupShutdownLock.lock();
            if (status==SystemStatus.KILLED) {
                throw new IllegalStateException("System already killed!");
            } else if (status==SystemStatus.DOWN) {
                throw new IllegalStateException("System already shut down!");
            } else {
                onKill();
                status = SystemStatus.KILLED;
            }
        
        } finally {
            startupShutdownLock.unlock();
        }
    }
    
    public SystemStatus status() {
        return status;
    }
    
    
    protected abstract void onStartup(boolean force);
    protected abstract void onShutdown();
    protected abstract void onKill();
}
