package ex3A.a.src.creator;

import ex3A.a.src.product.Article;

import java.util.Map;

public abstract class Magasin {
    protected Map<String, Article> articles;

    public Magasin() {
        articles = new java.util.HashMap<>();
    }

    protected abstract Article creerArticle(String name, int anneeDeParution);

    public void ajouterArticle(String name, int anneeDeParution) {
        Article article = creerArticle(name, anneeDeParution);
        articles.put(name, article);
    }

    public void affiche() {
        for (String key : articles.keySet()) {
            System.out.println(articles.get(key).getName());
        }
    }
}
