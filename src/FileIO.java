import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class FileIO {

	public static final int MIN_YEAR = 2020;
	public static final int MAX_YEAR = 2022;

	public static final int FIRST_MONTH = 1;
	public static final int LAST_MONTH = 12;

	private File locationsFile;
	private Scanner locationsInputFile;
	ArrayList<City> cities;
	ArrayList<Country> countries;

	public FileIO() {
		cities = new ArrayList<>();
		countries = new ArrayList<>();
		locationsFile = new File("src/countries_and_cities.csv");
		try {
			locationsInputFile = new Scanner(locationsFile);
		} catch (FileNotFoundException e) {
			System.out.println("Error opening the file countries_and_cities.csv.");
			System.out.println(e.getMessage());
			System.exit(0);
		}

	}

	// initializeCityAndCountryArrayLists methodu private olarak constructor da direkt çağrılsa daha iyi olur
	// çünkü getCities ve getCountries methodları public, dolayısıyla initializeCityAndCountryArrayLists
	// methodu çağrılmadan getCities ve getCountries çağrılırsa sıkıntı olabilir
	public ArrayList<City> getCities() {
		ArrayList<City> temp = new ArrayList<>();

		for (City city : cities)
			temp.add(new City(city));
		return temp;
	}

	public ArrayList<Country> getCountries() {
		ArrayList<Country> temp = new ArrayList<>();

		for (Country country : countries)
			temp.add(new Country(country));
		return temp;
	}

	public void initializeCityAndCountryArrayLists() {
		
		// Constructor da zaten yapılmış
		locationsFile = new File("src/countries_and_cities.csv");
		try {
			locationsInputFile = new Scanner(locationsFile);
		} catch (FileNotFoundException e) {
			System.out.println("Error opening the file countries_and_cities.csv.");
			System.out.println(e.getMessage());
			System.exit(0);
		}
		//
		
		while (locationsInputFile.hasNext()) {
			ArrayList<City> citiesForACountry = new ArrayList<>();
			ArrayList<Temperature> temperaturesForACountry = new ArrayList<>();
			ArrayList<Temperature> temperaturesForACity = new ArrayList<>();
			ArrayList<Humidity> humidities = new ArrayList<>();
			ArrayList<WindSpeed> windSpeeds = new ArrayList<>();
			ArrayList<RadiationAbsorbtion> radiationAbsorbtions = new ArrayList<>();
			initializeArrayListsForCities(temperaturesForACity, humidities, windSpeeds, radiationAbsorbtions);
			String line = locationsInputFile.nextLine();
			String[] data = line.split(", ");
			for (String d : data) {
				d = d.trim();
			}
			for (int i = 1; i <= 3; i++) {
				cities.add(new City(data[i], temperaturesForACity, humidities, windSpeeds, radiationAbsorbtions));
				citiesForACountry
						.add(new City(data[i], temperaturesForACity, humidities, windSpeeds, radiationAbsorbtions));
			}
			initializeArrayListsForCountries(temperaturesForACountry);
			countries.add(new Country(data[0], temperaturesForACountry, citiesForACountry));

		}
	}
	////

	private double generateRandomDoubleInRange(double min, double max) {
		Random random = new Random();
		return min + (max - min) * random.nextDouble();
	}

	private void initializeArrayListsForCountries(ArrayList<Temperature> temperatures) {
		ClimateMeasurement.Months[] monthArray = ClimateMeasurement.Months.values();
		for (int year = MIN_YEAR; year <= MAX_YEAR; year++) {
			for (int month = FIRST_MONTH; month <= LAST_MONTH; month++) {
				temperatures.add(new Temperature(year, monthArray[month - 1].name(),
						generateRandomDoubleInRange(Temperature.MAX_CELCIUS, Temperature.MAX_CELCIUS)));
			}
		}
	}

	private void initializeArrayListsForCities(ArrayList<Temperature> temperatures, ArrayList<Humidity> humidities,
			ArrayList<WindSpeed> windSpeeds, ArrayList<RadiationAbsorbtion> radiationAbsorbtions) {
		ClimateMeasurement.Months[] monthArray = ClimateMeasurement.Months.values();
		for (int year = MIN_YEAR; year <= MAX_YEAR; year++) {
			for (int month = FIRST_MONTH; month <= LAST_MONTH; month++) {
				temperatures.add(new Temperature(year, monthArray[month - 1].name(),
						generateRandomDoubleInRange(Temperature.MAX_CELCIUS, Temperature.MAX_CELCIUS)));
				humidities.add(new Humidity(year, monthArray[month - 1].name(), generateRandomDoubleInRange(
						Humidity.MIN_HUMIDITY_PERCENTAGE, Humidity.MAX_HUMIDITY_PERCENTAGE)));
				windSpeeds.add(new WindSpeed(year, monthArray[month - 1].name(),
						generateRandomDoubleInRange(WindSpeed.MIN_WIND_SPEED, WindSpeed.MAX_WIND_SPEED)));
				radiationAbsorbtions.add(new RadiationAbsorbtion(year, monthArray[month - 1].name(),
						RadiationAbsorbtion.RadiationIntensity.values()[new Random().nextInt(3)].name(),
						generateRandomDoubleInRange(RadiationAbsorbtion.MIN_UNIT_ABSORBTION,
								RadiationAbsorbtion.MAX_UNIT_ABSORBTION)));
			}
		}
	}

}
