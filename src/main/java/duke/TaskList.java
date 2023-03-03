package duke;

import exception.EmptyTaskException;
import exception.InvalidTaskNumberException;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

import java.util.ArrayList;

public class TaskList {

    private final Ui ui = new Ui();
    public ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Reads the saveFile and initialise the list of task based on it.
     *
     * @param line Line of text from the saveFile.
     */
    public void initialiseTaskList(String line) {
        String[] task = line.split("[|\\-]");

        switch(task[0].trim()) {
        case "T":
            try {
                addTodoTask(task[2].trim());
            } catch (EmptyTaskException e) {
                System.out.println("This shouldn't be happening :O");
            }
            break;
        case "D":
            addDeadlineTask(task[2].trim(), task[3].trim());
            break;
        case "E":
            addEventTask(task[2].trim(), task[3].trim(), task[4].trim());
            break;
        }

        initialiseTaskStatus(task[1]);
    }

    private void initialiseTaskStatus(String task) {
        if (task.contains("X")) {
            try {
                markTaskDone(tasks.size()-1);
            } catch (NumberFormatException e) {
                System.out.println("☹ OOPS!!! Task number should be an integer.");
                ui.printDivider();
            } catch (InvalidTaskNumberException e) {
                System.out.println("☹ OOPS!!! The task specified does not exist in the task list.");
                ui.printDivider();
            } catch (IndexOutOfBoundsException e) {
                System.out.println("☹ OOPS!!! The description of 'mark' cannot be empty.");
                ui.printDivider();
            }
        }
    }

    /**
     * Prints to CLI the list of task.
     */
    public void printTaskList() {
        if (tasks.size() == 0) {
            System.out.println("You are free today :)");
        } else {
            for (int i = 0; i < tasks.size(); ++i) {
                System.out.print(i+1 + ".");
                System.out.println(tasks.get(i));
            }
        }
        ui.printDivider();
    }


    /**
     * Marks task based on taskIndex as 'done'.
     *
     * @param taskIndex The position of task in the task list.
     * @throws InvalidTaskNumberException Task number inputted exceeds the range of the task list.
     */
    public void markTaskDone(Integer taskIndex) throws InvalidTaskNumberException {
        if (taskIndex < 0 || taskIndex > tasks.size()) {
            throw new InvalidTaskNumberException();
        } else {
            tasks.get(taskIndex).markDone();
        }
    }

    /**
     * Marks task based on taskIndex as 'undone'.
     *
     * @param taskIndex The position of task in the task list.
     * @throws InvalidTaskNumberException Task number inputted exceeds the range of the task list.
     */
    public void markTaskUndone(Integer taskIndex) throws InvalidTaskNumberException {
        if (taskIndex < 0 || taskIndex > tasks.size()) {
            throw new InvalidTaskNumberException();
        } else {
            tasks.get(taskIndex).markUndone();
        }
    }

    /**
     * Adds todo task to the task list.
     *
     * @param task Description of task.
     * @throws EmptyTaskException Description of task is empty.
     */
    public void addTodoTask(String task) throws EmptyTaskException {
        if (task.equals("")) {
            throw new EmptyTaskException();
        } else {
            tasks.add(new ToDo(task));
        }
    }

    /**
     * Adds deadline task to the task list.
     *
     * @param task Description of task.
     * @param deadline Deadline of task.
     */
    public void addDeadlineTask(String task, String deadline) {
        tasks.add(new Deadline(task, deadline));
    }

    /**
     * Adds event task to the task list.
     *
     * @param task Description of task.
     * @param fromDate Start date of event.
     * @param byDate End date of event.
     */
    public void addEventTask(String task, String fromDate, String byDate) {
        tasks.add(new Event(task, fromDate, byDate));
    }

    /**
     * Prints message to show that task has been added successfully.
     */
    public void printTaskAdded() {
        System.out.println("Got it. I've added this task:\n " + tasks.get(tasks.size()-1)
                + "\nNow you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Delete task based on taskIndex.
     *
     * @param taskIndex The position of task in the list.
     * @throws InvalidTaskNumberException Task number inputted exceeds the range of the task list.
     */
    public void deleteTask(int taskIndex) throws InvalidTaskNumberException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new InvalidTaskNumberException();
        } else {
            System.out.println("Noted. I've removed this task:\n " + tasks.get(taskIndex)
                    + "\nNow you have " + (tasks.size()-1) + " tasks in the list.");
            tasks.remove(tasks.get(taskIndex));
        }
    }
}
