package me.superbiebel.punishapi.abstractions;

import lombok.Getter;
import me.superbiebel.punishapi.SystemStatus;
import me.superbiebel.punishapi.exceptions.ShutDownException;
import me.superbiebel.punishapi.exceptions.StartupException;
import org.apache.logging.log4j.LogManager;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public abstract class System {
    
    private final Lock startupShutdownLock = new ReentrantLock(true);
    
    @Getter
    private SystemStatus status = SystemStatus.DOWN;
    
    //locked so threadsafe
    public void startup(boolean force) throws StartupException{
        try {
            LogManager.getLogger().debug("starting up system");
            startupShutdownLock.lock();
            if (status == SystemStatus.STARTING_UP) {
                throw new IllegalStateException("Cannot start up while already starting up!");
            }
            if ((status == SystemStatus.READY || status == SystemStatus.FORCED_READY)&&!force) {
                LogManager.getLogger().debug("System already ready, aborting...");
                    throw new IllegalStateException("System already started up!");
            }
            status = SystemStatus.STARTING_UP;
            onStartup(force);
            if (force) {
                status = SystemStatus.FORCED_READY;
            } else {
                status = SystemStatus.READY;
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
            if (status == SystemStatus.DOWN || status == SystemStatus.KILLED) {
                throw new IllegalStateException("System already shut down!");
            }
            onShutdown();
            status = SystemStatus.DOWN;
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
            if (status == SystemStatus.KILLED) {
                throw new IllegalStateException("System already killed!");
            } else if (status == SystemStatus.DOWN) {
                throw new IllegalStateException("System already shut down!");
            } else {
                onKill();
                status = SystemStatus.KILLED;
            }
        } catch (Exception e) {
            throw new ShutDownException(e);
        } finally {
            startupShutdownLock.unlock();
        }
    }
    
    public SystemStatus status() {
        return status;
    }
    public boolean canInteract() {
        return status.equals(SystemStatus.READY) || status.equals(SystemStatus.FORCED_READY);
    }
    
    
    protected abstract void onStartup(boolean force) throws StartupException;
    protected abstract void onShutdown() throws ShutDownException;
    protected abstract void onKill() throws ShutDownException;
}
