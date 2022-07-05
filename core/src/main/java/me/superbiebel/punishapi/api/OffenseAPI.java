package me.superbiebel.punishapi.api;

import me.superbiebel.punishapi.PunishCore;
import me.superbiebel.punishapi.common.dataobjects.verdict.OffenseHistoryRecord;
import me.superbiebel.punishapi.common.dataobjects.requestoffenseprocessing.PunishmentCalculationRequest;
import me.superbiebel.punishapi.exceptions.FailedDataOperationException;
import me.superbiebel.punishapi.exceptions.OffenseProcessingException;

/**
 * Class in which you can trigger the processing of a punishment.
 */
public class OffenseAPI {
    private final PunishCore core;

    /**
     * Instantiates a new Offense api.
     *
     * @param core the core that will be used.
     */
    protected OffenseAPI(PunishCore core) {
        this.core = core;
    }

    /**
     * Trigger an offense to be processed.
     *
     * @param punishmentCalculationRequest the request with all the information about the offense.
     * @return the offense history record(punishment) that was calculated
     * @throws OffenseProcessingException   thrown if something went wrong (wrapper)
     */
    public OffenseHistoryRecord submitOffense(PunishmentCalculationRequest punishmentCalculationRequest) throws OffenseProcessingException {
        return core.getOffenseManager().calculateRecord(punishmentCalculationRequest);
    }

    /**
     * Submit offense without processing.
     *
     * @param offenseRecord the record that will be used.
     * @throws FailedDataOperationException thrown if something went wrong.
     */
    public void submitOffenseWithoutProcessing(OffenseHistoryRecord offenseRecord) throws FailedDataOperationException {
        core.getOffenseManager().submitOffenseWithoutProcessing(offenseRecord);
    }
}
