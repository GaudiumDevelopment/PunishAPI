package me.superbiebel.punishapi.offenseprocessing.services;

import me.superbiebel.punishapi.exceptions.FailedServiceOperationException;
import me.superbiebel.punishapi.offenseprocessing.dataobjects.OffenseProcessingRequest;
import me.superbiebel.punishapi.offenseprocessing.dataobjects.OffenseHistoryRecord;
import me.superbiebel.punishapi.services.Service;

public interface OffenseProcessor extends Service {
    /**
     * @param offenseProcessingRequest the request that will be processed.
     * @return the record that will be stored in the database.
     * @throws FailedServiceOperationException if some kind of exception happened it should be wrapped in a FailedServiceOperationException
     */
    OffenseHistoryRecord process(OffenseProcessingRequest offenseProcessingRequest) throws FailedServiceOperationException;
    
    /**
     * @param offenseProcessingRequest the request that will be processed.
     * @return  the record that will be stored in the database.
     * @throws FailedServiceOperationException If some kind of exception happened it should be wrapped in a FailedServiceOperation
     * @throws UnsupportedOperationException If batch processing is not supported but this method was called anyway.
     */
    OffenseHistoryRecord[] process(OffenseProcessingRequest[] offenseProcessingRequest) throws FailedServiceOperationException, UnsupportedOperationException;
    
    /**
     * @return true if this processor can handle batch processing.
     */
    boolean canHandleBatch();
}
