package me.superbiebel.punishapi.data.services;

import me.superbiebel.punishapi.exceptions.FailedServiceOperationException;

public interface TestService extends Service{
    
    String exampleOperation(String returnString) throws FailedServiceOperationException;
}
