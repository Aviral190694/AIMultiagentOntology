// Agent hello in project helloWorld

/* Initial beliefs and rules */

/* Initial goals */

!start.

/* Plans */

+!start : message(M) <- printMsg(M).
+!start : true <- .print("hello world.").

{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }

// uncomment the include below to have an agent compliant with its organisation
//{ include("$jacamoJar/templates/org-obedient.asl") }
