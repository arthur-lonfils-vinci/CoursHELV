import java.util.Scanner;

// Cette classe représente un joueur manuel.
// Pour chaque coup à jouer, elle  interroge ce joueur à travers un terminal.  
public class ManualPlayer extends SimpleSpectator implements Player {

  private Scanner scanner = new Scanner(System.in);

  @Override
  public boolean nextPlay() {
    System.out.println("g ou d ?");
    char ans = scanner.next().charAt(0);
    return ans == 'g';
  }
}
