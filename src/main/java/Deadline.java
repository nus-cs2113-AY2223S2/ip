import java.util.ArrayList;
import java.util.regex.Pattern;

public class Deadline extends Task {
    public static final String MARKER = "D";
    private static final String KEYWORD_BY = "/by";
    private final String by;
    private static final Pattern pattern = Pattern.compile(
            "^(\\S+[\\S\\s]*)(\\s+/by\\s+)(\\S+[\\S\\s]*)$",
            Pattern.CASE_INSENSITIVE);

    public Deadline(ArrayList<String> details) {
        super(details.get(0));
        this.by = details.get(1);
    }

    @Override
    public String describe() {
        return getCheckbox(true, MARKER)
                + super.describe()
                + " (by: " + by + ")";
    }

    public static boolean isValidInput(String input) {
        return Task.isValidInput(input, pattern);
    }

    public static ArrayList<String> convertInputIntoDetails(String input) {
        ArrayList<String> result = new ArrayList<>();
        if (!isValidInput(input)) {
            return result;
        }

        int byStartIndex = input.indexOf(KEYWORD_BY);
        result.add(input.substring(0, byStartIndex)
                        .trim());
        result.add(input.substring(byStartIndex + KEYWORD_BY.length())
                        .trim());
        return result;
    }
}
