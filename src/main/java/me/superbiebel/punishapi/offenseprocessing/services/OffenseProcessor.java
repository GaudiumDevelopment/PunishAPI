package me.superbiebel.punishapi.offenseprocessing.services;

import me.superbiebel.punishapi.exceptions.FailedServiceOperationException;
import me.superbiebel.punishapi.offenseprocessing.dataobjects.OffenseProcessingRequest;
import me.superbiebel.punishapi.offenseprocessing.dataobjects.Punishment;
import me.superbiebel.punishapi.services.Service;

import java.util.List;

public interface OffenseProcessor extends Service {
    List<Punishment> process(OffenseProcessingRequest offenseProcessingRequest) throws FailedServiceOperationException;
    //returns true if this OffenseProcessor can handle a batch of criminal actions by the same person.
    boolean canHandleBatch();
}
