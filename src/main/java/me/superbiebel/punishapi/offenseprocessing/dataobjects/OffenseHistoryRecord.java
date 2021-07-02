package me.superbiebel.punishapi.offenseprocessing.dataobjects;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Getter
@Builder
public class OffenseHistoryRecord {
    protected final UUID offenseProcessingTemplateUUID;
    protected final long timeregistered;
    protected final Map<String, String> attributes;
    private final List<Punishment> linkedPunishments;
}
