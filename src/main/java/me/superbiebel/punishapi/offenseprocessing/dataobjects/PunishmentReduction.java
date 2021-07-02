package me.superbiebel.punishapi.offenseprocessing.dataobjects;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class PunishmentReduction {
    private final UUID punishmentReductionUUID;
    private final int priority;
    private final int amountSubtracted;
}
