package ex3A.b.src.store;

import ex3A.b.src.factory.MagasinFactory;
import ex3A.b.src.product.Article;

import java.util.Map;

public class Magasin {
    private Map<String, Article> articles;
    private MagasinFactory factory;

    public Magasin(MagasinFactory factory) {
        articles = new java.util.HashMap<>();
        factory = factory;
    }

    public void ajouterArticle(String name, int anneeDeParution) {
        Article newArticle = factory.creerArticle(name, anneeDeParution);
        articles.put(newArticle.getName(), newArticle);
    }

    public void affiche() {
        for (String key : articles.keySet()) {
            System.out.println(articles.get(key).getName());
        }
    }
}
