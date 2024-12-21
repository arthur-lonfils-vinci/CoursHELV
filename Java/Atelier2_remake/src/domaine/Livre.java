package domaine;

import util.Util;

import java.util.*;

public class Livre {

    private SortedSet<Plat> plats = new TreeSet<>();
    private Map<Plat.Type, SortedSet<Plat>> platsParType = new TreeMap<>();

    public Livre() {
    }

    public void ajouterPlat(Plat plat){
        Util.checkObject(plat);
        plats.add(plat);
        if (!platsParType.containsKey(plat.getType())){
            platsParType.put(plat.getType(), new TreeSet<>());
        }
        platsParType.get(plat.getType()).add(plat);
    }

    public void supprimerPlat(Plat plat){
        Util.checkObject(plat);
        plats.remove(plat);
        platsParType.get(plat.getType()).remove(plat);
        if (platsParType.get(plat.getType()).isEmpty()){
            platsParType.remove(plat.getType());
        }
    }

    public SortedSet<Plat> plats(){
        return plats;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (Plat.Type type : platsParType.keySet()){
            sb.append(type).append(":\n");
            for (Plat plat : platsParType.get(type)){
                sb.append(plat).append("\n");
            }
        }
        return sb.toString();
    }
}
