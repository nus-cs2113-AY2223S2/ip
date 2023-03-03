import java.util.ArrayList;

/**
 * Represents a task manager to encapsulate the user's list of tasks and execute operations involving the
 * adding, deletion and marking of tasks.
 */
public class TaskList {
    //Data
    protected ArrayList<Task> list;
    //Commands
    static final String EVENT_START = "/from";
    static final String EVENT_END = "/to";
    static final String DEADLINE_BY = "/by";
    private static final String DIVIDER  = "______________________________";
    //Functions
    public TaskList() {
        this.list = new ArrayList<>();
    }

    public int getSize() {
        return list.size();
    }

    public void addTask(Task task) {
        list.add(task);
    }

    public Task getTask(int taskNum) {
        return list.get(taskNum);
    }

    /**
     * Prints the user's complete list of tasks including details such as task type and completion.
     */
    public void printList() {
        String listMessage = DIVIDER + System.lineSeparator() + "Here are the tasks in your list:";
        System.out.println(listMessage);
        for (int i = 0; i < getSize(); i++) {
            System.out.println((i+1) + "." + getTask(i).toString());
        }
        System.out.println(DIVIDER);
    }

    /**
     * Method to mark the status of a task as done.
     */
    public void markTask(int taskNum) throws ArrayIndexOutOfBoundsException, TaskAlreadyMarkedException {
        if (taskNum > getSize() || taskNum <= 0) {
            throw new ArrayIndexOutOfBoundsException();
        } else {
            int taskIndex = taskNum - 1;
            if (!list.get(taskIndex).isDone) {
                list.get(taskIndex).markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(DIVIDER + System.lineSeparator() + getTask(taskIndex).toString()
                        + System.lineSeparator() + DIVIDER);
            } else {
                throw new TaskAlreadyMarkedException();
            }
        }
    }
    /**
     * Method to mark the status of a task as not done.
     */
    public void unmarkTask(int taskNum) throws TaskAlreadyNotMarkedException, ArrayIndexOutOfBoundsException{
        if (taskNum > getSize() || taskNum <= 0) {
            throw new ArrayIndexOutOfBoundsException();
        } else {
            int taskIndex = taskNum - 1;
            if (list.get(taskIndex).isDone) {
                list.get(taskIndex).markAsNotDone();
                System.out.println("OK, I've marked this task as not done:");
                System.out.println(DIVIDER + System.lineSeparator() + getTask(taskIndex).toString()
                        + System.lineSeparator() + DIVIDER);
            } else {
                throw new TaskAlreadyNotMarkedException();
            }
        }
    }

    /**
     * Method to delete task from user's list of tasks.
     */
    public void deleteTask(int taskNum) {
        String acknowledge = DIVIDER + System.lineSeparator() + "Noted. I've removed this task: "
                + System.lineSeparator() + getTask(taskNum-1).toString() + System.lineSeparator()
                + "Now you have " + (getSize()-1) + " tasks in the list." + System.lineSeparator() + DIVIDER;
        System.out.println(acknowledge);
        list.remove(taskNum-1);
    }

    /**
     * Output message each time a todo, event or deadline task has been added.
     */
    public void acknowledgementMessage() {
        String acknowledgement = DIVIDER + System.lineSeparator()
                + "Got it. I've added this task: " + System.lineSeparator()
                + getTask(getSize()-1).toString();
        System.out.println(acknowledgement);
        System.out.println("Now you have " + (getSize()) + " task(s) in the list."
                + System.lineSeparator() + DIVIDER);
    }

    /**
     * Processes the user input and adds a todo to the user's list of tasks if valid.
     * @param userInput The string representing the task currently being handled.
     */
    public void addTodo(String userInput) {
        list.add(new Todo(userInput));
    }

    /**
     * Processes the user input and adds an event to the user's list of tasks if valid.
     * @param userInput The string representing the task currently being handled.
     */
    public void addEvent(String userInput) {
        //use string.split to split the string into their different descriptions
        String[] eventInput = userInput.split(EVENT_START);
        //split into task description and duration
        String eventTaskDesc = eventInput[0];
        String eventDuration = eventInput[1];
        String[] eventStartAndEnd = eventDuration.split(EVENT_END);
        String eventStart = eventStartAndEnd[0];
        String eventEnd = eventStartAndEnd[1];
        list.add(new Event(eventTaskDesc,eventStart,eventEnd));
    }

    /**
     * Processes the user input and adds a deadline to the user's list of tasks if valid.
     * @param userInput The string representing the task currently being handled.
     */
    public void addDeadline(String userInput) {
        //use string.split to split the string into their different descriptions
        String[] deadlineInput = userInput.split(DEADLINE_BY);
        //split into task description and duration
        String deadlineTaskDesc = deadlineInput[0];
        String deadlineDuration = deadlineInput[1];
        list.add(new Deadline(deadlineTaskDesc, deadlineDuration));
    }

    /**
     * Finds and prints the list of tasks that coincide with the keyword the user inputs.
     * @param keyWord is the string for which the user wants to find matching tasks.
     */
    public void findKeyword(String keyWord) {
        ArrayList<Task> foundList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            String taskDesc = getTask(i).toString();
            if (taskDesc.contains(keyWord)) {
                foundList.add(getTask(i));
            }
        }
        if (foundList.size() == 0) {
            System.out.println(DIVIDER + System.lineSeparator()
                    + "There are no matching tasks in the list."
                    + System.lineSeparator() + DIVIDER);
        } else {
            System.out.println(DIVIDER);
            for (Task item: foundList) {
                System.out.println((foundList.indexOf(item) + 1)
                        + "." + item.toString());
            }
            System.out.println(DIVIDER);
        }
    }
}
