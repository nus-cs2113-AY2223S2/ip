import java.util.ArrayList;
import java.util.regex.Pattern;

public class ToDo extends Task {
    public static final String MARKER = "T";
    private static final Pattern pattern = Pattern.compile(
            "^(\\S+[\\s*\\S]*)$",
            Pattern.CASE_INSENSITIVE);

    public ToDo(String name) {
        super(name);
    }

    public ToDo(ArrayList<String> details) {
        super(details.get(0));
    }

    @Override
    public String describe() {
        return getCheckbox(true, MARKER) + super.describe();
    }

    public static boolean isValidInput(String input) {
        return Task.isValidInput(input, pattern);
    }

    public static ArrayList<String> convertInputIntoDetails(String input) {
        ArrayList<String> result = new ArrayList<>();
        if (!isValidInput(input)) {
            return result;
        }
        result.add(input);
        return result;
    }
}
