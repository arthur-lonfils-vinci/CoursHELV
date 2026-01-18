package Q3;

public class ActionConsole implements Action {
	private DeplacementDisque deplacement;

	public ActionConsole(DeplacementDisque deplacement) {
		this.deplacement = deplacement;
	}

	public void effectuer() {
		System.out.println("d�placement de " + deplacement.getSource() + " vers " + deplacement.getDestination());
	}
}
