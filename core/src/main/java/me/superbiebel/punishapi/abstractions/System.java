package me.superbiebel.punishapi.abstractions;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import lombok.Getter;
import me.superbiebel.punishapi.SystemStatus;
import me.superbiebel.punishapi.exceptions.ShutDownException;
import me.superbiebel.punishapi.exceptions.StartupException;
import org.jetbrains.annotations.NotNull;

public abstract class System implements Service{

    private final Lock startupShutdownLock = new ReentrantLock(true);

    @Getter
    private final AtomicReference<SystemStatus> atomicStatus = new AtomicReference<>();

    public void startup(boolean force) throws StartupException {
        try {
            startupShutdownLock.lock();
            if (atomicStatus.get() == SystemStatus.STARTING_UP) {
                throw new IllegalStateException("Cannot start up while already starting up!");
            }
            if ((atomicStatus.get() == SystemStatus.READY || atomicStatus.get() == SystemStatus.FORCED_READY) && !force) {
                throw new IllegalStateException("System already started up!");
            }
            atomicStatus.set(SystemStatus.STARTING_UP);
            onStartup(force);
            if (force) {
                atomicStatus.set(SystemStatus.FORCED_READY);
            } else {
                atomicStatus.set(SystemStatus.READY);
            }
        } catch (StartupException e) {
            throw e;
        } catch (Exception e) {
            throw new StartupException(e);
        } finally {
            startupShutdownLock.unlock();
        }
    }

    public void startup() throws StartupException {
        startup(false);
    }

    public void shutdown() throws ShutDownException {
        try {
            startupShutdownLock.lock();
            if (atomicStatus.get() == SystemStatus.DOWN || atomicStatus.get() == SystemStatus.KILLED) {
                throw new IllegalStateException("System already shut down!");
            }
            onShutdown();
            atomicStatus.set(SystemStatus.DOWN);
        } catch (ShutDownException e) {
            throw e;
        } catch (Exception e) {
            throw new ShutDownException(e);
        } finally {
            startupShutdownLock.unlock();
        }
    }

    public void kill() {
        try {
            startupShutdownLock.lock();
            if (atomicStatus.get() == SystemStatus.KILLED) {
                throw new IllegalStateException("System already killed!");
            } else if (atomicStatus.get() == SystemStatus.DOWN) {
                throw new IllegalStateException("System already shut down!");
            } else {
                onKill();
                atomicStatus.set(SystemStatus.KILLED);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            startupShutdownLock.unlock();
        }
    }

    public @NotNull SystemStatus status() {
        return atomicStatus.get();
    }

    public void canInteract() {
        if (!(atomicStatus.get().equals(SystemStatus.READY) || atomicStatus.get().equals(SystemStatus.FORCED_READY))) {
            throw new IllegalStateException("Cannot interact with system because it is not ready");
        }
    }


    protected abstract void onStartup(boolean force) throws StartupException;

    protected abstract void onShutdown() throws ShutDownException;

    protected abstract void onKill() throws ShutDownException;
}
