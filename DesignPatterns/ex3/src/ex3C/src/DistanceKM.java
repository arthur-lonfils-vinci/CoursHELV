public class DistanceKM implements Distance {

	private int km;
	
	public DistanceKM(int km) {
		this.km=km;
	}

	@Override
	public String getDistance() {
		return km+" kms";
	}

}
