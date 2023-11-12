import java.util.Scanner;

public class MenuOperations {

	ClimateRecord climateRecord;
	Scanner scanner;

	public MenuOperations() {
		climateRecord = new ClimateRecord();
		scanner = new Scanner(System.in);
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

			if (option.equals("1")) {
				System.out.println("Enter the name of the country: ");
				scanner.nextLine();
				String countryName = scanner.nextLine();
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

			} else if (option.equals("2")) {
				System.out.println("Enter the name of the city: ");
				scanner.nextLine();
				String cityName = scanner.nextLine();
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
				if (!(yearOption > 0 && yearOption < climateRecord.getYears().length))
					System.out.print("Incorrect option input! Please reenter another option input: ");
				else
					done = true;					
			}
			catch (NumberFormatException e) {
				System.out.println("Please enter a number!");
			}
		}
		return yearOption;
	}

	private int getCorrectMeasurementUnitOption() {
		int measurementUnit = scanner.nextInt();
		while (!(measurementUnit > 0 && measurementUnit < climateRecord.getTemperatureMeasurementNames().length)) {
			System.out.print("Incorrect option input! Please reenter another option input: ");
			measurementUnit = scanner.nextInt();
		}
		return measurementUnit;

	}

}
