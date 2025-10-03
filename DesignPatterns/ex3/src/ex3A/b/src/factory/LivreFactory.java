package ex3A.b.src.factory;

import ex3A.b.src.product.Article;
import ex3A.b.src.product.Livre;

public class LivreFactory implements MagasinFactory {
    @Override
    public Article creerArticle(String name, int anneeDeParution) {
        return new Livre(name, anneeDeParution);
    }
}
