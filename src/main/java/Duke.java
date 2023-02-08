import java.util.Arrays;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args)  {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        String userInput;
        Scanner in = new Scanner(System.in);
        Task[] userTasks = new Task[0];
        Boolean isContinue = true;
        while (isContinue) {
            userInput = in.nextLine();
            userInput = userInput.trim();
            String[] userCommands = userInput.split(" ");
            String userCommand = userCommands[0];
            try {
                switch (userCommand) {
                case "list":
                    userCommandList(userTasks);
                    break;
                case "bye":
                    userCommandBye();
                    isContinue = false;
                    break;
                case "mark":
                    userCommandMark(userTasks, userCommands);
                    break;
                case "unmark":
                    userCommandUnmark(userTasks, userCommands);
                    break;
                case "todo":
                    userTasks = userCommandTodo(userTasks, userCommand, userInput);
                    break;
                case "deadline":
                    userTasks = userCommandDeadline(userTasks, userCommand, userInput);
                    break;
                case "event":
                    userTasks = userCommandEvent(userTasks, userCommand, userInput);
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
        String taskString = getTaskString(userInput,userCommand);
        String taskName = getEventTaskName(taskString);
        String eventFromDate = getEventFromDate(taskString);
        String eventToDate = getEventToDate(taskString);
        Event newEventTask = new Event(taskName,eventFromDate,eventToDate);
        return newEventTask;
    }

    private static Task[] userCommandEvent(Task[] userTasks, String userCommand, String userInput) throws DukeException {
        Event newEventTask = getNewEventTask(userInput, userCommand);
        userTasks = addUserTask(userTasks, newEventTask);
        printAddedNewTask(userTasks);
        return userTasks;
    }

    private static void printAddedNewTask(Task[] userTasks) {
        System.out.println("Got it. I've added this task:"); // shift this line below with the another print statement later
        System.out.println(userTasks[userTasks.length-1]);
        System.out.println("Now you have " + userTasks.length + " in the list.");
    }

    private static Deadline getNewDeadlineTask(String userInput, String userCommand) throws DukeException {
        String taskString = getTaskString(userInput,userCommand);
        String taskName = getDeadlineTaskName(taskString);
        String deadlineDueDate = getDeadlineDueDateString(taskString);
        Deadline newDeadlineTask = new Deadline(taskName,deadlineDueDate);
        return newDeadlineTask;
    }

    private static Task[] userCommandDeadline(Task[] userTasks, String userCommand, String userInput) throws DukeException {
        Deadline newDeadlineTask = getNewDeadlineTask(userInput, userCommand);
        userTasks = addUserTask(userTasks, newDeadlineTask);
        printAddedNewTask(userTasks);
        return userTasks;
    }

    private static Todo getNewTodoTask (String userInput, String userCommand) throws DukeException{
        String taskString = getTaskString(userInput,userCommand);
        String taskName = getTodoTaskName(taskString);
        Todo newTodoTask = new Todo(taskName);
        return newTodoTask;
    }

    private static Task[] userCommandTodo(Task[] userTasks, String userCommand, String userInput) throws DukeException {
        Todo newToDoTask = getNewTodoTask(userInput, userCommand);
        userTasks = addUserTask(userTasks, newToDoTask);
        printAddedNewTask(userTasks);
        return userTasks;
    }

    private static void userCommandUnmark(Task[] userTasks, String[] userCommands) {
        int taskIndex;
        taskIndex = Integer.parseInt(userCommands[1]) - 1;
        userTasks[taskIndex].setisDone(false);
        System.out.println(userTasks[taskIndex]);
        System.out.println("OK, I've marked this task as not done yet:");
    }

    private static void userCommandMark(Task[] userTasks, String[] userCommands) {
        int taskIndex;
        taskIndex = Integer.parseInt(userCommands[1]) - 1;
        userTasks[taskIndex].setisDone(true);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(userTasks[taskIndex]);
    }

    private static void userCommandList(Task[] userTasks) {
        for(int i = 0; i < userTasks.length; i++) {
            if (userTasks[i].getisDone()) {
                System.out.println( (i+1) + ". " + userTasks[i]);
            } else {
                System.out.println((i + 1) + ". " + userTasks[i]);
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
    private static Task[] addUserTask(Task[] userTasks, Task newTask) {
        userTasks = Arrays.copyOf(userTasks, userTasks.length+1);
        userTasks[userTasks.length-1] = newTask;
        return userTasks;
    }

    private static String getTodoTaskName(String taskString) {
        return taskString;
    }
    private static String getDeadlineTaskName(String taskString) throws DukeException {
        int slashIndex = taskString.indexOf("/by");
        if (slashIndex == -1) {
            System.out.println("Invalid Deadline String formatting: /by is missing");
            throw new DukeException();
        }
        String taskName = taskString.substring(0,slashIndex);
        if (taskName.isEmpty()) {
            System.out.println("Task needs to have a name!!!");
            throw new DukeException();
        }
        return taskName;
    }

    private static String getEventTaskName(String taskString) throws DukeException {
        int slashIndex = taskString.indexOf("/from");
        if (slashIndex == -1) {
            System.out.println("Invalid Event String formatting: /from is missing");
            throw new DukeException();
        }
        String taskName = taskString.substring(0,slashIndex);
        if (taskName.isEmpty()) {
            System.out.println("Task needs to have a name!!!");
            throw new DukeException();
        }
        return taskName;
    }
    private static String getEventFromDate(String taskString) throws DukeException {
        String[] taskStringParts = taskString.split("/from");
        String[] taskStringParts2 = taskStringParts[1].split("/to");
        if (taskStringParts2.length <= 1) {
            System.out.println("Invalid Event String formatting");
            throw new DukeException();
        }
        String eventFromDate = taskStringParts2[0].trim();
        if (eventFromDate.isEmpty()) {
            System.out.println("Invalid Event String formatting");
            throw new DukeException();
        }
        return eventFromDate;
    }
    private static String getEventToDate (String taskString) throws DukeException {
        String[] taskStringParts = taskString.split("/from");
        String[] taskStringParts2 = taskStringParts[1].split("/to");
        if (taskStringParts2.length <= 1) {
            System.out.println("Invalid Event String formatting: Either /to is missing or no description after /to");
            throw new DukeException();
        }
        String eventDueDate = taskStringParts2[1].trim();
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
        return taskStringParts[1].trim();
    }
    private static String getTaskString(String userInput, String userCommand) throws DukeException {
        int userInputLength = userInput.trim().length();
        int userCommandLength = userCommand.length();
        if (userInputLength <= userCommandLength) {
            System.out.println("Task Description cannot be empty!!!");
            throw new DukeException();
        }
        return userInput.substring(userCommand.length()+1);
    }
}




