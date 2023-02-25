package chronos.commandhandler;

/**
 * The InputParser class contains a single public static method named parseInput.
 * This method is used to parse the input string into a Command object so that it can
 * be more easily handled by the machine.
 */
public class InputParser {
    //takes from command and splits it into its components mainly
    //action: what type of task the user wants to add
    //detail: the description
    //for deadline: due
    //for event: start/end

    /**
     * Parses the input string into a Command object.
     *
     * @param input The input string to be parsed
     * @return The Command object created from the input string that has been parsed
     */
    public static Command parseInput(String input) {
        String[] parts = input.split(" /");

        String[] mainPart = parts[0].split(" ", 2);
        String action = mainPart[0];
        //checks if there is anything after the action command 'todo' if there is nothing it is null
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
            default:
                System.err.println("INVALID COMMAND");
            }
        }

        //for each component, eliminate trailing white spaces for easier processing
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