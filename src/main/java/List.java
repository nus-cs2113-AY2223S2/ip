import com.sun.source.util.TaskListener;

public class List {
    private static final int TASKLIST_SIZE = 100;
    private static final char SPACE = ' ';

    private int numItems = 0;
    private Task[] taskList;

    public List() {
        taskList = new Task[TASKLIST_SIZE]; // Note: only initialise array of obj, but not individual obj
    }

    public void listDisplay() {
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < numItems; i += 1) {
            // print index of task
            System.out.print(" " + (i + 1) + ".");

            // list the details about the task. Based on whether the task is ToDo, Deadline or Event.
            System.out.println(taskList[i].listTask());
        }
    }

    private void printSuccessfulAddMessage(int numItems, Task currTask) {
        System.out.println(" Got it. I've added this task: ");
        System.out.println("  " + currTask.listTask());
        System.out.println(" Now you have " + numItems + " tasks in the list.");

    }

    private void listAddToDo(String taskName) {
        taskList[numItems] = new ToDo(taskName);
    }

    private void listAddDeadline(String taskDetails) throws InvalidCommandException {
        int byIndex = taskDetails.indexOf(" /by ");
        if (byIndex == -1) {
            throw new InvalidCommandException();
        }
        String taskName = taskDetails.substring(0, byIndex);
        String dueDate = taskDetails.substring(byIndex + 5); // rest of string after " /by "
        taskList[numItems] = new Deadline(taskName, dueDate);
    }

    private void listAddEvent(String taskDetails) throws InvalidCommandException {
        int fromIndex = taskDetails.indexOf(" /from ");
        int toIndex = taskDetails.indexOf(" /to ");
        if (fromIndex == -1 || toIndex == -1) {
            throw new InvalidCommandException();
        }
        String taskName = taskDetails.substring(0, fromIndex);
        String startTime = taskDetails.substring(fromIndex + 7, toIndex);
        String endTime = taskDetails.substring(toIndex + 5);
        taskList[numItems] = new Event(taskName, startTime, endTime);
    }


    public void listAdd(String sentence) {
        String[] words = sentence.split(" ", 2); // split sentence only on first occurance of space
        String taskType = words[0];

        switch (taskType) {
        case "todo":
            try {
                listAddToDo(words[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Invalid todo command! Task description cannot be empty");
                return;
            }
            break;
        case "deadline":
            try {
                listAddDeadline(words[1]);
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
                listAddEvent(words[1]);
            } catch (InvalidCommandException e) {
                System.out.println("Invalid event command!");
                return;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Invalid event command! Task description cannot be empty");
                return;
            }
            break;
        default:
            System.out.println("Invalid task type!");
            return;
        }

        numItems += 1;
        printSuccessfulAddMessage(numItems, taskList[numItems - 1]);
    }


    // check if index represented by string is valid, if valid, return index in integer form.
    // else return -1.
    private int parseIndex(String indexString) throws InvalidIndexException {
        int indexInt;

        indexInt = Integer.parseInt(indexString);
        indexInt -= 1; // convert to 0-index
        if (indexInt >= numItems || indexInt < 0) {
            throw new InvalidIndexException();
        }
        return indexInt;
    }


    public void markTask(String indexString) {
        int indexInt;
        try {
            indexInt = parseIndex(indexString);
            taskList[indexInt].setIsComplete(true);
            System.out.println(" Nice! I've marked this task as done:");
            System.out.print("   [X] ");
            System.out.println(taskList[indexInt].getTaskName());
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
            taskList[indexInt].setIsComplete(false);
            System.out.println(" OK, I've marked this task as not done yet:");
            System.out.println("   [ ] " + taskList[indexInt].getTaskName());
        } catch (NumberFormatException e) {
            System.out.println("Given index is not a number!");
        } catch (InvalidIndexException e) {
            System.out.println("Given index is invalid!");
        }
    }
}
