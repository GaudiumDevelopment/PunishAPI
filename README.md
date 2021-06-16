# PunishAPI
A framework that will help you issue, maintain and store punishments as well as taking away the bad human aspect in punishing people.
## See and report
This is the concept behind the framework, a moderator only sees and reports the actions of the "criminal".
If a person does something he/she may not do, a moderator can trigger an offense. This moderator can optionally fill out some parameters about what happened. 
After that the offense gets submitted and the system inside using the settings you defined will calculate the appropriate punishment(s). 
This is much safer then a moderator who has to look up the punishment or worse, when the moderator itself must decide the punishment. 
As the punishment could possibly be more or less harshly based on the mood, not on the objective events which have happened.

## Examples
Person A has stolen something from person B, and when stealing, A used violence on B. moderator person C sees this and triggers an offense. 
C fills out a form of some sort (this is entirely customizable). As A has used violence on B, A will be punished more harshly. 
As it is the 10th offense of A in this week alone, A may be punished more harshly. The moderator submits this report and the system reviews it. 
The settings which you defined, or even a script, will process this offense. 
It takes some external parameters like the age of A which it can fetch from an external source into account and gives back punishments to PunishAPI. For example: 
-  A will serve a prison sentence of 3 months (punishment nr1)
-  A may not come near B (punishment nr2)

PunishAPI registers and stores the whole thing and everyone goes on with their lives. There may be an automated system (system D) in place which can detect if A comes close to B. 
System D will ask PunishAPI if A can come in the vicinity of B. If punishAPI says no then D will trigger an offense.

In this example, moderator C may be as agitated as possible on A, the punishment will be chosen by your settings/script, not by the mood of C. 
Errors are not possible as PunishAPi will follow your settings/script to the letter. 
C may be as bad as a mod can get, if C fills in the form in honesty, everyone is fine. This way of punishing someone is fair for both the criminal and the victim.
