package me.superbiebel.punishapi.offenseprocessing;

import java.io.File;
import me.superbiebel.punishapi.abstractions.Service;
import me.superbiebel.punishapi.dataobjects.OffenseHistoryRecord;
import me.superbiebel.punishapi.dataobjects.OffenseProcessingRequest;
import me.superbiebel.punishapi.exceptions.OffenseProcessingException;

public interface IOffenseProcessor extends Service {
    /**
     * @param offenseProcessingRequest the request that will be processed.
     * @return the record that will be stored in the database.
     */
    OffenseHistoryRecord processOffense(OffenseProcessingRequest offenseProcessingRequest, File scriptFile) throws OffenseProcessingException;

    boolean isScriptBased();
}
