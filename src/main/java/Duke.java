import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.MissingFormatArgumentException;
import java.util.Scanner;

public class Duke {
    public static final String BYE_COMMAND = "bye";
    public static final String CHANGE_COMMAND = "change";
    public static final String LANG_COMMAND = "lang";
    public static final String LIST_COMMAND = "list";
    public static final String MARK_COMMAND = "mark";
    public static final String UNMARK_COMMAND = "unmark";
    public static final String DELETE_COMMAND = "delete";
    public static final String TODO_COMMAND = "todo";
    public static final String DEADLINE_COMMAND = "deadline";
    public static final String DEADLINE_BY = "/by";
    public static final String EVENT_COMMAND = "event";
    public static final String EVENT_START_FROM = "/from";
    public static final String EVENT_END_TO = "/to";
    /** A fixed sized buffer to trim strings at the right index */
    public static final int COMMAND_BUFFER = 1;
    /** The return result when a substring is not found */
    public static final int OUT_OF_BOUNDS = -1;
    /** Name of the text file that stores all the list of tasks */
    public static final String STORAGE_INFO_TXT = "storage-info.txt";
    /** Name of the folder that stores the text file that stores all the list of tasks */
    public static final String STORE_DIR = "store";
    /** The buffer used to separate the various information containing in a task */
    public static final String STORAGE_BUFFER = "~;~";

    /** Language state of the program. */
    public static boolean isSinglish = false;
    /** A resizeable array to store all the tasks entered from the user. */
    public static ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Toggles the language setting between normal and Singlish mode.
     * Prints to the output the changes made.
     */
    public static void changeLanguage() {
        isSinglish = !isSinglish;
        Greeting.sayChangeLanguage(isSinglish);
    }

    /**
     * Adds the entered task to the list of tasks.
     * if the list of tasks is full, informs the user that the task list is full and no new tasks can be added.
     *
     * @param line String entered by user.
     * @param typeOfTask Type of tasks (TODO, DEADLINE, EVENT).
     * @param startDate startDate for EVENT, Deadline date for DEADLINE, null for TODO.
     * @param endDate endDate for EVENT, null for DEADLINE, null for TODO.
     */
    public static void addToList(String line, TypeOfTask typeOfTask, String startDate, String endDate) {
        if (typeOfTask.equals(TypeOfTask.TODO)) {
            Todo item = new Todo(false, line);
            tasks.add(item);
        } else if (typeOfTask.equals(TypeOfTask.DEADLINE)) {
            Deadline item = new Deadline(false, line, startDate);
            tasks.add(item);
        } else if (typeOfTask.equals(TypeOfTask.EVENT))  {
            Event item = new Event(false, line, startDate, endDate);
            tasks.add(item);
        }
        Greeting.sayAddToList(isSinglish);
        printTask(tasks.get(tasks.size() - 1), tasks.size());
        Greeting.printHorizontalLines(isSinglish);
    }

    /**
     * Prints out the task passed into it.
     *
     * @param task The Task to be printed out
     * @param index The index of the respective task
     */
    public static void printTask(Task task, int index) {
        System.out.print(index);
        task.printTask();
    }

    /**
     * Prints out the entire list of tasks entered by the user.
     */
    public static void printList() {
        int index = 1;
        for (Task task : tasks) {
            printTask(task, index);
            index++;
        }
        Greeting.printHorizontalLines(isSinglish);
    }

    /**
     * Marks or Unmark the selected task whether it is done, prints out the selected task alongside its state.
     * If the user gives an invalid index, informs the user about it.
     * Does nothing if the user trys to mark a marked task and vice versa.
     *
     * @param index The index of the task selected to be marked or unmarked.
     * @param isMark Whether to mark or unmark the task.
     * @throws IndexOutOfBoundsException When the user keys an index that is out of bounds.
     */
    public static void markTask(int index, boolean isMark) throws IndexOutOfBoundsException {
        index--;
        if (index < 0 || index >= tasks.size()) {
            throw new IndexOutOfBoundsException();
        } else {
            if (tasks.get(index).getIsDone() != isMark) {
                tasks.get(index).switchIsDone();
            }
            Greeting.sayUpdatedTask(isSinglish);
            printTask(tasks.get(index), index + 1);
        }
        Greeting.printHorizontalLines(isSinglish);
    }

