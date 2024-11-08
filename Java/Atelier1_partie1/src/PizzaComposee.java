import java.util.ArrayList;

public class PizzaComposee extends Pizza {
    public static final int REMISE = 15;

    public PizzaComposee(String titre, String description, ArrayList<Ingredient> ingredients) {
        super(titre, description, ingredients);

    }

    @Override
    public boolean ajouter(Ingredient ingredient) {
        return super.ajouter(ingredient);
    }

    @Override
    public boolean supprimer(Ingredient ingredient) {
        return super.supprimer(ingredient);
    }

    @Override
    public double calculerPrix() {
        return Math.ceil(super.calculerPrix() - (super.calculerPrix()*REMISE/100));
    }
}
