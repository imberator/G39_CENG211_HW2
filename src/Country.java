import java.util.ArrayList;

public class Country {

	private String name;
	private ArrayList<Temperature> temperatures;
	private ArrayList<City> cities;

	public Country() {
		this.name = "";
		this.temperatures = new ArrayList<>();
		this.cities = new ArrayList<>();
	}


	public Country(String name, ArrayList<Temperature> temperatures, ArrayList<City> cities) {
		checkValidity(name, temperatures, cities);
		this.name = name;
		this.temperatures = new ArrayList<>();
		this.cities = new ArrayList<>();
		initializeTemperatures(temperatures);
		initializeCities(cities);
	}

	public Country(Country otherCountry) {
		if (otherCountry == null) {
			System.out.println("Cannot found country!");
			System.exit(0);
		}
		checkValidity(otherCountry.name, otherCountry.temperatures, otherCountry.cities);
		this.name = otherCountry.name;
		this.temperatures = new ArrayList<>(otherCountry.temperatures);
		this.cities = new ArrayList<>(otherCountry.cities);
	}

	private void checkValidity(String name, ArrayList<Temperature> temperatures, ArrayList<City> cities) {
		if ((name == null) || (temperatures == null) || (cities == null)) {
			System.out.println("Error! Invalid attributes.");
			System.exit(0);
		}
	}

	private void initializeTemperatures(ArrayList<Temperature> temperatures) {
		for (int i = 0; i < temperatures.size(); i++) {
			if (temperatures.get(i) == null) {
				System.out.println("Cannot initialize temperature arraylist!");
				System.exit(0);
			}
			this.temperatures.add(new Temperature(temperatures.get(i)));
		}
	}

	private void initializeCities(ArrayList<City> cities) {
		for (int i = 0; i < cities.size(); i++) {
			this.cities.add(new City(cities.get(i)));
		}
	}
	
	public String getName() {
		return name;
	}
	
	public ArrayList<Temperature> getTemperatures() {
		ArrayList<Temperature> temp = new ArrayList<>();
		for (Temperature temperature : temperatures) {
			temp.add(new Temperature(temperature));
		}
		return temp;
	}

}
