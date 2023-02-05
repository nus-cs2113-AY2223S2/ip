package dev.joulev.archduke.datetime;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import dev.joulev.archduke.exceptions.ArchdukeException;
import dev.joulev.archduke.exceptions.UserInputException;
import dev.joulev.archduke.exceptions.UserInputException.UserInputExceptionCode;

public class DateTime {
    public static final String INPUT_PATTERN = "dd/MM/yyyy HH:mm:ss";
    public static final String EXAMPLE = "01/01/2021 23:59:59";

    public static final String OUTPUT_PATTERN = "MMM d yyyy, h:mm:ss a";

    private static DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern(INPUT_PATTERN);
    private static DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern(OUTPUT_PATTERN);

    public static LocalDateTime parse(String dateTime) throws ArchdukeException {
        try {
            return LocalDateTime.parse(dateTime, inputFormatter);
        } catch (DateTimeParseException e) {
            System.out.println(e);
            throw new UserInputException(UserInputExceptionCode.MALFORMED_DATETIME, dateTime);
        }
    }

    public static String display(LocalDateTime dateTime) {
        return dateTime.format(outputFormatter);
    }

    public static String formatForSave(LocalDateTime dateTime) {
        return dateTime.format(inputFormatter);
    }
}
