package ex3A.a.src.creator;

import ex3A.a.src.product.Article;
import ex3A.a.src.product.DVD;


public class MagasinDeDVD extends Magasin{

    @Override
    protected Article creerArticle(String name, int anneeDeParution) {
        return new DVD(name, anneeDeParution);
    }
}
