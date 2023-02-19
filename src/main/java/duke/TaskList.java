package duke;

import duke.exceptions.InvalidCommandException;
import duke.exceptions.InvalidIndexException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;


import java.util.ArrayList;


public class TaskList implements java.io.Serializable {

    private ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public void listDisplay() {
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i += 1) {
            // print index of task
            System.out.print(" " + (i + 1) + ".");

            // list the details about the task. Based on whether the task is ToDo, Deadline or Event.
            System.out.println(taskList.get(i));
        }
    }

    private void printSuccessfulAddMessage(Task currTask) {
        System.out.println(" Got it. I've added this task: ");
        System.out.println("  " + currTask);
        System.out.println(" Now you have " + taskList.size() + " tasks in the list.");

    }

    private void addToDo(String taskName) {
        taskList.add(new Todo(taskName));
    }

    private void addDeadline(String taskDetails) throws InvalidCommandException {
        int byIndex = taskDetails.indexOf(" /by ");
        if (byIndex == -1) {
            throw new InvalidCommandException();
        }
        String taskName = taskDetails.substring(0, byIndex);
        String dueDate = taskDetails.substring(byIndex + 5); // rest of string after " /by "
        taskList.add(new Deadline(taskName, dueDate));
    }

    private void addEvent(String taskDetails) throws InvalidCommandException {
        int fromIndex = taskDetails.indexOf(" /from ");
        int toIndex = taskDetails.indexOf(" /to ");
        if (fromIndex == -1 || toIndex == -1) {
            throw new InvalidCommandException();
        }
        String taskName = taskDetails.substring(0, fromIndex);
        String startTime = taskDetails.substring(fromIndex + 7, toIndex);
        String endTime = taskDetails.substring(toIndex + 5);
        taskList.add(new Event(taskName, startTime, endTime));
    }


    public void listAdd(String sentence) throws InvalidCommandException {
        String[] words = sentence.split(" ", 2); // split sentence only on first occurrence of space
        String taskType = words[0];

        switch (taskType) {
        case "todo":
            try {
                addToDo(words[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Invalid todo command! Task description cannot be empty");
                return;
            }
            break;
        case "deadline":
            try {
                addDeadline(words[1]);
            } catch (InvalidCommandException e) {
                System.out.println("Invalid deadline command!");
                return;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Invalid deadline command! Task description cannot be empty");
                return;
            }
            break;
        case "event":
            try {
                addEvent(words[1]);
            } catch (InvalidCommandException e) {
                System.out.println("Invalid event command!");
                return;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Invalid event command! Task description cannot be empty");
                return;
            }
            break;
        default:
            throw new InvalidCommandException();
        }

        printSuccessfulAddMessage(taskList.get(taskList.size() - 1)); // get latest task in taskList
    }


    // check if index represented by string is valid, if valid, return index in integer form.
    // else return -1.
    private int parseIndex(String indexString) throws InvalidIndexException {
        int indexInt;

        indexInt = Integer.parseInt(indexString);
        indexInt -= 1; // convert to 0-index
        if (indexInt >= taskList.size() || indexInt < 0) {
            throw new InvalidIndexException();
        }
        return indexInt;
    }


    public void markTask(String indexString) {
        int indexInt;
        try {
            indexInt = parseIndex(indexString);
            Task currTask = taskList.get(indexInt);
            currTask.setIsComplete(true);
            System.out.println(" Nice! I've marked this task as done:");
            System.out.print("   " + currTask.taskTypeBoxFormat() + currTask.markedBoxFormat() + " ");
            System.out.println(taskList.get(indexInt).getTaskName());
        } catch (NumberFormatException e) {
            System.out.println("Given index is not a number!");
        } catch (InvalidIndexException e) {
            System.out.println("Given index is invalid!");
        }
    }


    public void unmarkTask(String indexString) {
        int indexInt;

        try {
            indexInt = parseIndex(indexString);
            Task currTask = taskList.get(indexInt);
            currTask.setIsComplete(false);

            System.out.println(" OK, I've marked this task as not done yet:");
            System.out.println("   " + currTask.taskTypeBoxFormat() + currTask.markedBoxFormat() + " "
                    + currTask.getTaskName());
        } catch (NumberFormatException e) {
            System.out.println("Given index is not a number!");
        } catch (InvalidIndexException e) {
            System.out.println("Given index is invalid!");
        }
    }

    public void deleteTask(String indexString) {
        int indexInt;

        try {
            indexInt = parseIndex(indexString);
            Task removedTask = taskList.get(indexInt);
            taskList.remove(indexInt);

            System.out.println(" Noted. I've removed this task:");
            System.out.println("   " + removedTask.taskTypeBoxFormat() + removedTask.markedBoxFormat() +
                    " " + removedTask.getTaskName());
            System.out.println("Now you have " + taskList.size() + " tasks in the list.");

        } catch (NumberFormatException e) {
            System.out.println("Given index is not a number!");
        } catch (InvalidIndexException e) {
            System.out.println("Given index is invalid!");
        }
    }


}
