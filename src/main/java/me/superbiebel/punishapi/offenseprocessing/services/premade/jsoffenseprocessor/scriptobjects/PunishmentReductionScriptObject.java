package me.superbiebel.punishapi.offenseprocessing.services.premade.jsoffenseprocessor.scriptobjects;

import java.util.Map;
import me.superbiebel.punishapi.offenseprocessing.annotations.NoWriteFromScript;

/*the @Builder annotation cannot be used in the js script, graalvm will throw an exception
 *We will also not use the UUID class and instead use a string.
 *Every variable has been made public to allow editing from the script.
 */
public class PunishmentReductionScriptObject {
    @NoWriteFromScript
    public String punishmentReductionUUID;
    public int priority;
    public int amountSubtracted;
    public Map<String, String> attributes;
}
