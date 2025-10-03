public class Lait extends Additif {
  public Lait(Boisson boisson) { 
    super(boisson);
  }
  public double prix() {
    return 0.10 + super.prix();
  }
  public String toString() {
    return super.toString() + " au lait";
  }
}
