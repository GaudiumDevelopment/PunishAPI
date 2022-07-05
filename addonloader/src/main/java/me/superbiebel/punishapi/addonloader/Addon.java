package me.superbiebel.punishapi.addonloader;

public abstract class Addon {
    abstract void onStartup();
    abstract void onShutdown();
    abstract void onKill();
}
