package duke;

import java.io.IOException;
import java.util.ArrayList;

import static java.util.stream.Collectors.toList;

public class TaskList {

    /**
     * Finds all tasks that contains the string task and returns it to the user
     * @param tasks The ArrayList that contains all the tasks currently stored
     * @param task The keyword of the task that the user is finding
     * @return All the tasks that contains the keyword that the user is finding
     * @throws DukeException Exception thrown when the task param is a white space
     */
    public static ArrayList<Task> find_Input(ArrayList<Task> tasks, String task) throws DukeException {
        if (task == " ") {
            throw new DukeException();
        }
        return (ArrayList<Task>) tasks.stream()
                .filter(t -> t.description.contains(task))
                .collect(toList());
    }

    /**
     * Adds a new Event object into the tasks ArrayList and stores it locally
     * @param tasks The ArrayList that contains all the tasks currently stored
     * @param task The keyword of the task that the user is finding
     * @throws DukeException Exception thrown when the task param is a white space
     * @throws IOException Exception thrown if an error occurs in the .saveContents() method
     */
    public static void event_Input(ArrayList<Task> tasks, String task) throws DukeException, IOException {
        if (task == " ") {
            throw new DukeException();
        }
        System.out.println("Got it. I've added this task:");
        String taskAsArray[] = task.split("/");
        Event obj = new Event(taskAsArray[0], taskAsArray[1], taskAsArray[2]);
        tasks.add(obj); //.toString is automatically called;
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        FileHandling.saveContents();
    }

    /**
     * Adds a new Deadline object into the tasks ArrayList and stores it locally
     * @param tasks The ArrayList that contains all the tasks currently stored
     * @param task The keyword of the task that the user is finding
     * @throws DukeException Exception thrown when the task param is a white space
     * @throws IOException Exception thrown if an error occurs in the .saveContents() method
     */
    public static void deadline_Input(ArrayList<Task> tasks, String task) throws DukeException, IOException {
        if (task == " ") {
            throw new DukeException();
        }
        System.out.println("Got it. I've added this task:");
        String taskAsArray[] = task.split("/");
        Deadline obj = new Deadline(taskAsArray[0], taskAsArray[1]);
        tasks.add(obj);
        System.out.println(obj); //.toString is automatically called;
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        FileHandling.saveContents();

    }

    /**
     * Adds a new Todo object into the tasks ArrayList and stores it locally
     * @param tasks The ArrayList that contains all the tasks currently stored
     * @param task The keyword of the task that the user is finding
     * @throws DukeException Exception thrown when the task param is a white space
     * @throws IOException Exception thrown if an error occurs in the .saveContents() method
     */
    public static void todo_Input(ArrayList<Task> tasks, String task) throws DukeException, IOException {
        if (task == " ") {
            throw new DukeException();
        }
        System.out.println("Got it. I've added this task:");
        Todo obj = new Todo(task);
        tasks.add(obj);
        System.out.println(obj); //.toString is automatically called;
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        FileHandling.saveContents();

    }

    /**
     * Marks a task as done and stores this change locally
     * @param tasks The ArrayList that contains all the tasks currently stored
     * @param task The keyword of the task that the user is finding
     * @throws DukeException Exception thrown when the task param is a white space
     * @throws IOException Exception thrown if an error occurs in the .saveContents() method
     */
    public static void mark_Input(ArrayList<Task> tasks, String task) throws DukeException, IOException {
        if (task == " ") {
            throw new DukeException();
        }
        int taskNoInt = Integer.parseInt(task);
        tasks.get(taskNoInt - 1).markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(taskNoInt - 1).toString());
        FileHandling.saveContents();
    }

    /**
     * Marks a task as undone and stores this change locally
     * @param tasks The ArrayList that contains all the tasks currently stored
     * @param task The keyword of the task that the user is finding
     * @throws DukeException Exception thrown when the task param is a white space
     * @throws IOException Exception thrown if an error occurs in the .saveContents() method
     */
    public static void unmark_Input(ArrayList<Task> tasks, String task) throws DukeException, IOException {
        if (task == " ") {
            throw new DukeException();
        }
        int taskNoInt = Integer.parseInt(task);
        tasks.get(taskNoInt - 1).markAsUndone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks.get(taskNoInt - 1).toString());
        FileHandling.saveContents();
    }

    /**
     * Deletes a task from the tasks ArrayList and stores this change locally
     * @param tasks The ArrayList that contains all the tasks currently stored
     * @param task The keyword of the task that the user is finding
     * @throws DukeException Exception thrown when the task param is a white space
     * @throws IOException Exception thrown if an error occurs in the .saveContents() method
     */
    public static void delete(ArrayList<Task> tasks, String task) throws DukeException, IOException {
        if (task == " ") {
            throw new DukeException();
        }
        int taskNoInt = Integer.parseInt(task);
        System.out.println("Noted. I've removed this task: ");
        System.out.println(tasks.get(taskNoInt - 1).toString());
        tasks.remove(taskNoInt - 1);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        FileHandling.saveContents();
    }

    /**
     * List out all the elements currently in the tasks ArrayList
     * @param tasks The ArrayList that contains all the tasks currently stored
     */
    public static void list_Input(ArrayList<Task> tasks) {
        System.out.println("Here are the tasks in your list:");
        int index = 1;
        for (Task task : tasks) {
            System.out.println(index + "." + task.toString());
            ++index;
        }
    }
}
