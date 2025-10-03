package ex3A.b.src.product;

public class DVD implements Article {
	public static final double PRIX=19.99;
    private String name;
    private int anneeDeParution;
    private double prix;

    public DVD(String name, int anneeDeParution) {
        this.name=name;
        this.anneeDeParution=anneeDeParution;
        this.prix=PRIX;
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
