import java.util.Objects;

public class Parser {
    public static String[] parse(String userInput) throws DukeException {
        String arr[] = userInput.split(" ", 2);
        return arr;


    }

    static void handleBye() {
        UI.printBye();
        System.exit(0);
    }

    static void handleList() {
        TasksList.processList();
    }

    static void handleMark(String remainingWords) {

        int i = Integer.parseInt(remainingWords) - 1;
        TasksList.addMark(remainingWords, i);
    }

    static void handleUnmark(String remainingWords) {

        int i = Integer.parseInt(remainingWords) - 1;
        TasksList.unMark(remainingWords, i);
    }

    static void handleDelete(String remainingWords) {
        int i = Integer.parseInt(remainingWords) - 1;
        TasksList.deleteTask(i);
    }

    static void handleTodo(String remainingWords) {
        TasksList.addTask(new Todo(remainingWords));
    }

    static void handleDeadline(String remainingWords) {
        String[] parts = remainingWords.split("/");
        TasksList.addTask(new Deadline(parts[0], parts[1]));
    }

    static void handleEvent(String remainingWords) {
        String[] parts = remainingWords.split("/");
        TasksList.addTask(new Event(parts[0], parts[1], parts[2]));
    }

    static void handleFind(String remainingWords) {
        TasksList.findTasks(remainingWords);
    }
}
