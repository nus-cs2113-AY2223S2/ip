package duke;

import duke.storage.ReadFromFile;
import duke.storage.SaveToFile;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;
import duke.ui.Ui;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

public class Duke {
    final static String FILE_PATH = "savedTasks.txt";

    /**
     * Reads inputs from user and performs the corresponding actions.
     *
     * @param args user input
     */
    public static void main(String[] args) {
        Ui ui = new Ui();
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner in = new Scanner(System.in);

        ui.showWelcomeMessage();
        ui.showSavedTasksRetrievalMessage();
        try {
            printFileContents(FILE_PATH, tasks);
        } catch (FileNotFoundException e) {
            ui.showFileNotFoundMessage();
            ui.showNewFileCreationMessage();
        }

        boolean isExit = false;
        while (!isExit) {
            String messageFromUser;
            messageFromUser = in.nextLine();
            try {
                isExit = isExitCommandGiven(messageFromUser);
                handleMessageFromUser(messageFromUser, tasks);
            } catch (IndexOutOfBoundsException e) {
                ui.showMissingAttributesMessage();
            } catch (DukeException e) {
                ui.showIncorrectCommandWarning();
            } catch (FileNotFoundException e) {
                ui.showFileNotFoundMessage();
                ui.showNewFileCreationMessage();
            }
        }
    }

    /**
     * Returns true if the message from user contains the keywords todo, deadline or event.
     * This function checks if the user has mentioned any of the 3 keywords todo, deadline or event
     * to differentiate task creation actions from other actions.
     *
     * @param messageFromUser This is the user input
     * @return true if the message from user contains the keywords todo, deadline or event
     */
    public static boolean hasTaskKeyword(String messageFromUser) {
        boolean isToDo = messageFromUser.startsWith("todo");
        boolean isDeadline = messageFromUser.startsWith("deadline");
        boolean isEvent = messageFromUser.startsWith("event");
        return (isToDo || isDeadline || isEvent);
    }

    /**
     * This function creates a ToDo, Deadline or Event Object based on the type of task the user specified.
     * This object is then stored in the parent class object, Task as an ArrayList of Task is used to store
     * these objects.
     *
     * @param messageFromUser This is the user input
     * @return Task object
     */
    public static Task handleTaskCreation(String messageFromUser) {
        Task newTask;
        if (messageFromUser.startsWith("todo")) {
            newTask = createToDo(messageFromUser);
        } else if (messageFromUser.startsWith("deadline")) {
            newTask = createDeadline(messageFromUser);
        } else {
            newTask = createEvent(messageFromUser);
        }
        return newTask;
    }

    /**
     * Adds Task object to ArrayList of Tasks and prints out that the object has been successfully added as well as
     * the number of tasks in the ArrayList.
     *
     * @param newTask Task object to be added to ArrayList
     * @param tasks   ArrayList to store Task objects
     */
    public static void addToList(Task newTask, ArrayList<Task> tasks) {
        Ui ui = new Ui();
        ui.horizontalLine();
        ui.showTaskAddedMessage();
        displayTask(newTask);
        tasks.add(newTask);
        ui.printNumberOfTasks(tasks.size());
        ui.horizontalLine();
    }

    /**
     * Deletes the object at the specific index in the ArrayList as specified by the user.
     * Since the ArrayList has 0 based indexing, the 1 based index specified by the user will be decremented by 1.
     * Task that was deleted will be shown to the user.
     *
     * @param messageFromUser This is the user input
     * @param tasks           ArrayList to store Task objects
     */
    public static void deleteFromList(String messageFromUser, ArrayList<Task> tasks) {
        Ui ui = new Ui();
        ui.horizontalLine();
        // messageFromUser is in the format delete INDEX
        String[] splitMessage = messageFromUser.split(" ");
        int indexToDelete = Integer.parseInt(splitMessage[1]);
        // Decrement 1 as ArrayList is index 0 based
        indexToDelete--;
        Task taskToDelete = tasks.get(indexToDelete);
        tasks.remove(indexToDelete);
        ui.showTaskRemovedMessage();
        displayTask(taskToDelete);
        ui.printNumberOfTasks(tasks.size());
        ui.horizontalLine();
    }

