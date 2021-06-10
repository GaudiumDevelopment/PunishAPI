package me.superbiebel.punishapi.data.services;

import me.superbiebel.punishapi.System;

public interface Service {
    void startup(boolean force) throws Exception;
    void shutdown() throws Exception;
    void kill() throws Exception;
}
