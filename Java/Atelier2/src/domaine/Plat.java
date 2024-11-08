package domaine;

import java.time.Duration;
import java.util.*;

public class Plat {

    public enum Difficulte {
        X,XX,XXX,XXXX,XXXXX;

        Difficulte() {
        }

        @Override
        public String toString() {
            return "*".repeat(this.ordinal() + 1);
        }
    }

    public enum Cout {
        $,$$,$$$,$$$$,$$$$$;

        Cout() {
        }

        @Override
        public String toString() {
            return "€".repeat(this.ordinal() + 1);
        }
    }

    //Partie 2
    public enum Type {
        ENTREE("Entree"), PLAT("Plat"), DESSERT("Dessert");

        private String type;
        Type(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }

        @Override
        public String toString() {
            return type;
        }
    }

    private String nom;
    private int nbPersonnes;
    private Difficulte niveauDeDifficulte;
    private Cout cout;
    private Duration dureeEnMinutes;
    List<Instruction> instructions;
    Set<IngredientQuantifie> ingredients;
    //Partie 2
    private Type type;

    public Plat(String nom, int nbPersonnes, Difficulte niveauDeDifficulte, Cout cout, Type type) {
        this.nom = nom;
        this.nbPersonnes = nbPersonnes;
        this.niveauDeDifficulte = niveauDeDifficulte;
        this.cout = cout;
        this.dureeEnMinutes = Duration.ofMinutes(0);
        this.instructions = new ArrayList<>();
        this.ingredients = new HashSet<>();
        //Partie 2
        this.type = type;
    }

    public String getNom() {
        return nom;
    }

    public int getNbPersonnes() {
        return nbPersonnes;
    }

    public Difficulte getNiveauDeDifficulte() {
        return niveauDeDifficulte;
    }

    public Cout getCout() {
        return cout;
    }

    public Type getType() {
        return type;
    }

    public Duration getDureeEnMinutes() {
        return dureeEnMinutes;
    }

    public void insererInstruction(int position, Instruction instruction) throws IllegalArgumentException {
        if (position <= 0 || position > instructions.size()) {
            throw new IllegalArgumentException("Error : inserer");
        }
        instructions.add(position, instruction);
        //System.out.println("Inserer  =>   "+Arrays.toString(instructions.toArray()));

    }

    public void ajouterInstruction(Instruction instruction) {
        if (!instructions.contains(instruction)) {
            instructions.add(instruction);
            //System.out.println("Ajouter  =>   "+Arrays.toString(instructions.toArray()));
        }
    }

    public Instruction remplacerInstruction(int position, Instruction instruction) throws IllegalArgumentException {
        if (position <= 0 || position > instructions.size()) {
            throw new IllegalArgumentException("Error : remplacer");
        }
        Instruction deleted = instructions.get(position);
        instructions.remove(position);
        instructions.add(position, instruction);
        //System.out.println("Remplacer  =>   "+Arrays.toString(instructions.toArray()));

        return deleted;
    }

    public Instruction supprimerInstruction(int position) throws IllegalArgumentException {
        if (position <= 0 || position > instructions.size()) {
            throw new IllegalArgumentException();
        }
        Instruction instruction = instructions.get(position);
        instructions.remove(instructions.get(position));
        //System.out.println("Supprimer  =>   " + Arrays.toString(instructions.toArray()));
        return instruction;
    }

    public Iterator<Instruction> instructions(){
        return new Iterator<Instruction>() {
            private int position = 0;

            @Override
            public boolean hasNext() {
                return position < instructions.size();
            }

            @Override
            public Instruction next() {
                if (!hasNext()){
                    throw new NoSuchElementException();
                }
                return instructions.get(position++);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public boolean ajouterIngredient(Ingredient ingredient, int quantite, Unite unite) {
        IngredientQuantifie newIngredient = new IngredientQuantifie(ingredient, quantite, unite);
        return ingredients.add(newIngredient);
    }

    public boolean ajouterIngredient(Ingredient ingredient, int quantite) {
        IngredientQuantifie newIngredient = new IngredientQuantifie(ingredient, quantite);
        return ingredients.add(newIngredient);
    }

    public boolean modifierIngredient(Ingredient ingredient, int quantite, Unite unite) {
        for (IngredientQuantifie ingredientQuantifie : ingredients) {
            if (ingredientQuantifie.getIngredient().equals(ingredient)) {
                ingredientQuantifie.setQuantite(quantite);
                ingredientQuantifie.setUnite(unite);
                return true;
            }
        }
        return false;
    }

    public boolean supprimerIngredient(Ingredient ingredient) {
        for (IngredientQuantifie ingredientQuantifie : ingredients) {
            if (ingredientQuantifie.getIngredient().equals(ingredient)) {
                return ingredients.remove(ingredientQuantifie);
            }
        }
        return false;
    }

    public IngredientQuantifie trouverIngredientQuantifie(Ingredient ingredient) {
        for (IngredientQuantifie ingredientQuantifie : ingredients) {
            if (ingredientQuantifie.getIngredient().equals(ingredient)) {
                return ingredientQuantifie;
            }
        }
        return null;
    }

    public SortedSet<Ingredient> ingredients() {
        SortedSet<Ingredient> sortedIngredients = new TreeSet<>(new Comparator<Ingredient>() {
            @Override
            public int compare(Ingredient i1, Ingredient i2) {
                return i1.getNom().compareTo(i2.getNom());
            }
        });

        for (IngredientQuantifie iq : this.ingredients) {
            sortedIngredients.add(iq.getIngredient());
        }

        return sortedIngredients;
    }

    public Duration dureeHeureMinute(){
        int hms = 0;
        for (Instruction instruction : instructions) {
            hms += (int) instruction.getDureeEnMinutes().toMinutes();
        }
        //System.out.println("Duree heure minute : "+hms);
        dureeEnMinutes = Duration.ofMinutes(hms);
        return dureeEnMinutes;
    }

    @Override
    public String toString() {
        String hms = String.format("%02d h %02d m", dureeHeureMinute().toHours(), dureeHeureMinute().toMinutes()%60);
        String res = this.nom + "\n\n";
        res += "Type de recette : " + this.type + "\n";
        res += "Pour " + this.nbPersonnes + " personnes\n";
        res += "Difficulté : " + this.niveauDeDifficulte + "\n";
        res += "Coût : " + this.cout + "\n";
        res += "Durée : " + hms + " \n\n";
        res += "Ingrédients :\n";
        //Sorted List Ingredients
        Set<IngredientQuantifie> newList = new HashSet<>();
        for (Ingredient ing : this.ingredients()) {
            for (IngredientQuantifie ingQ : this.ingredients){
                if (ingQ.getIngredient().equals(ing)){
                    newList.add(ingQ);
                }
            }
        }
        for (IngredientQuantifie ing : newList) {
            res += ing + "\n";
        }
        ///
        int i = 1;
        res += "\n";
        for (Instruction instruction : this.instructions) {
            res += i++ + ". " + instruction + "\n";
        }
        res += "\n";
        return res;
    }
}
