package A.abstract_factory.factory;

import A.abstract_factory.product.Article;
import A.abstract_factory.product.DVD;

public class DVDFactory implements MagasinFactory {
    @Override
    public Article creerArticle(String name, int anneeDeParition) {
        return new DVD(name, anneeDeParition);
    }
}
