package A.factory_method.creator;

import A.factory_method.product.Article;

import java.util.HashMap;
import java.util.Map;

public abstract class Magasin {
    protected Map<String, Article> articles;

    public Magasin() {
        articles = new HashMap<>();
    }

    protected abstract Article createArticle(String name, int anneeDeParution);

    public void ajouterArticle(String name, int anneeDeParution) {
        Article article = createArticle(name, anneeDeParution);
        articles.put(name, article);
    }

    public void affiche() {
        for (String key : articles.keySet()) {
            System.out.println(articles.get(key).getName());
        }
    }
}
