package me.superbiebel.punishapi.data.services.testdataservice;

import me.superbiebel.punishapi.data.DataService;
import me.superbiebel.punishapi.data.Datamanager;

public interface TestDataService extends DataService, TestDataOperations {
    default Datamanager.DataServiceType serviceType(){
        return Datamanager.DataServiceType.TEST;
    }
}
