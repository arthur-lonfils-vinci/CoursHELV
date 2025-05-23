
public class Main {
	public static void main(String[] args) throws Exception {
		Graph g = new Graph("flight.xml");
		g.bfs(g.getAirport("JFK"));
	}
}
