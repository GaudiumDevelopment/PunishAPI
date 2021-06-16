package me.superbiebel.punishapi.api;

import me.superbiebel.punishapi.PunishCore;

public class PunishAPI {
    
    private PunishCore core;
    
    public void start() {
        core = new PunishCore();
        core.startup();
    }
    public void shutdown() {
        core.shutdown();
    }
}
