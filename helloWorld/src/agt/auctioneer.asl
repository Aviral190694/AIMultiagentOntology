// Agent auctioneer in project helloWorld

/* Initial beliefs and rules */

/* Initial goals */

!start.

/* Plans */

+!start <- .broadcast(tell, auction(service, flight_ticket(paris,athens,"15/12/2015"))).

+bid(Service, _) :  .findall(b(V,A),bid(Service,V)[source(A)],L) &  .length(L,4) <-
	  .min(L,b(V,W));
      .print("Winner for ", Service, " is ",W," with ", V);
       printMsg("Winner for ", Service, " is ",W," with ", V)[artifact_id(cobj_9)];
      .broadcast(tell, winner(Service,W)).



{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }

// uncomment the include below to have an agent compliant with its organisation
//{ include("$jacamoJar/templates/org-obedient.asl") }
