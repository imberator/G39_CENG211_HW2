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

	public FileIO() {
		locationsFile = new File("src/countries_and_cities.csv");
		try {
			locationsInputFile = new Scanner(locationsFile);
		} catch (FileNotFoundException e) {
			System.out.println("Error opening the file products.csv.");
			System.out.println(e.getMessage());
			System.exit(0);
		}
	}

	public ArrayList<City> getCityArrayList() {

	}

	private double generateRandomDoubleInRange(double min, double max) {
		Random random = new Random();
		return min + (max - min) * random.nextDouble();
	}

	private void initializeArrayLists() {
		ArrayList<Temperature> temperatures = new ArrayList<>();
		ArrayList<Humidity> humidities = new ArrayList<>();
		ArrayList<WindSpeed> windSpeeds = new ArrayList<>();
		ArrayList<RadiationAbsorbtion> radiationAbsorbtions = new ArrayList<>();
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
