package me.superbiebel.punishapi.api;

import me.superbiebel.punishapi.PunishCore;
import me.superbiebel.punishapi.dataobjects.OffenseHistoryRecord;
import me.superbiebel.punishapi.dataobjects.OffenseProcessingRequest;
import me.superbiebel.punishapi.exceptions.FailedDataOperationException;
import me.superbiebel.punishapi.exceptions.OffenseProcessingException;

/**
 * Class in which you can trigger the processing of a punishment.
 */
@SuppressWarnings("ClassCanBeRecord")
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
     * @param offenseProcessingRequest the request with all the information about the offense.
     * @return the offense history record(punishment) that was calculated
     * @throws OffenseProcessingException   thrown if something went wrong (wrapper)
     */
    public OffenseHistoryRecord submitOffense(OffenseProcessingRequest offenseProcessingRequest) throws OffenseProcessingException {
        return core.getOffenseManager().submitOffense(offenseProcessingRequest);
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
