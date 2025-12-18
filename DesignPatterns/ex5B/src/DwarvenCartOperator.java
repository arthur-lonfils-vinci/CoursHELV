public class DwarvenCartOperator extends DwarvenMineWorker {

  @Override
  public void work() {
    System.out.println(name()+" enleve du minerai d'or de la mine.");
  }

  @Override
  public String name() {
    return "Operateur de chariot nain";
  }
}
