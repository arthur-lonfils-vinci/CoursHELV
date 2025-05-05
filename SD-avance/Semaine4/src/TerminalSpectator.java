// Cette classe est un spectateur qui affiche le jeu en ASCII sur un terminal.
public class TerminalSpectator extends SimpleSpectator {
  private char[] p = new char[10];

  @Override
  public void start(State state) {
    super.start(state);

    for (int i = 0; i != p.length; i++) {
      p[i] = '?';
    }
  }

  @Override
  public void play(boolean isLeftPlay, State state) {
    super.play(isLeftPlay, state);

    State nextState = getCurrentState();
    if (nextState.isBlueToPlay() && isLeftPlay) {
      p[nextState.getLeftBar() - 1] = 'O';
    } else if (nextState.isBlueToPlay()) {
      p[nextState.getRightBar()] = 'O';
    } else if (isLeftPlay) {
      p[nextState.getLeftBar() - 1] = 'B';
    } else {
      p[nextState.getRightBar()] = 'B';
    }
  }

  @Override
  public void end(State state) {
    int blue = state.getBluePoints();
    int orange = state.getOrangePoints();
    if (blue > orange) {
      System.out.println("BLEU WINS");
    } else if (blue == orange) {
      System.out.println("TIE");
    } else {
      System.out.println("ORANGE WINS");
    }
  }

  void print() {
    State state = getCurrentState();
    int i = state.getLeftBar();
    int j = state.getRightBar();
    int[] t = state.getNbrs();

    System.out.print("t = ");

    for (int k = 0; k != i; k++) {
      System.out.print(t[k] + " ");
    }
    System.out.print("| ");
    for (int k = i; k != j; k++) {
      System.out.print(t[k] + " ");
    }
    System.out.print("| ");
    for (int k = j; k != t.length; k++) {
      System.out.print(t[k] + " ");
    }
    System.out.println();

    System.out.print("p = ");
    for (int k = 0; k != i; k++) {
      System.out.print(p[k] + " ");
    }
    System.out.print("| ");
    for (int k = i; k != j; k++) {
      System.out.print(p[k] + " ");
    }
    System.out.print("| ");
    for (int k = j; k != t.length; k++) {
      System.out.print(p[k] + " ");
    }
    System.out.print(", blue -> " + state.getBluePoints());
    System.out.print(", orange -> " + state.getOrangePoints());
    System.out.println();
    System.out.println("-------------------------------------------------------");
  }
}
