
public class Humidity extends ClimateMeasurement {

	public static final double MIN_HUMIDITY_PERCENTAGE = 0.0;
	public static final double MAX_HUMIDITY_PERCENTAGE = 100.0;

	private double humidityPercentage;
	private double humidity;

	public Humidity(int year, String month, double humidityPercentage) {
		super(year, month);
		this.humidityPercentage = humidityPercentage;
		this.humidity = humidityPercentage / 100;
	}

	public Humidity(Humidity other) {
		super(other.getYear(), other.getMonth());
		this.humidityPercentage = other.getHumidityPercentage();
	}

	public double getHumidityPercentage() {
		return humidityPercentage;
	}
	
	public double getHumidity() {
		return humidity;
	}

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

	@Override
	public String toString() {
		return "Humidity Percentage: " + String.format("%.1f", humidityPercentage);
	}
}
