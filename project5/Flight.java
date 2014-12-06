//Flights Represent the edges of a graph

public class Flight {
	/*==================================
	=            Attributes            =
	==================================*/
	private Airport source; // Name of  Airport
	private Airport destination; //Name of Airport 
	private Double cost; //represents weight in a directed graph

	/*===============================
	=            Methods            =
	===============================*/
	
	public Flight(String src, String dest, Double c) {
		source = src;
		destination = dest;
		cost = c;
	}


	
	
	
	
	
	
}