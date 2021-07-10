package me.superbiebel.punishapi.offenseprocessing.services;

import me.superbiebel.punishapi.dataobjects.OffenseHistoryRecord;
import me.superbiebel.punishapi.dataobjects.OffenseProcessingRequest;
import me.superbiebel.punishapi.exceptions.FailedServiceOperationException;
import me.superbiebel.punishapi.services.Service;

public interface IOffenseProcessor extends Service<String> {
    /**
     * @param offenseProcessingRequest the request that will be processed.
     * @return the record that will be stored in the database.
     * @throws FailedServiceOperationException if some kind of exception happened it should be wrapped in a FailedServiceOperationException
     */
    OffenseHistoryRecord processOffense(OffenseProcessingRequest offenseProcessingRequest) throws FailedServiceOperationException;
}
