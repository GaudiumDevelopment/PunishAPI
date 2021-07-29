package me.superbiebel.punishapi.data.services;

import me.superbiebel.punishapi.data.DataService;
import me.superbiebel.punishapi.data.Datamanager;
import me.superbiebel.punishapi.exceptions.FailedServiceOperationException;

public interface TestDataService extends DataService {
    
    String exampleOperation(String returnString) throws FailedServiceOperationException;
    default Datamanager.DataServiceType serviceType(){
        return Datamanager.DataServiceType.TEST;
    }
}
