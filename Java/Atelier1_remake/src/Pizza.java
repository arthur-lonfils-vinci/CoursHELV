import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public abstract class Pizza implements Iterable<Ingredient> {
    private String titre;
    private String description;
    private ArrayList<Ingredient> ingredients = new ArrayList<>();
    private static final int PRIX_BASE = 5;

    public Pizza(String titre, String description) {
        Util.checkString(titre);
        Util.checkString(description);
        this.titre = titre;
        this.description = description;
    }

    public Pizza(String titre, String description, ArrayList<Ingredient> ingredients) {
        this(titre, description);
        for (Ingredient ingredient : ingredients) {
            if (this.ingredients.contains(ingredient)) {
                throw new IllegalArgumentException("Il ne peut pas y avoir deux fois le même ingrédient dans une pizza.");
            }
            this.ingredients.add(ingredient);
        }
    }

    public String getTitre() {
        return this.titre;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public Iterator<Ingredient> iterator() {
        return this.ingredients.iterator();
    }

    public boolean ajouter(Ingredient ingredient) {
        if (this.ingredients.contains(ingredient)) {
            return false;
        }
        this.ingredients.add(ingredient);
        return true;
    }

    public boolean supprimer(Ingredient ingredient) {
        return this.ingredients.remove(ingredient);
    }

    public double calculerPrix() {
        double prix = PRIX_BASE;
        for (Ingredient ingredient : this.ingredients) {
            prix += ingredient.getPrix();
        }
        return prix;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pizza that = (Pizza) o;
        return titre.equals(that.titre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titre);
    }

}
