public class Ligne {
	private final int id;
	private final String nom;
	private final Station source;
	private final Station destination;
	private final String type;
	private final int attenteMoyenne;

	public Ligne(int id, String nom, Station source, Station destination, String type, int attenteMoyenne) {
		this.id = id;
		this.nom = nom;
		this.source = source;
		this.destination = destination;
		this.type = type;
		this.attenteMoyenne = attenteMoyenne;
	}

	public int getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	public Station getSource() {
		return source;
	}

	public Station getDestination() {
		return destination;
	}

	public String getType() {
		return type;
	}

	public int getAttenteMoyenne() {
		return attenteMoyenne;
	}

	@Override
	public String toString() {
		return "Ligne [id=" + id + ", nom=" + nom + ", source=" + source + ", destination=" + destination + ", type="
				+ type + ", attenteMoyenne=" + attenteMoyenne + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Ligne other = (Ligne) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
