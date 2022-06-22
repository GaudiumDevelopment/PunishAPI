package me.superbiebel.punishapi.offenseprocessing;

import java.util.List;

import me.superbiebel.punishapi.dataobjects.requestoffenseprocessing.PunishmentCalculationRequest;
import me.superbiebel.punishapi.dataobjects.verdict.PunishmentCalculation;
import me.superbiebel.punishapi.exceptions.OffenseProcessingException;

public interface IOffenseCalculator {
    /**
     * @param punishmentCalculationRequest the request that will be processed.
     * @return the record that will be stored in the database.
     */
    List<PunishmentCalculation> processOffense(PunishmentCalculationRequest punishmentCalculationRequest) throws OffenseProcessingException;

    boolean isScriptBased();
}
