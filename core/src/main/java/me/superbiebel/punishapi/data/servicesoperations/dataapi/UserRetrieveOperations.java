package me.superbiebel.punishapi.data.servicesoperations.dataapi;

import me.superbiebel.punishapi.dataobjects.UserAccount;
import me.superbiebel.punishapi.exceptions.FailedDataOperationException;

import java.util.UUID;

public interface UserRetrieveOperations {
    UserAccount retrieveUser(UUID userUUID) throws FailedDataOperationException;
}
