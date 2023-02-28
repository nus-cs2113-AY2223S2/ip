package storage;

import static task.TaskList.*;

public class DataParser {
    public static void parseTodo(String data) {
        String task = data.split("|",5)[4];
        String status = data.split("|",5)[2];
        addTodoData(task, status);
    }

    public static void parseDeadline(String data) {
        String task = data.split("|",5)[4];
        String status = data.split("|",5)[2];
        addDeadlineData(task, status);
    }

    public static void parseEvent(String data) {
        String task = data.split("|", 5)[4];
        String status = data.split("|", 5)[2];
        addEventData(task, status);
    }
}
