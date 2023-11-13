import java.util.ArrayList;

/**
 * The `Country` class represents a country with its name, temperatures, and
 * cities. It provides methods for initializing, accessing, and comparing
 * country objects.
 */
public class Country {

	private String name;
	private ArrayList<Temperature> temperatures;
	private ArrayList<City> cities;

	/**
	 * Parameterized constructor for creating a country with specified attributes.
	 *
	 * @param name         The name of the country.
	 * @param temperatures The list of temperatures associated with the country.
	 * @param cities       The list of cities associated with the country.
	 */
	public Country(String name, ArrayList<Temperature> temperatures, ArrayList<City> cities) {
		checkValidity(name, temperatures, cities);
		this.name = name;
		this.temperatures = new ArrayList<>();
		this.cities = new ArrayList<>();
		initializeTemperatures(temperatures);
		initializeCities(cities);
	}

	/**
	 * Copy constructor for creating a country by copying attributes from another
	 * country.
	 *
	 * @param otherCountry The country object to be copied.
	 */
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

	/**
	 * Gets the name of the country.
	 *
	 * @return The name of the country.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets a copy of the temperature list associated with the country.
	 *
	 * @return A new list containing copies of the temperatures.
	 */
	public ArrayList<Temperature> getTemperatures() {
		ArrayList<Temperature> temp = new ArrayList<>();
		for (Temperature temperature : temperatures) {
			temp.add(new Temperature(temperature));
		}
		return temp;
	}

	/**
	 * Overrides the default equals method to compare two country objects.
	 *
	 * @param other The object to compare with.
	 * @return True if the objects are equal, false otherwise.
	 */
	@Override
	public boolean equals(Object other) {
		if (other == null)
			return false;
		else if (getClass() != other.getClass())
			return false;
		else {
			Country country = (Country) other;
			return name.equalsIgnoreCase(country.name) && checkArrayListEqualities(country);
		}
	}

	private boolean checkArrayListEqualities(Country country) {

		if ((temperatures.size() != country.temperatures.size()) && (cities.size() != country.cities.size()))
			return false;
		else {
			for (int i = 0; i < temperatures.size(); i++) {
				if (!(temperatures.get(i).equals(country.temperatures.get(i))))
					return false;
			}
			for (int i = 0; i < cities.size(); i++) {
				if (!(cities.get(i).equals(country.cities.get(i))))
					return false;
			}
		}
		return true;
	}

	/**
	 * Returns a string representation of the country.
	 *
	 * @return A string describing the country and its associated data.
	 */
	@Override
	public String toString() {
		return name + " has the data of temperature from " + FileIO.MIN_YEAR + " to " + FileIO.MAX_YEAR + ",and it has "
				+ cities.size() + " cities.";
	}
}