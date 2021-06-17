package me.superbiebel.punishapi.offenseprocessing.services;

import me.superbiebel.punishapi.exceptions.FailedServiceOperationException;
import me.superbiebel.punishapi.offenseprocessing.dataobjects.Offense;
import me.superbiebel.punishapi.offenseprocessing.dataobjects.Punishment;
import me.superbiebel.punishapi.services.Service;

import java.util.List;

public interface OffenseProcessor extends Service {
    
    List<Punishment> process(Offense offense) throws FailedServiceOperationException;
}
