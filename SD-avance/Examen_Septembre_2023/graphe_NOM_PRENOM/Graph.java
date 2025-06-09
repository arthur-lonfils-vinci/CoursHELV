import java.io.File;
import java.nio.file.Files;
import java.util.*;

public class Graph {

	private final List<Troncon> listeAdjacence;
	private final Map<String, Station> mapNomStation;
	private final Map<Integer, Ligne> mapLignes;

	public Graph(File lignes, File troncons) throws Exception {
		mapLignes = new HashMap<>();
		listeAdjacence = new LinkedList<Troncon>();
		mapNomStation = new HashMap<>();

		List<String> allLines = Files.readAllLines(lignes.toPath());
		for (String line : allLines) {
			String[] attributes = line.split(",");
			int id = Integer.parseInt(attributes[0]);
			String numero = attributes[1];
			Station depart = getStation(attributes[2]);
			Station destination = getStation(attributes[3]);
			String transport = attributes[4];
			int tempsAttenteMoyen = Integer.parseInt(attributes[5]);
			mapLignes.put(id, new Ligne(id, numero, depart, destination, transport, tempsAttenteMoyen));
		}

		allLines = Files.readAllLines(troncons.toPath());

		for (String line : allLines) {
			String[] attributes = line.split(",");
			Ligne ligne = mapLignes.get(Integer.parseInt(attributes[0]));
			Station depart = getStation(attributes[1]);
			Station arrivee = getStation(attributes[2]);
			int duree = Integer.parseInt(attributes[3]);
			Troncon troncon = new Troncon(ligne, depart, arrivee, duree);
			listeAdjacence.add(troncon);
		}
	}

	public Station getStation(String nomStation) {
		Station station;
		if (!mapNomStation.containsKey(nomStation)) {
			station = new Station(nomStation);
			mapNomStation.put(station.getNom(), station);
		} else {
			station = mapNomStation.get(nomStation);
		}
		return station;
	}

	// A compléter
	// affiche a la sortie standard les noms des differentes stations
	// qu il est possible d'atteindre dans l ordre d un parcours en largeur (BFS)
	// depuis la station de depart.
	public void bfs(Station depart) {
		// TODO
	}

}
