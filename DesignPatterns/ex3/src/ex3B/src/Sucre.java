public class Sucre extends Additif {
  public Sucre(Boisson boisson) { 
    super(boisson);
  }
  public String toString() {
    return super.toString() + " sucré";
  }
  public double prix() {
    return 0.05 + super.prix();
  }
}
