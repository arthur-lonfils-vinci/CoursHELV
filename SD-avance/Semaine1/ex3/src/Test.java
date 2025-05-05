import static java.lang.Thread.sleep;

public class Test {
	public static void main(String[] args) throws InterruptedException {
		int[] pointures={42,44,42};
		LocationPatins l = new LocationPatins(pointures);
		System.out.println(l.attribuerCasierAvecPatins(42));
		System.out.println(l.attribuerCasierAvecPatins(42));
		System.out.println(l.attribuerCasierAvecPatins(44));
		System.out.println(l.attribuerCasierAvecPatins(44));
		System.out.println(l.attribuerCasierAvecPatins(42));
		sleep(400);
		System.out.println(l.libererCasier(0));
		sleep(500);
		System.out.println(l.libererCasier(1));
		System.out.println(l.attribuerCasierAvecPatins(42));
		System.out.println(l.attribuerCasierAvecPatins(42));
		System.out.println(l.attribuerCasierAvecPatins(44));

	}
}
