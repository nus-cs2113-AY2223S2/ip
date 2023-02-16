import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    final static int ZERO_INDEX = 0;
    final static int ONE_INDEX = 1;
    final static int OFFSET_ONE_FOR_ZERO_INDEXING = 1;
    final static int ERROR_NEGATIVE_ONE_RETURNED = -1;
    final static String DOUBLE_SPACING = "  ";
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        String userInput;
        Scanner in = new Scanner(System.in);
        ArrayList<Task> userTasks = new ArrayList<>();
        Boolean isContinue = true;
        while (isContinue) {
            userInput = in.nextLine();
            userInput = userInput.trim();
            String[] userInputWords = userInput.split(" ");
            String userCommandKeyword = userInputWords[ZERO_INDEX];
            try {
                switch (userCommandKeyword) {
                case "list":
                    userCommandList(userTasks);
                    break;
                case "bye":
                    userCommandBye();
                    isContinue = false;
                    break;
                case "mark":
                    userCommandMark(userTasks, userInputWords);
                    break;
                case "unmark":
                    userCommandUnmark(userTasks, userInputWords);
                    break;
                case "todo":
                    userTasks = userCommandTodo(userTasks, userCommandKeyword, userInput);
                    break;
                case "deadline":
                    userTasks = userCommandDeadline(userTasks, userCommandKeyword, userInput);
                    break;
                case "event":
                    userTasks = userCommandEvent(userTasks, userCommandKeyword, userInput);
                    break;
                case "delete":
                    userTasks = userCommandDelete(userTasks, userInputWords);
                    break;
                default:
                    userCommandDefault();
                    break;
                }
            } catch (DukeException e) {
                ;
            }
        }
    }

    private static Event getNewEventTask(String userInput, String userCommand) throws DukeException {
        String taskString = getTaskString(userInput, userCommand);
        String taskName = getEventTaskName(taskString);
        String eventFromDate = getEventFromDate(taskString);
        String eventToDate = getEventToDate(taskString);
        Event newEventTask = new Event(taskName, eventFromDate, eventToDate);
        return newEventTask;
    }

    private static ArrayList<Task> userCommandEvent(ArrayList<Task> userTasks, String userCommand, String userInput) throws DukeException {
        Event newEventTask = getNewEventTask(userInput, userCommand);
        addUserTask(userTasks, newEventTask);
        printAddedNewTask(userTasks);
        return userTasks;
    }

    private static void printAddedNewTask(ArrayList<Task> userTasks) {
        System.out.println("Got it. I've added this task:"); // shift this line below with the another print statement later
        System.out.println(userTasks.get(userTasks.size() - OFFSET_ONE_FOR_ZERO_INDEXING));
        System.out.println("Now you have " + userTasks.size() + " in the list.");
    }

    private static Deadline getNewDeadlineTask(String userInput, String userCommand) throws DukeException {
        String taskString = getTaskString(userInput, userCommand);
        String taskName = getDeadlineTaskName(taskString);
        String deadlineDueDate = getDeadlineDueDateString(taskString);
        Deadline newDeadlineTask = new Deadline(taskName, deadlineDueDate);
        return newDeadlineTask;
    }

    private static ArrayList<Task> userCommandDeadline(ArrayList<Task> userTasks, String userCommand, String userInput) throws DukeException {
        Deadline newDeadlineTask = getNewDeadlineTask(userInput, userCommand);
        addUserTask(userTasks, newDeadlineTask);
        printAddedNewTask(userTasks);
        return userTasks;
    }

    private static Todo getNewTodoTask (String userInput, String userCommand) throws DukeException{
        String taskString = getTaskString(userInput, userCommand);
        String taskName = getTodoTaskName(taskString);
        Todo newTodoTask = new Todo(taskName);
        return newTodoTask;
    }

    private static ArrayList<Task> userCommandTodo(ArrayList<Task> userTasks, String userCommand, String userInput) throws DukeException {
        Todo newToDoTask = getNewTodoTask(userInput, userCommand);
        addUserTask(userTasks, newToDoTask);
        printAddedNewTask(userTasks);
        return userTasks;
    }

    private static void userCommandUnmark(ArrayList<Task> userTasks, String[] userInputWords) throws DukeException {
        int taskIndex;
        taskIndex = Integer.parseInt(userInputWords[ONE_INDEX]) - OFFSET_ONE_FOR_ZERO_INDEXING;
        if (taskIndex + OFFSET_ONE_FOR_ZERO_INDEXING > userTasks.size()) {
            System.out.println("There is no task that is indexed: " + taskIndex);
            throw new DukeException();
        }
        if (taskIndex < ZERO_INDEX) {
            System.out.println("task number given cannot be less than 1");
            throw new DukeException();
        }
        userTasks.get(taskIndex).setisDone(false);
        System.out.println(userTasks.get(taskIndex));
        System.out.println("OK, I've marked this task as not done yet:");
    }

    private static void userCommandMark(ArrayList<Task> userTasks, String[] userInputWords) throws DukeException {
        int taskIndex;
        taskIndex = Integer.parseInt(userInputWords[ONE_INDEX]) - OFFSET_ONE_FOR_ZERO_INDEXING;
        if (taskIndex + OFFSET_ONE_FOR_ZERO_INDEXING > userTasks.size()) {
            System.out.println("There is no task that is indexed: " + taskIndex);
            throw new DukeException();
        }
        if (taskIndex < ZERO_INDEX) {
            System.out.println("task number given cannot be less than 1");
            throw new DukeException();
        }
        userTasks.get(taskIndex).setisDone(true);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(userTasks.get(taskIndex));
    }

    private static void userCommandList(ArrayList<Task> userTasks) {
        for(int i = 0; i < userTasks.size(); i++) {
            if (userTasks.get(i).getisDone()) {
                System.out.println((i + 1) + ". " + userTasks.get(i));
            } else {
                System.out.println((i + 1) + ". " + userTasks.get(i));
            }
        }
    }
    
    private static void userCommandBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void userCommandDefault() throws DukeException {
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        throw new DukeException();
    }

    private static ArrayList<Task> userCommandDelete(ArrayList<Task> userTasks, String[] userInputWords) throws DukeException {
        int taskIndex;
        taskIndex = Integer.parseInt(userInputWords[ONE_INDEX]) - OFFSET_ONE_FOR_ZERO_INDEXING;
        if (taskIndex + OFFSET_ONE_FOR_ZERO_INDEXING > userTasks.size()) {
            System.out.println("There is no task that is indexed: " + taskIndex);
            throw new DukeException();
        }
        if (taskIndex < ZERO_INDEX) {
            System.out.println("task number given cannot be less than 1");
            throw new DukeException();
        }
        System.out.println("Noted. I've removed this task: ");
        System.out.println(DOUBLE_SPACING + userTasks.get(taskIndex));
        userTasks.remove(taskIndex);
        System.out.println("Now you have " + userTasks.size() + " in the list.");
        return userTasks;
    }
    private static void addUserTask(ArrayList<Task> userTasks, Task newTask) {
        userTasks.add(newTask);
    }

    private static String getTodoTaskName(String taskString) {
        return taskString;
    }
    private static String getDeadlineTaskName(String taskString) throws DukeException {
        int slashIndex = taskString.indexOf("/by");
        if (slashIndex == ERROR_NEGATIVE_ONE_RETURNED) {
            System.out.println("Invalid Deadline String formatting: /by is missing");
            throw new DukeException();
        }
        String taskName = taskString.substring(ZERO_INDEX, slashIndex);
        if (taskName.isEmpty()) {
            System.out.println("Task needs to have a name!!!");
            throw new DukeException();
        }
        return taskName;
    }

    private static String getEventTaskName(String taskString) throws DukeException {
        int slashIndex = taskString.indexOf("/from");
        if (slashIndex == ERROR_NEGATIVE_ONE_RETURNED) {
            System.out.println("Invalid Event String formatting: /from is missing");
            throw new DukeException();
        }
        String taskName = taskString.substring(ZERO_INDEX, slashIndex);
        if (taskName.isEmpty()) {
            System.out.println("Task needs to have a name!!!");
            throw new DukeException();
        }
        return taskName;
    }
    private static String getEventFromDate(String taskString) throws DukeException {
        String[] taskStringPartsSplitByFrom = taskString.split("/from");
        String[] taskStringPartsSplitByTo = taskStringPartsSplitByFrom[ONE_INDEX].split("/to");
        if (taskStringPartsSplitByTo.length <= 1) {
            System.out.println("Invalid Event String formatting");
            throw new DukeException();
        }
        String eventFromDate = taskStringPartsSplitByTo[ZERO_INDEX].trim();
        if (eventFromDate.isEmpty()) {
            System.out.println("Invalid Event String formatting");
            throw new DukeException();
        }
        return eventFromDate;
    }
    private static String getEventToDate (String taskString) throws DukeException {
        String[] taskStringPartsSplitByFrom = taskString.split("/from");
        String[] taskStringPartsSplitByTo = taskStringPartsSplitByFrom[ONE_INDEX].split("/to");
        if (taskStringPartsSplitByTo.length <= 1) {
            System.out.println("Invalid Event String formatting: Either /to is missing or no description after /to");
            throw new DukeException();
        }
        String eventDueDate = taskStringPartsSplitByTo[ONE_INDEX].trim();
        if (eventDueDate.isEmpty()) {
            System.out.println("Invalid Event String formatting");
            throw new DukeException();
        }
        return eventDueDate;
    }

    // Comment: When this function is called, we assumed that /by is found and exists
    private static String getDeadlineDueDateString(String taskString) throws DukeException {
        String[] taskStringParts = taskString.split("/by");
        if (taskStringParts.length != 2) {
            System.out.println("Invalid Deadline String formatting: Description after /by is missing");
            throw new DukeException();
        }
        return taskStringParts[ONE_INDEX].trim();
    }
    private static String getTaskString(String userInput, String userCommand) throws DukeException {
        int userInputLength = userInput.trim().length();
        int userCommandLength = userCommand.length();
        if (userInputLength <= userCommandLength) {
            System.out.println("Task Description cannot be empty!!!");
            throw new DukeException();
        }
        return userInput.substring(userCommand.length() + 1);
    }
}




