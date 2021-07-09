package me.superbiebel.punishapi.offenseprocessing.dataobjects;

import java.util.List;
import java.util.Map;

/*the @Builder annotation cannot be used in the js script, graalvm will throw an exception
 *We will also not use the UUID class and instead use a string.
 *Every variable has been made public to allow editing from the script.
 */

public class PunishmentScriptObject {
    public Map<String,String> attributes;
    public long startTime;
    public long duration;
    public boolean activated;
    public List<String> scopes;
    public List<PunishmentReductionScriptObject> punishmentReductionList;
    public boolean decrementsDuration;
}
