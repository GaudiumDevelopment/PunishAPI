package me.superbiebel.punishapi.data.services;

import me.superbiebel.punishapi.services.Service;

import java.util.UUID;

public interface UserLockService extends Service {
    
    //Disables editing of that person (history, punishment applying)
    //This means that there is currently an offense that is being processed
    void lockUser(UUID uuid);
    //The processing is done
    void unlockUser(UUID uuid);
}
