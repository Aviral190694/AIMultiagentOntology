// Agent hello4 in project helloWorld

/* Initial beliefs and rules */

/* Initial goals */

!start.

/* Plans */

+!print_h    : focused(jacamo,gui,ArtId) <- .print("Art Id is ",ArtId); printMsg("H")[artifact_id(ArtId)]; .wait(700).
+!print_e    : focused(jacamo,gui,ArtId) <- printMsg("e")[artifact_id(ArtId)]; .wait(700).
+!print_l1   : focused(jacamo,gui,ArtId) <- printMsg("l")[artifact_id(ArtId)]; .wait(700).
+!print_l2   : focused(jacamo,gui,ArtId) <- printMsg("l")[artifact_id(ArtId)]; .wait(700).
+!print_l3   : focused(jacamo,gui,ArtId) <- printMsg("l")[artifact_id(ArtId)]; .wait(700).
+!print_spc  : focused(jacamo,gui,ArtId) <- printMsg(" ")[artifact_id(ArtId)]; .wait(700).
+!print_w    : focused(jacamo,gui,ArtId) <- printMsg("W")[artifact_id(ArtId)]; .wait(700).
+!print_o1   : focused(jacamo,gui,ArtId) <- printMsg("o")[artifact_id(ArtId)]; .wait(700).
+!print_o2   : focused(jacamo,gui,ArtId) <- printMsg("o")[artifact_id(ArtId)]; .wait(700).
+!print_r    : focused(jacamo,gui,ArtId) <- printMsg("r")[artifact_id(ArtId)]; .wait(700).
+!print_d    : focused(jacamo,gui,ArtId) <- printMsg("d")[artifact_id(ArtId)]; .wait(700).
+!print_excl : focused(jacamo,gui,ArtId) <- printMsg("!")[artifact_id(ArtId)]; .wait(700).


+tick <- .print("perceived a tick").
//+!start : true <- .print("hello world.").

+!start : message(M) <- printMsg(M).
+!start : focused(jacamo,gui,ArtId) <- printMsg("running this is good")[artifact_id(ArtId)];
		   .broadcast(tell, auction(service, flight_ticket(paris,athens,"15/12/2015"))).

+bid(Service, _) :  .findall(b(V,A),bid(Service,V)[source(A)],L) &  .length(L,4) <-
	  .min(L,b(V,W));
      .print("Winner for ", Service, " is ",W," with ", V);
      .print(ArtId); 
      .broadcast(tell, winner(Service,W)).
      
+!start : true <- .print("hello world.").

{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }

// uncomment the include below to have an agent compliant with its organisation
{ include("$jacamoJar/templates/org-obedient.asl") }
