package A.factory_method.creator;

import A.factory_method.product.Article;
import A.factory_method.product.Livre;

public class MagasinLivre extends Magasin {
    @Override
    protected Article createArticle(String name, int anneeDeParution) {
        return new Livre(name, anneeDeParution);
    }
}
