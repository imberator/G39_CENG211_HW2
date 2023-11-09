
public abstract class ClimateMeasurement {

	private int year;
	private String month;

	public ClimateMeasurement(int year, String month) {
		this.year = year;
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public String getMonth() {
		return month;
	}

}
