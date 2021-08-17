package me.superbiebel.punishapi.dataobjects;

import java.util.Map;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@SuppressWarnings("ClassCanBeRecord")
@Getter
@Builder
public class PunishmentReduction {
    private final UUID punishmentReductionUUID;
    private final int priority;
    private final int amountSubtracted;
    private final Map<String, String> attributes;
}
