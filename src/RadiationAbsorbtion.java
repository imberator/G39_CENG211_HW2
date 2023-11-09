
public class RadiationAbsorbtion extends ClimateMeasurement {

	public enum RadiationIntensity {
		LOW, MEDIUM, HIGH
	}

	private RadiationIntensity radiationIntensity;
	private double unitAbsorbtionValue;

	public static final double MIN_UNIT_ABSORBTION = 5.0;
	public static final double MAX_UNIT_ABSORBTION = 20.0;

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
	
	private boolean isValidRadiationEnum(String radiationIntensity) {
		
		RadiationIntensity[] radiationIntensities = RadiationIntensity.values();
		for (int i = 0; i < radiationIntensities.length; i++) {
			if (radiationIntensity.equals(radiationIntensities[i].name()))
				return true;	
		}
		return false;
	}

	public RadiationAbsorbtion(RadiationAbsorbtion other) {
		super(other.getYear(), other.getMonth());	
		this.radiationIntensity = other.radiationIntensity;
		this.unitAbsorbtionValue = other.unitAbsorbtionValue;
	}

	public double getRadiationAbsorbtion() {
		return unitAbsorbtionValue;
	}

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

	@Override
	public String toString() {
		return "Radiation Intensity: " + radiationIntensity + ", Unit Absorbtion Value: "
				+ String.format("%.1f", unitAbsorbtionValue);
	}

	

}
