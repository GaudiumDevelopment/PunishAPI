package me.superbiebel.punishapi.data.servicesoperations;

import java.util.UUID;
import me.superbiebel.punishapi.exceptions.FailedDataOperationException;

public interface UserLockOperations {
    //false means that there is currently an offense that is being processed on that user (false = locked)
    //this doesn't mean there should be an actual lock or something like that, this method is just an indication.
    //This should NEVER block like a real lock
    boolean tryLockUser(UUID uuid) throws FailedDataOperationException;

    //The processing is done
    void unlockUser(UUID uuid) throws FailedDataOperationException;

    //Checks if the user is locked
    boolean isUserLocked(UUID userUUID) throws FailedDataOperationException;
}
