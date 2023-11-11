import java.util.ArrayList;
public class ClimateApp {

	public static void main(String[] args) {
		FileIO file = new FileIO();

		file.initializeCityAndCountryArrayLists();

		ArrayList<Country> a = file.getCountries();
		ArrayList<City> b = file.getCities();

		for (City city : b)
			System.out.println(city);

		System.out.println();

		for (Country country : a)
			System.out.println(country.getName());


	}
}
