import java.util.ArrayList;

public class City {

	private String name;
	private ArrayList<Temperature> temperatureArrayList;
	private ArrayList<Humidity> humidityArrayList;
	private ArrayList<WindSpeed> windSpeedArrayList;
	private ArrayList<RadiationAbsorbtion> radiationAbsorbtionArrayList;
	private ArrayList<Temperature> feltTemperatureArrayList;

	public City(String name, ArrayList<Temperature> temperatureArrayList, ArrayList<Humidity> humidityArrayList,
			ArrayList<WindSpeed> windSpeedArrayList, ArrayList<RadiationAbsorbtion> radiationAbsorbtionArrayList) {
		checkValidity(name, temperatureArrayList, humidityArrayList, windSpeedArrayList, radiationAbsorbtionArrayList);
		this.name = name;
		this.temperatureArrayList = new ArrayList<>();
		this.humidityArrayList = new ArrayList<>();
		this.windSpeedArrayList = new ArrayList<>();
		this.radiationAbsorbtionArrayList = new ArrayList<>();
		this.feltTemperatureArrayList = new ArrayList<>(); //Initialize edilmediği için null döndürüyordu.
		initializeArrayLists(temperatureArrayList, humidityArrayList, windSpeedArrayList, radiationAbsorbtionArrayList);
	}

	//Null pointer exception atıyordu. Yanlış yazılmış copy constructor.
	public City(City otherCity) {
        if (otherCity == null) {
            System.out.println("Error! City cannot be found.");
            System.exit(0);
        }
        this.name = otherCity.name;
        // Objects need to be immutable ?
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

	public String getName() {
		return name;
	}
	
	public ArrayList<Temperature> getTemperatures() {
		ArrayList<Temperature> listToReturn = new ArrayList<>();
		for (Temperature temperature : temperatureArrayList)
			listToReturn.add(new Temperature(temperature));
		
		return listToReturn;
	}
	
	public ArrayList<WindSpeed> getWindSpeeds() {
		ArrayList<WindSpeed> listToReturn = new ArrayList<>();
		for (WindSpeed windSpeed : windSpeedArrayList)
			listToReturn.add(new WindSpeed(windSpeed));
		
		return listToReturn;
	}
	
	public ArrayList<Humidity> getHumidities() {
		ArrayList<Humidity> listToReturn = new ArrayList<>();
		for (Humidity humidity : humidityArrayList)
			listToReturn.add(new Humidity(humidity));
		
		return listToReturn;
	}
	
	public ArrayList<RadiationAbsorbtion> getRadiationAbsorbtions() {
		ArrayList<RadiationAbsorbtion> listToReturn = new ArrayList<>();
		for (RadiationAbsorbtion radiationAbsorbtion : radiationAbsorbtionArrayList)
			listToReturn.add(new RadiationAbsorbtion(radiationAbsorbtion));
		
		return listToReturn;
	}
	
	public ArrayList<Temperature> getFeltTemperatures() {
		ArrayList<Temperature> listToReturn = new ArrayList<>();
		for (Temperature temperature : feltTemperatureArrayList)
			listToReturn.add(new Temperature(temperature));
		
		return listToReturn;
	}
	
	@Override
	public String toString(){ //Kontrol için öylesine eklendi.
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
