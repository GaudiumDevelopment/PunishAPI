package me.superbiebel.punishapi.offenseprocessing.dataobjects;

import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Getter
public class HistoryRecord {
    
    protected UUID moderatorUUID;
    protected UUID criminalUUID;
    protected long timeregistered;
    protected Map<String,String> attributes;
    protected List<Offense> offensesDone; //list if a batch of offenses was done, otherwise just one.
    protected List<Punishment> punishments;
    
    public HistoryRecord(UUID moderatorUUID, UUID criminalUUID, long timeregistered, Map<String, String> attributes, List<Offense> offensesDone, List<Punishment> punishments) {
        this.moderatorUUID = moderatorUUID;
        this.criminalUUID = criminalUUID;
        this.timeregistered = timeregistered;
        this.attributes = attributes;
        this.offensesDone = offensesDone;
        this.punishments = punishments;
    }
}
