package ex3A.b.src.factory;

import ex3A.b.src.product.Article;

public interface MagasinFactory {
    Article creerArticle(String name, int anneeDeParution);
}
