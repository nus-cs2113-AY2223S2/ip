package util;

import java.util.ArrayList;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

/**
 * Stores functions that prints lines accessible to most classes
 */

public class OutputUI {

    /**
     * Prints a Line
     */
    private static void printLine() {
        System.out.println("  ____________________________________________________________");
    }

    /**
     * Prints Marked Message when it is done
     */
    public void markTaskMessage(Task task) {
        System.out.println("Pikapi has marked the task as done\n");
        System.out.println("[" + task.getStatusIcon() + "] " + task.getDescription());
    }

    /**
     * Prints Unmarked Message when it is unmarked
     */
    public void unmarkTaskMessage(Task task) {
        System.out.println("Pikapi has unmarked the task\n");
        System.out.println("[" + task.getStatusIcon() + "] " + task.getDescription());
    }

    /**
     * Parses and prints a task list
     */
    public void addToListMessage(Todo todo, int numTasks) {
        printLine();
        System.out.println("Pikapi add this task: " + "\n" + "  [T][ ]" + todo.getDescription());
        System.out.println("Pikapi sees that now you have " + numTasks + " tasks in the list");
    }

    /**
     * Prints the output when finished adding a deadline Task
     */
    public void addToListMessage(Deadline deadline, int numTasks) {
        printLine();
        System.out.println("Pikapi add this task: " + "\n" + "  [D][ ]"
                + deadline.getDescription() + "(by :" + deadline.getDeadline() + ")");
        System.out.println("Pikapi sees that now you have " + numTasks + " tasks in the list");
    }

    /**
     * Prints the output when finished adding an event Task
     */
    public void addToListMessage(Event event, int numTasks) {
        printLine();
        System.out.println("Pikapi add this task: " + "\n" + "  [E][ ] "
                + event.getDescription() + "(from: " + event.getStartDate() + " to: " + event.getEndDate() + ")");
        System.out.println("Pikapi sees that now you have " + numTasks + " tasks in the list");
    }

    /**
     * Prints the whole list of a given set of tasks in a parsed manner
     */
    public void printList(ArrayList<Task> tasks, int numTasks) {

        if (numTasks == 0) {
            System.out.println("Pikapi's list is completely empty, please add some tasks!");
        } else {
            System.out.println("Here is your list:");
            for (int i = 0; i < numTasks; i++) {
                System.out.println((i + 1) + ". " + tasks.get(i).toString());
            }
        }
    }

    /**
     * Prints the output when finished deleting a task
     */
    public void printDeleteTaskMessage(Task task, int numTasks) {
        printLine();
        String taskSymbol;
        if (task instanceof Todo) {
            taskSymbol = "T";
        } else if (task instanceof Deadline) {
            taskSymbol = "D";
        } else {
            taskSymbol = "E";
        }


        System.out.println("Pikapi has deleted the task: " + "\n" + "  [" + taskSymbol + "]["
                + task.getStatusIcon() + "]" + task.getDescription());
        System.out.println("Pikapi sees that now you have " + numTasks + " tasks in the list");
    }

    /**
     * Prints the output when quitting Duke
     */
    public void printByeByeMessage() {
        System.out.println("Pikapi is surprised to see you go, see you soon friend\n");
    }


    /**
     * Prints a list when queried for tasks for a specific keyword with the corresponding flavour text
     * @param output
     */
    public void printTaskListWithKeyword(ArrayList<Task> output) {
        System.out.println("Pikapi has found a list of things pertaining to your keyword");
        printList(output, output.size());
    }

    public void printNoTaskWithKeyword() {
        System.out.println("No tasks with descriptions containing the keyword is found, Pikapi tried his best");
    }

}
