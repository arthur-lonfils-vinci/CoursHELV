import java.util.Random;

//Cette classe représente un joueur qui joue aléatoirement.  
public class RandomPlayer extends SimpleSpectator implements Player {

  private Random rand = new Random();

  public boolean nextPlay() {
    return rand.nextBoolean();
  }
}
