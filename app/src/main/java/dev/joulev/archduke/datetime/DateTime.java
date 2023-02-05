package dev.joulev.archduke.datetime;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import dev.joulev.archduke.exceptions.ArchdukeException;
import dev.joulev.archduke.exceptions.UserInputException;
import dev.joulev.archduke.exceptions.UserInputException.UserInputExceptionCode;

/**
 * This class provides static methods to handle date-time parsing and
 * formatting.
 */
public class DateTime {
    public static final String INPUT_PATTERN = "dd/MM/yyyy HH:mm:ss";
    public static final String EXAMPLE = "01/01/2021 23:59:59";

    public static final String OUTPUT_PATTERN = "MMM d yyyy, h:mm:ss a";

    private static DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern(INPUT_PATTERN);
    private static DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern(OUTPUT_PATTERN);

    /**
     * Parses a date-time string from user input or stored file to a
     * {@code LocalDateTime} object.
     * 
     * @param dateTime The input string.
     * @return The parsed {@code LocalDateTime} object.
     * @throws ArchdukeException If the input string is not in the correct format.
     */
    public static LocalDateTime parse(String dateTime) throws ArchdukeException {
        try {
            return LocalDateTime.parse(dateTime, inputFormatter);
        } catch (DateTimeParseException e) {
            System.out.println(e);
            throw new UserInputException(UserInputExceptionCode.MALFORMED_DATETIME, dateTime);
        }
    }

    /**
     * Formats a {@code LocalDateTime} object to a string for display.
     * 
     * @param dateTime The {@code LocalDateTime} object.
     * @return The formatted string.
     */
    public static String display(LocalDateTime dateTime) {
        return dateTime.format(outputFormatter);
    }

    /**
     * Formats a {@code LocalDateTime} object to a string for saving to file.
     * 
     * @param dateTime The {@code LocalDateTime} object.
     * @return The formatted string.
     */
    public static String formatForSave(LocalDateTime dateTime) {
        // We use the same format as the input format, as this exact string will be used
        // later as input when we parse the date-time string from the file.
        return dateTime.format(inputFormatter);
    }
}
