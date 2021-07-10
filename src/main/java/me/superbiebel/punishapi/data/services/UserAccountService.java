package me.superbiebel.punishapi.data.services;

import me.superbiebel.punishapi.data.DataService;

import java.util.Map;
import java.util.UUID;

public interface UserAccountService extends DataService {
    void createUser(UUID userUUID, Map<String, String> attributes);
    
}
