package model;

public enum PropertyType {
    DETATCHED("D"), SEMI_DETATCHED("S"), TERRACED("T"),
    FLATS_MAISONETTES("F"), OTHER("O");

    String code;

    PropertyType(String code) {
        this.code = code;
    }

    public static PropertyType getTypeByCode(String code) {
        if (code == null) return null;
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
}
