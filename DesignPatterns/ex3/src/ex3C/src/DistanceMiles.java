public class DistanceMiles implements Distance {

	private int miles;
	
	public DistanceMiles(int miles) {
		this.miles=miles;
	}

	@Override
	public String getDistance() {
		return miles+" miles";
	}

}
