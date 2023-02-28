package duke.tasklist;

import duke.DukeException;
import duke.ui.Ui;

import java.util.ArrayList;

public class TaskList {
    private static final String line = "__________________________________________________________";
    //private static Ui ui;
    protected static ArrayList<Task> tasks = new ArrayList<>();
    private static int numTasks = 0;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public static ArrayList<Task> getTasks() {
        return tasks;
    }

    public static Task getTask(Integer x) {
        return tasks.get(x-1);
    }
    public static int getNumTasks() {
        return numTasks;
    }

    public static void validTask(String[] userInput) throws IndexOutOfBoundsException{
        if (userInput.length < 2 && (userInput[0].equals("todo") ||
                userInput[0].equals("event") || userInput[0].equals("deadline"))) {
            throw new IndexOutOfBoundsException();
        }
    }

    public static void markValidTask(String[] userInput) throws IndexOutOfBoundsException {
        try {
            int x = Integer.parseInt(userInput[1]);
            if (tasks.get(x-1) == null || tasks.size() == 0) {
                throw new IndexOutOfBoundsException();
            }
            tasks.get(x - 1).markAsDone(userInput[0]);
        } catch (NumberFormatException e) {
            System.out.println("This is not a valid index... unable to mark the task for you.");
        }
    }

    public static void addTask(String userInput) throws DukeException {
        Task t;
        String[] words = userInput.split(" ");
        validTask(words);
        String descriptor = userInput.substring(userInput.indexOf(words[1]), userInput.length());
        if (words[0].equals("todo")) {
            t = new Todo(descriptor);
            Ui.printMessage(t, Ui.CommandType.TODO);
        } else if (words[0].equals("deadline")) {
            String by = descriptor.split("/by ")[1];
            descriptor = descriptor.split("/by ")[0];
            t = new Deadline(descriptor, by);
            Ui.printMessage(t, Ui.CommandType.DEADLINE);
        } else if (words[0].equals("event")) {
            String to = descriptor.split("/to ")[1];
            String from = descriptor.split(" /")[1];
            descriptor = descriptor.split("/")[0];
            t = new Event(descriptor, from, to);
            Ui.printMessage(t, Ui.CommandType.EVENT);
        } else {
            throw new IndexOutOfBoundsException();
        }
        tasks.add(t);
        numTasks = tasks.size();
    }

    public static void deleteTask(String userInput) throws DukeException {
        String taskNum = userInput.substring(userInput.length()-1);
        int x = Integer.parseInt(taskNum);
        if (tasks.get(x-1) == null || tasks.size() == 0) {
            throw new DukeException();
        }
        Task temp = tasks.get(x-1);
        tasks.remove(x-1);
        numTasks = tasks.size();
        Ui.printMessage(temp, Ui.CommandType.DELETE);
    }

    public static void findTasks(String query) throws IndexOutOfBoundsException {
        if (tasks.isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        //System.out.println("testing to for loop?");
        for (Task t : tasks) {
            if (t.description.contains(query)) {
                System.out.println(t.toString());
            }
        }
    }
}
