package A.factory_method;

import A.factory_method.creator.Magasin;
import A.factory_method.creator.MagasinDVD;
import A.factory_method.creator.MagasinLivre;

public class Main {
    public static void main(String[] args) {
        Magasin dvdStore = new MagasinDVD();
        dvdStore.ajouterArticle("Inception", 1985);
        dvdStore.ajouterArticle("The Matrix", 2015);
        dvdStore.affiche();

        System.out.println("----------------");

        Magasin bookStore = new MagasinLivre();
        bookStore.ajouterArticle("1984", 1984);
        bookStore.ajouterArticle("Brave New World", 2008);
        bookStore.affiche();
    }

}