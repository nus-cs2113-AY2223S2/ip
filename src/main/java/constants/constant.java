package constants;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class constant {
    public static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\____|_|\\_\\___|\n";
    public static final LocalDate today = LocalDate.now();
    public static String todayDate = today.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
    public static final String HORIZONTAL_LINE = "============================================================";
    public static final String DASH_LINE = "------------------------------------------------------------";
}