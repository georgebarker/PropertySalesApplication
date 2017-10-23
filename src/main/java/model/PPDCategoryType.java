package model;

public enum PPDCategoryType {
    STANDARD_PRICE_PAID("A"), ADDITIONAL_PRICE_PAID("B");

    String code;
    PPDCategoryType(String code) {
        this.code = code;
    }

    public static PPDCategoryType getTypeByCode(String code) {
        if (code == null) return null;
        switch(code) {
            case "A":
                return STANDARD_PRICE_PAID;
            case "B":
                return ADDITIONAL_PRICE_PAID;
            default:
                return null;
        }
    }
}
