package me.superbiebel.punishapi.offenseprocessing;

import java.io.File;
import me.superbiebel.punishapi.abstractions.ServiceSystem;
import me.superbiebel.punishapi.dataobjects.OffenseHistoryRecord;
import me.superbiebel.punishapi.dataobjects.OffenseProcessingRequest;
import me.superbiebel.punishapi.exceptions.OffenseProcessingException;

public abstract class AbstractOffenseProcessor extends ServiceSystem implements IOffenseProcessor {

    public final OffenseHistoryRecord processOffense(OffenseProcessingRequest offenseProcessingRequest, File scriptFile) throws OffenseProcessingException {
        try {
            return process(offenseProcessingRequest, scriptFile);
        } catch (Exception e) {
            throw new OffenseProcessingException(e);
        }
    }

    protected abstract OffenseHistoryRecord process(OffenseProcessingRequest offenseProcessingRequest, File scriptFile);
}
