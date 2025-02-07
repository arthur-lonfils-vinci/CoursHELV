import java.time.LocalDateTime;

public class PlageHoraire {
	private final LocalDateTime debut;
	private final LocalDateTime fin;
	private int nbParticipantPresent;
	public PlageHoraire(LocalDateTime debut, LocalDateTime fin) {
		super();
		this.debut = debut;
		this.fin = fin;
		this.nbParticipantPresent=0;
	}
	public int getNbParticipantPresent() {
		return nbParticipantPresent;
	}
	public void setNbParticipantPresent(int nbParticipantPresent) {
		this.nbParticipantPresent = nbParticipantPresent;
	}
	@Override
	public String toString() {
		return "PlageHoraire [debut=" + debut + ", fin=" + fin
				+ ", nbParticipantPresent=" + nbParticipantPresent + "]";
	}
	
	
}
