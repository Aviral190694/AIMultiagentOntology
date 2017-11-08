// Agent hello2 in project helloWorld

/* Initial beliefs and rules */

/* Initial goals */
msg(fr,"Bonjour").
msg(br,"Bom dia").
msg(it,"Buon giorno").
msg(us,"Good morning").

!start.

/* Plans */

+!start : country(C) & msg(C,M) <- .print(M).
+!start : true <- .print("hello world.").

{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }

// uncomment the include below to have an agent compliant with its organisation
//{ include("$jacamoJar/templates/org-obedient.asl") }
