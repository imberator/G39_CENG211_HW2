package business_layer;
/**
 * Represents a temperature measurement, extending the ClimateMeasurement class.
 */
public class Temperature extends ClimateMeasurement {

	// Instance variables
	private double celciusMeasurement; // Temperature in degrees Celsius
	private double fahrenheitMeasurement; // Temperature in degrees Fahrenheit
	private double kelvinMeasurement; // Temperature in Kelvin

	/**
	 * Absolute zero temperature in Celsius.
	 */
	public static final double ABSOLUTE_ZERO_CELCIUS = -273.15;
	/**
	 * Minimum Celsius temperature allowed.
	 */
	public static final double MIN_CELCIUS = -40.0;
	/**
	 * Maximum Celsius temperature allowed.
	 */
	public static final double MAX_CELCIUS = 50.0;

	/**
	 * Constructs a Temperature object with the specified year, month, and Celsius
	 * temperature.
	 *
	 * @param year        The year of the temperature measurement.
	 * @param month       The month of the temperature measurement.
	 * @param temperature The Celsius temperature measurement.
	 */
	public Temperature(int year, String month, double temperature) {
		super(year, month);
		this.celciusMeasurement = temperature;
		this.fahrenheitMeasurement = convertCtoF(celciusMeasurement);
		this.kelvinMeasurement = convertCtoK(celciusMeasurement);
	}

	/**
	 * Constructs a Temperature object as a copy of another Temperature object.
	 *
	 * @param other The Temperature object to be copied.
	 */
	public Temperature(Temperature other) {
		super(other.getYear(), other.getMonth());
		this.celciusMeasurement = other.celciusMeasurement;
		this.fahrenheitMeasurement = other.fahrenheitMeasurement;
		this.kelvinMeasurement = other.kelvinMeasurement;
	}

	/**
	 * Calculates the felt temperature based on humidity, wind speed, and radiation
	 * absorption.
	 *
	 * @param humidity            The humidity object.
	 * @param windSpeed           The wind speed object.
	 * @param radiationAbsorbtion The radiation absorption object.
	 * @return A new Temperature object representing the felt temperature.
	 */
	public Temperature calcFeltTemperature(Humidity humidity, WindSpeed windSpeed,
			RadiationAbsorbtion radiationAbsorbtion) {

		double feltTemperature = celciusMeasurement + 0.3 * (humidity.getHumidity())
				- 0.7 * (radiationAbsorbtion.getRadiationAbsorbtion() / (windSpeed.getMetersPerSecond() + 10));

		return new Temperature(getYear(), getMonth(), feltTemperature);
	}

	/**
	 * Gets the Celsius temperature measurement.
	 *
	 * @return The Celsius temperature measurement.
	 */
	public double getCelciusMeasurement() {
		return celciusMeasurement;
	}

	/**
	 * Gets the Fahrenheit temperature measurement.
	 *
	 * @return The Fahrenheit temperature measurement.
	 */
	public double getFahrenheitMeasurement() {
		return fahrenheitMeasurement;
	}

	/**
	 * Gets the Kelvin temperature measurement.
	 *
	 * @return The Kelvin temperature measurement.
	 */
	public double getKelvinMeasurement() {
		return kelvinMeasurement;
	}

	/**
	 * Converts Celsius temperature to Kelvin.
	 *
	 * @param celcius The temperature in Celsius.
	 * @return The equivalent temperature in Kelvin.
	 */
	public static double convertCtoK(double celcius) {
		return celcius + 273.15;

	}

	/**
	 * Converts Celsius temperature to Fahrenheit.
	 *
	 * @param celcius The temperature in Celsius.
	 * @return The equivalent temperature in Fahrenheit.
	 */
	public static double convertCtoF(double celcius) {
		return celcius * 9 / 5 + 32;

	}

	/**
	 * Checks if two Temperature objects are equal.
	 *
	 * @param otherObject The object to compare.
	 * @return True if the objects are equal, false otherwise.
	 */
	@Override
	public boolean equals(Object otherObject) {
		if (otherObject == null)
			return false;
		else if (getClass() != otherObject.getClass())
			return false;

		else {
			Temperature otherTemperature = (Temperature) otherObject;
			return (getYear() == otherTemperature.getYear() && getMonth() == otherTemperature.getMonth()
					&& celciusMeasurement == otherTemperature.celciusMeasurement
					&& fahrenheitMeasurement == otherTemperature.fahrenheitMeasurement
					&& kelvinMeasurement == otherTemperature.kelvinMeasurement);
		}

	}

	/**
	 * Returns a string representation of the Temperature object.
	 *
	 * @return The string representation of the Temperature object.
	 */
	@Override
	public String toString() {
		return "Celcius Measurement: " + String.format("%.1f", celciusMeasurement) + " °C\n"
				+ "Fahrenheit Measurement: " + String.format("%.1f", fahrenheitMeasurement) + " °F\n"
				+ "Kelvin Measurement: " + String.format("%.1f", kelvinMeasurement) + " K";
	}

}
