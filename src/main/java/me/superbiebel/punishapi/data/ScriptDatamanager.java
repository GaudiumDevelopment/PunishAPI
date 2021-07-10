package me.superbiebel.punishapi.data;

import me.superbiebel.punishapi.dataobjects.OffenseHistoryRecord;
import me.superbiebel.punishapi.dataobjects.OffenseProcessingTemplate;
import me.superbiebel.punishapi.exceptions.ServiceNotFoundException;

import java.util.UUID;

//it will request and then convert the objects to an object graalvm can actually understand
public class ScriptDatamanager {
    private final Datamanager datamanager;
    
    public ScriptDatamanager(Datamanager datamanager) {
        this.datamanager = datamanager;
    }
    
    public OffenseHistoryRecord retrieveOffense(String offenseUUID) throws ServiceNotFoundException {
        return datamanager.retrieveOffense(UUID.fromString(offenseUUID));
    }
    
    public OffenseProcessingTemplate retrieveOffenseProcessingTemplate(String templateUUID) throws ServiceNotFoundException {
        return datamanager.retrieveOffenseProcessingTemplate(UUID.fromString(templateUUID));
    }
}