    /**
     * Changes the task status of the task in the ArrayList as specified by the user.
     * Only marking an undone task as done or a done task as unmarked is allowed.
     *
     * @param sentence User input
     * @param tasks    ArrayList to store Task objects
     */
    public static void changeTaskStatus(String sentence, ArrayList<Task> tasks) {
        Ui ui = new Ui();
        String[] words = sentence.split(" ");
        int taskNumber = Integer.parseInt(words[1]);
        // Decrement 1 as ArrayList is index 0 based
        taskNumber--;
        Task currentTask = tasks.get(taskNumber);
        if (words[0].trim().equals("mark")) {
            currentTask.markTaskAsDone();
        } else {
            currentTask.markTaskAsUndone();
        }
        ui.horizontalLine();
    }

    /**
     * Displays all the tasks in the ArrayList to the user by iterating through the entire ArrayList.
     * If there are no tasks in the ArrayList, a corresponding message will be printed.
     *
     * @param tasks ArrayList to store Task objects
     */
    public static void displayList(ArrayList<Task> tasks) {
        Ui ui = new Ui();
        int totalNumberOfTasks = tasks.size();
        if (totalNumberOfTasks > 0) {
            ui.showDisplayListHeaderMessage();
            iterateThroughListAndDisplayTasks(tasks);
        } else {
            ui.showNoTasksToDisplayMessage();
        }
        ui.horizontalLine();
    }

    public static void iterateThroughListAndDisplayTasks(ArrayList<Task> tasks) {
        int totalNumberOfTasks = tasks.size();
        for (int index = 0; index < totalNumberOfTasks; index += 1) {
            Task currentTask = tasks.get(index);
            System.out.print(index + 1 + ". ");
            displayTask(currentTask);
        }
    }

    /**
     * Displays each specific task based on whether it is a ToDo, Deadline or Event type of task.
     * The information of the corresponding task will be formatted and displayed correctly.
     * The tasks will be displayed as follows:
     * ToDo: [T] [{STATUS}] {TODO MESSAGE}
     * Deadline: [D] [{STATUS}] {Deadline message (by: {DUE_DATE})}
     * Event: [E] [{STATUS}] {Event message (from: {START} to: {END})}
     *
     * @param currentTask Single task selected from ArrayList of Task objects to be displayed
     */
    public static void displayTask(Task currentTask) {
        // Check task type
        String taskType = currentTask.getTaskType();
        System.out.print(taskType + " ");
        System.out.print(currentTask.getStatus());
        System.out.print(currentTask.getTaskInfo());

        switch (taskType) {
        case "[T]":
            System.out.println();
            break;
        case "[D]":
            Deadline currentDeadline = (Deadline) currentTask;
            String due = currentDeadline.getDueInfo();
            System.out.print("(by:" + due + ")");
            System.out.println();
            break;
        case "[E]":
            Event currentEvent = (Event) currentTask;
            String eventStart = currentEvent.getEventStartInfo();
            String eventEnd = currentEvent.getEventEndInfo();
            System.out.print("(from:" + eventStart + "to:" + eventEnd + ")");
            System.out.println();
            break;
        default:
            System.out.println("Unknown task type error!");
        }
    }

    /**
     * Creates and returns a new ToDo object containing the message the user specified.
     * The input to this function has to be of the form:
     * todo MESSAGE
     *
     * @param messageFromUser This is the user input
     * @return ToDo object
     */
    public static ToDo createToDo(String messageFromUser) {
        // Remove the word "todo" from message
        String[] messageComponents = messageFromUser.split(" ", 2);
        // Add space in front of todo task for formatting
        messageComponents[1] = " " + messageComponents[1];
        return new ToDo(messageComponents[1]);
    }

    /**
     * Creates and returns a new Deadline object containing the message and due date the user specified.
     * The input to this function has to be of the form:
     * deadline MESSAGE /by DUE_DATE
     *
     * @param messageFromUser This is the user input
     * @return Deadline object
     */
    public static Deadline createDeadline(String messageFromUser) {
        // Remove the word "deadline" from message
        messageFromUser = messageFromUser.substring(8);
        String[] messageComponents = messageFromUser.split("/by", 2);
        return new Deadline(messageComponents[0], messageComponents[1]);
    }

    /**
     * Creates and returns a new Event object containing the message, start and end information the user specified.
     * The input to this function has to be of the form:
     * event MESSAGE /from START /to END
     *
     * @param messageFromUser This is the user input
     * @return Event object
     */
    public static Event createEvent(String messageFromUser) {
        // Remove the word "event" from message
        messageFromUser = messageFromUser.substring(5);
        // Event has 3 aspects message /from startDate /to endDate
        String[] messageComponents = messageFromUser.split("/from", 2);
        String[] timeComponents = messageComponents[1].split("/to", 2);
        return new Event(messageComponents[0], timeComponents[0], timeComponents[1]);
    }

