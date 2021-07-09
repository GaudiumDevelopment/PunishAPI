var offenseProcessingResultClass = Java.type("me.superbiebel.punishapi.offenseprocessing.dataobjects.OffenseScriptProcessingResult");
var HashMap = Java.type("java.util.HashMap");
var ArrayList = Java.type("java.util.ArrayList");
var PunishmentScriptObject = Java.type("me.superbiebel.punishapi.offenseprocessing.dataobjects.PunishmentScriptObject");
var PunishmentReductionScriptObject = Java.type("me.superbiebel.punishapi.offenseprocessing.dataobjects.PunishmentReductionScriptObject")

var verdict = new offenseProcessingResultClass();
var punishment1attributes = new HashMap();
var verdictattributes = new HashMap();
var linkedPunishments = new ArrayList();
var punishment1 = new PunishmentScriptObject();
var scopes = new ArrayList();
var punishmentReduction1 = new PunishmentReductionScriptObject();
var punishmentReductionList = new ArrayList();

scopes.add("MINECRAFT");
punishment1attributes.put("testkeypunishment", "testvaluepunishment");
verdictattributes.put("testkeyresult", "testvalueresult")



punishmentReduction1.priority = 123160;
punishmentReduction1.amountSubtracted = 65464;
var punishmentreductionattributes = new HashMap();
punishmentreductionattributes.put("testkeypunishmentreductionattributes", "testvaluepunishmentreductionattributes");
punishmentReduction1.attributes = punishmentreductionattributes;
punishmentReductionList.add(punishmentReduction1)

punishment1.punishmentReductionList = punishmentReductionList;

punishment1.startTime = 1000;
punishment1.attributes = punishment1attributes;
punishment1.duration = 2000;
punishment1.activated = true;
punishment1.scopes = scopes;
punishment1.decrementsDuration = false;

linkedPunishments.add(punishment1);

verdict.attributes = verdictattributes;
verdict.punishments = linkedPunishments;
