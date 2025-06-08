
public class Main {
	public static void main(String[] args) {
		Athlete damas = new Athlete("Damas");
		Athlete leconte = new Athlete("Leconte");
		Athlete vandermeulen = new Athlete("Vander Meulen");
		Athlete seront = new Athlete("Seront");
		Competition c = new Competition(damas, leconte, vandermeulen, seront);
		c.ajouterSaut(damas, 50);
		c.ajouterSaut(damas, 250);
		c.ajouterSaut(damas, 0);
		c.ajouterSaut(leconte, 450);
		c.ajouterSaut(leconte, 0);
		c.ajouterSaut(leconte, 0);
		c.ajouterSaut(vandermeulen, 50);
		c.ajouterSaut(vandermeulen, 250);
		c.ajouterSaut(vandermeulen, 0);
		c.ajouterSaut(seront, 30);
		c.ajouterSaut(seront, 300);
		c.ajouterSaut(seront, 0);
		c.classement();
	}
}
