package duke.ui;

import duke.data.TaskList;
import duke.exceptions.DukeException;
import duke.filemanager.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Ui {

    /**
     * Prints out the greeting message to the user
     */
    public static void greetUser() {
        String logo = " .----------------.  .----------------.  .----------------.  .----------------. \n" +
                "| | |_   ___ `.  | || ||_   _||_   _|| || | |_  ||_  _|  | || | |_   ___  |  | |\n" +
                "| |   | |   `. \\ | || |  | |    | |  | || |   | |_/ /    | || |   | |_  \\_|  | |\n" +
                "| |   | |    | | | || |  | '    ' |  | || |   |  __'.    | || |   |  _|  _   | |\n" +
                "| |  _| |___.' / | || |   \\ `--' /   | || |  _| |  \\ \\_  | || |  _| |___/ |  | |\n" +
                "| | |________.'  | || |    `.__.'    | || | |____||____| | || | |_________|  | |\n" +
                " '----------------'  '----------------'  '----------------'  '----------------' ";
        System.out.println("Hello from\n" + logo);
        String greetMessage = "Hello! I'm Duke\n"
                + "Send me a list of things to remember!\n"
                + "Type <bye> to exit";
        System.out.println(greetMessage);
    }

    public void showLine() {
        System.out.println("===================");
    }

    public void errorMessage(String errorText) {
        System.out.println(errorText);
        System.out.println("xxxxxxxxxxxxxxxxx");
    }

    public void printTaskDeadline(Deadline deadline, TaskList tasks) {
        System.out.println("Got it! Added \n"
                + "[D][ ] " + deadline.getDescription() + deadline.getDueBy() + "\n"
                + "to the list.");
        System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
    }

    public void printTaskEvent(Event event, TaskList tasks) {
        System.out.println("Got it! Added \n"
                + "[E][ ] " + event.getDescription() + event.getDuration() + "\n"
                + "to the list.");
        System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
    }

    public void printTaskTodo(Task task, TaskList tasks) {
        System.out.println("Got it! Added \n"
                + "[T][ ]" + task.getDescription() + "\n"
                + "to the list.");
        System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
    }

    public void printDeletedTask(Task task, TaskList tasks) {
        System.out.println("Noted sir, I have removed \n"
                + task.getTaskType() + "[ ]" + task.getDescription() + "\n"
                + "from the list");
        System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
    }

    public void printMarkedTask(Task task) {
        System.out.println("Noted sir, I have marked \n"
                + task.getTaskType() + "[X] " + task.getDescription() + "\n"
                + "as done.");
    }

    public void printUnmarkedTask(Task task) {
        System.out.println("Noted sir, I have marked \n"
                + task.getTaskType() + "[ ]" + task.getDescription() + "\n"
                + "as not done.");
    }

    public void printTasks(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        if (tasks.size() == 0) {
            System.out.println("There are no tasks in your list!");
            return;
        }
        ArrayList<Task> readableList = tasks.getReadableList();
        int count = 1;
        for (Task i : readableList) {
            System.out.println(count + ". " + i.getTaskStatus());
            count++;
        }
        System.out.println("Now you have " + readableList.size() + " tasks in the list.");
    }

    public void printSorted(Storage storage) throws DukeException {
        System.out.println("Sorting the list for you...");
        TaskList toSort = new TaskList(storage.setTasks()); //temp TaskList to sort and discard
        toSort.sortTaskList();
        printTasks(toSort);
    }

    public void sortTasks(TaskList tasks, Storage storage) throws DukeException {
        System.out.println("Sorting and saving the list for you...");
        tasks.sortTaskList();
        printTasks(tasks);
        tasks.saveList(storage);
    }


}
