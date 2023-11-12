import java.util.ArrayList;

public class ClimateRecord {

	private ArrayList<Country> countries;
	private ArrayList<City> cities;

	public ClimateRecord() {

	}

	// ZERO DIVISION ERROR MONTHS
	// private or public?
	// getAverageTemperatureOfCountry and getAverageTemperatureOfCity tek bir
	// methodda birleştirilebilir mi?
	// Query 1
	public double getAverageTemperatureOfCountry(String countryName, int year) {

		int indexOfCountry = getIndexOfCountry(countryName);

		Country country = countries.get(indexOfCountry);
		ArrayList<Temperature> temperatures = country.getTemperatures();

		return calcAverageTemperatureOfLocation(temperatures, year);
	}

	private int getIndexOfCountry(String countryName) {

		for (int i = 0; i < countries.size(); i++) {
			if (countryName.equals(countries.get(i).getName()))
				return i;
		}
		return -1;
	}

	// Query 2
	public double getAverageTemperatureOfCity(String cityName, int year) {

		int indexOfCity = getIndexOfCity(cityName);

		City city = cities.get(indexOfCity);
		ArrayList<Temperature> temperatures = city.getTemperatures();

		return calcAverageTemperatureOfLocation(temperatures, year);
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

	// Query 3
	public double getAverageWindSpeedOfCity(String cityName, int year) {

		int indexOfCity = getIndexOfCity(cityName);

		City city = cities.get(indexOfCity);
		ArrayList<WindSpeed> windSpeeds = city.getWindSpeeds();

		return calcAverageWindSpeedOfLocation(windSpeeds, year);
	}

	private double calcAverageWindSpeedOfLocation(ArrayList<WindSpeed> windSpeeds, int year) {
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

	// Query 4
	// PDF'deki instructionı tam anlamadım.
	public ArrayList<Double> calcAverageHumidityOfCity(String cityName) {

		int indexOfCity = getIndexOfCity(cityName);

		City city = cities.get(indexOfCity);
		ArrayList<Humidity> humidities = city.getHumidities();

		ArrayList<Double> averageHumidities = new ArrayList<>();
		for (int i = 0; i < FileIO.MAX_YEAR - FileIO.MIN_YEAR + 1; i++) {

			double total = 0;
			// getHumidityPercentage() or getHumidity() ???
			for (int month = FileIO.FIRST_MONTH - 1; month < FileIO.LAST_MONTH; month++)
				total += humidities.get(month).getHumidity();
			
			averageHumidities.add(total / (FileIO.LAST_MONTH - FileIO.FIRST_MONTH + 1));
		}
		return averageHumidities;
	}

	// Query 5
	public int getRadiationIntensityForCity(String cityName,
			RadiationAbsorbtion.RadiationIntensity intensity, int year) {

		int indexOfCity = getIndexOfCity(cityName);
		City city = cities.get(indexOfCity);

		ArrayList<RadiationAbsorbtion> radiationAbsorbtions = city.getRadiationAbsorbtions();

		int count = 0;
		for (RadiationAbsorbtion radiationAbsorbtion : radiationAbsorbtions) {
			if (radiationAbsorbtion.getRadiationIntensity() == intensity &&
				radiationAbsorbtion.getYear() == year)
				count++;
		}
		return count;
	}
	
	// Query 6
	public double getFeltTemperatureOfCity(String cityName, int year, String month) {
		
		int indexOfCity = getIndexOfCity(cityName);
		City city = cities.get(indexOfCity);
		
		ArrayList<Temperature> feltTemperatures = city.getFeltTemperatures();
		
		Temperature feltTemperature = new Temperature();
		
		for (Temperature temperature : feltTemperatures) {
			if (temperature.getYear() == year &&
				temperature.getMonth().equals(month)) {
				feltTemperature = new Temperature(temperature);
				break;
			}
		}
		return feltTemperature.getCelciusMeasurement();
	}

	private int getIndexOfCity(String cityName) {

		for (int i = 0; i < cities.size(); i++) {
			if (cityName.equals(countries.get(i).getName()))
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
