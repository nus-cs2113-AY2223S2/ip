import java.util.ArrayList;
import java.util.regex.Pattern;

public class Event extends Task {
    public static final String MARKER = "E";
    static final String KEYWORD_FROM = "/from";
    static final String KEYWORD_TO = "/to";
    private final String from, to;
    private static final Pattern pattern = Pattern.compile(
            "^(\\S+[\\S\\s]*)(\\s+/from\\s+)(\\S+[\\S\\s]*)(\\s+/to\\s+)(\\S+[\\S\\s]*)$",
            Pattern.CASE_INSENSITIVE);

    public Event(ArrayList<String> details) {
        super(details.get(0));
        this.from = details.get(1);
        this.to = details.get(2);
    }

    @Override
    public String describe() {
        return getCheckbox(true, MARKER) + super.describe()
                + " (from: " + from + " to: " + to + ")";
    }

    public static boolean isValidInput(String input) {
        return Task.isValidInput(input, pattern);
    }

    public static ArrayList<String> convertInputIntoDetails(String input) {
        ArrayList<String> result = new ArrayList<>();
        if (!isValidInput(input)) {
            return result;
        }

        int fromIndex = input.indexOf(KEYWORD_FROM);
        int toIndex = input.indexOf(KEYWORD_TO);
        result.add(input.substring(0, fromIndex).trim());
        result.add(input.substring(fromIndex + KEYWORD_FROM.length(), toIndex).trim());
        result.add(input.substring(toIndex + KEYWORD_TO.length()).trim());
        return result;
    }
}
