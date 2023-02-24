/**
 * This <code>Parser</code> class is used to parse the user input into individual strings to be used by other classes.
 * It provides a single static method, <code>parseCommand</code>, which takes in a String of user input and returns an
 * array of Strings.
 * The returned array contains the first word in the user input as the first element (the user command), and the
 * remainder of the input as the second element.
 * @version v0.2
 * @since 2023-02-24
 */
public class Parser {
    public static String[] parseCommand(String userInput) {
        return userInput.split(" ", 2);
    }
}
