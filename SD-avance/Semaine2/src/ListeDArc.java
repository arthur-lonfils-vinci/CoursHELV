import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class ListeDArc extends Graph{
	
	private ArrayList<Flight> flights;
	private ArrayList<Airport> airports;
	private HashMap<String, ArrayList<Airport>> listeArc;

	public ListeDArc() {
		super();
		flights=new ArrayList<Flight>();
		listeArc = new HashMap<>();
		airports = new ArrayList<>();
	}

	@Override
	// Complexit�: ?
	protected void ajouterSommet(Airport a) {
		airports.add(a);
	}

	@Override
	// Complexit�: ?
	protected void ajouterArc(Flight f) {
		//� compl�ter
		flights.add(f);

		listeArc.put(f.getAirline(), new ArrayList<>());
		listeArc.get(f.getAirline()).add(f.getSource());
		listeArc.get(f.getAirline()).add(f.getDestination());
	}

	@Override
	// Complexit�: ?
	public Set<Flight> arcsSortants(Airport a) {
		//� compl�ter
		HashSet<Flight> set = new HashSet<>();
		for (Flight f : flights) {
			if (f.getSource().equals(a)) {
				set.add(f);
			}
		}

		return set;
	}

	@Override
	// Complexit�: ?
	public boolean sontAdjacents(Airport a1, Airport a2) {
		// � compl�ter
		for (Flight f : flights) {
			if (f.getSource().equals(a1) && f.getDestination().equals(a2) || f.getDestination().equals(a2) && f.getSource().equals(a1)) {
				return true;
			}
		}
		return false;
	}

}
