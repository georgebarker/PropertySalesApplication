package model;

public enum PropertyType {
	DETATCHED("D"), SEMI_DETATCHED("S"), TERRACED("T"), FLATS_MAISONETTES("F"), OTHER("O");

	String code;

	PropertyType(String code) {
		this.code = code;
	}

	public static PropertyType getTypeByCode(String code) {
		if (code == null)
			return null;
		switch (code) {
		case "D":
			return DETATCHED;
		case "S":
			return SEMI_DETATCHED;
		case "T":
			return TERRACED;
		case "F":
			return FLATS_MAISONETTES;
		case "O":
			return OTHER;
		default:
			return null;
		}
	}

	public static String getTypeString(PropertyType type) {
		switch (type) {
		case DETATCHED:
			return "Detatched";
		case SEMI_DETATCHED:
			return "Semi detatched";
		case TERRACED:
			return "Terraced";
		case FLATS_MAISONETTES:
			return "Flat or maisonette";
		case OTHER:
			return "Other";
		default:
			return null;
		}
	}
}
