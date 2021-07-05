package me.superbiebel.punishapi.offenseprocessing.services;

import me.superbiebel.punishapi.abstractions.ServiceSystem;
import me.superbiebel.punishapi.exceptions.FailedServiceOperationException;
import me.superbiebel.punishapi.offenseprocessing.dataobjects.OffenseHistoryRecord;
import me.superbiebel.punishapi.offenseprocessing.dataobjects.OffenseProcessingRequest;

public abstract class AbstractOffenseProcessor extends ServiceSystem<String> implements IOffenseProcessor {
    
    public final OffenseHistoryRecord processOffense(OffenseProcessingRequest offenseProcessingRequest) throws FailedServiceOperationException {
        try {
            return process(offenseProcessingRequest);
        } catch (Exception e) {
            throw new FailedServiceOperationException(e);
        }
    }
    protected abstract OffenseHistoryRecord process(OffenseProcessingRequest offenseProcessingRequest);
}
