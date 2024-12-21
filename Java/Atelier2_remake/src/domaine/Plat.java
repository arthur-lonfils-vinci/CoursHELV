package domaine;

import util.Util;

import java.time.Duration;
import java.util.*;

public class Plat implements Comparable<Plat>{


    public enum Difficulte{
        X,XX,XXX,XXXX,XXXXX;
        @Override
        public String toString() {
            return super.toString().replace("X","*");
        }
    }

    public enum Cout{
        $,$$,$$$,$$$$,$$$$$;
        @Override
        public String toString() {
            return super.toString().replace("$","€");
        }
    }

    public enum Type{
        ENTREE,PLAT,DESSERT;
        @Override
        public String toString() {
            return super.toString().toLowerCase();
        }
    }

    private final String nom;
    private int nbPersonnes;
    private Difficulte niveauDeDifficulte;
    private Cout cout;
    private Duration dureeEnMinutes = Duration.ofMinutes(0);
    private List<Instruction> recette = new ArrayList<>();
    private Set<IngredientQuantifie> ingredients = new HashSet<>();
    private Type type;

    public Plat(String nom, int nbPersonnes, Difficulte niveauDeDifficulte, Cout cout, Type type) {
        Util.checkString(nom);
        Util.checkStrictlyPositive(nbPersonnes);
        Util.checkObject(niveauDeDifficulte);
        Util.checkObject(cout);
        this.nom = nom;
        this.nbPersonnes = nbPersonnes;
        this.niveauDeDifficulte = niveauDeDifficulte;
        this.cout = cout;
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

    public Duration getDureeEnMinutes() {
        return dureeEnMinutes;
    }

    public Type getType() {
        return type;
    }

    // gestion de la recette et de la dureeEnMinutes

    /** Cette méthode insère l'instruction à la position précisée (la position commence à 1)
     * @param position la position à laquelle l'instruction doit être insérée
     * @param instruction l'instruction à insérer
     * @throws IllegalArgumentException en cas de position invalide ou d'instruction null
     */
    public void insererInstruction(int position, Instruction instruction){
        Util.checkStrictlyPositive(position);
        Util.checkObject(instruction);
        if (position > recette.size() + 1) throw new IllegalArgumentException();
        recette.add(position-1,instruction);
        dureeEnMinutes = dureeEnMinutes.plus(instruction.getDureeEnMinutes());
    }

    /** Cette méthode ajoute l'instruction en fin de la liste
     * @param instruction l'instruction à ajouter
     * @throws IllegalArgumentException en cas d'instruction null
     */
    public void ajouterInstruction (Instruction instruction){
        Util.checkObject(instruction);
        recette.add(instruction);
        dureeEnMinutes = dureeEnMinutes.plus(instruction.getDureeEnMinutes());
    }

    /**
     * Cette méthode remplace l’instruction de la position précisée par celle en paramètre (la position commence à 1).
     * @param position la position de l'instruction à remplacer
     * @param instruction la nouvelle instruction
     * @return l'instruction remplacée
     * @throws IllegalArgumentException en cas de position invalide ou d'instruction null
     */
    public Instruction remplacerInstruction (int position, Instruction instruction){
        Util.checkStrictlyPositive(position);
        Util.checkObject(instruction);
        if (position > recette.size()) throw new IllegalArgumentException();
        Instruction instructionRemplacee = recette.set(position-1,instruction);
        dureeEnMinutes = dureeEnMinutes.minus(instructionRemplacee.getDureeEnMinutes());
        dureeEnMinutes = dureeEnMinutes.plus(instruction.getDureeEnMinutes());
        return instructionRemplacee;
    }

    /**
     * Cette méthode supprime l’instruction qui se trouve à la position précisée en paramètre (la position commence à 1).
     * @param position la position de l'instruction à supprimer
     * @return l'instuction supprimée
     * @throws IllegalArgumentException en cas de position invalide
     */
    public Instruction supprimerInstruction (int position){
        Util.checkStrictlyPositive(position);
        if (position > recette.size() ) throw new IllegalArgumentException();
        Instruction instructionSupprimee = recette.remove(position-1);
        dureeEnMinutes = dureeEnMinutes.minus(instructionSupprimee.getDureeEnMinutes());
        return instructionSupprimee;
    }

    public List<Instruction> instructions(){
        return Collections.unmodifiableList(recette);
    }



    // gestion des ingrédients
    public boolean ajouterIngredient(Ingredient ingredient, int quantite, Unite unite){
        Util.checkObject(ingredient);
        Util.checkStrictlyPositive(quantite);
        Util.checkObject(unite);
        IngredientQuantifie ingredientQuantifie = new IngredientQuantifie(ingredient,quantite,unite);
        return ingredients.add(ingredientQuantifie);
    }

    public boolean ajouterIngredient(Ingredient ingredient, int quantite){
        Util.checkObject(ingredient);
        Util.checkStrictlyPositive(quantite);
        IngredientQuantifie ingredientQuantifie = new IngredientQuantifie(ingredient,quantite, Unite.NEANT);
        return ingredients.add(ingredientQuantifie);
    }

    public boolean modifierIngredient(Ingredient ingredient, int quantite, Unite unite){
        Util.checkObject(ingredient);
        Util.checkStrictlyPositive(quantite);
        Util.checkObject(unite);
        IngredientQuantifie ingredientQuantifie = new IngredientQuantifie(ingredient,quantite,unite);
        if (ingredients.remove(ingredientQuantifie)){
            return ingredients.add(ingredientQuantifie);
        }
        return false;
    }

    public boolean supprimerIngredient(Ingredient ingredient){
        Util.checkObject(ingredient);
        IngredientQuantifie ingredientQuantifie = new IngredientQuantifie(ingredient,1, Unite.NEANT);
        return ingredients.remove(ingredientQuantifie);
    }

    public IngredientQuantifie trouverIngredientQuantifie(Ingredient ingredient){
        Util.checkObject(ingredient);
        IngredientQuantifie ingredientQuantifie = new IngredientQuantifie(ingredient,1, Unite.NEANT);
        for (IngredientQuantifie ing : ingredients) {
            if (ing.getIngredient().equals(ingredientQuantifie.getIngredient())){
                return ing;
            }
        }
        return null;
    }

    @Override
    public int compareTo(Plat other) {
        // Implement comparison logic, for example, based on a field like name or id
        return this.nom.compareTo(other.nom);
    }


    @Override
    public String toString() {
        String hms = String.format("%d h %02d m", dureeEnMinutes.toHours(), dureeEnMinutes.toMinutes()%60);
        String res = this.nom + "\n\n";
        res += "Pour " + this.nbPersonnes + " personnes\n";
        res += "Difficulté : " + this.niveauDeDifficulte + "\n";
        res += "Coût : " + this.cout + "\n";
        res += "Durée : " + hms + " \n\n";
        res += "Ingrédients :\n";
        for (domaine.IngredientQuantifie ing : this.ingredients) {
            res += ing + "\n";
        }
        int i = 1;
        res += "\n";
        for (domaine.Instruction instruction : this.recette) {
            res += i++ + ". " + instruction + "\n";
        }
        return res;
    }
}
