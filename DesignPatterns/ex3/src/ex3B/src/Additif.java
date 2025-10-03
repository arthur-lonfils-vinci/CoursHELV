public abstract class Additif implements Boisson { 
  private Boisson boisson;
  public Additif(Boisson boisson) { 
    if (boisson == null) throw new IllegalArgumentException(); 
    this.boisson = boisson;
  }
  public String getNom() {
    return boisson.getNom();
  }
  public double prix() {
    return boisson.prix();
  }
  public String toString() {
    return boisson.toString();
  }
}
