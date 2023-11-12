import java.util.ArrayList;
import java.util.Scanner;

public class MenuOperations {

	private ClimateRecord climateRecord;
	private Scanner scanner;
	private ArrayList<Country> countries;
	private ArrayList<City> cities;

	public MenuOperations() {
		climateRecord = new ClimateRecord();
		scanner = new Scanner(System.in);
		FileIO fileIO = new FileIO();
		cities = fileIO.getCities();
		countries = fileIO.getCountries();
	}

	public void displayMenu() {

		while (true) {
			System.out
					.println("[1] Calculate average temperature for a country according to temperature unit and year.");
			System.out.println("[2] Calculate average temperature for a city according to temperature unit and year.");
			System.out.println("[3] Calculate average wind speed for a city according to speed unit and year.");
			System.out.println("[4] Calculate average humidity of a city for every year.");
			System.out.println("[5] Count how many times a year a specific radiation intensity value appears.");
			System.out.println("[6] Calculate the “felt temperature” value for a specific month.");
			System.out.println("[7] Exit the application.");

			System.out.print("Please select an option: ");
			String option = scanner.next();
			scanner.nextLine();

			if (option.equals("1")) {
				System.out.println("Enter the name of the country: ");
				String countryName = getCorrectCountryName();
				System.out.println("[1] Celsius [2] Fahrenheit [3] Kelvin");
				System.out.print("Please select the temperature unit: ");
				int temperatureUnit = getCorrectMeasurementUnitOption();
				System.out.println("[1] 2020 [2] 2021 [3] 2022");
				System.out.print("Please select the year: ");
				int yearOption = getCorrectYearOption();
				double output = climateRecord.getAverageTemperatureOfCountry(countryName, yearOption, temperatureUnit);
				System.out.println("Average temperature of " + countryName + " in "
						+ climateRecord.getTemperatureMeasurementNames()[temperatureUnit - 1] + " in "
						+ climateRecord.getYears()[yearOption - 1] + ": " + output);
				System.out.println();
			} else if (option.equals("2")) {
				System.out.println("Enter the name of the city: ");
				String cityName = getCorrectCityName();
				System.out.println("[1] Celsius [2] Fahrenheit [3] Kelvin");
				System.out.print("Please select the temperature unit: ");
				int temperatureUnit = getCorrectMeasurementUnitOption();
				System.out.println("[1] 2020 [2] 2021 [3] 2022");
				System.out.print("Please select the year: ");
				int yearOption = getCorrectYearOption();
				double output = climateRecord.getAverageTemperatureOfCity(cityName, yearOption, temperatureUnit);
				System.out.println("Average temperature of " + cityName + " in "
						+ climateRecord.getTemperatureMeasurementNames()[temperatureUnit - 1] + " in "
						+ climateRecord.getYears()[yearOption - 1] + ": " + output);
			}

		}

	}

	private int getCorrectYearOption() {

		int yearOption = -1;
		boolean done = false;
		while (!done) {
			String yearInput = scanner.next();
			try {
				yearOption = Integer.parseInt(yearInput);
				if (!(yearOption > 0 && yearOption <= climateRecord.getYears().length))
					System.out.print("Incorrect option input! Please reenter another option input:");
				else
					done = true;
			} catch (NumberFormatException e) {
				System.out.println("Please enter a number!");
			}
		}
		return yearOption;
	}

	private int getCorrectMeasurementUnitOption() {
		int unitOption = -1;
		boolean done = false;
		while (!done) {
			String unitInput = scanner.next();
			try {
				unitOption = Integer.parseInt(unitInput);
				if (!(unitOption > 0 && unitOption <= climateRecord.getTemperatureMeasurementNames().length))
					System.out.print("Incorrect option input! Please reenter another option input: ");
				else
					done = true;
			} catch (NumberFormatException e) {
				System.out.print("Please enter a number: ");
			}
		}
		return unitOption;
	}

	private String getCorrectCountryName() {
		boolean found = false;
		String countryName = scanner.nextLine();
		while (!found) {
			for (Country country : countries) {
				if (countryName.equalsIgnoreCase(country.getName()))
					found = true;
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
				if (cityName.equalsIgnoreCase(city.getName()))
					found = true;
			}
			if (!found) {
				System.out.print("Incorrect country name! Please reenter city name: ");
				cityName = scanner.nextLine();
			}
		}
		return cityName;
	}
}
