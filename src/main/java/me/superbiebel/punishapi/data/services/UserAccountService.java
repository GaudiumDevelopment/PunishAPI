package me.superbiebel.punishapi.data.services;

import me.superbiebel.punishapi.data.DataService;
import me.superbiebel.punishapi.dataobjects.UserAccount;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface UserAccountService extends DataService {
    UserAccount createUser(UUID userUUID, Map<String, String> attributes);
    UserAccount retrieveUser(UUID userUUID);
    void setAttribute(String key, String value);
    void removeAttribute(String key);
    List<UserAccount> getByAttribute(String key, String value);
    List<UserAccount> getBykey(String key);
    List<UserAccount> getByValue(String value);
}
