import java.util.ArrayList;

public class ClimateRecord {

	private ArrayList<Country> countries;
	private ArrayList<City> cities;

	public ClimateRecord() {

	}

	
	// ZERO DIVISION ERROR MONTHS
	public double getAverageTemperatureOfCountry(String countryName, int year) {

		int indexOfCountry = getIndexOfCountry(countryName);
		
		Country country = countries.get(indexOfCountry);
		ArrayList<Temperature> temperatures = country.getTemperatures();
		
		int numberOfMonths = 0;
		int total = 0;
		for (Temperature temperature : temperatures) {
			if (temperature.getYear() == year) {
				total += temperature.getCelciusMeasurement();
				numberOfMonths++;
			}
		}
		return total / numberOfMonths;
	}

	private int getIndexOfCountry(String countryName) {
		
		for (int i = 0; i < countries.size(); i++) {
			if (countryName.equals(countries.get(i).getName()))
				return i;
		}
		return -1;
	}
	

//	private boolean isCountryExist(String countryName) {
//		for (Country country : countries) {
//			if (country.getName().equals(countryName))
//				return true;
//		}
//		return false;
//	}


}
