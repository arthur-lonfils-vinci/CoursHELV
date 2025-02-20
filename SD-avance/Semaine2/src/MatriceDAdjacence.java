import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MatriceDAdjacence extends Graph{
	
	private Map<Integer, Airport>  correspondanceIndiceAirport;
	private Map<Airport, Integer>  correspondanceAirportIndice;
	private Flight[][] matrice= new Flight[0][0];
	private int nbAirport=0;

	public MatriceDAdjacence() {
		super();
		correspondanceAirportIndice= new HashMap<Airport,Integer>();
		correspondanceIndiceAirport= new HashMap<Integer,Airport>();
	}

	@Override
	// Complexit�: ?
	protected void ajouterSommet(Airport a) {	
		//� compl�ter
		correspondanceAirportIndice.put(a, nbAirport++);
		correspondanceIndiceAirport.put(nbAirport, a);

		Flight[][] newMatrice= new Flight[nbAirport][nbAirport];
		for (int k = 0; k < matrice.length; k++) {
			for (int l = 0; l < matrice[k].length; l++) {
				newMatrice[k][l]= matrice[k][l];
			}
		}
		matrice= newMatrice;

	}

	@Override
	// Complexit�: ?
	protected void ajouterArc(Flight f) {
		//� compl�ter
		Integer source = correspondanceAirportIndice.get(f.getSource());
		Integer destination = correspondanceAirportIndice.get(f.getDestination());

		matrice[source][destination] = f;
	}

	@Override
	// Complexit�: ?
	public Set<Flight> arcsSortants(Airport a) {
		//� compl�ter
		Set<Flight> res= new HashSet<Flight>();

		Integer source = correspondanceAirportIndice.get(a);

		for (int k = 0; k < matrice.length; k++) {
			if (matrice[k][source] != null) {
				res.add(matrice[k][source]);
			}
		}

		return res;
	}

	@Override
	// Complexit�: ?
	public boolean sontAdjacents(Airport a1, Airport a2) {
		// � compl�ter

		Integer airport1 = correspondanceAirportIndice.get(a1);
		Integer airport2 = correspondanceAirportIndice.get(a2);

		if (matrice[airport1][airport2] != null || matrice[airport2][airport1] != null) {
			return true;
		}

		return false;
	}
	
	

}
