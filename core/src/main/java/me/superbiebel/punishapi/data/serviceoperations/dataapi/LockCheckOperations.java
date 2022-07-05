package me.superbiebel.punishapi.data.serviceoperations.dataapi;

import me.superbiebel.punishapi.exceptions.FailedDataOperationException;

import java.util.UUID;

public interface LockCheckOperations {
    //Checks if the user is locked
    boolean isUserLocked(UUID userUUID) throws FailedDataOperationException;
}
