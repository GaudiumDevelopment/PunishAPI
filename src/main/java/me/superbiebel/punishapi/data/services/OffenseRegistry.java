package me.superbiebel.punishapi.data.services;

import me.superbiebel.punishapi.offenseprocessing.dataobjects.OffenseRecord;
import me.superbiebel.punishapi.services.Service;

public interface OffenseRegistry extends Service {
    void storeOffense(OffenseRecord offenseRecord);
}
