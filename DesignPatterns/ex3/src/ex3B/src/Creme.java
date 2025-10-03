public class Creme extends Additif { 
  public Creme(Boisson boisson) { 
    super(boisson);
  }
  public double prix() {
    return 0.15 + super.prix();
  }
  public String toString() {
    return super.toString() + " crème";
  }
 }

