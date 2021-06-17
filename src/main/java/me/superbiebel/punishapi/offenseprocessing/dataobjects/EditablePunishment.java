package me.superbiebel.punishapi.offenseprocessing.dataobjects;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class EditablePunishment extends Punishment {
    public EditablePunishment(UUID uuid, UUID offenseUUID, Map<String, String> attributes, long startTime, long originalDuration, long duration, boolean activated, List<String> scopes, List<PunishmentReduction> punishmentReductions) {
        super(uuid, offenseUUID, attributes, startTime, originalDuration, duration, activated, scopes, punishmentReductions);
    }
    
    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
    
    public void setOffenseUUID(UUID offenseUUID) {
        this.offenseUUID = offenseUUID;
    }
    
    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }
    
    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }
    
    public void setOriginalDuration(long originalDuration) {
        this.originalDuration = originalDuration;
    }
    
    public void setDuration(long duration) {
        this.duration = duration;
    }
    
    public void setActivated(boolean activated) {
        this.activated = activated;
    }
    
    public void setScopes(List<String> scopes) {
        this.scopes = scopes;
    }
    
    public void setPunishmentReductions(List<PunishmentReduction> punishmentReductions) {
        this.punishmentReductions = punishmentReductions;
    }
}
