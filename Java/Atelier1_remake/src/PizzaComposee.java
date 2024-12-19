import java.util.ArrayList;

public class PizzaComposee extends Pizza{
    private static final int REMISE = 15;

    public PizzaComposee(String titre, String description, ArrayList<Ingredient> ingredients) {
        super(titre, description, ingredients);
    }

    @Override
    public double calculerPrix() {
        double prix = super.calculerPrix();
        return Math.ceil(prix - (prix * REMISE / 100));
    }

    @Override
    public boolean ajouter(Ingredient ingredient) {
        throw new UnsupportedOperationException("Impossible d'ajouter un ingrédient à une pizza composée.");
    }

    @Override
    public boolean supprimer(Ingredient ingredient) {
        throw new UnsupportedOperationException("Impossible de supprimer un ingrédient d'une pizza composée.");
    }
}
