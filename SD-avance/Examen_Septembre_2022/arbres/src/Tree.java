import java.util.*;

public class Tree implements Iterable<Tree> {

  private int value;

  private Tree parent;

  private Tree[] children;

  // *******************************************************
  // CONSTRUCTEURS
  // *******************************************************
  public Tree(int v, Tree[] chd) {
    value = v;
    children = chd;

    for (Tree child : chd) {
      child.parent = this;
    }
  }

  public Tree(int v) {
    this(v, new Tree[0]);
  }

  // *******************************************************
  // GETTERS
  // *******************************************************
  public int getValue() {
    return value;
  }

  public Tree getParent() {
    return parent;
  }

  public Tree[] getChildren() {
    return children;
  }

  // *******************************************************
  // ITERATEUR
  // *******************************************************
  public Iterator<Tree> iterator() {
    return Arrays.asList(children).iterator();
  }

  public int nbrChildren() {
    return children.length;
  }

  public boolean isLeaf() {
    return children.length == 0;
  }

  // Méthode clone() corrigée - crée une copie profonde de l'arbre
  public Tree clone() {
    // Créer un tableau pour les enfants clonés
    Tree[] clonedChildren = new Tree[children.length];

    // Cloner récursivement chaque enfant
    for (int i = 0; i < children.length; i++) {
      clonedChildren[i] = children[i].clone();
    }

    // Créer une nouvelle instance avec les enfants clonés
    return new Tree(this.value, clonedChildren);
  }

  // Méthode corrigée pour afficher tous les nœuds avec leurs ancêtres
  public void afficherNoeudsAvecAncetres() {
    afficherNoeudsAvecAncetresRecursif(this);
  }

  private void afficherNoeudsAvecAncetresRecursif(Tree node) {
    // Afficher le nœud courant suivi de ses ancêtres
    System.out.print(node.getValue());

    // Remonter jusqu'à la racine en affichant les ancêtres
    Tree current = node.getParent();
    while (current != null) {
      System.out.print(" " + current.getValue());
      current = current.getParent();
    }
    System.out.println(); // Nouvelle ligne après chaque nœud

    // Appel récursif pour tous les enfants
    for (Tree child : node) {
      afficherNoeudsAvecAncetresRecursif(child);
    }
  }


  public static void main(String[] args) {
    Tree l6 = new Tree(6);
    Tree l1 = new Tree(1);
    Tree t9 = new Tree(9, new Tree[]{l6, l1});
    Tree l3 = new Tree(3);
    Tree l8 = new Tree(8);
    Tree t8 = new Tree(8, new Tree[]{l3, l8});
    Tree l4 = new Tree(4);
    Tree t4 = new Tree(4, new Tree[]{t8, t9, l4});
    Tree t4bis = t4.clone();
    TreeDisplay.displayTree(t4);
    t4.afficherNoeudsAvecAncetres();
    System.out.println("-----------");
    TreeDisplay.displayTree(t4bis);
    t4bis.afficherNoeudsAvecAncetres();
  }
}

