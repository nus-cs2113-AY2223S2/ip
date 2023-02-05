package commandhandler;

public class InputParser {
    public static Command parseInput(String input) {
        String[] parts = input.split(" /");

        String[] mainPart = parts[0].split(" ", 2);
        String action = mainPart[0];
        String details = mainPart.length > 1 ? mainPart[1] : null;

        String start = null;
        String end = null;
        String due = null;

        for (int i = 1; i < parts.length; i++) {
            String[] option = parts[i].split(" ", 2);
            String optionName = option[0];
            String optionValue = option.length > 1 ? option[1] : null;

            switch (optionName) {
            case "start":
                start = optionValue;
                break;
            case "end":
                end = optionValue;
                break;
            case "due":
                due = optionValue;
                break;
            }
        }

        if (action != null) {
            action = action.trim();
        }
        if (details != null) {
            details = details.trim();
        }
        if (start != null) {
            start = start.trim();
        }
        if (end != null) {
            end = end.trim();
        }
        if (due != null) {
            due = due.trim();
        }

        return new Command(action, details, start, end, due);
    }
}