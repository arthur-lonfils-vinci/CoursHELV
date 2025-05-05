import java.time.LocalDateTime;

public class Test {
	public static void main(String[] args) {
		PlageHoraire p1= new PlageHoraire(LocalDateTime.of(2017,10,5,16,0,0), LocalDateTime.of(2017,10,5,17,0,0));
		PlageHoraire p2= new PlageHoraire(LocalDateTime.of(2017,10,12,14,0,0), LocalDateTime.of(2017,10,12,15,0,0));
		PlageHoraire p3= new PlageHoraire(LocalDateTime.of(2017,10,19,12,0,0), LocalDateTime.of(2017,10,19,13,0,0));
		Doodle d= new Doodle(p1,p2,p3);
		boolean[] dispoJean= {true,false,false};
		d.ajouterDisponibilites("jean", dispoJean);
		boolean[] dispoMarc= {false,true,true};
		d.ajouterDisponibilites("marc", dispoMarc);
		boolean[] dispoLuc= {false,true,false};
		d.ajouterDisponibilites("luc", dispoLuc);
		System.out.println("Jean est-il disponible pour la premi�re date ? " + d.estDisponible("jean", p1));
		System.out.println("Jean est-il disponible pour la deuxi�me date ? " + d.estDisponible("jean", p2));
		System.out.println("Jean est-il disponible pour la troisi�me date ? " + d.estDisponible("jean", p3));
		System.out.println("la plage horaire qui convient le mieux est "+d.trouverPlageQuiConvientLeMieux());
		
	}
}
