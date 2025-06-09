import java.time.LocalTime;


public class Course {

	// ajouter attributs ici

	//initialise ce qu'il faut initialiser si nécéssaire
	public Course() {

	}

	// encode l'heure de départ pour un coureur (en utilisant la méthode setDepart de Coureur)
	public void encoderDepart(Coureur coureur, LocalTime l) {
		//à compléter
	}


	// encode l'heure d'arrivee pour un coureur (en utilisant la méthode setArrivee de Coureur)
	// si le coureur en paramètre n'a pas de temps de départ encodé alors la méthode lance une IllegalArgumentException
	public void encoderArrivee(Coureur coureur, LocalTime l) {
		//à compléter
	}

	// affiche (en utilisant le toString de coureur) les coureurs arrivés en triant selon le temps mis (les coureurs ayant mis moins de temps apparaissent en premier)
	// si deux coureurs ont le même temps, l'ordre n'a pas d'importance
	// à la fin, la méthode affiche une ligne de séparation constitué de '-'.
	public void afficherClassementProvisoire() {
		//à compléter
		System.out.println("-----------------------------");
	}
	
	public static void main(String[] args) {
		Coureur c1= new Coureur(1, "jean");
		Coureur c2= new Coureur(2, "pol");
		Coureur c3= new Coureur(3, "marc");
		Coureur c4= new Coureur(4, "luc");
		Coureur c5= new Coureur(5, "jim");
		Course c= new Course();
		
		c.encoderDepart(c1, LocalTime.of(2, 0, 0));
		c.encoderDepart(c2, LocalTime.of(2, 1, 0));
		c.encoderDepart(c3, LocalTime.of(2, 2, 0));
		c.encoderArrivee(c2, LocalTime.of(2, 2, 30));
		c.encoderArrivee(c1, LocalTime.of(2, 2, 45));
		c.encoderDepart(c4, LocalTime.of(2, 3, 0));
		c.encoderArrivee(c3, LocalTime.of(2, 3, 15));

		c.afficherClassementProvisoire();
		
		c.encoderDepart(c5, LocalTime.of(2, 4, 0));
		c.encoderArrivee(c4, LocalTime.of(2, 4, 20));
		c.encoderArrivee(c5, LocalTime.of(2, 6, 45));

		c.afficherClassementProvisoire();
 
	}
}
