package duke;

import java.util.ArrayList;

public class Parser {
    public Parser() {
    }
    public String[] parseCommand(String command) {
        return command.split(" ", 2);
    }
    public ArrayList<Task> parseDeadline(String[] savedDataLine, ArrayList<Task>storedTasks) {
        Deadline deadline = new Deadline(savedDataLine[2], savedDataLine[3]);
        if (savedDataLine[1].equals("[X]")) {
            deadline.setDone(true);
        }
        storedTasks.add(deadline);
        return storedTasks;
    }
    public ArrayList<Task> parseTodo(String[] savedDataLine, ArrayList<Task>storedTasks){
        Todo todo = new Todo(savedDataLine[2]);
        if (savedDataLine[1].equals("[X]")) {
            todo.setDone(true);
        }
        storedTasks.add(todo);
        return storedTasks;
    }
    public ArrayList<Task> parseEvent(String[] savedDataLine, ArrayList<Task>storedTasks){
        String[] period = savedDataLine[3].split(",");
        Event event = new Event(savedDataLine[2], period[0], period[1]);
        if (savedDataLine[1].equals("[X]")) {
            event.setDone(true);
        }
        storedTasks.add(event);
        return storedTasks;
    }

}
