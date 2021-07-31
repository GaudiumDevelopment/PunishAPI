package me.superbiebel.punishapi.data.services.offenserecordstorage;

import me.superbiebel.punishapi.data.DataService;
import me.superbiebel.punishapi.data.Datamanager;

public interface OffenseRecordStorage extends DataService, OffenseRecordStorageOperations {
    default Datamanager.DataServiceType serviceType(){
        return Datamanager.DataServiceType.OFFENSE_RECORD_STORAGE;
    }
}