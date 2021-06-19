package me.superbiebel.punishapi.offenseprocessing.dataobjects;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class EditablePunishment extends Punishment {
    public EditablePunishment(UUID uuid, UUID offenseUUID, Map<String, String> attributes, long startTime, long originalDuration, long duration, boolean activated, List<String> scopes, List<PunishmentReduction> punishmentReductions) {
        super(uuid, offenseUUID, attributes, startTime, originalDuration, duration, activated, scopes, punishmentReductions);
    }
    
    public void setUuid(UUID uuid) {
        super.uuid = uuid;
    }
    
    public void setOffenseUUID(UUID offenseUUID) {
        super.offenseUUID = offenseUUID;
    }
    
    public void setAttributes(Map<String, String> attributes) {
        super.attributes = attributes;
    }
    
    public void setStartTime(long startTime) {
        super.startTime = startTime;
    }
    
    public void setOriginalDuration(long originalDuration) {
        super.originalDuration = originalDuration;
    }
    
    public void setDuration(long duration) {
        super.duration = duration;
    }
    
    public void setActivated(boolean activated) {
        super.activated = activated;
    }
    
    public void setScopes(List<String> scopes) {
        super.scopes = scopes;
    }
    
    public void setPunishmentReductions(List<PunishmentReduction> punishmentReductions) {
        super.punishmentReductions = punishmentReductions;
    }
}
