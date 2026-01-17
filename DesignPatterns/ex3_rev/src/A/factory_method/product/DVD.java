package A.factory_method.product;

public class DVD extends Article {
    public static final double PRIX=19.99;

    public DVD(String name, int anneeDeParution) {
        super(name, anneeDeParution, PRIX);
    }
}
