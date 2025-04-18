import java.util.Random;

//Cette classe joue une 20000 parties entre 2 joueurs.
//Elle affiche ensuite: 
// 1) le % de victoire de P1;
// 2) le % de partie nulle;
// 3) le % de victoire de P2.
public class MainStats {
  public static final int BOUND = 10;
  public static final int NBR = 20000;

  // aut vs aut 0.48955 0.0116 0.49885
  // aut vs rand 0.87855 0.0202 0.10125
  // aut vs max 0.60895 0.0296 0.36145
  // rand vs rand 0.47565 0.04295 0.4814
  // rand vs max 0.0935 0.02055 0.88595
  // max vs max 0.4842 0.04085 0.47495

  private static TerminalSpectator spectator = new TerminalSpectator();
  private static Player p1 = new AutomaticPlayer();
  private static Player p2 = new MaxPlayer();

  public static void main(String[] args) {
    int p1 = 0;
    int p2 = 0;
    int tie = 0;

    Random rand = new Random();

    for (int i = 0; i != NBR; i++) {
      boolean turn = rand.nextBoolean();
      State current = oneGame(BOUND, turn);

      boolean isP1 = turn && current.getBluePoints() > current.getOrangePoints();
      isP1 = isP1 || !turn && current.getBluePoints() < current.getOrangePoints();

      boolean isTie = current.getBluePoints() == current.getOrangePoints();

      if (isP1) {
        p1++;
      } else if (isTie) {
        tie++;
      } else {
        p2++;
      }
    }

    System.out.println("Le joueur 1 gagne " + 1.0 * p1 / NBR + "% des parties");
    System.out.println("Il y a une égalité " + 1.0 * tie / NBR + "% des parties");
    System.out.println("Le joueur 2 gagne " + 1.0 * p2 / NBR + "% des parties");
  }

  public static State oneGame(int bound, boolean turn) {
    Random rand = new Random();
    // INIT TAB

    int[] t = new int[10];
    for (int i = 0; i != 10; i++) {
      t[i] = rand.nextInt(bound) + 1;
    }

    State state = new State(t);

    spectator.start(state);
    p1.start(state);
    p2.start(state);

    for (int k = 0; k != 10; k++) {
      boolean isLeftPlay;
      if (turn) {
        isLeftPlay = p1.nextPlay();
      } else {
        isLeftPlay = p2.nextPlay();
      }

      if (isLeftPlay) {
        state = state.playLeft();
      } else {
        state = state.playRight();
      }
      turn = !turn;

      spectator.play(isLeftPlay, state);
      p1.play(isLeftPlay, state);
      p2.play(isLeftPlay, state);
    }

    return state;
  }
}