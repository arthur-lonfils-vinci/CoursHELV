package Q3;

import java.util.List;

public class DeplacementDisque implements Deplacement {
	private int source, destination;

	public DeplacementDisque(int source, int destination) {
		this.source = source;
		this.destination = destination;
	}

	public void stocker(List<DeplacementDisque> deplacements) {
		deplacements.add(this);
	}

	public int getDestination() {
		return destination;
	}

	public int getSource() {
		return source;
	}
}
