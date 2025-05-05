import java.util.ArrayList;


public class TestAvecDesistement {
	public static void main(String[] args) {
		ArrayList<String> v= new ArrayList<String>();
		v.add("piano");
		AcademieAvecDesistement a = new AcademieAvecDesistement(v);
		a.mettreEnAttente("piano", "Christophe");
		a.mettreEnAttente("piano", "Jos�");
		a.mettreEnAttente("piano", "Annick");
		a.desistement("piano","Jos�");
		String s= a.attribuerPlace("piano");
		System.out.println(s);
		s= a.attribuerPlace("piano");
		System.out.println(s);
	}
}
