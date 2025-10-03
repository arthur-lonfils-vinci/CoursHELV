public class Main {
  public static void main(String[] args) {
    Boisson b = new Lait(new Sucre(new Decafeine()));
    System.out.println(b + " au prix de " + b.prix() + " euros."); 
    b = new Sucre(new Creme(new Dessert()));
    System.out.println(b + " au prix de " + b.prix() + " euros.");
  }
}
