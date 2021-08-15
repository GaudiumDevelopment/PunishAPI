package me.superbiebel.punishapi.data.servicesoperations;

import java.util.UUID;
import me.superbiebel.punishapi.exceptions.FailedDataOperationException;

public interface UserLockOperations {
    //Disables editing of that person (history, punishment applying)
    //This means that there is currently an offense that is being processed
    //this doesnt mean there should be an actual lock or something like that, this method is just an indication.
    void lockUser(UUID uuid) throws FailedDataOperationException;

    //The processing is done
    void unlockUser(UUID uuid) throws FailedDataOperationException;
}
