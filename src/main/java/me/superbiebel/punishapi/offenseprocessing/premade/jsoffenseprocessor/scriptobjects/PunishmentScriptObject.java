package me.superbiebel.punishapi.offenseprocessing.premade.jsoffenseprocessor.scriptobjects;

import java.util.List;
import java.util.Map;
import me.superbiebel.punishapi.offenseprocessing.annotations.NoWriteFromScript;

/*the @Builder annotation cannot be used in the js script, graalvm will throw an exception
 *We will also not use the UUID class and instead use a string.
 *Every variable has been made public to allow editing from the script.
 */

public class PunishmentScriptObject {
    @NoWriteFromScript
    public String punishmentUUID;
    @NoWriteFromScript
    public String offenseUUID;
    @NoWriteFromScript
    public long originalDuration;

    public Map<String, String> attributes;
    public long startTime;
    public long duration;
    public boolean activated;
    public List<String> scopes;
    public List<PunishmentReductionScriptObject> punishmentReductions;
    public boolean decrementsDuration;
}
