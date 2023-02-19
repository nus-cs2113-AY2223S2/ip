package duke.tasks;

import duke.exceptions.InvalidTaskFormatException;

import java.util.ArrayList;
import java.util.function.Function;
import java.util.regex.Pattern;

public class ToDo extends Task {
    public static final String MARKER = "T";
    private static final Pattern pattern = Pattern.compile(
            "^(\\S+[\\S\\s]*)$",
            Pattern.CASE_INSENSITIVE);

    public ToDo(ArrayList<String> details) {
        super(details.get(0), TaskEnum.TODO);
    }

    @Override
    public String describe() {
        return getCheckbox(true, MARKER) + super.describe();
    }

    public static boolean isValidInput(String input) {
        return Task.isValidInput(input, pattern);
    }

    public static ArrayList<String> convertInputIntoDetails(String input) throws InvalidTaskFormatException {
        ArrayList<String> result = new ArrayList<>();
        if (!isValidInput(input)) {
            throw new InvalidTaskFormatException(TaskEnum.TODO);
        }
        result.add(input);
        return result;
    }
}
