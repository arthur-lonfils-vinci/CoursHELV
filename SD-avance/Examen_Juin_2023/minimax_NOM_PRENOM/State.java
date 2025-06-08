
// Cette classe représente un état du jeu des nombres.
public class State {

  // Les 10 nombres
  private int[] nbrs;

  // La barre de gauche (à gauche du nombre de gauche à jouer)
  private int leftBar;

  // La barre de droite (à droite du nombre de droite à jouer)
  private int rightBar;

  // C'est au joueur bleu de jouer ssi isBlueToPlay vaut true.
  // C'est au joueur orange de jouer ssi isBlueToPlay vaut false.
  private boolean isBlueToPlay;

  // Les points du joueur bleu.
  private int bluePoints;

  // Les points du joueur orange.
  private int orangePoints;

  // Initialise un nouvel état de la manière suivant :
  // 1) Les 10 nombres du jeu valent nbrs;
  // 2) La barre de gauche est positionnée à 0;
  // 3) La barre de droite est positionnée à 10;
  // 4) C'est au joueur bleu de jouer;
  // 5) Le joueur bleu à 0 point;
  // 6) Le joueur orange 0 point.
  public State(int[] nbrs) {
    this.nbrs = nbrs;
    this.leftBar = 0;
    this.rightBar = nbrs.length;
    this.isBlueToPlay = true;
    this.bluePoints = 0;
    this.orangePoints = 0;
  }

  // Cette méthode renvoie un nouvel état lorsque le coup de gauche est joué.
  public State playLeft() {
    State res = new State(nbrs);
    res.leftBar = leftBar + 1;
    res.rightBar = rightBar;
    res.isBlueToPlay = !isBlueToPlay;
    res.bluePoints = bluePoints;
    res.orangePoints = orangePoints;
    if (this.isBlueToPlay) {
      res.bluePoints += nbrs[leftBar];
    } else {
      res.orangePoints += nbrs[leftBar];
    }

    return res;
  }

  // Cette méthode renvoie un nouvel état lorsque le coup de droite est joué.
  public State playRight() {
    State res = new State(nbrs);
    res.leftBar = leftBar;
    res.rightBar = rightBar - 1;
    res.isBlueToPlay = !isBlueToPlay;
    res.bluePoints = bluePoints;
    res.orangePoints = orangePoints;
    if (this.isBlueToPlay) {
      res.bluePoints = bluePoints + nbrs[res.rightBar];
    } else {
      res.orangePoints = orangePoints + nbrs[res.rightBar];
    }
    return res;
  }

  // Getter des nombres
  public int[] getNbrs() {
    return nbrs;
  }

  // Getter de la barre de gauche
  public int getLeftBar() {
    return leftBar;
  }

  // Getter de la barre de droite
  public int getRightBar() {
    return rightBar;
  }

  // Renvoie true si c'est au joueur bleu de jouer.
  // Renvoie false si si c'est au joueur orange de jouer.
  public boolean isBlueToPlay() {
    return isBlueToPlay;
  }

  // Getter des points du joueur bleu
  public int getBluePoints() {
    return bluePoints;
  }

  // Getter des points de joueur Orange
  public int getOrangePoints() {
    return orangePoints;
  }
}
