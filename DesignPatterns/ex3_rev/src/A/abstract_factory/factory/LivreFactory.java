package A.abstract_factory.factory;

import A.abstract_factory.product.Article;
import A.abstract_factory.product.Livre;

public class LivreFactory implements MagasinFactory {
    @Override
    public Article creerArticle(String name, int anneeDeParition) {
        return new Livre(name, anneeDeParition);
    }
}
