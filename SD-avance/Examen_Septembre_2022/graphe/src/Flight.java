
public class Flight {
	private final Airport source;
	private final Airport destination;
	private final String airline;
	public Flight(Airport source, Airport destination, String airline) {
		this.source = source;
		this.destination = destination;
		this.airline = airline;
	}
	public Airport getSource() {
		return source;
	}
	public Airport getDestination() {
		return destination;
	}
	public String getAirline() {
		return airline;
	}
	@Override
	public String toString() {
		return "Flight [source=" + source.getIata() + ", destination=" + destination.getIata() + ", airline=" + airline + "]";
	}
	
	
}
