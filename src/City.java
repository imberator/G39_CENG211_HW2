import java.util.ArrayList;


//doıjfoıeshoıs

public class City {

	public enum Months {
		JANUARY, FEBRUARY, MARCH, APRIL, MAY, JUNE, JULY, AUGUST, SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER
	}

	private String name;
	private ArrayList<Temperature> temperatureArrayList;
	private ArrayList<Humidity> humidityArrayList;
	private ArrayList<WindSpeed> windSpeedArrayList;
	private ArrayList<RadiationAbsorbtion> radiationAbsorbtionArrayList;
	private ArrayList<Temperature> feltTemperatureArrayList;

	public City(String name, ArrayList<Temperature> temperatureArrayList, ArrayList<Humidity> humidityArrayList,
			ArrayList<WindSpeed> windSpeedArrayList, ArrayList<RadiationAbsorbtion> radiationAbsorbtionArrayList) {
		this.name = name;
		this.temperatureArrayList = new ArrayList<Temperature>();
		this.humidityArrayList = new ArrayList<Humidity>();
		this.windSpeedArrayList = new ArrayList<WindSpeed>();
		this.radiationAbsorbtionArrayList = new ArrayList<RadiationAbsorbtion>();
		checkMatchingOfArrayListsLengths(temperatureArrayList, humidityArrayList, windSpeedArrayList,
				radiationAbsorbtionArrayList);
		initializeArrayLists(temperatureArrayList, humidityArrayList, windSpeedArrayList, radiationAbsorbtionArrayList);
	}

	private void checkMatchingOfArrayListsLengths(ArrayList<Temperature> temperatureArrayList,
			ArrayList<Humidity> humidityArrayList, ArrayList<WindSpeed> windSpeedArrayList,
			ArrayList<RadiationAbsorbtion> radiationAbsorbtionArrayList) {
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
	
	public String getName() {
		return name;
	}

//	private void initializeArrayLists234567() {
//		for (int year = 2020; year <= 2022; year++) {
//			for (int month = 1; month <= 12; month++) {
//				temperatureArrayList.add(new Temperature(year, Months.values()[month - 1].name()));
//				humidityArrayList.add(new Humidity(year, Months.values()[month - 1].name()));
//				windSpeedArrayList.add(new WindSpeed(year, Months.values()[month - 1].name()));
//				radiationAbsorbtionArrayList.add(new RadiationAbsorbtion(year, Months.values()[month - 1].name()));
//			}
//		}
//	}

}
