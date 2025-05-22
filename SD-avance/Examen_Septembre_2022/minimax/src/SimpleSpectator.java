// Cette classe représente un spectateur qui mémorise l'état du jeu.
//
// Elle est destinée à être étendue.
public class SimpleSpectator implements Spectator {
  private State currentState;

  @Override
  public void start(State state) {
    this.currentState = state;
  }

  @Override
  public void play(boolean isLeftMove, State state) {
    this.currentState = state;
  }

  public void end(State state) {
  }

  @Override
  public State getCurrentState() {
    return this.currentState;
  }
}
