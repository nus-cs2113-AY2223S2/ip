package Commands;

import java.time.LocalDate;

public class handleDate {
    public static LocalDate getDate(String UserInput) {
        String date = UserInput.substring(UserInput.indexOf("/by") + 4);
        return LocalDate.parse(date);
    }

}
