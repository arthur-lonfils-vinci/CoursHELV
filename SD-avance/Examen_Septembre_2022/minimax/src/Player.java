// Cette interface représente un des deux joueur du jeu.

public interface Player extends Spectator {
  // Cette méthode est appelée pour connaitre le coup de ce joueur :
  // 1) Elle renvoie vrai si ce joueur joue la barre de gauche; et
  // 2) Elle renvoie faux si ce joueur joue la barre de droite.
  boolean nextPlay();
}
