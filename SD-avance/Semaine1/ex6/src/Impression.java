public class Impression {

	private String idUtilisateur;
	private String fichier;

	public Impression(String idUtilisateur, String fichier) {
		this.idUtilisateur = idUtilisateur;
		this.fichier = fichier;
	}

	public String getIdUtilisateur() {
		return idUtilisateur;
	}

	public String getFichier() {
		return fichier;
	}

}
