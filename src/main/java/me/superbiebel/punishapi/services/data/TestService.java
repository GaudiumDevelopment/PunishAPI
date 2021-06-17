package me.superbiebel.punishapi.services.data;

import me.superbiebel.punishapi.exceptions.FailedServiceOperationException;
import me.superbiebel.punishapi.services.Service;

public interface TestService extends Service {
    
    String exampleOperation(String returnString) throws FailedServiceOperationException;
}
