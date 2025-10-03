package ex3A.a.src;

import ex3A.a.src.creator.Magasin;
import ex3A.a.src.creator.MagasinDeDVD;
import ex3A.a.src.creator.MagasinDeLivre;

public class Main {
    public static void main(String[] args) {
        Magasin dvdStore = new MagasinDeDVD();
        dvdStore.ajouterArticle("Inception", 1985);
        dvdStore.ajouterArticle("The Matrix", 2015);
        dvdStore.affiche();

        System.out.println("----------------");

        Magasin bookStore = new MagasinDeLivre();
        bookStore.ajouterArticle("1984", 1984);
        bookStore.ajouterArticle("Brave New World", 2008);
        bookStore.affiche();
    }

}

