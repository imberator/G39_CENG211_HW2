import java.util.ArrayList;

/**
 * The City class represents a city's climate data, including temperature,
 * humidity, wind speed, radiation absorption, and felt temperature values.
 */
public class City {

	private String name;
	private ArrayList<Temperature> temperatureArrayList;
	private ArrayList<Humidity> humidityArrayList;
	private ArrayList<WindSpeed> windSpeedArrayList;
	private ArrayList<RadiationAbsorbtion> radiationAbsorbtionArrayList;
	private ArrayList<Temperature> feltTemperatureArrayList;

	/**
	 * Constructs a City object with the specified attributes.
	 *
	 * @param name                         The name of the city.
	 * @param temperatureArrayList         List of temperature data.
	 * @param humidityArrayList            List of humidity data.
	 * @param windSpeedArrayList           List of wind speed data.
	 * @param radiationAbsorbtionArrayList List of radiation absorption data.
	 */
	public City(String name, ArrayList<Temperature> temperatureArrayList, ArrayList<Humidity> humidityArrayList,
			ArrayList<WindSpeed> windSpeedArrayList, ArrayList<RadiationAbsorbtion> radiationAbsorbtionArrayList) {
		checkValidity(name, temperatureArrayList, humidityArrayList, windSpeedArrayList, radiationAbsorbtionArrayList);
		this.name = name;
		this.temperatureArrayList = new ArrayList<>();
		this.humidityArrayList = new ArrayList<>();
		this.windSpeedArrayList = new ArrayList<>();
		this.radiationAbsorbtionArrayList = new ArrayList<>();
		this.feltTemperatureArrayList = new ArrayList<>();
		initializeArrayLists(temperatureArrayList, humidityArrayList, windSpeedArrayList, radiationAbsorbtionArrayList);
	}

	/**
	 * Constructs a City object by copying another City object.
	 *
	 * @param otherCity The City object to be copied.
	 */
	public City(City otherCity) {
		if (otherCity == null) {
			System.out.println("Error! City cannot be found.");
			System.exit(0);
		}
		this.name = otherCity.name;
		this.temperatureArrayList = new ArrayList<>(otherCity.temperatureArrayList);
		this.humidityArrayList = new ArrayList<>(otherCity.humidityArrayList);
		this.windSpeedArrayList = new ArrayList<>(otherCity.windSpeedArrayList);
		this.radiationAbsorbtionArrayList = new ArrayList<>(otherCity.radiationAbsorbtionArrayList);
		this.feltTemperatureArrayList = new ArrayList<>(otherCity.feltTemperatureArrayList);
	}

	private void checkValidity(String name, ArrayList<Temperature> temperatureArrayList,
			ArrayList<Humidity> humidityArrayList, ArrayList<WindSpeed> windSpeedArrayList,
			ArrayList<RadiationAbsorbtion> radiationAbsorbtionArrayList) {

		if ((name == null) || (temperatureArrayList == null) || (humidityArrayList == null)
				|| (windSpeedArrayList == null) || (radiationAbsorbtionArrayList == null)) {
			System.out.println("Error! Improper attributes.");
			System.exit(0);
		}
		if (!(humidityArrayList.size() == temperatureArrayList.size()
				&& humidityArrayList.size() == windSpeedArrayList.size()
				&& humidityArrayList.size() == radiationAbsorbtionArrayList.size())) {
			System.out.println("Error! Climate data lengths do not match.");
			System.exit(0);
		}
	}

	private void initializeArrayLists(ArrayList<Temperature> temperatureArrayList,
			ArrayList<Humidity> humidityArrayList, ArrayList<WindSpeed> windSpeedArrayList,
			ArrayList<RadiationAbsorbtion> radiationAbsorbtionArrayList) {

		int totalMonths = temperatureArrayList.size();
		for (int i = 0; i < totalMonths; i++) {
			Temperature temperature = new Temperature(temperatureArrayList.get(i));
			Humidity humidity = new Humidity(humidityArrayList.get(i));
			WindSpeed windSpeed = new WindSpeed(windSpeedArrayList.get(i));
			RadiationAbsorbtion radiationAbsorbtion = new RadiationAbsorbtion(radiationAbsorbtionArrayList.get(i));
			Temperature feltTemperature = temperature.calcFeltTemperature(humidity, windSpeed, radiationAbsorbtion);
			this.temperatureArrayList.add(temperature);
			this.humidityArrayList.add(humidity);
			this.windSpeedArrayList.add(windSpeed);
			this.radiationAbsorbtionArrayList.add(radiationAbsorbtion);
			this.feltTemperatureArrayList.add(feltTemperature);
		}
	}

