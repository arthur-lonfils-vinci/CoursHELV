
public class Airport {
	private final String iata;
	private final String name;

	public Airport(String iata, String name) {
		super();
		this.iata = iata;
		this.name = name;
	}
	public String getIata() {
		return iata;
	}
	public String getName() {
		return name;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((iata == null) ? 0 : iata.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Airport other = (Airport) obj;
		if (iata == null) {
			if (other.iata != null)
				return false;
		} else if (!iata.equals(other.iata))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Airport [iata=" + iata + ", name=" + name + "]";
	}

}
