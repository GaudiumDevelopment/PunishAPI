var offenseProcessingResultClass = Java.type('me.superbiebel.punishapi.offenseprocessing.dataobjects.OffenseScriptProcessingResult');
var HashMap = Java.type('java.util.HashMap');
var ArrayList = Java.type('java.util.ArrayList');

var PunishmentClass = Java.type('me.superbiebel.punishapi.offenseprocessing.dataobjects.Punishment');

var punishmentlist = new ArrayList();
var map = new HashMap();

var verdict = new offenseProcessingResultClass()

map.put("testkey", "testvalue")

var punishment = new PunishmentClass();
punishment.uuid = '5bf1653c-dfe4-11eb-ba80-0242ac130004'
punishment.offenseUUID = 'test'
var attributeMap = new HashMap();
attributeMap.put("punishmenttestkey", "punishmenttestvalue")

punishment.attributes = attributeMap;
punishment.startTime = 1000;
punishment.originalDuration = 2000;
punishment.duration = 3000;
punishment.activated = true;
var scopesList = new ArrayList();
scopesList.add("testscope")
punishment.scopes = scopesList;

punishmentlist.add(punishment)


verdict.attributes = map;
verdict.linkedPunishments = punishmentlist;