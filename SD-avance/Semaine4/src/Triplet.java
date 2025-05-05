// Cette classe représente une variante des valeurs Minimax. 
public class Triplet {

  // true si un des meilleurs coups est le coup de gauche.
  // false si un des meilleurs coups est le coup de droite.
  private boolean isLeftMove;

  // Le minimum de points que le joueur bleu aura à la fin du jeu si le joueur bleu
  // joue parfaitement.
  private int minBlue;

  // Le minimum de points que le joueur orange aura à la fin du jeu si le joueur
  // orange joue parfaitement.
  private int minOrange;

  // Initialise une valeur Minimax tel que:

  // 1) isLeftMove vaut true si le meilleur coup à jouer est à gauche.isLeftMove vaut false sinon.
  // 2) Le minimum de points que le joueur bleu aura à la fin du jeu si le joueur
  // bleu joue parfaitement vaut minBlue.
  // 3) Le minimum de points que le joueur orange aura à la fin du jeu si le joueur
  // orange joue parfaitement vaut minOrange.
  public Triplet(boolean isLeftMove, int minBlue, int minOrange) {
    this.isLeftMove = isLeftMove;
    this.minBlue = minBlue;
    this.minOrange = minOrange;
  }

  // Getter du meilleur coup.
  public boolean isLeftMove() {
    return isLeftMove;
  }

  // Getter du minimum de points du joueur bleu.
  public int getMinBlue() {
    return minBlue;
  }

  // Getter du minimum de points du joueur orange.
  public int getMinOrange() {
    return minOrange;
  }
  
  @Override
  public String toString() {
    return isLeftMove + " " + minBlue + " " + minOrange;
  }
}
