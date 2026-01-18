package Q3;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Execution {
	private Hanoi hanoi;
	private DeplacementDisque deplacementEnCours = null;
	private Iterator<DeplacementDisque> it;
	private Set<Vue> vues = new HashSet<Vue>();

	public Execution(Hanoi hanoi) {
		this.hanoi = hanoi;
		this.it = hanoi.iterator();
	}

	public boolean hasNext() {
		return it.hasNext();
	}

	private DeplacementDisque next() {
		return deplacementEnCours = it.next();
	}

	public void addVue(Vue vue) {
		this.vues.add(vue);
	}

	public void removeVue(Vue vue) {
		this.vues.remove(vue);
	}

	public void notifyVues() {
		for (Vue vue : vues)
			vue.update(deplacementEnCours);
	}

	public int getTaille() {
		return hanoi.getTaille();
	}

	public void execute() {
		next();
		notifyVues();
	}
}
