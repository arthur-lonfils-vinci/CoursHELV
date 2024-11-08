import java.util.ArrayList;
import java.util.Iterator;

public abstract class Pizza implements Iterable<Ingredient> {

    public static final double PRIX_BASE = 5.0;
    private String titre;
    private String description;
    private ArrayList<Ingredient> ingredients;

    public Pizza(String titre, String description) {
        this.titre = titre;
        this.description = description;
        this.ingredients = new ArrayList<>();
    }

    public Pizza(String titre, String description, ArrayList<Ingredient> ingredients) {
        this.titre = titre;
        this.description = description;
        this.ingredients = ingredients;

        ArrayList<Ingredient> newIngredients= new ArrayList<>();
        for (Ingredient ingr : ingredients) {
            if (newIngredients.contains(ingr)){
                throw new IllegalArgumentException("Il ne peut pas y avoir deux fois le même ingrédient dans une pizza");
            } else {
                newIngredients.add(ingr);
            }
        }

    }

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public boolean ajouter(Ingredient ingredient) {
        if (!ingredients.contains(ingredient))
            return this.ingredients.add(ingredient);
        else
            return false;
    }

    public boolean supprimer(Ingredient ingredient) {
        return this.ingredients.remove(ingredient);
    }

    public double calculerPrix(){
        double prix = PRIX_BASE;
        for (Ingredient ingredient : ingredients) {
            prix += ingredient.getPrix();
        }
        return prix;
    }

    @Override
    public Iterator<Ingredient> iterator() {
        return null;
    }

    @Override
    public String toString() {
        String infos = titre + "\n" + description + "\nIngrédients : ";
        for (Ingredient ingredient : ingredients){
            infos +="\n" + ingredient.getNom();
        }
        infos +="\nprix : " + calculerPrix() + " euros";
        return infos;
    }
}
