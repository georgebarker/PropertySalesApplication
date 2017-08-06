package model;

public enum PropertyLeaseType {
    FREEHOLD("F"), LEASEHOLD("L");

    String code;
    PropertyLeaseType(String code) {
        this.code = code;
    }

    public static PropertyLeaseType getTypeByCode(String code) {
        if (code == null) return null;
        switch (code) {
            case "F":
                return FREEHOLD;
            case "L":
                return LEASEHOLD;
            default:
                return null;
        }
    }
}
