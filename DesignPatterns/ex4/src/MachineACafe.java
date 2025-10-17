public class MachineACafe {
    int montantEnCours = 0;
    ToucheBoisson boisson = null;
    State state;

    public MachineACafe() {
        setState(State.IDLE);
    }

    public void setState(State state) {
        this.state = state;
    }

    public void afficherMontant() {
        System.out.println(montantEnCours + " cents disponibles");
    }

    public void afficherRetour() {
        System.out.println(montantEnCours + " cents rendus");
    }

    public void afficherPasAssez(ToucheBoisson toucheBoisson) {
        System.out.println("Vous n'avez pas introduit un montant suffisant pour un " + toucheBoisson);
        System.out.println("Il manque encore " + (toucheBoisson.getPrix() - montantEnCours) + " cents");
    }

    public void afficherBoisson(ToucheBoisson toucheBoisson) {
        System.out.println("Voici un " + toucheBoisson);

    }

    public void entrerMonnaie(Piece piece) {
        state.entrerMonnaie(this, piece);
    }

    public void selectionnerBoisson(ToucheBoisson toucheBoisson) {
        state.selectionnerBoisson(this, toucheBoisson);
    }

    public void rendreMonnaie() {
        state.rendreMonnaie(this);
    }
}
