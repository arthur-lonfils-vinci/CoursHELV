package A.abstract_factory.product;

public class Livre implements Article {

    public static final double PRIX=14.99;
    private String name;
    private int anneeDeParition;
    private double prix;

    public Livre(String name, int anneeDeParition) {
        this.name = name;
        this.anneeDeParition = anneeDeParition;
        this.prix = PRIX;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getAnneeDeParition() {
        return anneeDeParition;
    }

    @Override
    public double getPrix() {
        return prix;
    }
}
