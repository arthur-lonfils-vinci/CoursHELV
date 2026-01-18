package Q3;

public class ActionGUI implements Action {
	private DeplacementDisque deplacement;
	private Tour[] tours;

	public ActionGUI(DeplacementDisque deplacement, Tour[] tours) {
		this.deplacement = deplacement;
		this.tours = tours;
	}

	public void effectuer() {
		tours[deplacement.getSource()].deplacer(tours[deplacement.getDestination()]);
	}
}
