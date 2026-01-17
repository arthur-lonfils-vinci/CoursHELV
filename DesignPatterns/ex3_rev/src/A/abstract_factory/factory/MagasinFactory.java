package A.abstract_factory.factory;

import A.abstract_factory.product.Article;

public interface MagasinFactory {
    Article creerArticle(String name, int anneeDeParition);
}
