
/**
 * The `WindSpeed` class extends the `ClimateMeasurement` class and represents wind speed data.
 * It includes measurements in meters per second (m/s) and kilometers per hour (km/h).
 */
public class WindSpeed extends ClimateMeasurement {

	// Instance variables
	private double metersPerSecond;	// Wind speed measurement in meters per second
	private double kmPerHour; 		// Wind speed measurement in kilometers per hour

	/**
     * The minimum allowed wind speed in meters per second.
     */
	public static final double MIN_WIND_SPEED = 0.0;

	/**
     * The maximum allowed wind speed in meters per second.
     */
	public static final double MAX_WIND_SPEED = 113.2;

	/**
     * Constructs a `WindSpeed` object with the specified year, month, and wind speed in meters per second.
     *
     * @param year            The year of the wind speed measurement.
     * @param month           The month of the wind speed measurement.
     * @param metersPerSecond The wind speed measurement in meters per second.
     */
	public WindSpeed(int year, String month, double metersPerSecond) {
		super(year, month);
		this.metersPerSecond = metersPerSecond;
		this.kmPerHour = metersPerSecondTokmPerHour(this.metersPerSecond);
	}

	/**
     * Copy constructor for the `WindSpeed` class.
     * Creates a copy of another `WindSpeed` object by copying its year, month, and wind speed measurements.
     *
     * @param other The `WindSpeed` object to be copied.
     */
	public WindSpeed(WindSpeed other) {
		super(other.getYear(), other.getMonth());
		this.metersPerSecond = other.metersPerSecond;
		this.kmPerHour = other.kmPerHour;
	}

	 /**
     * Gets the wind speed measurement in meters per second.
     *
     * @return The wind speed measurement in meters per second.
     */
	public double getMetersPerSecond() {
		return metersPerSecond;
	}

	/**
     * Gets the wind speed measurement in kilometers per hour.
     *
     * @return The wind speed measurement in kilometers per hour.
     */
	public double getKmPerHour() {
		return kmPerHour;
	}

	/**
     * Converts wind speed from meters per second to kilometers per hour.
     *
     * @param metersPerSecond The wind speed measurement in meters per second.
     * @return The converted wind speed measurement in kilometers per hour.
     */
	public static double metersPerSecondTokmPerHour(double metersPerSecond) {
		return metersPerSecond * 3.6;
	}

	/**
     * Overrides the equals method to compare two `WindSpeed` objects for equality.
     *
     * @param otherObject The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
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

	/**
     * Generates a string representation of the `WindSpeed` object.
     *
     * @return The string representation of the `WindSpeed` object.
     */
	@Override
	public String toString() {
		return "Wind Speed: " + String.format("%.1f", metersPerSecond) + " m/s, " + String.format("%.1f", kmPerHour)
				+ " km/h";
	}

}
