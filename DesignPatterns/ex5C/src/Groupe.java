import java.util.Collections;
import java.util.Iterator;
import java.util.Vector;

public class Groupe implements Partie {
	private static int dernier = 0;
	private Vector parties = new Vector();
	private int niveau;
	private String nom;
	private int numero;

	public Groupe(String nom, int niveau) {
		this.nom = nom;
		this.niveau = niveau;
		numero = dernier++;
	}

	public void add(Partie nouveau) {
		parties.add(nouveau);
	}

	public Iterator getParties() {
		return Collections.unmodifiableList(parties).iterator();
	}

	public int getNiveau() {
		return niveau;
	}

	public String getNom() {
		return nom;
	}

	public int getNumero() {
		return numero;
	}

	public void demande(Traitement traitement) {
		traitement.traiteGroupe(this);
	}
}
