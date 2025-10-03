package ex3A.b.src.product;

public class Livre implements Article {
	public static final double PRIX=14.99;
    private String name;
    private int anneeDeParution;
    private double prix;

    public Livre(String name, int anneeDeParution) {
        this.name = name;
        this.anneeDeParution = anneeDeParution;
        this.prix = PRIX;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getAnneeDeParution() {
        return anneeDeParution;
    }

    @Override
    public double getPrix() {
        return prix;
    }
}