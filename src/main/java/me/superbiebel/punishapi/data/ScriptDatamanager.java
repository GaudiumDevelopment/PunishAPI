package me.superbiebel.punishapi.data;

import java.util.UUID;
import me.superbiebel.punishapi.dataobjects.OffenseHistoryRecord;
import me.superbiebel.punishapi.dataobjects.OffenseProcessingTemplate;
import me.superbiebel.punishapi.exceptions.FailedDataOperationException;

//it will request and then convert the objects to an object graalvm can actually understand
public class ScriptDatamanager {
    private final Datamanager datamanager;

    public ScriptDatamanager(Datamanager datamanager) {
        this.datamanager = datamanager;
    }

    public OffenseHistoryRecord retrieveOffense(String offenseUUID) throws FailedDataOperationException {
        return datamanager.retrieveOffenseRecord(UUID.fromString(offenseUUID));
    }

    public OffenseProcessingTemplate retrieveOffenseProcessingTemplate(String templateUUID) throws FailedDataOperationException {
        return datamanager.retrieveOffenseProcessingTemplate(UUID.fromString(templateUUID));
    }
}
