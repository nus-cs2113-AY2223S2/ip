package duke.utils;

public enum Constants {
    // DATA FILE NAME USED FOR APPLIATION. default: items.txt
    FILE_NAME("items.txt"),

    // ACCEPTABLE DATE TIME FORMAT FOR DEADLINE AND EVENT. default: dd-MM-yyyy HH:mm
    ACCEPTABLE_DATE_TIME_FORMAT("dd-MM-yyyy HH:mm"),

    // OUTPUT DATE TIME FORMAT FOR DISPLAYING DATE TIME. default: dd MMM yyyy hh:mm a
    OUTPUT_DATE_TIME_FORMAT("dd MMM yyyy hh:mm a");

    private final String key;

    private Constants(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return key;
    }
}
