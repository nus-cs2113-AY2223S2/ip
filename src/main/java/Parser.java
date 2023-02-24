public class Parser {
    public static String[] parseCommand(String userInput) {
        return userInput.split(" ", 2);
    }
}
