package data_access_layer;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import business_layer.City;
import business_layer.ClimateMeasurement;
import business_layer.Country;
import business_layer.Humidity;
import business_layer.RadiationAbsorbtion;
import business_layer.Temperature;
import business_layer.WindSpeed;

/**
 * The `FileIO` class is responsible for reading data from a file, initializing lists of cities and countries,
 * and generating random climate data for cities and countries.
 * It uses the "countries_and_cities.csv" file to gather information about cities and countries.
 */
public class FileIO {

	/**
     * The minimum year for climate data generation.
     */
	public static final int MIN_YEAR = 2020;

	 /**
     * The maximum year for climate data generation.
     */
	public static final int MAX_YEAR = 2022;

	/**
     * The first month for climate data generation.
     */
	public static final int FIRST_MONTH = 1;

	/**
     * The last month for climate data generation.
     */
	public static final int LAST_MONTH = 12;

	// Instance variables
	private File locationsFile; 		// File containing information about countries and cities
	private Scanner locationsInputFile; // Scanner for reading data from the file
	ArrayList<City> cities; 			// List of cities
	ArrayList<Country> countries;   	// List of countries

	/**
     * Default constructor for the `FileIO` class.
     * Initializes lists of cities and countries and opens the file "countries_and_cities.csv".
     * Exits the program if the file is not found.
     */
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
		initializeCityAndCountryArrayLists();
	}
	
	/**
     * Gets a copy of the list of cities.
     *
     * @return A new list containing copies of the cities.
     */
	public ArrayList<City> getCities() {
		ArrayList<City> temp = new ArrayList<>();

		for (City city : cities)
			temp.add(new City(city));
		return temp;
	}

	/**
     * Gets a copy of the list of countries.
     *
     * @return A new list containing copies of the countries.
     */
	public ArrayList<Country> getCountries() {
		ArrayList<Country> temp = new ArrayList<>();

		for (Country country : countries)
			temp.add(new Country(country));
		return temp;
	}

	private void initializeCityAndCountryArrayLists() {
		
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
			for (int i = 1; i < data.length; i++) {
				cities.add(new City(data[i], temperaturesForACity, humidities, windSpeeds, radiationAbsorbtions));
				citiesForACountry
						.add(new City(data[i], temperaturesForACity, humidities, windSpeeds, radiationAbsorbtions));
			}
			initializeArrayListsForCountries(temperaturesForACountry);
			countries.add(new Country(data[0], temperaturesForACountry, citiesForACountry));

		}
	}

	private double generateRandomDoubleInRange(double min, double max) {
		Random random = new Random();
		return min + (max - min) * random.nextDouble();
	}

	private void initializeArrayListsForCountries(ArrayList<Temperature> temperatures) {
		ClimateMeasurement.Months[] monthArray = ClimateMeasurement.Months.values();
		for (int year = MIN_YEAR; year <= MAX_YEAR; year++) {
			for (int month = FIRST_MONTH; month <= LAST_MONTH; month++) {
				temperatures.add(new Temperature(year, monthArray[month - 1].name(),
						generateRandomDoubleInRange(Temperature.MIN_CELCIUS, Temperature.MAX_CELCIUS)));
			}
		}
	}

	private void initializeArrayListsForCities(ArrayList<Temperature> temperatures, ArrayList<Humidity> humidities,
			ArrayList<WindSpeed> windSpeeds, ArrayList<RadiationAbsorbtion> radiationAbsorbtions) {
		
		ClimateMeasurement.Months[] monthArray = ClimateMeasurement.Months.values();
		for (int year = MIN_YEAR; year <= MAX_YEAR; year++) {
			
			for (int month = FIRST_MONTH; month <= LAST_MONTH; month++) {
			
				temperatures.add(new Temperature(year, monthArray[month - 1].name(),
						generateRandomDoubleInRange(Temperature.MIN_CELCIUS, Temperature.MAX_CELCIUS)));
				
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
