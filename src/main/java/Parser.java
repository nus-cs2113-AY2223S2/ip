import tasks.Deadline;
import tasks.Event;
import tasks.Todo;

public class Parser {
    public static String[] getLineParts(String line) {
        String[] lineParts = line.split(" ");
        return lineParts;
    }
    
    public static int getNumWords(String line) {
        String[] lineParts = getLineParts(line);
        return lineParts.length;
    }

    public static String getCommand(String line) {
        String[] lineParts = getLineParts(line);
        return lineParts[0];
    }

    public static int getInputIndex(String line) {
        String[] lineParts = getLineParts(line);
        return Integer.parseInt(lineParts[1]);
    }

    public static Event initEvent(String line) {
        String[] lineParts = line.split(" /from | /to ", 3);
        String itemDescription = lineParts[0];
        String itemFrom = lineParts[1];
        String itemTo = lineParts[2];
        Event item = new Event(itemDescription, itemFrom, itemTo);
        return item;
    }

    public static Todo initTodo(String line) {
        Todo item = new Todo(line);
        return item;
    }

    public static Deadline initDeadline(String line) {
        String[] lineParts = line.split(" /by ", 2);
        String itemDescription = lineParts[0];
        String itemDeadline = lineParts[1];
        Deadline item = new Deadline(itemDescription, itemDeadline);
        return item;
    }
}
