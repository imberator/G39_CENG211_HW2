/**
 * The `Humidity` class extends the `ClimateMeasurement` class and represents
 * humidity data. It includes information about humidity percentage and humidity
 * value.
 */
public class Humidity extends ClimateMeasurement {

	/**
	 * The minimum allowed humidity percentage.
	 */
	public static final double MIN_HUMIDITY_PERCENTAGE = 0.0;

	/**
	 * The maximum allowed humidity percentage.
	 */
	public static final double MAX_HUMIDITY_PERCENTAGE = 100.0;

	// Instance variables
	private double humidityPercentage; // Humidity percentage value
	private double humidity; // Calculated humidity value

	/**
	 * Constructs a `Humidity` object with the specified year, month, and humidity
	 * percentage.
	 *
	 * @param year               The year of the humidity measurement.
	 * @param month              The month of the humidity measurement.
	 * @param humidityPercentage The humidity percentage value.
	 */
	public Humidity(int year, String month, double humidityPercentage) {
		super(year, month);
		this.humidityPercentage = humidityPercentage;
		this.humidity = humidityPercentage / 100;
	}

	/**
	 * Copy constructor for the `Humidity` class. Creates a copy of another
	 * `Humidity` object by copying its year, month, and humidity percentage.
	 *
	 * @param other The `Humidity` object to be copied.
	 */
	public Humidity(Humidity other) {
		super(other.getYear(), other.getMonth());
		this.humidityPercentage = other.getHumidityPercentage();
	}

	/**
	 * Gets the humidity percentage value.
	 *
	 * @return The humidity percentage value.
	 */
	public double getHumidityPercentage() {
		return humidityPercentage;
	}

	/**
	 * Gets the calculated humidity value.
	 *
	 * @return The calculated humidity value.
	 */
	public double getHumidity() {
		return humidity;
	}

	/**
	 * Overrides the equals method to compare two `Humidity` objects for equality.
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
			Humidity otherHumidity = (Humidity) otherObject;
			return (getYear() == otherHumidity.getYear() && getMonth() == otherHumidity.getMonth()
					&& humidityPercentage == otherHumidity.humidityPercentage);
		}
	}

	/**
	 * Generates a string representation of the `Humidity` object.
	 *
	 * @return The string representation of the `Humidity` object.
	 */
	@Override
	public String toString() {
		return "Humidity Percentage: " + String.format("%.1f", humidityPercentage);
	}
}
