// Agent participant in project helloWorld

/* Initial beliefs and rules */

/* Initial goals */

!start.

/* Plans */

+!start : true <- .print("hello Participant.").
+auction(service, D)[source(A)] <- .send(A,tell,bid(D, math.random * 100 + 10)).

{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }

// uncomment the include below to have an agent compliant with its organisation
//{ include("$jacamoJar/templates/org-obedient.asl") }
