import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BunnySession {
    private final static String divider = "____________________________________________________________";
    private final static String globalIndentation = "\t";
    private final static String messageIndentation = " ";

    private ArrayList<Todo> todoList;

    public BunnySession() {
        this.todoList = new ArrayList<>();
    }

    public void printMessage(String message) {
        this.printMessage(Arrays.asList(message.split("\n")));
    }

    public void printMessage(Iterable<String> messageLines) {
        String output = "";
        output += globalIndentation + divider + "\n";
        for (String line : messageLines) {
            output += globalIndentation + messageIndentation + line + "\n";
        }
        output += globalIndentation + divider + "\n";

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
