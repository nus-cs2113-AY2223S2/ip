package Commands;

import java.time.LocalDate;

public class handleDate {

    /**
     * parses a user given string into LocalDate time format
     *
     * @param UserInput - string from user that contains the date as a string
     * @return the processed date in Localdate format
     */
    public static LocalDate getDate(String UserInput) {
        String date = UserInput.substring(UserInput.indexOf("/by") + 4);
        return LocalDate.parse(date);
    }

}
