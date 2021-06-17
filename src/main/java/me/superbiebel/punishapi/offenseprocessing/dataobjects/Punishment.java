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
}
