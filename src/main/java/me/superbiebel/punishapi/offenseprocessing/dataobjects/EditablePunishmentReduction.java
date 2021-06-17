package me.superbiebel.punishapi.offenseprocessing.dataobjects;

public class EditablePunishmentReduction extends PunishmentReduction {
    public void setPriority(int priority) {
        this.priority = priority;
    }
    
    public void setAmountSubtracted(int amountSubtracted) {
        this.amountSubtracted = amountSubtracted;
    }
}