    /**
     * Deletes the task at the index specfied from the list
     * If the user gives an invalid index, informs the user about it.
     *
     * @param index The index of the task to be deleted
     * @throws IndexOutOfBoundsException yyWhen the user keys an index that is out of bounds.
     */
    public static void deleteTask(int index) {
        index--;
        if (index < 0 || index >= tasks.size()) {
            throw new IndexOutOfBoundsException();
        } else {
            Greeting.sayDeleteTaskFromList(isSinglish);
            printTask(tasks.get(index), index + 1);
            tasks.remove(index);
        }
        Greeting.printHorizontalLines(isSinglish);
    }

    /**
     * Updates the list of tasks stored in the text file.
     * @param storageFilePath The file path of the text file where the list of tasks is stored.
     * @throws IOException when the text file is corrupted.
     */
    public static void updateTasksSaved(Path storageFilePath) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(
                (new FileWriter(storageFilePath.toFile())))) {
            for (Task task : tasks) {
                String line = "error";
                if (task.getTypeOfTask() == TypeOfTask.TODO) {
                    line = task.getTypeOfTask() + STORAGE_BUFFER
                            + task.getStatusIcon() + STORAGE_BUFFER
                            + task.getDescription();
                } else if (task.getTypeOfTask() == TypeOfTask.DEADLINE) {
                    line = task.getTypeOfTask() + STORAGE_BUFFER
                            + task.getStatusIcon() + STORAGE_BUFFER
                            + task.getDescription() + STORAGE_BUFFER
                            + ((Deadline) task).getDate();
                } else if (task.getTypeOfTask() == TypeOfTask.EVENT) {
                    line = task.getTypeOfTask() + STORAGE_BUFFER
                            + task.getStatusIcon() + STORAGE_BUFFER
                            + task.getDescription() + STORAGE_BUFFER
                            + ((Event) task).getStartDate() + STORAGE_BUFFER
                            + ((Event) task).getEndDate();
                }
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            throw new IOException();
        }
    }

    /**
     * Initializes the program's list with previously saved tasks.
     * Does nothing if the text file is empty.
     * @param storageFilePath The file path of the text file where the list of tasks is stored.
     * @throws IOException when the text file is corrupted.
     */
    public static void initializeListWithSavedTasks(Path storageFilePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(storageFilePath.toFile()))) {
            String line;
            line = reader.readLine();
            int index = 1;
            while (line != null) {
                String[] commands = line.split(STORAGE_BUFFER);
                System.out.print(index);
                if (commands[0].equals(TypeOfTask.TODO.toString())) {
                    Todo todo = new Todo((commands[1].equals(Task.markedStatusIcon)), commands[2]);
                    todo.printTask();
                    tasks.add(todo);
                } else if (commands[0].equals(TypeOfTask.DEADLINE.toString())) {
                    Deadline deadline = new Deadline((commands[1].equals(Task.markedStatusIcon)), commands[2], commands[3]);
                    deadline.printTask();
                    tasks.add(deadline);
                } else if (commands[0].equals(TypeOfTask.EVENT.toString())) {
                    Event event = new Event((commands[1].equals(Task.markedStatusIcon)), commands[2], commands[3], commands[4]);
                    event.printTask();
                    tasks.add(event);
                }
                line = reader.readLine();
                index++;
            }
        } catch (IOException e) {
            throw new IOException();
        }
    }

    public static void main(String[] args) throws IOException {
        Greeting.sayHello(isSinglish);

        Path storageFilePath = loadStorageFolderAndFile();
        initializeListWithSavedTasks(storageFilePath);

        boolean isQuit = false;

        while (!isQuit) {
            Scanner in = new Scanner(System.in);
            String line = in.nextLine();

            String[] commands = line.split(" ");
            boolean isBye = commands[0].equals(BYE_COMMAND);
            boolean isValidChangeLangCommand = commands[0].equals(CHANGE_COMMAND) && commands.length == 2 && commands[1].equals(LANG_COMMAND);
            boolean isValidPrintListCommand = commands[0].equals(LIST_COMMAND) && commands.length == 1;
            boolean isValidMarkOrUnmarkCommand = (commands[0].equals(MARK_COMMAND) || commands[0].equals(UNMARK_COMMAND)) && commands.length == 2;
            boolean isValidDeleteCommand = commands[0].equals(DELETE_COMMAND) && commands.length == 2;
            boolean isTodo = commands[0].equals(TODO_COMMAND);
            boolean isDeadline = commands[0].equals(DEADLINE_COMMAND);
            boolean isEvent = commands[0].equals(EVENT_COMMAND);

            try {
                if (isBye) {
                    Greeting.sayGoodbye(isSinglish);
                    isQuit = true;
                    break;
                } else if (isValidChangeLangCommand) {
                    changeLanguage();
                } else if (isValidPrintListCommand) {
                    printList();
                } else if (isValidMarkOrUnmarkCommand) {
                    checkAndMarkTask(commands);
                    updateTasksSaved(storageFilePath);
                } else if (isValidDeleteCommand) {
                    checkAndDeleteTask(commands);
                    updateTasksSaved(storageFilePath);
                } else if (isTodo) {
                    addTodo(line);
                    updateTasksSaved(storageFilePath);
                } else if (isDeadline) {
                    addDeadline(line);
                    updateTasksSaved(storageFilePath);
                } else if (isEvent) {
                    addEvent(line);
                    updateTasksSaved(storageFilePath);
                } else {
                    throw new IllegalAccessException();
                }
            } catch (IndexOutOfBoundsException e) {
                Greeting.warnOutOfRange(isSinglish);
            } catch (NumberFormatException e) {
                Greeting.warnWrongSyntax(isSinglish);
            } catch (IllegalAccessException e) {
                Greeting.warnWrongSyntax(isSinglish);
            } catch (MissingFormatArgumentException e) {
                Greeting.warnEmptyDesc(isSinglish);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Creates the storage folder and the respective text file to store the tasks.
     * If the folder and/or the text file exists, it will only create what is missing and not overwrite any existing
     * folder/file.
     * It also prints a message stating the location of the text file.
     * @return storageFilePath The file path where the text file containing the list of tasks is stored.
     */
    private static Path loadStorageFolderAndFile() {
        new File("store").mkdirs();
        String ipFolderPath = System.getProperty("user.dir");
        Path storageFolderPath = java.nio.file.Paths.get(ipFolderPath, STORE_DIR);
        Path storageFilePath = java.nio.file.Paths.get(storageFolderPath.toString(), STORAGE_INFO_TXT);

        try {
            java.nio.file.Files.createFile(storageFilePath);
            System.out.println("File created successfully at: " + storageFilePath);
        } catch (IOException e) {
            System.out.println("File already exist: " + e.getMessage());
        }
        return storageFilePath;
    }

    /**
     * Checks the validity of the event command.
     * adds the event to the list if command has the right syntax.
     * otherwise, it informs the user that it has a wrong syntax.
     *
     * @param line String entered by the user.
     * @throws MissingFormatArgumentException When the user fails to key in all the arguments.
     */
    private static void addEvent(String line) throws MissingFormatArgumentException{
        int indexOfStartDate = line.indexOf(EVENT_START_FROM);
        int indexOfEndDate = line.indexOf(EVENT_END_TO);
        boolean isOutOfBounds = indexOfStartDate == OUT_OF_BOUNDS || indexOfEndDate == OUT_OF_BOUNDS;
        boolean isMissingDescription = indexOfStartDate <= EVENT_COMMAND.length() + COMMAND_BUFFER;

        if (!isOutOfBounds && !isMissingDescription) {
            // +6 for "/from " length
            String startOfDate = line.substring(indexOfStartDate + EVENT_START_FROM.length() + COMMAND_BUFFER, indexOfEndDate);
            // +5 for "/to " length
            String endOfDate = line.substring(indexOfEndDate + EVENT_END_TO.length() + COMMAND_BUFFER);
            // +7 for "event " length
            String descOfTask = line.substring(EVENT_COMMAND.length() + COMMAND_BUFFER, indexOfStartDate);
            addToList(descOfTask, TypeOfTask.EVENT, startOfDate, endOfDate);
        } else if (isOutOfBounds) {
            throw new IndexOutOfBoundsException();
        } else if (isMissingDescription) {
            throw new MissingFormatArgumentException("Missing description");
        }
    }

    /**
     * Checks the validity of the deadline command.
     * adds the deadline to the list if command has the right syntax.
     * otherwise, it informs the user that it has a wrong syntax.
     *
     * @param line String entered by the user.
     * @throws IndexOutOfBoundsException When the user keys an index that is out of bounds.
     * @throws MissingFormatArgumentException When the user fails to key in all the arguments.
     */
    private static void addDeadline(String line) throws IndexOutOfBoundsException, MissingFormatArgumentException {
        int indexOfDate = line.indexOf(DEADLINE_BY);
        boolean isOutOfBounds = indexOfDate == OUT_OF_BOUNDS;
        boolean isMissingDescription = indexOfDate <= DEADLINE_COMMAND.length() + COMMAND_BUFFER;

        if (!isOutOfBounds && !isMissingDescription) {
            // +4 for "/by " length
            String startOfDate = line.substring(indexOfDate + DEADLINE_BY.length() + COMMAND_BUFFER);
            // +10 for "deadline " length
            String descriptionOfTask = line.substring(DEADLINE_COMMAND.length() + COMMAND_BUFFER, indexOfDate);
            addToList(descriptionOfTask, TypeOfTask.DEADLINE, startOfDate, null);
        } else if (isOutOfBounds) {
            throw new IndexOutOfBoundsException();
        } else if (isMissingDescription) {
            throw new MissingFormatArgumentException("Missing description");
        }

    }

    /**
     * Checks the validity of the todo command.
     * adds the todo task to the list if command has the right syntax.
     * otherwise, it informs the user that it has a wrong syntax.
     *
     * @param line String entered by the user.
     */
    private static void addTodo(String line) {
        // +5 for "todo " length
        String desc = line.substring(TODO_COMMAND.length() + COMMAND_BUFFER);
        addToList(desc, TypeOfTask.TODO, null, null);
    }

    /**
     * Checks the validity of the mark/unmark command.
     * if the task selected is valid, marks or unmark the task depending on the exact command.
     * otherwise, informs the user that the command entered has wrong syntax or task of that index does not exist.
     *
     * @param commands Commands entered by the user.
     * @throws NumberFormatException When the user keys an invalid value for index.
     */
    private static void checkAndMarkTask(String[] commands) throws NumberFormatException {
        if (commands[1].matches("\\d+?")) {
            boolean isMark = commands[0].equals(MARK_COMMAND);
            markTask(Integer.parseInt(commands[1]), isMark);
        } else {
            throw new NumberFormatException();
        }
    }

    /**
     * Check the validity of the delete command
     * if the task selected is valid, deletes the task from the list
     * otherwise, informs the user that the command entered has wrong syntax or task of that index does not exist.
     *
     * @param commands Commands entered by the user.
     * @throws NumberFormatException When the user keys an invalid value for index.
     */
    private static void checkAndDeleteTask(String[] commands) throws NumberFormatException {
        if (commands[1].matches("\\d+?")) {
            deleteTask(Integer.parseInt(commands[1]));
        } else {
            throw new NumberFormatException();
        }
    }
}
