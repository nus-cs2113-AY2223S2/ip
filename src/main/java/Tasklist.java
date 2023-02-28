import java.util.ArrayList;

public class Tasklist {

    private static final int FROM_LEN = 6;
    private static final int BY_LEN = 4;

    /** Adds a todo-type task */
    static void addTodo(ArrayList<Task> tasks, String taskDescription) {
        Task newTodo = new Todo(taskDescription);
        tasks.add(newTodo);
        System.out.println("Created todo: " + Ui.printTask(newTodo));
    }

    /** Adds a deadline-type task */
    static void addDeadline(ArrayList<Task> tasks, String taskDescription) {
        int deadlineByIndex = taskDescription.indexOf("/");

        String deadlineDescription = taskDescription.substring(0, deadlineByIndex);
        String deadlineBy = taskDescription.substring(deadlineByIndex + BY_LEN);

        Task newDeadline = new Deadline(deadlineDescription, deadlineBy);
        tasks.add(newDeadline);
        System.out.println("Created deadline: " + Ui.printTask(newDeadline));
    }

    /** Adds an event-type task */
    static void addEvent(ArrayList<Task> tasks, String taskDescription) {
        int eventStartIndex = taskDescription.indexOf("/");
        int eventEndIndex = taskDescription.lastIndexOf("/");

        String eventDescription = taskDescription.substring(0, eventStartIndex);
        String eventStart = taskDescription.substring(eventStartIndex + FROM_LEN, eventEndIndex);
        String eventEnd = taskDescription.substring(eventEndIndex + BY_LEN);

        Task newEvent = new Event(eventDescription, eventStart, eventEnd);
        System.out.println("Created event: " + Ui.printTask(newEvent));
        tasks.add(newEvent);

    }

    /** Mark a task as complete */
    static void markTask(ArrayList<Task> tasks, int taskNo) {
        tasks.get(taskNo).markAsDone();
    }

    /** Mark a task as incomplete */
    static void unmarkTask(ArrayList<Task> tasks, int taskNo) {
        tasks.get(taskNo).unmarkAsDone();
    }

    /** Delete a task by task number */
    static void deleteTask(ArrayList<Task> tasks, int taskNo) {
        if (tasks.size() > 0) {
            System.out.println("Got it! This task will be removed:");
            System.out.println(Ui.printTask(tasks.get(taskNo)));
            tasks.remove(taskNo);
            System.out.println("Number of tasks left: " + tasks.size());

        } else {
            System.out.println("There are no tasks in the list to remove!");
        }
    }
}
