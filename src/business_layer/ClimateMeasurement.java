package business_layer;
/**
 * This abstract class represents a climate measurement with information about
 * the year and month. Subclasses should extend this class to include specific
 * climate measurements.
 */
public abstract class ClimateMeasurement {

	/**
	 * Enum representing months of the year.
	 */
	public enum Months {
		JANUARY, FEBRUARY, MARCH, APRIL, MAY, JUNE, JULY, AUGUST, SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER
	}

	private int year;
	private String month;

	/**
	 * Constructs a ClimateMeasurement object with the specified year and month.
	 *
	 * @param year  The year of the measurement.
	 * @param month The month of the measurement.
	 */
	public ClimateMeasurement(int year, String month) {
		this.year = year;
		this.month = month;
	}

	/**
	 * Constructs a new ClimateMeasurement object as a copy of another
	 * ClimateMeasurement object.
	 *
	 * @param climateMeasurement The ClimateMeasurement object to be copied.
	 */
	public ClimateMeasurement(ClimateMeasurement climateMeasurement) {
		if (climateMeasurement == null) {
			System.out.println("Error identifying object!");
			System.exit(0);
		}
		this.year = climateMeasurement.year;
		this.month = climateMeasurement.month;
	}

	/**
	 * Gets the year of the climate measurement.
	 *
	 * @return The year of the measurement.
	 */
	public int getYear() {
		return year;
	}

	/**
	 * Gets the month of the climate measurement.
	 *
	 * @return The month of the measurement.
	 */
	public String getMonth() {
		return month;
	}

	/**
	 * Checks if this ClimateMeasurement is equal to another object.
	 *
	 * @param other The object to compare with.
	 * @return True if the objects are equal, false otherwise.
	 */
	@Override
	public boolean equals(Object other) {
		if (other == null)
			return false;
		else if (getClass() != other.getClass())
			return false;
		else {
			ClimateMeasurement o = (ClimateMeasurement) other;
			return year == o.year && month.equalsIgnoreCase(o.month);
		}
	}

	/**
	 * Returns a string representation of this ClimateMeasurement.
	 *
	 * @return A string representation of the object.
	 */
	@Override
	public String toString() {
		return "This measurement is in " + month + " " + year;
	}
}
