package me.superbiebel.punishapi.data.services.useraccountservice;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import me.superbiebel.punishapi.dataobjects.UserAccount;
import me.superbiebel.punishapi.exceptions.FailedDataOperationException;

public interface UserAccountOperations {
    UserAccount createUser(UUID userUUID, Map<String, String> attributes) throws FailedDataOperationException;
    UserAccount retrieveUser(UUID userUUID) throws FailedDataOperationException;
    void setUserAttribute(String key, String value) throws FailedDataOperationException;
    void removeUserAttribute(String key) throws FailedDataOperationException;
    List<UserAccount> getUsersByAttribute(String key, String value) throws FailedDataOperationException;
    List<UserAccount> getUsersByAttributekey(String key) throws FailedDataOperationException;
    List<UserAccount> getUsersByAttributeValue(String value) throws FailedDataOperationException;
}
