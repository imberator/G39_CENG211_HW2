package presentation_layer;
import java.util.ArrayList;
import java.util.Scanner;

import business_layer.City;
import business_layer.ClimateMeasurement;
import business_layer.ClimateRecord;
import business_layer.Country;
import business_layer.RadiationAbsorbtion;
import data_access_layer.FileIO;

/**
 * The `MenuOperations` class handles user interactions by displaying a menu and
 * performing climate-related operations. It interacts with the `ClimateRecord`
 * class to calculate and display various climate-related statistics.
 */
public class MenuOperations {

	// Instance variables
	private ClimateRecord climateRecord; // ClimateRecord object for handling climate data
	private Scanner scanner; // Scanner for user input
	private ArrayList<Country> countries; // List of countries
	private ArrayList<City> cities; // List of cities

	/**
	 * Default constructor for the `MenuOperations` class. Initializes the
	 * `ClimateRecord` object, `Scanner`, and retrieves the list of cities and
	 * countries from `FileIO`.
	 */
	public MenuOperations() {
		climateRecord = new ClimateRecord();
		scanner = new Scanner(System.in);
		FileIO fileIO = new FileIO();
		cities = fileIO.getCities();
		countries = fileIO.getCountries();
	}

	/**
	 * Displays the main menu and handles user input to perform different
	 * climate-related operations.
	 */
	public void displayMenu() {

		while (true) {
			System.out
					.println("[1] Calculate average temperature for a country according to temperature unit and year.");
			System.out.println("[2] Calculate average temperature for a city according to temperature unit and year.");
			System.out.println(
					"[3] Calculate average wind speed for a city depending on the unit of speed and month of the year.");
			System.out.println("[4] Calculate average humidity of a city for every year.");
			System.out.println("[5] Count how many times a year a specific radiation intensity value appears.");
			System.out.println("[6] Calculate the “felt temperature” value for a specific month.");
			System.out.println("[7] Exit the application.");

			System.out.print("Please select an option: ");
			String option = scanner.next();
			scanner.nextLine();

			if (option.equals("1")) {
				averageTemperatureOfCountry();
			} else if (option.equals("2")) {
				averageTemperatureOfCity();
			} else if (option.equals("3")) {
				averageWindSpeedOfCity();
			} else if (option.equals("4")) {
				averageHumidityOfCity();
			} else if (option.equals("5")) {
				radiationIntensityForCity();
			} else if (option.equals("6")) {
				feltTemperatureOfCity();
			} else if (option.equals("7")) {
				System.out.println("Closing the application...");
				break;
			} else {
				System.out.println("Invalid option. Please try again.");
			}
			String repeatedString = "*".repeat(80);
			System.out.println(repeatedString);
		}
	}

	private void averageTemperatureOfCountry() {
		System.out.print("Enter the name of the country: ");
		String countryName = getCorrectCountryName();
		System.out.println("[1] Celsius [2] Fahrenheit [3] Kelvin");
		System.out.print("Please select the temperature unit: ");
		int temperatureUnit = getCorrectOption(climateRecord.getTemperatureMeasurementNames());
		printYears(climateRecord.getYears());
		System.out.print("Please select the year: ");
		int yearOption = getCorrectOption(climateRecord.getYears());
		double avgTemp = climateRecord.getAverageTemperatureOfCountry(countryName, yearOption, temperatureUnit);
		System.out.println("Average temperature of " + countryName + " in "
				+ climateRecord.getTemperatureMeasurementNames()[temperatureUnit - 1] + " in "
				+ climateRecord.getYears()[yearOption - 1] + ": " + String.format("%.1f", avgTemp));
	}

	private void averageTemperatureOfCity() {
		System.out.print("Enter the name of the city: ");
		String cityName = getCorrectCityName();
		System.out.println("[1] Celsius [2] Fahrenheit [3] Kelvin");
		System.out.print("Please select the temperature unit: ");
		int temperatureUnit = getCorrectOption(climateRecord.getTemperatureMeasurementNames());
		printYears(climateRecord.getYears());
		System.out.print("Please select the year: ");
		int yearOption = getCorrectOption(climateRecord.getYears());
		double avgTemp = climateRecord.getAverageTemperatureOfCity(cityName, yearOption, temperatureUnit);
		System.out.println("Average temperature of " + cityName + " in "
				+ climateRecord.getTemperatureMeasurementNames()[temperatureUnit - 1] + " in "
				+ climateRecord.getYears()[yearOption - 1] + ": " + String.format("%.1f", avgTemp));
	}

	private void averageWindSpeedOfCity() {
		System.out.print("Enter the name of the city: ");
		String cityName = getCorrectCityName();
		System.out.println("[1] m/s [2] km/h");
		System.out.print("Enter the wind speed unit: ");
		int speedUnitOption = getCorrectOption(climateRecord.getSpeedUnits());
		scanner.nextLine();
		System.out.print("Enter the month name: ");
		String monthName = getCorrectMonthName();
		double avgWindSpeed = climateRecord.getAverageWindSpeedOfCity(cityName, monthName, speedUnitOption);
		System.out.println(cityName + "'s average wind speed in " + climateRecord.getSpeedUnits()[speedUnitOption - 1]
				+ " in " + monthName + " from " + FileIO.MIN_YEAR + " to " + FileIO.MAX_YEAR + " is: "
				+ String.format("%.1f", avgWindSpeed));
	}