    /**
     * This function saves all tasks in the ArrayList to the file savedTasks and displays the goodbye message.
     *
     * @param tasks    ArrayList to store Task objects
     * @param filePath Location of the text file that is used to store the tasks
     */
    public static void saveAndExit(ArrayList<Task> tasks, String filePath) {
        Ui ui = new Ui();
        SaveToFile saveToFile = new SaveToFile();
        saveToFile.initialiseWritingToFile(tasks, filePath);
        ui.showGoodbyeMessage();
    }

    /**
     * Prints all the tasks stored in the savedTasks text file to the user and copies the tasks to the ArrayList
     * so that the user can manipulate the tasks later on.
     * If there are no tasks in the savedTasks text file, then a message saying there is no tasks to display
     * will be printed.
     *
     * @param filePath Location of the text file that is used to store the tasks
     * @param tasks    ArrayList to store Task objects
     * @throws FileNotFoundException if there is no savedTasks file.
     */
    private static void printFileContents(String filePath, ArrayList<Task> tasks) throws FileNotFoundException {
        Ui ui = new Ui();
        ReadFromFile readFromFile = new ReadFromFile();
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        if (s.hasNext()) {
            ui.horizontalLine();
            while (s.hasNext()) {
                String lineInFile = s.nextLine();
                readFromFile.copyToArrayList(lineInFile, tasks);
            }
            displayList(tasks);
            ui.horizontalLine();
        } else {
            ui.showNoTasksToDisplayMessage();
            ui.horizontalLine();
        }
    }

    /**
     * Returns a boolean based on whether the user has given the exit command "bye".
     *
     * @param messageFromUser User input
     * @return true is user inputs "bye".
     */
    public static boolean isExitCommandGiven(String messageFromUser) {
        if (messageFromUser.equals("bye")) {
            return true;
        }
        return false;
    }

    /**
     * Calls the relevant methods based on the action the user specifies.
     * "todo", "deadline", "event", "mark", "unmark", "list", "bye", "delete" are the keywords that the user has to
     * input. If any other keyword is given, an exception is thrown.
     *
     * @param messageFromUser User input
     * @param tasks           ArrayList to store Task objects
     * @throws DukeException if user inputs a keyword that is incorrect.
     */
    public static void handleMessageFromUser(String messageFromUser, ArrayList<Task> tasks) throws DukeException, FileNotFoundException {
        if (hasTaskKeyword(messageFromUser)) {
            Task newTask = handleTaskCreation(messageFromUser);
            addToList(newTask, tasks);
        } else if (messageFromUser.startsWith("mark") || messageFromUser.startsWith("unmark")) {
            changeTaskStatus(messageFromUser, tasks);
        } else if (messageFromUser.equals("list")) {
            displayList(tasks);
        } else if (messageFromUser.equals("bye")) {
            saveAndExit(tasks, FILE_PATH);
        } else if (messageFromUser.startsWith("delete")) {
            deleteFromList(messageFromUser, tasks);
        } else if (messageFromUser.startsWith("find")) {
            findTask(messageFromUser, tasks, FILE_PATH);
        } else {
            throw new DukeException();
        }
    }

    public static void findTask(String messageFromUser, ArrayList<Task> tasks, String filePath) throws FileNotFoundException {
        String[] messageComponents = messageFromUser.split(" ", 2);
        ArrayList<Task> similarTasks = new ArrayList<>();
        SaveToFile saveToFile = new SaveToFile();
        saveToFile.initialiseWritingToFile(tasks, filePath);
        File f = new File(filePath);
        ReadFromFile readFromFile = new ReadFromFile();

        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String lineInFile = s.nextLine();
            if (lineInFile.contains(messageComponents[1])) {
                readFromFile.copyToArrayList(lineInFile, similarTasks);
            }
        }

        Ui ui = new Ui();
        if (similarTasks.size() > 0) {
            ui.horizontalLine();
            ui.showMessageForSimilarTasksFound();
            iterateThroughListAndDisplayTasks(similarTasks);
            ui.horizontalLine();
        } else {
            ui.horizontalLine();
            ui.showMessageForNoSimilarTasksFound();
            ui.horizontalLine();
        }
    }

}
