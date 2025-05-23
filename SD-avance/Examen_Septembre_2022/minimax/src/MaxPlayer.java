
// Cette classe représente un joueur qui choisit tjr le plus grand des deux nombres. 
// Si les deux nombres sont égaux, il prend celui de droite.
public class MaxPlayer extends SimpleSpectator implements Player {

  @Override
  public boolean nextPlay() {
    State state = getCurrentState();
    int[] nbrs = state.getNbrs();

    int lb = state.getLeftBar();
    int rb = state.getRightBar();

    int x = nbrs[lb];
    int y = nbrs[rb - 1];
    return x > y;
  }
}