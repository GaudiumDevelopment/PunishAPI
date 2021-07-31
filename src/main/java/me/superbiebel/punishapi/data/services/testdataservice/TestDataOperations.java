package me.superbiebel.punishapi.data.services.testdataservice;

import me.superbiebel.punishapi.exceptions.FailedServiceOperationException;

public interface TestDataOperations {
    String exampleOperation(String returnString) throws FailedServiceOperationException;
}
