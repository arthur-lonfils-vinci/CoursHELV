package B;

public class Sucre extends Additif {
  public Sucre(Boisson boisson) { 
    super(boisson);
  }
  public String toString() {
    return super.toString() + " sucr�";
  }
  public double prix() {
    return 0.05 + super.prix();
  }
}
