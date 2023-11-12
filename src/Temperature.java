
public class Temperature extends ClimateMeasurement {

	private double celciusMeasurement;
	private double fahrenheitMeasurement;
	private double kelvinMeasurement;

	public static final double ABSOLUTE_ZERO_CELCIUS = -273.15;
	public static final double MIN_CELCIUS = -40.0;
	public static final double MAX_CELCIUS = 50.0;
	
	public Temperature(int year, String month, double temperature) {
		super(year, month);
		this.celciusMeasurement = temperature;
		this.fahrenheitMeasurement = convertCtoF(celciusMeasurement);
		this.kelvinMeasurement = convertCtoK(celciusMeasurement);
	}

	public Temperature(Temperature other) {
		super(other.getYear(), other.getMonth());
		this.celciusMeasurement = other.celciusMeasurement;
		this.fahrenheitMeasurement = other.fahrenheitMeasurement;
		this.kelvinMeasurement = other.kelvinMeasurement;
	}

	public Temperature calcFeltTemperature(Humidity humidity, WindSpeed windSpeed,
			RadiationAbsorbtion radiationAbsorbtion) {

		double feltTemperature = celciusMeasurement + 0.3 * humidity.getHumidityPercentage() / 100
				- 0.7 * (radiationAbsorbtion.getRadiationAbsorbtion() / (windSpeed.getMetersPerSecond() + 10));

		return new Temperature(getYear(), getMonth(), feltTemperature);
	}

//	public Temperature(double degree, String measurementType) {
//
//	}
	
	public double getCelciusMeasurement() {
		return celciusMeasurement;
	}
	
	public double getFahrenheitMeasurement() {
		return fahrenheitMeasurement;
	}
	
	public double getKelvinMeasurement() {
		return kelvinMeasurement;
	}

	public static double convertCtoK(double celcius) {
		return celcius + 273.15;

	}

	public static double convertCtoF(double celcius) {
		return celcius * 9 / 5 + 32;

	}

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

	@Override
	public String toString() {
		return "Celcius Measurement: " + String.format("%.1f", celciusMeasurement) + " °C\n"
				+ "Fahrenheit Measurement: " + String.format("%.1f", fahrenheitMeasurement) + " °F\n"
				+ "Kelvin Measurement: " + String.format("%.1f", kelvinMeasurement) + " K";
	}

}
