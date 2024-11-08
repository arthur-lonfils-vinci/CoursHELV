package main;

import domaine.*;
import domaine.Plat.Cout;
import domaine.Plat.Difficulte;

import java.util.Iterator;

public class Main {

	public static void main(String[] args) {

		Plat plat = null;
		plat = new Plat("Waterzooi", 4, Difficulte.XX, Cout.$$$, Plat.Type.PLAT);

		Instruction instruction = new Instruction("Couper les légumes", 15);
		try {
			plat.insererInstruction(0,instruction);
		} catch(IllegalArgumentException iae){
			System.out.println(iae.getMessage());
		}
		plat.ajouterInstruction(instruction);
		instruction = new Instruction("Faire revenir les légumes", 5);
		plat.ajouterInstruction(instruction);
		instruction = new Instruction("Laisser mijoter jusqu'à cuisson du poulet",50);
		try {
			plat.insererInstruction(4,instruction);
		} catch(IllegalArgumentException iae){
			System.out.println(iae.getMessage());
		}
		plat.ajouterInstruction(instruction);
		instruction = new Instruction("Laisser légèrement refroidir", 30);
		plat.ajouterInstruction(instruction);
		instruction = new Instruction("Ajouter la crème et servir", 0);
		plat.ajouterInstruction(instruction);
		instruction = new Instruction("Laisser mijoter jusqu'à cuisson du poulet", 67);
		plat.remplacerInstruction(3,instruction);
		instruction = new Instruction("Ajouter le poulet", 0);
		plat.insererInstruction(3,instruction);
		plat.supprimerInstruction(2);

		Iterator<Instruction> instructionIterator = plat.instructions();
		while(instructionIterator.hasNext()){
			instruction = instructionIterator.next();
		}
		try{
			instructionIterator.remove();
		} catch (UnsupportedOperationException uoe){}

		Ingredient ing = new Ingredient("Blanc de poulet");
		plat.ajouterIngredient(ing,400, Unite.GRAMME);
		ing = new Ingredient("Céleri");
		plat.ajouterIngredient(ing,200, Unite.GRAMME);
		ing = new Ingredient("Carottes");
		plat.ajouterIngredient(ing, 2);
		ing = new Ingredient("jus de citron");
		plat.ajouterIngredient(ing,10,Unite.MILLILITRE);
		ing = new Ingredient("Sel");
		plat.ajouterIngredient(ing,1, Unite.PINCEE);
		ing = new Ingredient("Crème fraiche");
		plat.ajouterIngredient(ing,10, Unite.CENTILITRE);
		plat.modifierIngredient(new Ingredient("Blanc de poulet"), 600,Unite.GRAMME);
		plat.supprimerIngredient(new Ingredient("jus de citron"));
		IngredientQuantifie ingQuantifie = plat.trouverIngredientQuantifie(new Ingredient("Blanc de poulet"));
		System.out.println("Quantité de blanc de poulet nécessaire : " + ingQuantifie.getQuantite() + " " + ingQuantifie.getUnite()+"\n");
		System.out.println(plat);

		Livre livre = new Livre();
		livre.ajouterPlat(plat);
		System.out.println(livre);
		livre.supprimerPlat(plat);
		System.out.println(livre);
		System.out.println("----------------------------");

		livre.ajouterPlat(plat);
		Plat croquette = new Plat("Croquettes au fromage", 4, Difficulte.XXX, Cout.$$, Plat.Type.ENTREE);
		livre.ajouterPlat(croquette);
		System.out.println(livre);
		System.out.println("----------------------------");

		System.out.println(livre.getPlatsParType(Plat.Type.ENTREE));
		System.out.println("----------------------------");

		System.out.println("Est ce que le Livre contient 'Croquettes au fromage' ? -> "+livre.contient(croquette));
		System.out.println("----------------------------");

		Plat moule = new Plat("Moules Frites", 8, Difficulte.X, Cout.$$$, Plat.Type.PLAT);
		livre.ajouterPlat(moule);
		System.out.println(livre.tousLesPlats());

		System.out.println("----------------------------");
		System.out.println(livre);
	}

}
