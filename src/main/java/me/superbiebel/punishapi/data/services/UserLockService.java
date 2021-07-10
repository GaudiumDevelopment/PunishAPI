package me.superbiebel.punishapi.data.services;

import me.superbiebel.punishapi.data.DataService;
import me.superbiebel.punishapi.data.Datamanager;

import java.util.UUID;

public interface UserLockService extends DataService {
    
    //Disables editing of that person (history, punishment applying)
    //This means that there is currently an offense that is being processed
    //this doesnt mean there should be an actual lock or something like that, this method is just an indication.
    void lockUser(UUID uuid);
    //The processing is done
    void unlockUser(UUID uuid);
    default Datamanager.DataServiceType serviceType(){
        return Datamanager.DataServiceType.USER_LOCKING;
    }
}
