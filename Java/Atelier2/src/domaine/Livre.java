package domaine;

import util.Util;

import java.util.*;

public class Livre {

    private Map<Plat.Type, SortedSet<Plat>> livre;

    public Livre() {
        livre = new TreeMap<>(new Comparator<Plat.Type>() {
            @Override
            public int compare(Plat.Type type1, Plat.Type type2) {
                // Définir l'ordre des types de plats
                List<Plat.Type> order = Arrays.asList(Plat.Type.ENTREE, Plat.Type.PLAT, Plat.Type.DESSERT);
                return Integer.compare(order.indexOf(type1), order.indexOf(type2));
            }
        });
    }

    public boolean ajouterPlat(Plat plat) {
        Util.checkObject(plat);
        SortedSet<Plat> plats = livre.get(plat.getType());
        if (plats == null) {
            plats = new TreeSet<>(new Comparator<Plat>() {
                @Override
                public int compare(Plat plat1, Plat plat2) {
                    int comp = plat1.getNiveauDeDifficulte().compareTo(plat2.getNiveauDeDifficulte());
                    if (comp == 0) return plat1.getNom().compareTo(plat2.getNom());
                    return comp;
                }
            });
            livre.put(plat.getType(), plats);
        }
        return plats.add(plat);
    }

    public boolean supprimerPlat(Plat plat) {
        Util.checkObject(plat);
        SortedSet<Plat> plats = livre.get(plat.getType());
        if (plats == null) return false;
        boolean platDeleted = plats.remove(plat);
        if (platDeleted && plats.isEmpty()) {
            livre.remove(plat.getType());
        }
        return platDeleted;
    }

    public SortedSet<Plat> getPlatsParType(Plat.Type type) {
        Util.checkObject(type);
        SortedSet<Plat> plats = livre.get(type);
        if (plats == null) return null;
        return Collections.unmodifiableSortedSet(plats);
    }

    public boolean contient(Plat plat) {
        Util.checkObject(plat);
        if (livre.containsKey(plat.getType())) {
            return livre.get(plat.getType()).contains(plat);
        }
        return false;
    }

    public Set<Plat> tousLesPlats() {
        Set<Plat> plats = new TreeSet<>(new Comparator<Plat>() {
            @Override
            public int compare(Plat p1, Plat p2) {

                int typeComparison = p1.getType().compareTo(p2.getType());
                if (typeComparison != 0) {
                    return typeComparison;
                }

                int difficultyComparison = p1.getNiveauDeDifficulte().compareTo(p2.getNiveauDeDifficulte());
                if (difficultyComparison != 0) {
                    return difficultyComparison;
                }

                return p1.getNom().compareTo(p2.getNom());
            }
        });

        for (Set<Plat> platSet : livre.values()) {
            plats.addAll(platSet);
        }

        return plats;
    }



    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Map.Entry<Plat.Type, SortedSet<Plat>> entry : this.livre.entrySet()) {
            str.append(entry.getKey().getType()).append("\n=====\n");
            for (Plat plat : entry.getValue()) {
                str.append(plat.getNom()).append("\n\n");
            }
        }
        return str.toString();
    }
}
