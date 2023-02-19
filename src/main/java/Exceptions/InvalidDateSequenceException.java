package Exceptions;

import java.time.LocalDateTime;

import EntityUtils.DateParser;

/**
 * Called when start date is later than end date
 */
public class InvalidDateSequenceException extends DukeException {
    /**
     * Constructor for printing error
     * @param startDate
     * @param endDate
     */
    public InvalidDateSequenceException(LocalDateTime startDate, LocalDateTime endDate) {
        super(DateParser.dateToString(startDate) + " cannot be after " + DateParser.dateToString(endDate) + "!");
    }
}
