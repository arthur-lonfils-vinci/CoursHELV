
public class Main {

	public static void main(String[] args) {
		JeuxOlympiques j= new JeuxOlympiques();
		Pays usa= new Pays("USA");
		Pays france= new Pays("France");
		Pays australie= new Pays("Australie");
		Pays serbie= new Pays("Serbie");
		j.ajouterResultat("Basket masculin", usa, france, serbie);
		j.ajouterResultat("Basket feminin", usa, france, australie);
		j.afficherClassement();
	}

}
