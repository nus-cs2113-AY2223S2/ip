import com.sun.source.util.TaskListener;

public class List {
    private static final int TASKLIST_SIZE = 100;
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

    private void listAddToDo(String sentence, int spaceIndex) {
        String taskName = sentence.substring(spaceIndex + 1); // taskname rest of string
        taskList[numItems] = new ToDo(taskName);
    }

    private void listAddDeadline(String sentence, int spaceIndex) {
        int byIndex = sentence.indexOf(" /by ");
        if (byIndex == -1) {
            System.out.println("Invalid deadline command! Please specify due date.");
            return;
        }
        String taskName = sentence.substring(spaceIndex + 1, byIndex);
        String dueDate = sentence.substring(byIndex + 5); // rest of string after " /by "
        taskList[numItems] = new Deadline(taskName, dueDate);
    }

    private void listAddEvent(String sentence, int spaceIndex) {
        int fromIndex = sentence.indexOf(" /from ");
        int toIndex = sentence.indexOf(" /to ");
        if (fromIndex == -1 || toIndex == -1) {
            System.out.println("Invalid event command! Please specify start and end time.");
            return;
        }
        String taskName = sentence.substring(spaceIndex + 1, fromIndex);
        String startTime = sentence.substring(fromIndex + 7, toIndex);
        String endTime = sentence.substring(toIndex + 5);
        taskList[numItems] = new Event(taskName, startTime, endTime);
    }


    public void listAdd(String sentence) {
        int spaceIndex = sentence.indexOf(' ');
        if (spaceIndex == -1) {
            System.out.println("Invalid command! Please specify task type.");
            return;
        }

        String taskType = sentence.substring(0, spaceIndex); // task type up till first space


        if (taskType.equals("todo")) {
            listAddToDo(sentence, spaceIndex);
        } else if (taskType.equals("deadline")) {
            listAddDeadline(sentence, spaceIndex);
        } else if (taskType.equals("event")) {
            listAddEvent(sentence, spaceIndex);
        } else {
            System.out.println("Invalid task type!");
            return;
        }
        numItems += 1;

        printSuccessfulAddMessage(numItems, taskList[numItems - 1]);
    }


    // check if index represented by string is valid, if valid, return index in integer form.
    // else return -1.
    private int parseIndex(String index) {
        int i;
        // ensure its a number
        // https://stackoverflow.com/questions/1486077/good-way-to-encapsulate-integer-parseint
        try {
            i = Integer.parseInt(index);
            i -= 1; // convert to 0-index
            if (i >= numItems) {
                System.out.println(" Invalid task number!");
                return -1;
            }
        } catch (NumberFormatException e) {
            System.out.println("NOT A NUMBER!");
            i = -1;
        }
        return i;
    }


    public void markTask(String stringIndex) {
        int index = parseIndex(stringIndex);
        if (index != -1) {
            taskList[index].setIsComplete(true);
            System.out.println(" Nice! I've marked this task as done:");
            System.out.print("   [X] ");
            System.out.println(taskList[index].getTaskName());
        }
    }


    public void unmarkTask(String stringIndex) {
        int index = parseIndex(stringIndex);

        if (index != -1) {
            taskList[index].setIsComplete(false);
            System.out.println(" OK, I've marked this task as not done yet:");
            System.out.print("   [ ] ");
            System.out.println(taskList[index].getTaskName());
        }
    }
}