	private void averageHumidityOfCity() {
		System.out.print("Enter the name of the city: ");
		String cityName = getCorrectCityName();
		double avgHumidity = climateRecord.getAverageHumidityOfCity(cityName);
		System.out.println(cityName + "'s average humidity from " + FileIO.MIN_YEAR + " to " + FileIO.MAX_YEAR + " is %"
				+ String.format("%.1f", avgHumidity));
	}

	private void radiationIntensityForCity() {
		System.out.print("Enter the name of the city: ");
		String cityName = getCorrectCityName();
		System.out.println("[1] Low [2] Medium [3] High");
		System.out.print("Please select a radiation intensity value: ");
		RadiationAbsorbtion.RadiationIntensity intensity = getCorrectIntensityOption();
		printYears(climateRecord.getYears());
		System.out.print("Please select the year: ");
		int yearOption = getCorrectOption(climateRecord.getYears());
		int intensityCount = climateRecord.getRadiationIntensityForCity(cityName, yearOption, intensity);
		System.out.println("Total count of " + intensity.name().toLowerCase() + " radiation intensity in " + cityName
				+ " in " + climateRecord.getYears()[yearOption - 1] + ": " + intensityCount);
	}

	private void feltTemperatureOfCity() {
		System.out.print("Enter the name of the city: ");
		String cityName = getCorrectCityName();
		System.out.print("Enter the month name: ");
		String monthName = getCorrectMonthName();
		printYears(climateRecord.getYears());
		System.out.print("Please select the year: ");
		int yearOption = getCorrectOption(climateRecord.getYears());
		double feltTemperature = climateRecord.getFeltTemperatureOfCity(cityName, yearOption, monthName);
		System.out.println("The felt temperature value of " + monthName + " " + climateRecord.getYears()[yearOption - 1]
				+ " for " + cityName + ": " + String.format("%.1f", feltTemperature));

	}

	private int getCorrectOption(Object[] arr) {
		int option = -1;
		boolean done = false;
		while (!done) {
			String input = scanner.next();
			try {
				option = Integer.parseInt(input);
				if (!(option > 0 && option <= arr.length))
					System.out.print("Incorrect option! Please reenter another option:");
				else
					done = true;
			} catch (NumberFormatException e) {
				System.out.print("Please enter a number: ");
			}
		}
		return option;
	}

	private String getCorrectCountryName() {
		boolean found = false;
		String countryName = scanner.nextLine();
		while (!found) {
			for (Country country : countries) {
				if (countryName.equalsIgnoreCase(country.getName())) {
					found = true;
					countryName = country.getName();
				}
			}
			if (!found) {
				System.out.print("Incorrect country name! Please reenter country name: ");
				countryName = scanner.nextLine();
			}
		}
		return countryName;
	}

	private String getCorrectCityName() {
		boolean found = false;
		String cityName = scanner.nextLine();
		while (!found) {
			for (City city : cities) {
				if (cityName.equalsIgnoreCase(city.getName())) {
					found = true;
					cityName = city.getName();
				}
			}
			if (!found) {
				System.out.print("Incorrect city name! Please reenter city name: ");
				cityName = scanner.nextLine();
			}
		}
		return cityName;
	}

	private String getCorrectMonthName() {
		boolean found = false;
		String monthName = scanner.nextLine();
		while (!found) {
			for (ClimateMeasurement.Months month : ClimateMeasurement.Months.values()) {
				if (monthName.equalsIgnoreCase(month.name())) {
					found = true;
					monthName = capitalizeFirstLetter(month.name().toLowerCase());
				}
			}
			if (!found) {
				System.out.print("Incorrect month! Please reenter month: ");
				monthName = scanner.nextLine();
			}
		}
		return monthName;
	}

	private RadiationAbsorbtion.RadiationIntensity getCorrectIntensityOption() {
		RadiationAbsorbtion.RadiationIntensity[] intensityArray = RadiationAbsorbtion.RadiationIntensity.values();
		int intensityOption = -1;
		boolean done = false;
		while (!done) {
			String intensityInput = scanner.next();
			try {
				intensityOption = Integer.parseInt(intensityInput);
				if (!(intensityOption > 0 && intensityOption <= intensityArray.length))
					System.out.print("Incorrect option input! Please reenter another option input: ");
				else
					done = true;
			} catch (NumberFormatException e) {
				System.out.print("Please enter a number: ");
			}
		}
		return intensityArray[intensityOption - 1];
	}

	private static String capitalizeFirstLetter(String input) {
		if (input == null || input.isEmpty()) {
			return input;
		}
		return input.substring(0, 1).toUpperCase() + input.substring(1);
	}

	private void printYears(Integer[] years) {
		String string = "";
		for (int i = 0; i < years.length; i++) {
			string += "[" + (i + 1) + "] " + years[i] + " ";
		}
		System.out.println(string);
	}
}
