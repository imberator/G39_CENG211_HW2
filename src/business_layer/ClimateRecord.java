package business_layer;
import java.util.ArrayList;

import data_access_layer.FileIO;

/**
 * The ClimateRecord class represents a collection of climate data for countries
 * and cities. It provides methods to calculate and retrieve various
 * climate-related information.
 */
public class ClimateRecord {

	/**
	 * Constant representing the Celsius temperature unit.
	 */
	public static final int CELCIUS = 1;

	/**
	 * Constant representing the Fahrenheit temperature unit.
	 */
	public static final int FAHRENHEIT = 2;

	/**
	 * Constant representing the Kelvin temperature unit.
	 */
	public static final int KELVIN = 3;

	/**
	 * Constant representing the speed unit in meters per second.
	 */
	public static final int METERS_PER_SECOND = 1;

	/**
	 * Constant representing the speed unit in kilometers per hour.
	 */
	public static final int KM_PER_HOUR = 2;

	private ArrayList<Country> countries;
	private ArrayList<City> cities;
	private Integer[] years;

	/**
	 * Constructs a ClimateRecord object and initializes data from a FileIO
	 * instance.
	 */
	public ClimateRecord() {
		FileIO fileIO = new FileIO();
		cities = fileIO.getCities();
		countries = fileIO.getCountries();
		initializeYears();
	}

	private void initializeYears() {
		years = new Integer[FileIO.MAX_YEAR - FileIO.MIN_YEAR + 1];
		for (int i = 0; i < years.length; i++)
			years[i] = FileIO.MIN_YEAR + i;
	}

	/**
	 * Retrieves the average temperature of a specific country for a given year.
	 *
	 * @param countryName     The name of the country.
	 * @param yearOption      The index representing the selected year.
	 * @param measurementUnit The unit of measurement for temperature (CELCIUS,
	 *                        FAHRENHEIT, KELVIN).
	 * @return The average temperature of the country in the specified measurement
	 *         unit.
	 */
	public double getAverageTemperatureOfCountry(String countryName, int yearOption, int measurementUnit) {

		int indexOfCountry = getIndexOfCountry(countryName);

		Country country = countries.get(indexOfCountry);
		ArrayList<Temperature> temperatures = country.getTemperatures();

		double celcius = calcAverageTemperatureOfLocation(temperatures, years[yearOption - 1]);
		if (measurementUnit == CELCIUS)
			return celcius;
		else if (measurementUnit == FAHRENHEIT)
			return Temperature.convertCtoF(celcius);
		else
			return Temperature.convertCtoK(celcius);
	}

	private int getIndexOfCountry(String countryName) {

		for (int i = 0; i < countries.size(); i++) {
			if (countryName.equalsIgnoreCase(countries.get(i).getName()))
				return i;
		}
		return -1;
	}

	/**
	 * Retrieves the average temperature of a specific city for a given year.
	 *
	 * @param cityName        The name of the city.
	 * @param yearOption      The index representing the selected year.
	 * @param measurementUnit The unit of measurement for temperature (CELCIUS,
	 *                        FAHRENHEIT, KELVIN).
	 * @return The average temperature of the city in the specified measurement
	 *         unit.
	 */
	public double getAverageTemperatureOfCity(String cityName, int yearOption, int measurementUnit) {

		int indexOfCity = getIndexOfCity(cityName);

		City city = cities.get(indexOfCity);
		ArrayList<Temperature> temperatures = city.getTemperatures();

		double celcius = calcAverageTemperatureOfLocation(temperatures, years[yearOption - 1]);
		if (measurementUnit == CELCIUS)
			return celcius;
		else if (measurementUnit == FAHRENHEIT)
			return Temperature.convertCtoF(celcius);
		else
			return Temperature.convertCtoK(celcius);

	}

	private double calcAverageTemperatureOfLocation(ArrayList<Temperature> temperatures, int year) {
		int numberOfMonths = 0;
		double total = 0;
		for (Temperature temperature : temperatures) {
			if (temperature.getYear() == year) {
				total += temperature.getCelciusMeasurement();
				numberOfMonths++;
			}
		}
		return total / numberOfMonths;
	}

