import java.util.ArrayList;

public class Test {
	public static void main(String[] args) {
		ArrayList<String> v= new ArrayList<String>();
		v.add("piano");
		Academie a = new Academie(v);
		a.mettreEnAttente("piano", "Christophe");
		a.mettreEnAttente("piano", "Jos�");
		String s= a.attribuerPlace("piano");
		System.out.println(s);
		s= a.attribuerPlace("piano");
		System.out.println(s);
	}
}
