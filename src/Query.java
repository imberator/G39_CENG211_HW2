import java.util.ArrayList;

public class Query {

	private ArrayList<Country> countries;
	private ArrayList<City> cities;
	
	public Query() {
		
	}
	
	private void averageTemperatureOfACountry(int year, String countryName, String measurementUnit) {
	
		if (isCountryExist(countryName)) {
			
		}
	}
	
	private boolean isCountryExist(String countryName) {
		for (Country country : countries) {
			if (country.getName().equals(countryName))
				return true;
		}
		return false;
	}
}
