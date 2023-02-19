package Exceptions;

import java.sql.Date;
import java.time.LocalDateTime;

import EntityUtils.DateParser;

public class InvalidDateSequenceException extends DukeException {
    public InvalidDateSequenceException(LocalDateTime startDate, LocalDateTime endDate) {
        super(DateParser.dateToString(startDate) + " cannot be after " + DateParser.dateToString(endDate) + "!");
    }
}
