package me.superbiebel.punishapi.offenseprocessing.dataobjects;

import lombok.Getter;

@Getter
public class PunishmentReduction {
    
    public PunishmentReduction(int priority, int amountSubtracted) {
        this.priority = priority;
        this.amountSubtracted = amountSubtracted;
    }

    protected int priority;
    protected int amountSubtracted;
}
