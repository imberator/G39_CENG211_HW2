import java.util.ArrayList;

public class ClimateRecord {

	public static final int CELCIUS = 1;
	public static final int FAHRENHEIT = 2;
	public static final int KELVIN = 3;

	public static final int METERS_PER_SECOND = 1;
	public static final int KM_PER_HOUR = 2;

	private ArrayList<Country> countries;
	private ArrayList<City> cities;
	private int[] years;

	public ClimateRecord() {
		FileIO fileIO = new FileIO();
		cities = fileIO.getCities();
		countries = fileIO.getCountries();
		initializeYears();
	}

	private void initializeYears() {
		years = new int[FileIO.MAX_YEAR - FileIO.MIN_YEAR + 1];
		for (int i = 0; i < years.length; i++)
			years[i] = FileIO.MIN_YEAR + i;
	}
	
	public int[] getYears() {
		int[] temp = new int[years.length];
		for (int i = 0; i < years.length; i++)
			temp[i] = years[i];
		return temp;
	}
	
	public String[] getTemperatureMeasurementNames() {
		String[] temperatureMeasurementNames = {"Celcius", "Fahrenheit", "Kelvin"};
		return temperatureMeasurementNames;
	}

	// ZERO DIVISION ERROR MONTHS
	// private or public?
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

	public double getAverageWindSpeedOfCity(String cityName, int yearOption, int measurementUnit) {

		int indexOfCity = getIndexOfCity(cityName);

		City city = cities.get(indexOfCity);
		ArrayList<WindSpeed> windSpeeds = city.getWindSpeeds();

		double windSpeedMetersPerSecond = calcAverageWindSpeedOfCity(windSpeeds, years[yearOption - 1]);
		if (measurementUnit == METERS_PER_SECOND)
			return windSpeedMetersPerSecond;
		else
			return WindSpeed.metersPerSecondTokmPerHour(windSpeedMetersPerSecond);
	}

	private double calcAverageWindSpeedOfCity(ArrayList<WindSpeed> windSpeeds, int year) {
		int numberOfMonths = 0;
		double total = 0;
		for (WindSpeed windSpeed : windSpeeds) {
			if (windSpeed.getYear() == year) {
				total += windSpeed.getMetersPerSecond();
				numberOfMonths++;
			}
		}
		return total / numberOfMonths;
	}

	public ArrayList<Double> calcAverageHumidityOfCity(String cityName) {

		int indexOfCity = getIndexOfCity(cityName);

		City city = cities.get(indexOfCity);
		ArrayList<Humidity> humidities = city.getHumidities();

		ArrayList<Double> averageHumidities = new ArrayList<>();
		for (int i = 0; i < FileIO.MAX_YEAR - FileIO.MIN_YEAR + 1; i++) {

			double total = 0;
			for (int month = i * (FileIO.LAST_MONTH); month < (i + 1) * FileIO.LAST_MONTH; month++)
				total += humidities.get(month).getHumidityPercentage();

			averageHumidities.add(total / (FileIO.LAST_MONTH - FileIO.FIRST_MONTH + 1));
		}
		return averageHumidities;
	}

	public int getRadiationIntensityForCity(String cityName, int yearOption, int intensityOption) {

		int indexOfCity = getIndexOfCity(cityName);

		City city = cities.get(indexOfCity);
		ArrayList<RadiationAbsorbtion> radiationAbsorbtions = city.getRadiationAbsorbtions();

		int year = years[yearOption - 1];
		RadiationAbsorbtion.RadiationIntensity intensity = RadiationAbsorbtion.RadiationIntensity
				.values()[intensityOption - 1];
		int count = 0;
		for (RadiationAbsorbtion radiationAbsorbtion : radiationAbsorbtions) {
			if (intensity == radiationAbsorbtion.getRadiationIntensity() && year == radiationAbsorbtion.getYear())
				count++;
		}
		return count;
	}

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

//	private boolean isCountryExist(String countryName) {
//		for (Country country : countries) {
//			if (country.getName().equals(countryName))
//				return true;
//		}
//		return false;
//	}

}
