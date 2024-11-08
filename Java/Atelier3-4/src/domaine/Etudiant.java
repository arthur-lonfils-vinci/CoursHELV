package domaine;

/**
 * Repr�sente un �tudiant.
 * Simple structure de donn�e contenant les informations d'identification d'un �tudiant.
 * Un �tudiant est un objet immutable
 */
public class Etudiant {

    /**
     * L'email de l'�tudiant
     */
    private String email;
    /**
     * le matricule de l'�tudiant
     */
    private String idNumber;
    /**
     * le nom de l'�tudiant , est parfois null
     */
    private String lastName;
    /**
     * Le pr�nom de l'�tudiant, est parfois null
     */
    private String firstName;

    /**
     * Cr�e un nouvel �tudiant avec un email et un matricule mais sans nom ni pr�nom
     *
     * @param email l'email de l'�tudiant
     * @param matricule le matricule de l'�tudiant
     */
    public Etudiant(String email, String idNumber) {
        this.email = email;
        this.idNumber = idNumber;
    }

    /**
     * Cr�e un nouvel �tudiant sur base d'une cha�ne de caract�re dont les diff�rents champs sont s�par�s par des ";"
     * L'ordre des champs est : matricule;nom;prenom;email
     *
     * @param toSplit la cha�ne de caract�res incluant les diff�rents champs.
     */
    public Etudiant(String toSplit) {
        String[] mots = toSplit.split(";");
        this.email = mots[3];
        this.idNumber = mots[0];
        this.lastName = mots[1];
        this.firstName = mots[2];
    }

    /**
     * @return l'email de l'�tudiant
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return le matricule de l'�tudiant
     */
    public String getIdNumber() {
        return idNumber;
    }

    /**
     * @return une cha�ne de caract�res reprenant les diff�rentes informations d'identification de l'�tudiant
     */
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return lastName + " " + firstName + " [" + idNumber + "] - " + email;
    }

    /**
     * @return le nom de l'�tudiant (ou null si inconnu )
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @return le pr�nom de l'�tudiant (ou null si inconnu )
     */
    public String getFirstName() {
        return firstName;
    }
}
