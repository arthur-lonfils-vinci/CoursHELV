

public class BattleFishingBoat implements BattleShip {

  private FishingBoat boat;

  public BattleFishingBoat() {
    boat = new FishingBoat();
  }

  @Override
  public void fire() {
    boat.fish();
  }

  @Override
  public void move(Distance distance) {
    boat.sail(distance);
  }
}
