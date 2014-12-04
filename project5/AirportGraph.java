//FlightPaths
//main input for start of program 
//will contain the user inputs for flight start and destination 
//and will find the shortest path inclding path/cost/connections
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class AirportGraph {

	public HashMap<String, Airport> graph;

	public AirportGraph(String filename) {
		graph = new HashMap<>();
		Airport tempAirport;
		try {
			for (String line : Files.readAllLines(Paths.get(filename))) {
				tempAirport = new Airport(line); 
				graph.put(tempAirport.getName(),tempAirport);
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

	public void displayDeparturesAndPrices(String airportKey) throws NullPointerException {
		 Airport tempAirport = graph.get(airportKey);
		 Set s = tempAirport.destinations.entrySet();
		 Iterator i = s.iterator();
		 System.out.println("Departing " + airportKey +" to: ");
		 while(i.hasNext()) {
		 	Map.Entry m = (Map.Entry) i.next();
		 	System.out.println("\t"+m.getKey()+" "+m.getValue());
		 }

	}

	public Airport getAirport(String key) {
		return graph.get(key);
	}

	//find the cheapest route using dijkstra's algorithm
	//between starting location(departureKey) and ending location(arrivalKey)
	public String getRoute(String departureKey, String arrivalKey) {
		Airport start = graph.get(departureKey);
		start.setDistance(0.00);
		start.setVisted(true);
		while(true) {
			Airport nextAirport = getAirport(start.getCheapestFlight());
			
		}
		return "";
	} 


	public void resetAirportsVisted() {
		for (String key : graph.keySet())
		{
			graph.get(key).setVisted(false);
		}
	}















	public static void main (String [] args) {
		String filename = args[0]; //file name of airport.txt needed to read
		System.out.println(filename);
		AirportGraph graph = new AirportGraph(filename);
		System.out.println("Enter airport to view flights");
		System.out.println(graph.getRoute("DFW","LAX"));

	}
}