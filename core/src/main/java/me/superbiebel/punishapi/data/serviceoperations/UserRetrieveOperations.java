package me.superbiebel.punishapi.data.serviceoperations;

import me.superbiebel.punishapi.common.dataobjects.UserAccount;
import me.superbiebel.punishapi.exceptions.FailedDataOperationException;

import java.util.UUID;

public interface UserRetrieveOperations {
    UserAccount retrieveUser(UUID userUUID) throws FailedDataOperationException;
}
