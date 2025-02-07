public class Test {
	public static void main(String[] args) {
		GestionImpression g=new GestionImpression();
		g.ajouterImpression(new Impression("jean","1er essai de jean"));
		g.ajouterImpression(new Impression("marc","essai final de marc"));
		g.ajouterImpression(new Impression("jean","2�me essai de jean"));
		g.ajouterImpression(new Impression("luc","1er essai de luc"));
		g.ajouterImpression(new Impression("luc","essai final de luc"));
		System.out.println(g.selectionnerImpression().getFichier());
		System.out.println(g.selectionnerImpression().getFichier());
		System.out.println(g.selectionnerImpression().getFichier());
		g.ajouterImpression(new Impression("jean","3�me essai de jean"));
		g.ajouterImpression(new Impression("jean","essai final de jean"));
		System.out.println(g.selectionnerImpression().getFichier());
	}
}
