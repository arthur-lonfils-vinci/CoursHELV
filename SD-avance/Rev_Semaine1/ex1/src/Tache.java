import java.time.LocalDate;

public class Tache {
	private LocalDate date;
	private String descriptif;
	private int priorite;

	public Tache(String descriptif, int priorite) {
		super();
		this.date = LocalDate.now();
		this.descriptif = descriptif;
		this.priorite = priorite;
	}

	public LocalDate getDate() {
		return date;
	}

	public String getDescriptif() {
		return descriptif;
	}

	public int getPriorite() {
		return priorite;
	}
}
