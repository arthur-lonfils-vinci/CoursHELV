package A.abstract_factory;


import A.abstract_factory.factory.DVDFactory;
import A.abstract_factory.factory.LivreFactory;
import A.abstract_factory.store.Magasin;

public class Main {
    public static void main(String[] args) {
        // Magasin de DVD
        Magasin dvdStore = new Magasin(new DVDFactory());
        dvdStore.ajouterArticle("Inception", 1985);
        dvdStore.ajouterArticle("The Matrix", 2015);
        dvdStore.display();

        System.out.println("----------------");

        // Magasin de Livre
        Magasin bookStore = new Magasin(new LivreFactory());
        bookStore.ajouterArticle("1984", 1984);
        bookStore.ajouterArticle("Brave New World", 2008);
        bookStore.display();
    }
}