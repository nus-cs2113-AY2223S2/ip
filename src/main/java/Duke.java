import java.nio.file.Path;
import java.sql.Array;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Duke {
    final static int ZERO_INDEX = 0;
    final static int ONE_INDEX = 1;
    final static int OFFSET_ONE_FOR_ZERO_INDEXING = 1;
    final static int ERROR_NEGATIVE_ONE_RETURNED = -1;
    final static String FILE_PATH = "data/duke.txt";
    final static String DIRECTORY_PATH = "data";
    final static int TASK_TYPE_INDEX = 0;
    final static int TASK_NAME_INDEX = 1;
    final static int TASK_IS_DONE_INDEX = 2;
    final static int TASK_DEADLINE_INDEX = 3;
    final static int TASK_EVENT_START_TIME_INDEX = 3;
    final static int TASK_EVENT_END_TIME_INDEX = 4;
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        String userInput;
        Scanner in = new Scanner(System.in);
        //Task[] userTasks = new Task[ZERO_INDEX];
        ArrayList<Task> userTasks = new ArrayList<>();
        try {
            userTasks = retrieveExistingTasksFromFile(FILE_PATH);
            System.out.println("File Found and successfully read");
            userCommandList(userTasks);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        Boolean isContinue = true;
        while (isContinue) {
            userInput = in.nextLine();
            userInput = userInput.trim();
            String[] userCommands = userInput.split(" ");
            String userCommand = userCommands[ZERO_INDEX];
            try {
                switch (userCommand) {
                case "list":
                    userCommandList(userTasks);
                    break;
                case "bye":
                    userCommandBye(userTasks);
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
            } catch (IOException e) {
                System.out.println("IO EXCEPTION, Data might be lost")
                ; // nothing for now need to add
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
    
    private static void userCommandBye(ArrayList<Task> userTasks) throws IOException {
        System.out.println("Bye. Hope to see you again soon!");
        saveData(DIRECTORY_PATH,FILE_PATH,userTasks);
    }

    private static void userCommandDefault() throws DukeException {
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        throw new DukeException();
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
        taskName = taskName.trim();
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
        taskName = taskName.trim();
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

    private static void saveExistingTasksToFile(ArrayList<Task> userTasks) throws IOException {
        String saveFilePath = FILE_PATH;
        for (int i = 0; i < userTasks.size(); i++) {
            Task currentTask = userTasks.get(i);
            Boolean isAppendMode = true;
            if (i == 0) {
                isAppendMode = false;
            }
            if (currentTask instanceof Todo) {
                writeToFile(saveFilePath, "T , " + currentTask.getTaskName() + " , " + currentTask.getisDone() + System.lineSeparator(), isAppendMode);
            } else if (currentTask instanceof Deadline) {
                writeToFile(saveFilePath, "D , " + currentTask.getTaskName() + " , " + currentTask.getisDone() + " , " + ((Deadline) currentTask).deadline +
                        System.lineSeparator(), isAppendMode);
            } else { // current task is instance of event
                writeToFile(saveFilePath, "E , " + currentTask.getTaskName() + " , " + currentTask.getisDone() + " , " + ((Event) currentTask).startTime +
                        " , " + ((Event) currentTask).endTime + System.lineSeparator(), isAppendMode);
            }
        }
    }

    private static void writeToFile(String filePath, String textToAdd, Boolean isAppendMode) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath, isAppendMode);
        fileWriter.write(textToAdd);
        fileWriter.close();
    }

    private static ArrayList<Task> retrieveExistingTasksFromFile(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        //Task[] userTasks = new Task[ZERO_INDEX];
        ArrayList<Task> userTasks = new ArrayList<>();
        while (scanner.hasNext()) {
            String Line = scanner.nextLine();
            String[] taskInformationWords = Line.split(",");
            String taskType = taskInformationWords[TASK_TYPE_INDEX].trim();
            if (taskType.equals("T")) {
                saveTodoTaskIntoSaveFile(userTasks, taskInformationWords);
            } else if (taskType.equals("D")) {
                saveDeadlineTaskIntoSaveFile(userTasks, taskInformationWords);
            } else if (taskType.equals("E")) {
                saveEventTaskIntoSaveFile(userTasks, taskInformationWords);
            }
        }
        return userTasks;
    }

    private static void saveEventTaskIntoSaveFile(ArrayList<Task> userTasks, String[] taskInformationWords) {
        String taskName = taskInformationWords[TASK_NAME_INDEX].trim();
        Boolean isDone = false;
        if (taskInformationWords[TASK_IS_DONE_INDEX].trim().equals("true")) {
            isDone = true;
        }
        String taskEventStartTime = taskInformationWords[TASK_EVENT_START_TIME_INDEX].trim();
        String taskEventEndTime = taskInformationWords[TASK_EVENT_END_TIME_INDEX].trim();
        Event newEventTask = new Event(taskName, taskEventStartTime, taskEventEndTime);
        newEventTask.setisDone(isDone);
        addUserTask(userTasks, newEventTask);
    }

    private static void saveDeadlineTaskIntoSaveFile(ArrayList<Task> userTasks, String[] taskInformationWords) {
        String taskName = taskInformationWords[TASK_NAME_INDEX].trim();
        Boolean isDone = false;
        if (taskInformationWords[TASK_IS_DONE_INDEX].trim().equals("true")) {
            isDone = true;
        }
        String taskDeadline = taskInformationWords[TASK_DEADLINE_INDEX].trim();
        Deadline newDeadlineTask = new Deadline(taskName, taskDeadline);
        newDeadlineTask.setisDone(isDone);
        addUserTask(userTasks, newDeadlineTask);
    }

    private static void saveTodoTaskIntoSaveFile(ArrayList<Task> userTasks, String[] taskInformationWords) {
        String taskName = taskInformationWords[TASK_NAME_INDEX].trim();
        Boolean isDone = false;
        if (taskInformationWords[TASK_IS_DONE_INDEX].trim().equals("true")) {
            isDone = true;
        }
        Todo newTodoTask = new Todo(taskName);
        newTodoTask.setisDone(isDone);
        addUserTask(userTasks, newTodoTask);
    }

    private static boolean isSaveDirectoryPresent(String DIRECTORY_PATH) {
        Path directoryPath = Paths.get(DIRECTORY_PATH);
        if (Files.exists(directoryPath) && Files.isDirectory(directoryPath)) {
            return true;
        }
        return false;
    }

    private static void createSaveFileDirectory(String DIRECTORY_PATH) {
        File file = new File(DIRECTORY_PATH);
        if (file.mkdir() == true) {
            System.out.println("Directory successfully created at [project_root]/" + DIRECTORY_PATH);

        } else {
            System.out.println("Failed to create directory, data might be lost");
        }
    }

    private static void saveData(String DIRECTORY_PATH, String FILE_PATH, ArrayList<Task> userTasks) throws IOException {
        if (isSaveDirectoryPresent(DIRECTORY_PATH) == false) {
            createSaveFileDirectory(DIRECTORY_PATH);
            if (isSaveDirectoryPresent(DIRECTORY_PATH) == false) {
                throw new IOException();
            }
        }
        saveExistingTasksToFile(userTasks);
    }
}



