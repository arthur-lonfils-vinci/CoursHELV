package A.abstract_factory.store;

import A.abstract_factory.factory.MagasinFactory;
import A.abstract_factory.product.Article;

import java.util.HashMap;
import java.util.Map;

public class Magasin {
    private Map<String, Article> articles;
    private MagasinFactory magasinFactory;

    public Magasin(MagasinFactory magasinFactory) {
        this.articles = new HashMap<>();
        this.magasinFactory = magasinFactory;
    }

    public void ajouterArticle(String name, int anneeDeParition) {
        Article article = magasinFactory.creerArticle(name, anneeDeParition);
        articles.put(name, article);
    }

    public void display() {
        for (String key : articles.keySet()) {
            System.out.println(articles.get(key).getName());
        }
    }
}
