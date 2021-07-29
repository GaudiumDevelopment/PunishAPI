package me.superbiebel.punishapi.offenseprocessing.premade.jsoffenseprocessor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import me.superbiebel.punishapi.dataobjects.OffenseHistoryRecord;
import me.superbiebel.punishapi.dataobjects.OffenseProcessingRequest;
import me.superbiebel.punishapi.dataobjects.Punishment;
import me.superbiebel.punishapi.dataobjects.PunishmentReduction;
import me.superbiebel.punishapi.offenseprocessing.premade.jsoffenseprocessor.scriptobjects.OffenseHistoryRecordScriptObject;
import me.superbiebel.punishapi.offenseprocessing.premade.jsoffenseprocessor.scriptobjects.PunishmentReductionScriptObject;
import me.superbiebel.punishapi.offenseprocessing.premade.jsoffenseprocessor.scriptobjects.PunishmentScriptObject;

public class ScriptObjectConverter {
    
    private ScriptObjectConverter() {
    }
    
    public static OffenseHistoryRecord convertOffenseScriptProcessingResultToOffenseHistoryRecord(OffenseHistoryRecordScriptObject result, OffenseProcessingRequest offenseProcessingRequest, boolean keepUUIDs) {
        List<Punishment> punishmentList = new ArrayList<>();
    
        result.punishments.forEach(punishmentScriptObject->{
            Punishment punishment = convertPunishmentScriptObjectToPunishment(punishmentScriptObject, keepUUIDs);
            punishmentList.add(punishment);
        });
    
    
        return OffenseHistoryRecord.builder().attributes(result.attributes)
                       .moderatorUUID(offenseProcessingRequest.getModeratorUUID())
                       .criminalUUID(offenseProcessingRequest.getCriminalUUID())
                       .linkedPunishments(punishmentList).build();
    }
    public static PunishmentReduction convertPunishmentReductionScriptObjectToPunishmentReduction(PunishmentReductionScriptObject punishmentReductionScriptObject, boolean keepUUIDs) {
        return PunishmentReduction.builder()
                       .punishmentReductionUUID(keepUUIDs ? UUID.fromString(punishmentReductionScriptObject.punishmentReductionUUID) : UUID.randomUUID())
                       .priority(punishmentReductionScriptObject.priority)
                       .amountSubtracted(punishmentReductionScriptObject.amountSubtracted)
                       .attributes(punishmentReductionScriptObject.attributes).build();
    }
    public static Punishment convertPunishmentScriptObjectToPunishment(PunishmentScriptObject punishmentScriptObject, boolean keepUUIDs) {
            Punishment.PunishmentBuilder builder = Punishment.builder()
                                               .punishmentUUID(keepUUIDs ? UUID.fromString(punishmentScriptObject.punishmentUUID) : UUID.randomUUID())
                                               .offenseUUID(keepUUIDs ? UUID.fromString(punishmentScriptObject.offenseUUID) : UUID.randomUUID())
                                               .attributes(punishmentScriptObject.attributes)
                                               .startTime(punishmentScriptObject.startTime)
                                               .originalDuration(punishmentScriptObject.duration)
                                               .duration(punishmentScriptObject.duration)
                                               .decrementsDuration(punishmentScriptObject.decrementsDuration)
                                               .activated(punishmentScriptObject.activated)
                                               .scopes(punishmentScriptObject.scopes);
        List<PunishmentReduction> punishmentReductionList = new ArrayList<>();
        punishmentScriptObject.punishmentReductions.forEach(punishmentReductionScriptObject-> punishmentReductionList.add(convertPunishmentReductionScriptObjectToPunishmentReduction(punishmentReductionScriptObject, keepUUIDs)));
        builder.punishmentReductions(punishmentReductionList);

        return builder.build();
    }
}
