package me.superbiebel.punishapi.dataobjects;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@SuppressWarnings("ClassCanBeRecord")
@Getter
@Builder
public class Punishment {
    private final UUID punishmentUUID;
    private final UUID offenseUUID;
    private final Map<String, String> attributes;
    private final long startTime;
    private final long originalDuration;
    private final long duration;
    private final boolean decrementsDuration;
    private final boolean activated;
    private final List<String> scopes;
    private final List<PunishmentReduction> punishmentReductions;
}
