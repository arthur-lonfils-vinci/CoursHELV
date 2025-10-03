package ex3A.b.src.factory;

import ex3A.b.src.product.Article;
import ex3A.b.src.product.DVD;

public class DVDFactory implements MagasinFactory{
    @Override
    public Article creerArticle(String name, int anneeDeParution) {
        return new DVD(name, anneeDeParution);
    }
}
