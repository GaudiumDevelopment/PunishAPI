package me.superbiebel.punishapi.tests.testobjects;

import me.superbiebel.punishapi.data.Datamanager.DataServiceType;
import me.superbiebel.punishapi.data.services.testdataservice.TestDataOperations;

public class TestDataServiceImpl extends BaseDataTestingService implements TestDataOperations {

    @Override
    public String exampleOperation(String returnString) {
        return returnString;
    }

    @Override
    public DataServiceType[] supportsDataOperations() {
        DataServiceType[] dataServiceTypes = new DataServiceType[1];
        dataServiceTypes[0] = DataServiceType.TEST;
        return dataServiceTypes;
    }
}
