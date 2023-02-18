package Exceptions;

public class InvalidDateException extends DukeException {
    public InvalidDateException(String date) {
        super(
            "Apologies, I do not understand this date: " + 
            date + 
            "!\nPlease format your dates as yyyy-MM-dd [HH:mm]");
    }
}
