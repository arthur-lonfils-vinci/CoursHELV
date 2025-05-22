import java.util.Random;

public class MainTree {
  // Le jeu est composé de 10 nombres allant de 1 à BOUND compris.
  public static final int BOUND = 5;

  public static void main(String[] args) {
    Random rand = new Random();

    int[] t = new int[10];
    for (int i = 0; i != 10; i++) {
      t[i] = rand.nextInt(BOUND) + 1;
    }

    State state = new State(t);
    Tree tree = new Tree(state);
    int nbr = tree.nbrNode();
    System.out.println(nbr);
  }
}
