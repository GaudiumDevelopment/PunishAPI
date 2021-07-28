package me.superbiebel.punishapi.offenseprocessing.services.premade.jsoffenseprocessor.scriptobjects;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import me.superbiebel.punishapi.offenseprocessing.annotations.NoWriteFromScript;

public class OffenseHistoryRecordScriptObject {

    @NoWriteFromScript
    public UUID recordUUID;
    @NoWriteFromScript
    public UUID moderatorUUID;
    @NoWriteFromScript
    public UUID criminalUUID;
    @NoWriteFromScript
    public UUID offenseProcessingTemplateUUID;
    @NoWriteFromScript
    public long timeregistered;
    
    public Map<String, String> attributes;
    public List<PunishmentScriptObject> punishments;
}
