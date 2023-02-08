package duke;

public class Parser {

    public String[] parseInput(String userInput) {
        String[] intermediateInput = userInput.split(" ", 2);
        String[] parsedInput = new String[4];
        parsedInput[0] = intermediateInput[0];

        switch (parsedInput[0]) {
            case("list"):
                if (intermediateInput.length > 1) {
                    throw new DukeException();
                }
                break;
            case("deadline"):
                int byIndex = intermediateInput[1].indexOf("/by ");
                parsedInput[1] = intermediateInput[1].substring(0, byIndex - 1);
                parsedInput[2] = intermediateInput[1].substring(byIndex + 4);
                break;
            case("event"):
               int fromIndex = intermediateInput[1].indexOf("/from ");
               int toIndex = intermediateInput[1].indexOf("/to ");
               parsedInput[1] = intermediateInput[1].substring(0, fromIndex - 1);
               parsedInput[2] = intermediateInput[1].substring(fromIndex + 6, toIndex - 1);
               parsedInput[3] = intermediateInput[1].substring(toIndex + 4);
                break;
            case("todo"): case("mark"): case("unmark"):
                parsedInput[1] = intermediateInput[1];
                break;
            default:
                throw new DukeException();
        }
        return parsedInput;
    }
}
