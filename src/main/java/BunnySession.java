import java.util.ArrayList;
import java.util.Arrays;

public class BunnySession {
    private final static String DIVIDER = "____________________________________________________________";
    private final static String GLOBAL_INDENTATION = "\t";
    private final static String MESSAGE_INDENTATION = " ";

    private ArrayList<Todo> todoList;

    public BunnySession() {
        this.todoList = new ArrayList<>();
    }

    public void printMessage(String message) {
        this.printMessage(Arrays.asList(message.split("\n")));
    }

    public void printMessage(Iterable<String> messageLines) {
        String output = "";
        output += GLOBAL_INDENTATION + DIVIDER + "\n";
        for (String line : messageLines) {
            output += GLOBAL_INDENTATION + MESSAGE_INDENTATION + line + "\n";
        }
        output += GLOBAL_INDENTATION + DIVIDER + "\n";

        System.out.print(output);
    }

    public void addTodo(Todo todo) {
        this.todoList.add(todo);
    }

    public Todo getTodo(int index) {
        return this.todoList.get(index);
    }

    public int numTodos() {
        return this.todoList.size();
    }
}
