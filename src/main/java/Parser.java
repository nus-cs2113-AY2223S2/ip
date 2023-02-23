import java.util.Objects;

public class Parser {
    public static String[] parse(String userInput) throws DukeException {
        String arr[] = userInput.split(" ", 2);
        return arr;


    }
}
