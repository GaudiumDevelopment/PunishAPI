package me.superbiebel.punishapi.data.services;

import me.superbiebel.punishapi.data.DataService;
import me.superbiebel.punishapi.data.Datamanager;
import me.superbiebel.punishapi.dataobjects.OffenseHistoryRecord;

import java.util.UUID;

public interface OffenseRecordStorage extends DataService {
    void storeOffenseRecord(OffenseHistoryRecord offenseHistoryRecord);
    OffenseHistoryRecord retrieveOffenseRecord(UUID offenseUUID);
    default Datamanager.DataServiceType serviceType(){
        return Datamanager.DataServiceType.OFFENSE_RECORD_STORAGE;
    }
}