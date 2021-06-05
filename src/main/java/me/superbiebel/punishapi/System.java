package me.superbiebel.punishapi;

public abstract class System {
    
    public void startup(boolean force) {
    
    }
    public void startup() {
        startup(false);
    }
    
    
    protected abstract void onStartup(boolean force);
    protected abstract void onStartup();
    protected abstract void onShutdown();
    protected abstract void onKill();
}
