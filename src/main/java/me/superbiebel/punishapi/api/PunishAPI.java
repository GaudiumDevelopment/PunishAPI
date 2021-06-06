package me.superbiebel.punishapi.api;


import lombok.Getter;
import me.superbiebel.punishapi.System;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//multiple instances of this class can be created!
public class PunishAPI extends System {
    
    @Getter
    private DataAPI dataAPI;
    
    public void onStartup(boolean forcedInit) {
    }
    public void onShutdown() {
    }
    public void onKill() {
    }
}
