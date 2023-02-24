import tasks.Deadline;
import tasks.Event;
import tasks.Todo;

public class Parser {

    /**
     * Converts the line input by user into the line parts
     * Separated by whitespace
     * There is no \n characters from input line
     * 
     * @param line Line input by user
     * @returns An array of words in the line provided
     */
    public static String[] getLineParts(String line) {
        String[] lineParts = line.split(" ");
        return lineParts;
    }
    
    /**
     * Gets the number of words in the given line.
     * There is no \n characters from input line
     * 
     * @param line Line input by user
     * @returns An array of words in the line provided
     */
    public static int getNumWords(String line) {
        String[] lineParts = getLineParts(line);
        return lineParts.length;
    }

    /**
     * Gets the specific command from the given line.
     * There is no \n characters from input line
     * 
     * @param line Line input by user
     * @returns A string representing the name of the command used
     */
    public static String getCommand(String line) {
        String[] lineParts = getLineParts(line);
        return lineParts[0];
    }

    /**
     * Gets the index of the item selected by the user and converts
     * into an integer (for other use)
     * 
     * @param line Line input by user
     * @returns An integer relating the the index chosen by the user
     */
    public static int getInputIndex(String line) {
        String[] lineParts = getLineParts(line);
        return Integer.parseInt(lineParts[1]);
    }

    /**
     * Creates the event based on the line input
     * Assumes the command is already removed from the line input
     * 
     * @param line Line input by user without the command
     * @returns An Event Obj Instance that represents the event wanted to be created by the user input
     */
    public static Event initEvent(String line) {
        String[] lineParts = line.split(" /from | /to ", 3);
        String itemDescription = lineParts[0];
        String itemFrom = lineParts[1];
        String itemTo = lineParts[2];
        Event item = new Event(itemDescription, itemFrom, itemTo);
        return item;
    }

    /**
     * Creates the Todo Instance based on the line input
     * Assumes the command is already removed from the line input
     * 
     * @param line Line input by user without the command
     * @returns An Todo Obj Instance that represents the Todo wanted to be created by the user input
     */
    public static Todo initTodo(String line) {
        Todo item = new Todo(line);
        return item;
    }

    /**
     * Creates the Deadline Instance based on the line input
     * Assumes the command is already removed from the line input
     * 
     * @param line Line input by user without the command
     * @returns An Deadline Obj Instance that represents the Deadline wanted to be created by the user input
     */
    public static Deadline initDeadline(String line) {
        String[] lineParts = line.split(" /by ", 2);
        String itemDescription = lineParts[0];
        String itemDeadline = lineParts[1];
        Deadline item = new Deadline(itemDescription, itemDeadline);
        return item;
    }
}
