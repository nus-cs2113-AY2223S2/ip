package commands;

import exceptions.ArchdukeException;
import exceptions.ParserException;
import exceptions.ParserException.ParserExceptionCode;

public class Parser {
    public static Command parse(String input) throws ArchdukeException {
        String[] options = input.split(" /");

        // Separating the main command from the parameters aka the body
        String[] components = options[0].split(" ", 2);
        String type = components[0];
        String body = components.length > 1 ? components[1] : null;

        String from = null;
        String to = null;
        String by = null;

        for (int i = 1; i < options.length; i++) {
            String[] option = options[i].split(" ", 2);
            String optionName = option[0];
            String optionValue = option.length > 1 ? option[1] : null;

            switch (optionName) {
            case "from":
                from = optionValue;
                break;
            case "to":
                to = optionValue;
                break;
            case "by":
                by = optionValue;
                break;
            default:
                throw new ParserException(ParserExceptionCode.UNKNOWN_OPTION, optionName);
            }
        }

        if (type != null) {
            type = type.trim();
        }
        if (body != null) {
            body = body.trim();
        }
        if (from != null) {
            from = from.trim();
        }
        if (to != null) {
            to = to.trim();
        }
        if (by != null) {
            by = by.trim();
        }

        return new Command(type, body, from, to, by);
    }
}
