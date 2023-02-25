import java.io.IOException;
import java.util.ArrayList;
public class Ui {
    final static int ZERO_INDEX = 0;
    final static int ONE_INDEX = 1;
    final static int OFFSET_ONE_FOR_ZERO_INDEXING = 1;
    final static String DOUBLE_SPACING = "  ";
    final static String FILE_PATH = "data/duke.txt";
    final static String DIRECTORY_PATH = "data";

    /**
     * Executes user command based on the given user input
     * user command keyword is extracted using .split() function and passed on to various functions
     * toContinue boolean variable is returned to Duke class, to indicate whether user wants to end Duke program
     * @param taskList TaskList object containing the user tasks.
     * @param userInput Line inputted from user.
     * @return toContinue Boolean variable returned to Duke class.
     */
    public boolean executeUserCommand(TaskList taskList, String userInput) {
        userInput = userInput.trim();
        String[] userInputWords = userInput.split(" ");
        String userCommandKeyword = userInputWords[ZERO_INDEX];
        boolean toContinue = true;
        try {
            switch (userCommandKeyword) {
            case "list":
                userCommandList(taskList);
                break;
            case "bye":
                userCommandBye(taskList);
                toContinue = false;
                break;
            case "mark":
                userCommandMark(taskList, userInputWords);
                break;
            case "unmark":
                userCommandUnmark(taskList, userInputWords);
                break;
            case "todo":
                userCommandTodo(taskList, userCommandKeyword, userInput);
                break;
            case "deadline":
                userCommandDeadline(taskList, userCommandKeyword, userInput);
                break;
            case "event":
                userCommandEvent(taskList, userCommandKeyword, userInput);
                break;
            case "delete":
                userCommandDelete(taskList, userInputWords);
                break;
            case "find":
                userCommandFind(taskList, userCommandKeyword, userInput);
                break;
            default:
                userCommandDefault();
                break;
            }
        } catch (DukeException e) {
            System.out.println("Error Found see message above!!!");
        } catch (IOException e) {
            System.out.println("IO EXCEPTION, Data might be lost");
        }
        return toContinue;
    }

    /**
     * Executes the Event command:
     * 1. Creates new event task
     * 2. Add new event task to taskList
     * 3. Print out description of newly added task and current number of tasks
     * @param taskList TaskList object containing the user tasks.
     * @param userCommandKeyword Command keyword given by user, etc. mark, unmark, find etc.
     * @param userInput Line inputted from user.
     * @throws DukeException Thrown by getNewEventTask
     */
    private static void userCommandEvent(TaskList taskList, String userCommandKeyword, String userInput) throws DukeException {
        Event newEventTask = getNewEventTask(userInput, userCommandKeyword);
        taskList.addUserTask(newEventTask);
        printAddedNewTask(taskList);
    }

    /**
     * Returns a newly created event task.
     * @param userInput Line inputted from user.
     * @param userCommandKeyword Command keyword given by user, etc. mark, unmark, find etc.
     * @return Event object created according to userInput
     * @throws DukeException Thrown by getTaskString, getEventTaskName, getEventStartDate, getEventEndDate
     * getTaskString: If the task description extracted is empty, length of userInput <= length of userCommand
     * getEventTaskName: If taskString does not contain "/from" format keyword OR If task name extracted is empty string
     * getEventStartDate: If taskString is missing /from or /to (detected from looking at length of String[] taskStringPartsSplitByTo)
     * OR If start date extracted is empty
     * getEventEndDate: If taskString is missing /from or /to (detected from looking at length of String[] taskStringPartsSplitByTo)
     * OR If end date extracted is empty.
     */
    private static Event getNewEventTask(String userInput, String userCommandKeyword) throws DukeException {
        String taskString = Parser.getTaskString(userInput, userCommandKeyword);
        String taskName = Parser.getEventTaskName(taskString);
        String eventFromDate = Parser.getEventStartDate(taskString);
        String eventToDate = Parser.getEventEndDate(taskString);
        Event newEventTask = new Event(taskName, eventFromDate, eventToDate);
        return newEventTask;
    }

    /**
     * Executes the Deadline command:
     * 1. Creates new deadline task
     * 2. Add new deadline task to taskList
     * 3. Print out description of newly added task and current number of tasks
     * @param taskList TaskList object containing the user tasks.
     * @param userCommandKeyword Command keyword given by user, etc. mark, unmark, find etc.
     * @param userInput Line inputted from user.
     * @throws DukeException Thrown by getNewDeadlineTask
     */
    private static void userCommandDeadline(TaskList taskList, String userCommandKeyword, String userInput) throws DukeException {
        Deadline newDeadlineTask = getNewDeadlineTask(userInput, userCommandKeyword);
        taskList.addUserTask(newDeadlineTask);
        printAddedNewTask(taskList);
    }

