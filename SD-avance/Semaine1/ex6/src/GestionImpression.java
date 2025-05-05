import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

public class GestionImpression {

    private HashMap<String, Impression> impressions;

    public GestionImpression() {
        this.impressions = new HashMap<String, Impression>();
    }


    public void ajouterImpression(Impression impr) {
        impressions.put(impr.getIdUtilisateur(), impr);
    }

    public Impression selectionnerImpression() {
        return impressions.remove(impressions.keySet().stream().findFirst().get());
    }


}
