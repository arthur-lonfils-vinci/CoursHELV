import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ListeDAdjacence extends Graph{
	
	private Map<Airport,Set<Flight>> outputFlights;

	public ListeDAdjacence(){
		super();
		outputFlights=new HashMap<Airport,Set<Flight>>();

	}

	@Override
	// Complexit�: ?
	protected void ajouterSommet(Airport a) {	
		//� compl�ter
		if(!outputFlights.containsKey(a)){
			outputFlights.put(a, new HashSet<Flight>());
		}
	}

	@Override
	// Complexit�: ?
	protected void ajouterArc(Flight f) {
		//� compl�ter
		if(outputFlights.containsKey(f.getSource())){
			outputFlights.get(f.getSource()).add(f);
		}
		else{
			Set<Flight> flights = new HashSet<Flight>();
			flights.add(f);
			outputFlights.put(f.getSource(), flights);
		}
	}

	@Override
	// Complexit�: ?
	public Set<Flight> arcsSortants(Airport a) {
		//� compl�ter
		return outputFlights.get(a);
	}

	@Override
	// Complexit�: ?
	public boolean sontAdjacents(Airport a1, Airport a2) {
		// � compl�ter
		return outputFlights.get(a1).stream().anyMatch(f -> f.getDestination().equals(a2))
				|| outputFlights.get(a2).stream().anyMatch(f -> f.getDestination().equals(a1));
	}

}
