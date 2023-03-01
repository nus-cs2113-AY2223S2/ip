package duke;

import duke.commands.Ui;
import duke.exceptions.IncorrectDeadlineException;
import duke.exceptions.IncorrectEventException;

import java.util.ArrayList;

public abstract class TaskList{

    private static ArrayList<Task> tasks = new ArrayList<>();

    public static ArrayList<Task> getTaskList() {
        return tasks;
    }

    public static int getTaskSize() {
        return tasks.size();
    }

    public static void printTaskList() {
        for (int i = 0; i < getTaskSize(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i).toString());
        }
    }
    public static void addTodo (String command) {
        Todo todo = new Todo(command);
        tasks.add(todo);
        Ui.printTask(todo.toString(), TaskList.getTaskSize());
    }

    public static void addDeadline (String command) throws IncorrectDeadlineException {
        if (command.indexOf("/by") == -1) {
                throw new IncorrectDeadlineException();
        }
        String[] message = command.split(" /by", 2);
        Deadline deadline = new Deadline(message[0], message[1]);
        tasks.add(deadline);
        Ui.printTask(deadline.toString(), TaskList.getTaskSize());
    }

    public static void addEvent(String command) throws IncorrectEventException {
        if (command.indexOf("/from") == -1 || command.indexOf("/to") == -1) {
            throw new IncorrectEventException();
        }
        String[] message = command.split(" /from");
        String[] period = message[1].split(" /to");
        Event event = new Event(message[0], period[0], period[1]);
        tasks.add(event);
        Ui.printTask(event.toString(), TaskList.getTaskSize());
    }

    public static void deleteTask(int index) {
        Ui.printRemoval(tasks.get(index - 1).toString(), TaskList.getTaskSize() - 1);
        tasks.remove(index);
    }

    public static void markNotDone(int index) {
        tasks.get(index - 1).markAsUndone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks.get(index - 1).printTask());
    }

    public static void markDone(int index) {
        tasks.get(index - 1).markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(index - 1).printTask());
    }

    public static void findTask(String input) {
        int matchCount = 1;
        System.out.println("Here are the matching tasks in your list:");
        for (Integer i = 0; i < getTaskSize(); i++) {
            if (tasks.get(i).description.contains(input)) {
                System.out.println(matchCount + "." + tasks.get(i).toString());
                matchCount ++;
            }
        }
    }
}
