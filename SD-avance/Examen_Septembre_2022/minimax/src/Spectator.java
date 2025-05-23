
// Cette interface représente un spectacteur du jeu.

// 1) La méthode start est appelée à chaque début de partie.
// 2) La méthode play est appelée chaque fois qu'un coup est joué.
// 3) La méthode end est appelée à chaque fin de partie. 
public interface Spectator {

  // Cette méthode est appelée à chaque début de partie.
  // state représente l'état initial de la partie.
  void start(State state);

  // Cette méthode est appelée chaque fois qu'un coup est joué.
  // isLeftMove vaut true si le coup joué concerne la barre de gauche.
  // isLeftMove vaut false si le coup joué concerne la barre de droite.
  // state représente l'état après que le coup ait été joué.
  void play(boolean isLeftMove, State state);

  // Cette méthode est appelée à chaque fin de partie.
  // state représente l'état à la fin de la partie.
  void end(State state);

  // Getter de l'état courant.
  State getCurrentState();
}
