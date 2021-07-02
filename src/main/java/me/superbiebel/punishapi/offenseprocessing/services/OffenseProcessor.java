package me.superbiebel.punishapi.offenseprocessing.services;

import me.superbiebel.punishapi.exceptions.FailedServiceOperationException;
import me.superbiebel.punishapi.offenseprocessing.dataobjects.OffenseProcessingRequest;
import me.superbiebel.punishapi.offenseprocessing.dataobjects.OffenseRecord;
import me.superbiebel.punishapi.services.Service;

public interface OffenseProcessor extends Service {
    OffenseRecord process(OffenseProcessingRequest offenseProcessingRequest) throws FailedServiceOperationException;
    OffenseRecord[] process(OffenseProcessingRequest[] offenseProcessingRequest) throws FailedServiceOperationException;
    //returns true if this OffenseProcessor can handle a batch of criminal actions by the same person.
    boolean canHandleBatch();
}