	/**
	 * Retrieves the average wind speed of a specific city for a given month.
	 *
	 * @param cityName        The name of the city.
	 * @param month           The name of the month.
	 * @param measurementUnit The unit of measurement for wind speed
	 *                        (METERS_PER_SECOND, KM_PER_HOUR).
	 * @return The average wind speed of the city in the specified measurement unit.
	 */
	public double getAverageWindSpeedOfCity(String cityName, String month, int measurementUnit) {

		int indexOfCity = getIndexOfCity(cityName);

		City city = cities.get(indexOfCity);
		ArrayList<WindSpeed> windSpeeds = city.getWindSpeeds();

		double windSpeedMetersPerSecond = calcAverageWindSpeedOfCity(windSpeeds, month);
		if (measurementUnit == METERS_PER_SECOND)
			return windSpeedMetersPerSecond;
		else
			return WindSpeed.metersPerSecondTokmPerHour(windSpeedMetersPerSecond);
	}

	private double calcAverageWindSpeedOfCity(ArrayList<WindSpeed> windSpeeds, String month) {

		int numberOfYears = 0;
		double total = 0;
		for (WindSpeed windSpeed : windSpeeds) {
			if (windSpeed.getMonth().equalsIgnoreCase(month)) {
				numberOfYears++;
				total += windSpeed.getMetersPerSecond();
			}
		}
		return total / numberOfYears;
	}

	/**
	 * Retrieves the average humidity of a specific city.
	 *
	 * @param cityName The name of the city.
	 * @return The average humidity of the city.
	 */
	public double getAverageHumidityOfCity(String cityName) {

		int indexOfCity = getIndexOfCity(cityName);

		City city = cities.get(indexOfCity);
		ArrayList<Humidity> humidities = city.getHumidities();

		double total = 0;
		for (Humidity humidity : humidities)
			total += humidity.getHumidityPercentage();

		return total / humidities.size();
	}

	/**
	 * Retrieves the radiation intensity for a specific city, year, and radiation
	 * intensity level.
	 *
	 * @param cityName   The name of the city.
	 * @param yearOption The index representing the selected year.
	 * @param intensity  The radiation intensity level.
	 * @return The count of radiation events with the specified intensity for the
	 *         given city and year.
	 */
	public int getRadiationIntensityForCity(String cityName, int yearOption,
			RadiationAbsorbtion.RadiationIntensity intensity) {

		int indexOfCity = getIndexOfCity(cityName);

		City city = cities.get(indexOfCity);
		ArrayList<RadiationAbsorbtion> radiationAbsorbtions = city.getRadiationAbsorbtions();

		int year = years[yearOption - 1];
		int count = 0;
		for (RadiationAbsorbtion radiationAbsorbtion : radiationAbsorbtions) {
			if (intensity == radiationAbsorbtion.getRadiationIntensity() && year == radiationAbsorbtion.getYear())
				count++;
		}
		return count;
	}

	/**
	 * Retrieves the felt temperature of a specific city for a given year and month.
	 *
	 * @param cityName   The name of the city.
	 * @param yearOption The index representing the selected year.
	 * @param month      The name of the month.
	 * @return The felt temperature of the city for the specified year and month.
	 */
	public double getFeltTemperatureOfCity(String cityName, int yearOption, String month) {

		int indexOfCity = getIndexOfCity(cityName);

		City city = cities.get(indexOfCity);
		ArrayList<Temperature> feltTemperatures = city.getFeltTemperatures();

		int year = years[yearOption - 1];
		double feltTemperature = Temperature.ABSOLUTE_ZERO_CELCIUS;
		for (Temperature temperature : feltTemperatures) {
			if (temperature.getYear() == year && temperature.getMonth().equalsIgnoreCase(month)) {
				feltTemperature = temperature.getCelciusMeasurement();
				break;
			}
		}
		return feltTemperature;
	}

	private int getIndexOfCity(String cityName) {

		for (int i = 0; i < cities.size(); i++) {
			if (cityName.equalsIgnoreCase(cities.get(i).getName()))
				return i;
		}
		return -1;
	}

	/**
	 * Retrieves the array of years.
	 *
	 * @return An array containing the available years.
	 */
	public Integer[] getYears() {
		Integer[] temp = new Integer[years.length];
		for (int i = 0; i < years.length; i++)
			temp[i] = years[i];
		return temp;
	}

	/**
	 * Retrieves the names of temperature measurement units.
	 *
	 * @return An array containing the names of temperature measurement units.
	 */
	public String[] getTemperatureMeasurementNames() {
		String[] temperatureMeasurementNames = { "Celcius", "Fahrenheit", "Kelvin" };
		return temperatureMeasurementNames;
	}

	/**
	 * Retrieves the available speed measurement units.
	 *
	 * @return An array containing the names of speed measurement units.
	 */
	public String[] getSpeedUnits() {
		String[] velocityTimeNames = { "m/s", "km/h" };
		return velocityTimeNames;
	}

}
