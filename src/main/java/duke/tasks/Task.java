package duke.tasks;

import com.sun.jdi.InvalidLineNumberException;
import duke.exceptions.InvalidTaskFormatException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task {
    private final String name;
    private boolean isCompleted;
    private final TaskEnum type;
    private static final String checkbox = "X";

    public Task(String name, TaskEnum type) {
        this.name = name;
        isCompleted = false;
        this.type = type;
    }

    public Task(ArrayList<String> details) {
        this(details.get(0), TaskEnum.UNDEFINED);
    }

    public void setIsCompleted(boolean state) {
        isCompleted = state;
    }

    public static String getCheckbox(boolean checked) {
        return "[" + (checked ? checkbox : " ") + "]";
    }

    public static String getCheckbox(boolean checked, String marker) {
        return "[" + (checked ? marker : " ") + "]";
    }

    public String describe() {
        return getCheckbox(isCompleted) + " " + name;
    }

    public static boolean isValidInput(String input, Pattern pattern) {
        Matcher matcher = pattern.matcher(input.trim());
        return matcher.find();
    }
    public static ArrayList<String> convertInputIntoDetails(String input) throws InvalidTaskFormatException {
        ArrayList<String> result = new ArrayList<>();
        if (input.isEmpty()) {
            throw new InvalidTaskFormatException();
        }
        result.add(input);
        return result;
    }
}
