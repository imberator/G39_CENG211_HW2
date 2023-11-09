
public class WindSpeed extends ClimateMeasurement {

	private double metersPerSecond;
	private double kmPerHour;

	public static final double MIN_WIND_SPEED = 0.0;
	public static final double MAX_WIND_SPEED = 113.2;

	public WindSpeed(int year, String month, double metersPerSecond) {
		super(year, month);
		this.metersPerSecond = metersPerSecond;
		this.kmPerHour = metersPerSecondTokmPerHour();
	}

	public WindSpeed(WindSpeed other) {
		super(other.getYear(), other.getMonth());
		this.metersPerSecond = other.metersPerSecond;
		this.kmPerHour = other.kmPerHour;
	}

	public double getMetersPerSecond() {
		return metersPerSecond;
	}

	public double getKmPerHour() {
		return kmPerHour;
	}

	private double metersPerSecondTokmPerHour() {
		return metersPerSecond * 3.6;
	}

	@Override
	public boolean equals(Object otherObject) {
		if (otherObject == null)
			return false;
		else if (getClass() != otherObject.getClass())
			return false;

		else {
			WindSpeed otherWindSpeed = (WindSpeed) otherObject;
			return (getYear() == otherWindSpeed.getYear() && getMonth() == otherWindSpeed.getMonth()
					&& metersPerSecond == otherWindSpeed.metersPerSecond && kmPerHour == otherWindSpeed.kmPerHour);
		}
	}

	@Override
	public String toString() {
		return "Wind Speed: " + String.format("%.1f", metersPerSecond) + " m/s, " + String.format("%.1f", kmPerHour)
				+ " km/h";
	}

}
