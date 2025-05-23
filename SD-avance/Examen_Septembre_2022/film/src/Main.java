// Cette classe de test ne peut pas être modifiée

public class Main {
	public static void main(String[] args) {
		Catalogue c= new Catalogue();
		Film f1= new Film("Licorice Pizza",2021);
		c.ajouterFilm(f1);
		Film f2= new Film("Top Gun: Maverick",2022);
		c.ajouterFilm(f2);
		Film f3= new Film("Decision to Leave",2022);
		c.ajouterFilm(f3);
		Film f4= new Film("Elvis",2022);
		c.ajouterFilm(f4);
		Film f5= new Film("Contes du hasard et autres fantaisies",2021);
		c.ajouterFilm(f5);
		Film f6= new Film("The Innocents", 2021);
		c.ajouterFilm(f6);
		Film f7= new Film("Vortex", 2021);
		c.ajouterFilm(f7);
		Film f8= new Film("En Corps", 2022);
		c.ajouterFilm(f8);
		Film f9= new Film("The Chef", 2022);
		c.ajouterFilm(f9);
		
		c.afficherFilmParOrdreAlphabetique(2020);
		System.out.println("-------------");
		c.afficherFilmParOrdreAlphabetique(2021);
		System.out.println("-------------");
		c.afficherFilmParOrdreAlphabetique(2022);
		System.out.println("-------------");
	}
}
