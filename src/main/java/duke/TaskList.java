package duke;

import duke.exceptions.MissingDescriptionException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    public static final String LINE = "    ____________________________________________________________\n";
    public static final String NOT_DONE = "    OK :(, I've marked this task as not done yet: \n    ";
    public static final String DONE = "    Nice! I've marked this task as done: \n    ";
    public TaskList() {}

    public static void editMarkStatus(ArrayList<Task> tasks, String[] words, String command) {
        int index = Integer.parseInt(words[1]) - 1;
        if (command.equals("unmark")) {
            tasks.get(index).setDone(false);
            System.out.print(LINE + NOT_DONE);
        } else {
            tasks.get(index).setDone(true);
            System.out.print(LINE + DONE);
        }
        System.out.print(tasks.get(index).toString() + "\n" + LINE);
    }

    public static void addEvent(ArrayList<Task> tasks, String[] words) throws MissingDescriptionException {
        if (words.length != 2) {
            throw new MissingDescriptionException();
        }
        String[] description = words[1].split(" /from ");
        if (description.length != 2) {
            throw new MissingDescriptionException();
        }
        String[] dates = description[1].split(" /to ");
        if (dates.length != 2) {
            throw new MissingDescriptionException();
        }
        Event event = new Event(description[0], "E", dates[0], dates[1]);
        tasks.add(event);
    }

    public static void addDeadline(ArrayList<Task> tasks, String[] words) throws MissingDescriptionException {
        if (words.length != 2) {
            throw new MissingDescriptionException();
        }
        String[] description = words[1].split(" /by ");
        if (description.length != 2) {
            throw new MissingDescriptionException();
        }
        Deadline deadline = new Deadline(description[0], "D", description[1]);
        tasks.add(deadline);
    }

    public static void addTodo(ArrayList<Task> tasks, String[] words) throws MissingDescriptionException {
        if (words.length != 2) {
            throw new MissingDescriptionException();
        }
        String description = words[1];
        Task todo = new Task(description, "T");
        tasks.add(todo);
    }

    public static void deleteTask(ArrayList<Task> tasks, String[] words) {
        int index = Integer.parseInt(words[1]) - 1;
        System.out.print(LINE + "    Noted. I've removed this task:\n      " +
                tasks.get(index).toString());
        tasks.remove(index);
        System.out.print("\n    Now you have " + tasks.size() + " tasks in the list.\n" +
                LINE);
    }
}
