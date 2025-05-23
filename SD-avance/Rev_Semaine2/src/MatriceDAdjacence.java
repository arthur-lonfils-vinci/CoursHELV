import java.util.HashMap;
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
		correspondanceIndiceAirport.put(nbAirport, a);
		correspondanceAirportIndice.put(a, nbAirport);
		if (nbAirport==0) {
			matrice= new Flight[1][1];
		} else {
			Flight[][] matriceTemp= new Flight[nbAirport+1][nbAirport+1];
			for (int i=0; i<nbAirport; i++) {
				for (int j=0; j<nbAirport; j++) {
					matriceTemp[i][j]= matrice[i][j];
				}
			}
			matrice= matriceTemp;
		}
		nbAirport++;
	}

	@Override
	// Complexit�: ?
	protected void ajouterArc(Flight f) {
		//� compl�ter
		int i= correspondanceAirportIndice.get(f.getSource());
		int j= correspondanceAirportIndice.get(f.getDestination());
		matrice[i][j]= f;
	}

	@Override
	// Complexit�: ?
	public Set<Flight> arcsSortants(Airport a) {
		//� compl�ter
		Set<Flight> arcs= new java.util.HashSet<Flight>();
		int i= correspondanceAirportIndice.get(a);
		for (int j=0; j<nbAirport; j++) {
			if (matrice[i][j]!=null) {
				arcs.add(matrice[i][j]);
			}
		}
		return arcs;
	}

	@Override
	// Complexit�: ?
	public boolean sontAdjacents(Airport a1, Airport a2) {
		// � compl�ter
		int i= correspondanceAirportIndice.get(a1);
		int j= correspondanceAirportIndice.get(a2);
    return matrice[i][j] != null;
  }
	
	

}
