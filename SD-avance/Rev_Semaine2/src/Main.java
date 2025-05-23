
public class Main {
	public static void main(String[] args) throws Exception {
		Graph g= new MatriceDAdjacence();
		g.constructFromXML("flight.xml");
		g.arcsSortants(g.getAirport("JFK")).forEach(System.out::println);
		System.out.println("----------------------");
		System.out.println(g.sontAdjacents((g.getAirport("JFK")), (g.getAirport("STN"))));
		System.out.println(g.sontAdjacents((g.getAirport("JFK")), (g.getAirport("BCN"))));

	}
}
