package ex3A.a.src.product;

public class Livre extends Article {
	public static final double PRIX=14.99;


    public Livre(String name, int anneeDeParution) {
        super(name, anneeDeParution, PRIX);
    }
}