package me.superbiebel.punishapi.dataobjects;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@SuppressWarnings("ClassCanBeRecord")
@Getter
@Builder
public class OffenseHistoryRecord {
    private final UUID recordUUID;
    private final UUID moderatorUUID;
    private final UUID criminalUUID;
    private final UUID offenseProcessingTemplateUUID;
    private final long timeregistered;
    private final Map<String, String> attributes;
    private final List<Punishment> linkedPunishments;
}
