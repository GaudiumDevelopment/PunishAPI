package me.superbiebel.punishapi.data.services;

import me.superbiebel.punishapi.data.Datamanager;
import me.superbiebel.punishapi.offenseprocessing.dataobjects.OffenseHistoryRecord;
import me.superbiebel.punishapi.services.Service;

import java.util.UUID;

public interface OffenseRegistry extends Service<Datamanager.DataServiceType> {
    void storeOffense(OffenseHistoryRecord offenseHistoryRecord);
    void retrieveOffense(UUID offenseUUID);
}
