package A.factory_method.creator;

import A.factory_method.product.Article;
import A.factory_method.product.DVD;

public class MagasinDVD extends Magasin {
    @Override
    protected Article createArticle(String name, int anneeDeParution) {
        return new DVD(name, anneeDeParution);
    }
}
