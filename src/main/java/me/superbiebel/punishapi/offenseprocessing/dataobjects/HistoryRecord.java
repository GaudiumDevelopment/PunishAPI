package me.superbiebel.punishapi.offenseprocessing.dataobjects;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Getter
@Builder
public class HistoryRecord {
    
    private final UUID moderatorUUID;
    private final UUID criminalUUID;
    private final long timeregistered;
    private final Map<String,String> attributes;
    private final List<OffenseHistoryRecord> offensesDone; //list if a batch of offenses was done, otherwise just one.
    private final List<Punishment> punishments;
}
