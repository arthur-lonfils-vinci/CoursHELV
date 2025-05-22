import java.io.File;
import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Graph {

	protected Map<String, Airport> correspondanceIataAirport ;
	private Map<Integer, Airport>  correspondanceIndiceAirport;
	private Map<Airport, Integer>  correspondanceAirportIndice;
	private Flight[][] matrice;
	private int nbAirport=0;
	
	public Graph (String xmlFile) throws Exception {
		correspondanceIataAirport= new HashMap<String, Airport>();
		correspondanceAirportIndice= new HashMap<Airport,Integer>();
		correspondanceIndiceAirport= new HashMap<Integer,Airport>();
		File xml = new File(xmlFile);
		DocumentBuilderFactory docBuildFact = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuild = docBuildFact.newDocumentBuilder();
		Document doc = docBuild.parse(xml);
		NodeList airports = doc.getElementsByTagName("airport");
		for (int i = 0; i < airports.getLength(); i++) {
			Node airport = airports.item(i);
			Element elAirport = (Element) airport;
			String iata = elAirport.getAttribute("iata");
			String name = elAirport.getAttribute("name");
			Airport a = new Airport(iata, name);
			correspondanceIataAirport.put(iata, a);
			correspondanceIndiceAirport.put(nbAirport,a);
			correspondanceAirportIndice.put(a, nbAirport);
			nbAirport++;
		}
		matrice= new Flight[nbAirport][nbAirport];
		for (int i = 0; i < airports.getLength(); i++) {
			Node airport = airports.item(i);
			Element elAirport = (Element) airport;
			String iata = elAirport.getAttribute("iata");
			NodeList flights = elAirport.getElementsByTagName("flight");
			for (int j = 0; j < flights.getLength(); j++) {
				Node flight = flights.item(j);
				Element elFlight = (Element) flight;
				String dest = elFlight.getTextContent();
				String airline = elFlight.getAttribute("airline");
				Flight f = new Flight(correspondanceIataAirport.get(iata), correspondanceIataAirport.get(dest),
						airline);
				matrice[correspondanceAirportIndice.get(f.getSource())][correspondanceAirportIndice.get(f.getDestination())]=f;
			}
		}
	}
	
	public Airport getAirport(String iata) {
		return correspondanceIataAirport.get(iata);
	}	
	
	// A compl�ter
	// affiche a la sortie standard les codes iata des differents aeroports 
	// qu il est possible d'atteindre dans l ordre d un parcours en largeur (BFS) depuis l aeroport de depart.
	public void bfs(Airport a) {
		Queue<Airport> airportsQueue = new LinkedList<>();
		Set<Airport> airportSet = new HashSet<>();
		StringBuilder toReturn = new StringBuilder();

		airportsQueue.offer(a);
		airportSet.add(a);

		int position;

		while (!airportsQueue.isEmpty()) {
			Airport current = airportsQueue.poll();
			position = correspondanceAirportIndice.get(current);
			toReturn.append(current.getIata()).append(" ");

			for (int i = 0; i < nbAirport; i++) {
				Flight flight = matrice[position][i];
				if (flight != null && flight.getSource() == current && !airportSet.contains(flight.getDestination()) && !airportsQueue.contains(flight.getDestination())) {
					airportsQueue.offer(flight.getDestination());
					airportSet.add(flight.getDestination());
				}
			}
		}

		System.out.println(toReturn);
	}

}
