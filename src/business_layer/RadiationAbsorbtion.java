package business_layer;

/**
 * The `RadiationAbsorbtion` class extends the `ClimateMeasurement` class and represents radiation absorption data.
 * It includes radiation intensity levels (LOW, MEDIUM, HIGH) and the unit absorption value.
 */
public class RadiationAbsorbtion extends ClimateMeasurement {

	/**
     * Enumeration representing different levels of radiation intensity: LOW, MEDIUM, HIGH.
     */
	public enum RadiationIntensity {
		LOW, MEDIUM, HIGH
	}

	// Instance variables
	private RadiationIntensity radiationIntensity;	// Radiation intensity level
	private double unitAbsorbtionValue;				// Unit absorption value

	/**
     * The minimum allowed unit absorption value.
     */
	public static final double MIN_UNIT_ABSORBTION = 5.0;

	/**
     * The maximum allowed unit absorption value.
     */
	public static final double MAX_UNIT_ABSORBTION = 20.0;

	/**
     * Constructs a `RadiationAbsorbtion` object with the specified year, month, radiation intensity, and unit absorption value.
     *
     * @param year                 The year of the radiation absorption measurement.
     * @param month                The month of the radiation absorption measurement.
     * @param radiationIntensity  The radiation intensity level (LOW, MEDIUM, HIGH).
     * @param unitAbsorbtionValue The unit absorption value.
     */
	public RadiationAbsorbtion(int year, String month, String radiationIntensity, double unitAbsorbtionValue) {
		super(year, month);
		this.radiationIntensity = setRadiationIntensity(radiationIntensity);
		this.unitAbsorbtionValue = unitAbsorbtionValue;
	}
	
	private RadiationIntensity setRadiationIntensity(String radiationIntensity) {
		radiationIntensity = radiationIntensity.toUpperCase();
		if (!isValidRadiationEnum(radiationIntensity)) {
			System.out.println("Error! Undefined radiation intensity.");
			System.exit(0);
		}
		return RadiationIntensity.valueOf(radiationIntensity);
	}
	
	/**
     * Copy constructor for the `RadiationAbsorbtion` class.
     * Creates a copy of another `RadiationAbsorbtion` object by copying its year, month, radiation intensity, and unit absorption value.
     *
     * @param other The `RadiationAbsorbtion` object to be copied.
     */
	public RadiationAbsorbtion(RadiationAbsorbtion other) {
		super(other.getYear(), other.getMonth());	
		this.radiationIntensity = other.radiationIntensity;
		this.unitAbsorbtionValue = other.unitAbsorbtionValue;
	}
	
	private boolean isValidRadiationEnum(String radiationIntensity) {
		
		RadiationIntensity[] radiationIntensities = RadiationIntensity.values();
		for (int i = 0; i < radiationIntensities.length; i++) {
			if (radiationIntensity.equals(radiationIntensities[i].name()))
				return true;	
		}
		return false;
	}


	 /**
     * Gets the unit absorption value.
     *
     * @return The unit absorption value.
     */
	public double getRadiationAbsorbtion() {
		return unitAbsorbtionValue;
	}
	
	/**
     * Gets the radiation intensity level.
     *
     * @return The radiation intensity level.
     */

	public RadiationIntensity getRadiationIntensity() {
		return radiationIntensity;
	}

	/**
     * Overrides the equals method to compare two `RadiationAbsorbtion` objects for equality.
     *
     * @param otherObject The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
	@Override
	public boolean equals(Object otherObject) {

		if (otherObject == null)
			return false;
		else if (getClass() != otherObject.getClass())
			return false;
		else {
			RadiationAbsorbtion otherRadiationAbsorbtion = (RadiationAbsorbtion) otherObject;
			return (getYear() == otherRadiationAbsorbtion.getYear())
					&& (getMonth() == otherRadiationAbsorbtion.getMonth())
					&& (radiationIntensity == otherRadiationAbsorbtion.radiationIntensity)
					&& (unitAbsorbtionValue == otherRadiationAbsorbtion.unitAbsorbtionValue);
		}
	}

	/**
     * Generates a string representation of the `RadiationAbsorbtion` object.
     *
     * @return The string representation of the `RadiationAbsorbtion` object.
     */
	@Override
	public String toString() {
		return "Radiation Intensity: " + radiationIntensity + ", Unit Absorbtion Value: "
				+ String.format("%.1f", unitAbsorbtionValue);
	}

	

}
