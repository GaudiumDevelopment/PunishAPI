package me.superbiebel.punishapi.data.services;

import me.superbiebel.punishapi.offenseprocessing.dataobjects.OffenseHistoryRecord;
import me.superbiebel.punishapi.services.Service;

public interface OffenseRegistry extends Service {
    void storeOffense(OffenseHistoryRecord offenseHistoryRecord);
}
