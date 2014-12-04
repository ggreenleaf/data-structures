//Airport class
import java.util.HashMap;
import java.util.Set;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;

// class Vertex
//          {
//              public List     adj;
//              public boolean  known;
//              public DistType dist;
//              public Vertex   path;
// }
//Vertex implement as Airport
public class Airport {
	private String name = null;
	public HashMap<String, Double> destinations = null; //Key is airport identifier and value is cost of flight
	
	private boolean visted; //used for dijsktras
	private Double distance; //used for dijsktras
	private String airportPathName = null; //cheapest flight to this airport in path dijsktras
	
	//generate destination based on string input AUS  DFW 59  HOU 59  SAT 59 
	//airport named AUS
	//map contains keys DFW HOU SAT with values 59, 59, 59 
	public Airport(String dests) {
		destinations = new HashMap<String, Double>();
		String[] destArr = dests.split("\\s+"); //first value will be name of AIRPORT then 2nd 3rd are name and value
		name = destArr[0];
		
		visted = false;
		distance = Double.POSITIVE_INFINITY;

		//making the adjacent airports that this airport can fly to
		for(int i = 1; i < destArr.length; i+=2) {
			destinations.put(destArr[i], Double.parseDouble(destArr[i+1]));
		}
	}

	public Double getDestinationCost(String key) {
		return destinations.get(key);
	}

	public String getName() {
		return name;
	}

	public void setVisted(Boolean b) {
		visted = b;
	}

	public void setDistance(Double d) {
		distance = d;
	}

	public Boolean isVisted() {
		return visted;
	}

	//returns the cheapest flight in the list of destinations for this airport
	public String getCheapestFlight() {
		//use Priority queue for now iterate over map values
		Double min = Double.POSITIVE_INFINITY;
		String minKey = null;
		for(String airportKey : destinations.keySet()) {
			Double curVal = destinations.get(airportKey);
			if (curVal < min) {
				minKey = airportKey;
				min = curVal;
			}
		}

	return minKey;
	}

}