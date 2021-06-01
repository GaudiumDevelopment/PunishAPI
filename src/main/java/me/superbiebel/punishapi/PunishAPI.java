package me.superbiebel.punishapi;


import java.lang.annotation.Target;

//multiple instances of this class can be created!
public class PunishAPI {
    private boolean ready = false;
    private boolean killed = false;
    
    public void startup() {
        ready = true;
    }
    public void shutdown() {
        ready = false;
    }
    public void kill() {
        killed = true;
    }
}
