package me.superbiebel.punishapi.offenseprocessing.dataobjects;

import lombok.Getter;

@Getter
public class PunishmentReduction {
    
    protected int priority;
    protected int amountSubtracted;
    
    public PunishmentReduction(int priority, int amountSubtracted) {
        this.priority = priority;
        this.amountSubtracted = amountSubtracted;
    }
}
