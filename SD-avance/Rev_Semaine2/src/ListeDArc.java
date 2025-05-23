import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ListeDArc extends Graph{
	
	private ArrayList<Flight> flights;
	private ArrayList<Airport> airports;

	public ListeDArc() {
		super();
		flights=new ArrayList<Flight>();
		airports=new ArrayList<Airport>();
	}

	@Override
	// Complexit�: ?
	protected void ajouterSommet(Airport a) {	
		//� compl�ter
		if (!airports.contains(a)) {
			airports.add(a);
		}
	}

	@Override
	// Complexit�: ?
	protected void ajouterArc(Flight f) {
		//� compl�ter
		if (!flights.contains(f)) {
			flights.add(f);
		}
	}

	@Override
	// Complexit�: ?
	public Set<Flight> arcsSortants(Airport a) {
		//� compl�ter
		Set<Flight> result = new HashSet<Flight>();
		for (Flight flight : flights) {
			if (flight.getSource().equals(a)) {
				result.add(flight);
			}
		}
		return result;
	}

	@Override
	// Complexit�: ?
	public boolean sontAdjacents(Airport a1, Airport a2) {
		// � compl�ter
		return arcsSortants(a1).stream().anyMatch(flight -> flight.getDestination().equals(a2));
	}

}
