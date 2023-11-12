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
				////////////////////////////////////////////////////
				double output = climateRecord.getAverageTemperatureOfCountry(countryName, yearOption, temperatureUnit);
				System.out.println("Average temperature of " + countryName + " in "
						+ climateRecord.getTemperatureMeasurementNames()[temperatureUnit - 1] + " in "
						+ climateRecord.getYears()[yearOption - 1] + ": " + output);
				System.out.println();

			} else if (option.equals("2")) {
				System.out.print("Enter the name of the city: ");
				String cityName = getCorrectCityName();
				System.out.println("[1] Celsius [2] Fahrenheit [3] Kelvin");
				System.out.print("Please select the temperature unit: ");
				int temperatureUnit = getCorrectMeasurementUnitOption();
				System.out.println("[1] 2020 [2] 2021 [3] 2022");
				System.out.print("Please select the year: ");
				int yearOption = getCorrectYearOption();
				////////////////////////////////////////////////////////
				double output = climateRecord.getAverageTemperatureOfCity(cityName, yearOption, temperatureUnit);
				System.out.println("Average temperature of " + cityName + " in "
						+ climateRecord.getTemperatureMeasurementNames()[temperatureUnit - 1] + " in "
						+ climateRecord.getYears()[yearOption - 1] + ": " + output);

			} else if (option.equals("3")) {
				System.out.println("Enter the name of the city: ");
				String cityName = getCorrectCityName();

			} else if (option.equals("4")) {

			} else if (option.equals("5")) {
				System.out.print("Enter the name of the city: ");
				String cityName = getCorrectCityName();
				System.out.println("[1] Low [2] Medium [3] High");
				System.out.print("Please select a radiation intensity value: ");
				RadiationAbsorbtion.RadiationIntensity intensity = getCorrectIntensityOption();
				System.out.println("[1] 2020 [2] 2021 [3] 2022");
				System.out.print("Please select the year: ");
				int yearOption = getCorrectYearOption();
				int intensityCount = climateRecord.getRadiationIntensityForCity(cityName, yearOption, intensity);
				System.out.println("Total count of " + intensity.name().toLowerCase() + " radiation intensity in "
						+ cityName + " in " + climateRecord.getYears()[yearOption - 1] + ": " + intensityCount);
						
			} else if (option.equals("6")) {
				System.out.print("Enter the name of the city: ");
				String cityName = getCorrectCityName();
				System.out.print("Enter the month name: ");
				String monthName = getCorrectMonthName();
				System.out.println("[1] 2020 [2] 2021 [3] 2022");
				System.out.print("Please select the year: ");
				int yearOption = getCorrectYearOption();
				double feltTemperature = climateRecord.getFeltTemperatureOfCity(cityName, yearOption, monthName);
				System.out.println("The felt temperature value of " + monthName + " " + yearOption + " for" + cityName
						+ ": " + feltTemperature);

			} else if (option.equals("7")) {
				System.out.println("Closing the application...");
				break;
			} else {
				System.out.print("Invalid option. Please try again.");
			}
			String repeatedString = "*".repeat(80);
			System.out.println(repeatedString);
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

	private String getCorrectMonthName() {
		boolean found = false;
		String monthName = scanner.nextLine();
		while (!found) {
			for (ClimateMeasurement.Months month : ClimateMeasurement.Months.values()) {
				if (monthName.equalsIgnoreCase(month.name()))
					found = true;
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

}
