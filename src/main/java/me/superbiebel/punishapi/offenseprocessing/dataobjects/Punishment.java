package me.superbiebel.punishapi.offenseprocessing.dataobjects;

import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Getter
public class Punishment {
    
    protected UUID uuid;
    protected UUID offenseUUID;
    protected Map<String,String> attributes;
    protected long startTime;
    protected long originalDuration;
    protected long duration;
    protected boolean activated;
    protected List<String> scopes;
    protected List<PunishmentReduction> punishmentReductions;
    
    public Punishment(UUID uuid, UUID offenseUUID, Map<String, String> attributes, long startTime, long originalDuration, long duration, boolean activated, List<String> scopes, List<PunishmentReduction> punishmentReductions) {
        this.uuid = uuid;
        this.offenseUUID = offenseUUID;
        this.attributes = attributes;
        this.startTime = startTime;
        this.originalDuration = originalDuration;
        this.duration = duration;
        this.activated = activated;
        this.scopes = scopes;
        this.punishmentReductions = punishmentReductions;
    }
    
    
}
