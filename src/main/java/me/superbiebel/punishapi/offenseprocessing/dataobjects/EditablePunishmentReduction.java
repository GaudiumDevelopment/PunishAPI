package me.superbiebel.punishapi.offenseprocessing.dataobjects;

public class EditablePunishmentReduction extends PunishmentReduction {
    
    public EditablePunishmentReduction(int priority, int amountSubtracted) {
        super(priority, amountSubtracted);
    }
    
    public void setPriority(int priority) {
        super.priority = priority;
    }
    
    public void setAmountSubtracted(int amountSubtracted) {
        super.amountSubtracted = amountSubtracted;
    }
}
