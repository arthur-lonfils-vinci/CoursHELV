public abstract class Cafe implements Boisson { 
  private String nom;
  public Cafe(String nom) { 
    this.nom = nom;
  }
  public String getNom() { 
    return nom;
  }
  public abstract double prix();
  public String toString() {
    return "Café " + getNom();
  }
}
