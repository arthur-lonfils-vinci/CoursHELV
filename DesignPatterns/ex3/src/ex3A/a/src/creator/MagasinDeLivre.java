package ex3A.a.src.creator;

import ex3A.a.src.product.Article;
import ex3A.a.src.product.Livre;

public class MagasinDeLivre extends Magasin{

    @Override
    protected Article creerArticle(String name, int anneeDeParution) {
        return new Livre(name, anneeDeParution);
    }
}
