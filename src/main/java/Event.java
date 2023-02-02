import java.util.ArrayList;
import java.util.regex.Pattern;

public class Event extends Task {
    public static final String MARKER = "E";
    private static final String KEYWORD_FROM = "/from";
    private static final String KEYWORD_TO = "/to";
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

        int fromStartIndex = input.indexOf(KEYWORD_FROM);
        int toStartIndex = input.indexOf(KEYWORD_TO);
        result.add(input.substring(0, fromStartIndex).trim());
        result.add(input.substring(fromStartIndex + KEYWORD_FROM.length(), toStartIndex).trim());
        result.add(input.substring(toStartIndex + KEYWORD_TO.length()).trim());
        return result;
    }
}
