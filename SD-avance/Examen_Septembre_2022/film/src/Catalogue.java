import java.util.*;

public class Catalogue {
	
	//Vous pouvez ajouter des attributs et/ou des constructeurs 
	HashMap<Integer, List<Film>> filmByYear;
	Set<Film> allFilms;

	public Catalogue() {
		this.filmByYear = new HashMap<>();
		this.allFilms = new HashSet<>();
	}

	//ajoute le film au catalogue
	public void ajouterFilm(Film f) {
    if (allFilms.add(f)) {
			if (!filmByYear.containsKey(f.getAnnee())) {
				List<Film> list = new ArrayList<>();
				list.add(f);
				filmByYear.put(f.getAnnee(), list);
			} else {
				filmByYear.get(f.getAnnee()).add(f);
			}
		}
	}
	
	// affiche tous les films de l'ann�e en param�tre par ordre alphab�tique
	// utilise le toString() de Film
	public void afficherFilmParOrdreAlphabetique (int annee) {
		if(!filmByYear.containsKey(annee)){
			System.out.println("pas de film en " + annee);
			return;
		}

		filmByYear.get(annee).stream()
						.sorted(Comparator.comparing(Film::getNom))
						.forEach(System.out::println);
	}
}
