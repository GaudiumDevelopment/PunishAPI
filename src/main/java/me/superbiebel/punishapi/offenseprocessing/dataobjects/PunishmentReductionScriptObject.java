package me.superbiebel.punishapi.offenseprocessing.dataobjects;

/*the @Builder annotation cannot be used in the js script, graalvm will throw an exception
 *We will also not use the UUID class and instead use a string.
 *Every variable has been made public to allow editing from the script.
 */
public class PunishmentReductionScriptObject {
    public int priority;
    public int amountSubtracted;
}