    /**
     * Returns a newly created deadline task.
     * @param userInput Line inputted from user.
     * @param userCommandKeyword Command keyword given by user, etc. mark, unmark, find etc.
     * @return Deadline object created according to userInput
     * @throws DukeException Thrown by getTaskString, getDeadlineTaskName, getDeadlineDueDateString.
     * getTaskString: If the task description extracted is empty, length of userInput <= length of userCommand
     * getDeadlineTaskName: If taskString does not contain "/by" format keyword OR If task name extracted is empty string
     * getDeadlineDueDateString: If taskString is missing /by (detected from looking at length of String[] taskStringParts)
     */
    private static Deadline getNewDeadlineTask(String userInput, String userCommandKeyword) throws DukeException {
        String taskString = Parser.getTaskString(userInput, userCommandKeyword);
        String taskName = Parser.getDeadlineTaskName(taskString);
        String deadlineDueDate = Parser.getDeadlineDueDateString(taskString);
        Deadline newDeadlineTask = new Deadline(taskName, deadlineDueDate);
        return newDeadlineTask;
    }

    /**
     * Executes the Todo command:
     * 1. Creates new Todo task
     * 2. Add new Todo task to taskList
     * 3. Print out description of newly added task and current number of tasks
     * @param taskList TaskList object containing the user tasks.
     * @param userCommandKeyword Command keyword given by user, etc. mark, unmark, find etc.
     * @param userInput Line inputted from user.
     * @throws DukeException Thrown by getNewTodoTask
     */
    private static void userCommandTodo(TaskList taskList, String userCommandKeyword, String userInput) throws DukeException {
        Todo newToDoTask = getNewTodoTask(userInput, userCommandKeyword);
        taskList.addUserTask(newToDoTask);
        printAddedNewTask(taskList);
    }

    /**
     * Returns a newly created Todo task.
     * @param userInput Line inputted from user.
     * @param userCommandKeyword Command keyword given by user, etc. mark, unmark, find etc.
     * @return Todo object created according to userInput
     * @throws DukeException Thrown by getTaskString, getTodoTaskName.
     * getTaskString: If the task description extracted is empty, length of userInput <= length of userCommand.
     * getTodoTaskName: If taskString is an empty string.
     */
    private static Todo getNewTodoTask (String userInput, String userCommandKeyword) throws DukeException{
        String taskString = Parser.getTaskString(userInput, userCommandKeyword);
        String taskName = Parser.getTodoTaskName(taskString);
        Todo newTodoTask = new Todo(taskName);
        return newTodoTask;
    }

    /**
     * Unmarks task according to task index extracted from user input
     * @param taskList TaskList object containing the user tasks.
     * @param userInputWords Array of strings split by " " from userInput line.
     * @throws DukeException If taskIndex is greater than number of tasks in taskList, taskIndex >= number of user tasks OR
     * If taskIndex is lesser than 0, taskIndex < 0.
     */
    private static void userCommandUnmark(TaskList taskList, String[] userInputWords) throws DukeException {
        int taskIndex;
        taskIndex = Integer.parseInt(userInputWords[ONE_INDEX]) - OFFSET_ONE_FOR_ZERO_INDEXING;
        if (taskIndex + OFFSET_ONE_FOR_ZERO_INDEXING > taskList.getNumberOfUserTasks()) {
            System.out.println("There is no task that is indexed: " + taskIndex);
            throw new DukeException();
        }
        if (taskIndex < ZERO_INDEX) {
            System.out.println("task number given cannot be less than 1");
            throw new DukeException();
        }
        taskList.setTaskNotDone(taskIndex);
        System.out.println(taskList.getUserTask(taskIndex));
        System.out.println("OK, I've marked this task as not done yet:");
    }

    /**
     * Marks task according to task index extracted from user input
     * @param taskList TaskList object containing the user tasks.
     * @param userInputWords Array of strings split by " " from userInput line.
     * @throws DukeException If taskIndex is greater than number of tasks in taskList, taskIndex >= number of user tasks OR
     * If taskIndex is lesser than 0, taskIndex < 0.
     */
    private static void userCommandMark(TaskList taskList, String[] userInputWords) throws DukeException {
        int taskIndex;
        taskIndex = Integer.parseInt(userInputWords[ONE_INDEX]) - OFFSET_ONE_FOR_ZERO_INDEXING;
        if (taskIndex + OFFSET_ONE_FOR_ZERO_INDEXING > taskList.getNumberOfUserTasks()) {
            System.out.println("There is no task that is indexed: " + taskIndex);
            throw new DukeException();
        }
        if (taskIndex < ZERO_INDEX) {
            System.out.println("task number given cannot be less than 1");
            throw new DukeException();
        }
        taskList.setTaskDone(taskIndex);
        System.out.println(taskList.getUserTask(taskIndex));
        System.out.println("Nice! I've marked this task as done:");
    }

