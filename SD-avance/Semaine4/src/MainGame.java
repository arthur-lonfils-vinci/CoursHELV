import java.util.Random;

// Cette classe joue une unique partie entre 2 joueurs. 
public class MainGame {

  // Le jeu est composé de 10 nombres allant de 1 à BOUND compris.
  public static final int BOUND = 5;

  public static void main(String[] args) {
    Random rand = new Random();

    int[] t = new int[10];
    for (int i = 0; i != 10; i++) {
      t[i] = rand.nextInt(BOUND) + 1;
    }

    State state = new State(t);

    TerminalSpectator spectator = new TerminalSpectator();
    Player blue = new AutomaticPlayer();
    Player orange = new ManualPlayer();

    spectator.start(state);
    blue.start(state);
    orange.start(state);

    spectator.print();
    boolean turn = true;
    for (int k = 0; k != 10; k++) {
      boolean isLeftPlay;
      if (turn) {
        isLeftPlay = blue.nextPlay();
      } else {
        isLeftPlay = orange.nextPlay();
      }

      if (isLeftPlay) {
        state = state.playLeft();
      } else {
        state = state.playRight();
      }
      turn = !turn;

      spectator.play(isLeftPlay, state);
      blue.play(isLeftPlay, state);
      orange.play(isLeftPlay, state);
      spectator.print();
    }

    spectator.end(state);
    blue.end(state);
    orange.end(state);
  }
}