	/**
	 * Returns the name of the city.
	 *
	 * @return The name of the city.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns a copy of the temperature data for the city.
	 *
	 * @return A copy of the temperature data for the city.
	 */
	public ArrayList<Temperature> getTemperatures() {
		ArrayList<Temperature> listToReturn = new ArrayList<>();
		for (Temperature temperature : temperatureArrayList)
			listToReturn.add(new Temperature(temperature));
		return listToReturn;
	}

	/**
	 * Returns a copy of the wind speed data for the city.
	 *
	 * @return A copy of the wind speed data for the city.
	 */
	public ArrayList<WindSpeed> getWindSpeeds() {
		ArrayList<WindSpeed> listToReturn = new ArrayList<>();
		for (WindSpeed windSpeed : windSpeedArrayList)
			listToReturn.add(new WindSpeed(windSpeed));

		return listToReturn;
	}

/**
	 * Returns a copy of the humidity data for the city.
	 *
	 * @return A copy of the humidity data for the city.
	 */	public ArrayList<Humidity> getHumidities() {
		ArrayList<Humidity> listToReturn = new ArrayList<>();
		for (Humidity humidity : humidityArrayList)
			listToReturn.add(new Humidity(humidity));

		return listToReturn;
	}

	/**
	 * Returns a copy of the radiation absorbtion data for the city.
	 *
	 * @return A copy of the radiation absorbtion data for the city.
	 */
	public ArrayList<RadiationAbsorbtion> getRadiationAbsorbtions() {
		ArrayList<RadiationAbsorbtion> listToReturn = new ArrayList<>();
		for (RadiationAbsorbtion radiationAbsorbtion : radiationAbsorbtionArrayList)
			listToReturn.add(new RadiationAbsorbtion(radiationAbsorbtion));

		return listToReturn;
	}

	/**
	 * Returns a copy of the felt temperature data for the city.
	 *
	 * @return A copy of the felt temperature data for the city.
	 */
	public ArrayList<Temperature> getFeltTemperatures() {
		ArrayList<Temperature> listToReturn = new ArrayList<>();
		for (Temperature temperature : feltTemperatureArrayList)
			listToReturn.add(new Temperature(temperature));

		return listToReturn;
	}

	@Override
	public boolean equals(Object other) {
		if (other == null)
			return false;
		else if (getClass() != other.getClass())
			return false;
		else {
			City city = (City) other;
			return name.equalsIgnoreCase(city.name) && checkArrayListEqualities(city);
		}
	}

	private boolean checkArrayListEqualities(City city) {
		if (temperatureArrayList.size() != city.temperatureArrayList.size()
				|| humidityArrayList.size() != city.humidityArrayList.size()
				|| windSpeedArrayList.size() != city.windSpeedArrayList.size()
				|| radiationAbsorbtionArrayList.size() != city.radiationAbsorbtionArrayList.size())
			return false;
		else {
			for (int i = 0; i < temperatureArrayList.size(); i++) {
				if (!(temperatureArrayList.get(i).equals(city.temperatureArrayList.get(i))
						&& humidityArrayList.get(i).equals(city.humidityArrayList.get(i))
						&& windSpeedArrayList.get(i).equals(city.windSpeedArrayList.get(i))
						&& radiationAbsorbtionArrayList.get(i).equals(city.radiationAbsorbtionArrayList.get(i))
						&& feltTemperatureArrayList.get(i).equals(city.feltTemperatureArrayList.get(i))))
					return false;
			}
		}
		return true;

	}

	/**
     * Returns a string representation of the city.
     *
     * @return A string describing the city and its associated climate measurement data.
     */
	@Override
	public String toString() {
		return name + " has the data of temperature, humidity,"
				+ " wind speed, radiation absorbtion and felt temperature values from " + FileIO.MIN_YEAR + " to "
				+ FileIO.MAX_YEAR;
	}

}
