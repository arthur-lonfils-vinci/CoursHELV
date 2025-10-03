package ex3A.a.src.product;

public class DVD extends Article {
	public static final double PRIX=19.99;

    public DVD(String name, int anneeDeParution) {
        super(name, anneeDeParution, PRIX);
    }
}
