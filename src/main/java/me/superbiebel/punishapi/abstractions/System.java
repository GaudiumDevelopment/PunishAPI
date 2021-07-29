package me.superbiebel.punishapi.abstractions;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import lombok.Getter;
import me.superbiebel.punishapi.SystemStatus;
import me.superbiebel.punishapi.exceptions.ShutDownException;
import me.superbiebel.punishapi.exceptions.StartupException;

public abstract class System {
    
    private final Lock startupShutdownLock = new ReentrantLock(true);
    
    @Getter
    private final AtomicReference<SystemStatus> atomicstatus = new AtomicReference<>();
    
    //locked so threadsafe
    public void startup(boolean force) throws StartupException{
        try {
            startupShutdownLock.lock();
            if (atomicstatus.get() == SystemStatus.STARTING_UP) {
                throw new IllegalStateException("Cannot start up while already starting up!");
            }
            if ((atomicstatus.get() == SystemStatus.READY || atomicstatus.get() == SystemStatus.FORCED_READY)&&!force) {
                    throw new IllegalStateException("System already started up!");
            }
           atomicstatus.set(SystemStatus.STARTING_UP);
            onStartup(force);
            if (force) {
               atomicstatus.set(SystemStatus.FORCED_READY);
            } else {
               atomicstatus.set(SystemStatus.READY);
            }
        } catch (StartupException e) {
            throw e;
        } catch(Exception e) {
            throw new StartupException(e);
        } finally {
            startupShutdownLock.unlock();
        }
    }
    //locked so threadsafe
    public void startup() throws StartupException{
        startup(false);
    }
    //locked so threadsafe
    public void shutdown() throws ShutDownException {
        try {
            startupShutdownLock.lock();
            if (atomicstatus.get() == SystemStatus.DOWN || atomicstatus.get() == SystemStatus.KILLED) {
                throw new IllegalStateException("System already shut down!");
            }
            onShutdown();
           atomicstatus.set(SystemStatus.DOWN);
        } catch (ShutDownException e) {
            throw e;
        } catch(Exception e) {
            throw new ShutDownException(e);
        } finally {
            startupShutdownLock.unlock();
        }
    }
    //locked so threadsafe
    public void kill() throws ShutDownException {
        try {
            startupShutdownLock.lock();
            if (atomicstatus.get() == SystemStatus.KILLED) {
                throw new IllegalStateException("System already killed!");
            } else if (atomicstatus.get() == SystemStatus.DOWN) {
                throw new IllegalStateException("System already shut down!");
            } else {
                onKill();
               atomicstatus.set(SystemStatus.KILLED);
            }
        } catch (Exception e) {
            throw new ShutDownException(e);
        } finally {
            startupShutdownLock.unlock();
        }
    }
    
    public SystemStatus status() {
        return atomicstatus.get();
    }
    public boolean canInteract() {
        return atomicstatus.get().equals(SystemStatus.READY) || atomicstatus.get().equals(SystemStatus.FORCED_READY);
    }
    
    
    protected abstract void onStartup(boolean force) throws StartupException;
    protected abstract void onShutdown() throws ShutDownException;
    protected abstract void onKill() throws ShutDownException;
}
