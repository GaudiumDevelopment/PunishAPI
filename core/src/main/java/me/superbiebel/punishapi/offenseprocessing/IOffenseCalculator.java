package me.superbiebel.punishapi.offenseprocessing;

import java.util.List;

import me.superbiebel.punishapi.addonloader.Addon;
import me.superbiebel.punishapi.common.dataobjects.requestoffenseprocessing.PunishmentCalculationRequest;
import me.superbiebel.punishapi.common.dataobjects.verdict.PunishmentCalculation;
import me.superbiebel.punishapi.exceptions.OffenseProcessingException;

public abstract class IOffenseCalculator extends Addon {
    /**
     * @param punishmentCalculationRequest the request that will be processed.
     * @return the record that will be stored in the database.
     */
    abstract List<PunishmentCalculation> processOffense(PunishmentCalculationRequest punishmentCalculationRequest) throws OffenseProcessingException;
}
