package me.superbiebel.punishapi.data.serviceoperations;

import me.superbiebel.punishapi.common.dataobjects.UserAccount;
import me.superbiebel.punishapi.exceptions.FailedDataOperationException;

import java.util.List;
import java.util.UUID;

public interface UserAccountAttributeOperations {
    boolean setUserAttribute(UUID userUUID, String key, String value) throws FailedDataOperationException;
    
    boolean removeUserAttribute(UUID userUUID, String key) throws FailedDataOperationException;
    
    List<UserAccount> getUsersByAttribute(String key, String value) throws FailedDataOperationException;
    
    List<UserAccount> getUsersByAttributeKey(String key) throws FailedDataOperationException;
    
    List<UserAccount> getUsersByAttributeValue(String value) throws FailedDataOperationException;
}
