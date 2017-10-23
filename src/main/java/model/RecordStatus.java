package model;

public enum RecordStatus {
    ADDITION("A"), CHANGE("C"), DELETE("D");

    String code;
    RecordStatus(String code) {
        this.code = code;
    }

    public static RecordStatus getTypeByCode(String code) {
        if (code == null) return null;
        switch(code) {
            case "A":
                return ADDITION;
            case "C":
                return CHANGE;
            case "D":
            return DELETE;
            default:
                return null;
        }
    }
}
