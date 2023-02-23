package duke.tasks;

import com.sun.jdi.InvalidLineNumberException;
import duke.exceptions.InvalidTaskFormatException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parent class for all tasks created. Can be initialised as a generic task
 */
public class Task {
    private final String name;
    private boolean isCompleted;
    private final TaskEnum type;
    private static final String checkbox = "X";

    /**
     * Initialise the Task object.
     *
     * @param name Description of the task
     * @param type TaskEnum, for deserializing from JSON
     */
    public Task(String name, TaskEnum type) {
        this.name = name;
        isCompleted = false;
        this.type = type;
    }

    public Task(ArrayList<String> details) {
        this(details.get(0), TaskEnum.UNDEFINED);
    }

    /**
     * Set whether the task is completed or not.
     *
     * @param state true if task is completed, false otherwise
     */
    public void setIsCompleted(boolean state) {
        isCompleted = state;
    }

    /**
     * A helper function which visualises the task status, uses the default marker X.
     *
     * @param checked Status of the task
     * @return String visualising if the task is checked [X] or not [ ]
     */
    public static String getCheckbox(boolean checked) {
        return "[" + (checked ? checkbox : " ") + "]";
    }

    /**
     * A helper function which visualises the task status, uses the marker provided.
     * Is also used for showing the type of the task - [T], [D], [E].
     *
     * @param checked Status of the checkbox
     * @param marker  Marker to be shown
     * @return String showing checkbox with marker if true, without if false
     */
    public static String getCheckbox(boolean checked, String marker) {
        return "[" + (checked ? marker : " ") + "]";
    }

    /**
     * Describes the task with its status and description.
     *
     * @return String describing the task
     */
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
