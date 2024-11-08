package code_theorie;

import domaine.Employe;
import domaine.Genre;

import java.util.function.Predicate;

public class PredicatGenreHomme implements Predicate<Employe> {
    @Override
    public boolean test(Employe e) {
        return e.getGenre() == Genre.HOMME;
    }
}
