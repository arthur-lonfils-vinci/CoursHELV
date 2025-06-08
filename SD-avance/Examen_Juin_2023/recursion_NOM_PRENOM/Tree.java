import java.util.Arrays;
import java.util.Iterator;

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

	// Cette méthode renvoie vrai si tous les noeuds ont au maximum deux fils, faux sinon.
	public boolean estArbreBinaire() {
		return false;
	}
	

	public static void main(String[] args) {
		Tree l4 = new Tree(4);
		Tree l6 = new Tree(6);
		Tree l7 = new Tree(7);
		Tree l8 = new Tree(8);
		Tree l10 = new Tree(10);
		Tree t9 = new Tree(9, new Tree[] {l10});
		Tree t5 = new Tree(5, new Tree[] {l7,l8,t9});
		Tree t2 = new Tree(2, new Tree[] {l4});
		Tree t3 = new Tree(3, new Tree[] {t5,l6});
		Tree t1 = new Tree(1, new Tree[] {t2,t3});
		System.out.println(t1.estArbreBinaire());
		System.out.println("----------------");
		Tree l4b = new Tree(4);
		Tree l6b = new Tree(6);
		Tree l7b = new Tree(7);
		Tree l10b = new Tree(10);
		Tree t9b = new Tree(9, new Tree[] {l10b});
		Tree t5b = new Tree(5, new Tree[] {l7b,t9b});
		Tree t2b = new Tree(2, new Tree[] {l4b});
		Tree t3b = new Tree(3, new Tree[] {t5b,l6b});
		Tree t1b = new Tree(1, new Tree[] {t2b,t3b});
		System.out.println(t1b.estArbreBinaire());

	}
}