    /**
     * Lists out every task present in userTasks from taskList
     * @param taskList TaskList object containing the user tasks.
     */
    private static void userCommandList(TaskList taskList) {
        ArrayList<Task> userTasks = taskList.getUserTasksArrayList();
        for(int i = 0; i < userTasks.size(); i++) {
            if (userTasks.get(i).getisDone()) {
                System.out.println((i + 1) + ". " + userTasks.get(i));
            } else {
                System.out.println((i + 1) + ". " + userTasks.get(i));
            }
        }
    }

    /**
     * Saves the userTasks in taskList to a given directory and file.
     * @param taskList TaskList object containing the user tasks.
     * @throws IOException If directory path or file path is not present, and program fails
     * to create a new directory or file path.
     */
    private static void userCommandBye(TaskList taskList) throws IOException {
        System.out.println("Bye. Hope to see you again soon!");
        Storage.saveData(DIRECTORY_PATH, FILE_PATH, taskList);
    }

    /**
     * Throws a DukeException, informing that user inputted an invalid line format
     * @throws DukeException Always throws DukeException.
     */
    private static void userCommandDefault() throws DukeException {
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        throw new DukeException();
    }

    /**
     * Deletes task according to task index extracted from user input
     * If task is deleted successfully, prints out deleted task description and shows number of user tasks remaining.
     * @param taskList TaskList object containing the user tasks.
     * @param userInputWords Array of strings split by " " from userInput line.
     * @throws DukeException If taskIndex is greater than number of tasks in taskList, taskIndex >= number of user tasks OR
     * If taskIndex is lesser than 0, taskIndex < 0.
     */
    private static void userCommandDelete(TaskList taskList, String[] userInputWords) throws DukeException {
        int taskIndex;
        taskIndex = Integer.parseInt(userInputWords[ONE_INDEX]) - OFFSET_ONE_FOR_ZERO_INDEXING;
        if (taskIndex + OFFSET_ONE_FOR_ZERO_INDEXING > taskList.getNumberOfUserTasks()) {
            System.out.println("There is no task that is indexed: " + taskIndex);
            throw new DukeException();
        }
        if (taskIndex < ZERO_INDEX) {
            System.out.println("task number given cannot be less than 1");
            throw new DukeException();
        }
        System.out.println("Noted. I've removed this task: ");
        System.out.println(DOUBLE_SPACING + taskList.getUserTask(taskIndex));
        taskList.removeUserTask(taskIndex);
        System.out.println("Now you have " + taskList.getNumberOfUserTasks() + " in the list.");
    }

    /**
     * Prints out last added task and number of user tasks remaining.
     * @param taskList TaskList object containing the user tasks.
     */
    private static void printAddedNewTask(TaskList taskList) {
        System.out.println("Got it. I've added this task:"); // shift this line below with the another print statement later
        int numOfUserTasks = taskList.getNumberOfUserTasks();
        System.out.println(taskList.getUserTask(numOfUserTasks- OFFSET_ONE_FOR_ZERO_INDEXING));
        System.out.println("Now you have " + numOfUserTasks + " in the list.");
    }

    /**
     * Getter function for userCommandList function.
     * @param taskList TaskList object containing the user tasks.
     */
    public static void executeListCommand(TaskList taskList) {
        userCommandList(taskList);
    }

    /**
     * Searches userTasks in taskList according to the find keyword given by user.
     * Prints out every user task that contains the given keyword.
     * @param taskList TaskList object containing the user tasks.
     * @param userCommandKeyword Command keyword given by user, etc. mark, unmark, find etc.
     * @param userInput Line inputted from user.
     * @throws DukeException Thrown by getTaskString.
     * getTaskString: If the task description extracted is empty, length of userInput <= length of userCommand.
     */
    private static void userCommandFind(TaskList taskList, String userCommandKeyword, String userInput) throws DukeException {
        String taskName = Parser.getTaskString(userInput, userCommandKeyword);
        ArrayList<Integer> tasksIndexWithSimilarName = taskList.findTasksBasedOnName(taskName);
        int numberOfTasksToPrint = tasksIndexWithSimilarName.size();
        if (numberOfTasksToPrint == 0) {
            System.out.println("No tasks with entered keyword found");
        }
        for(int i = 0; i < numberOfTasksToPrint; i++) {
            int currentTaskIndex = tasksIndexWithSimilarName.get(i);
            Task currentTask = taskList.getUserTask(currentTaskIndex);
            System.out.println((i + 1) + ". " + currentTask);
        }
    }
}
