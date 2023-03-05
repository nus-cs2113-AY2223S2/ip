package duke;
import duke.exceptions.*;

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Entry point of the Mike application.
 * Initialises the application and begins interaction with the user.
 */
public class Duke {
    private static Ui ui;
    private static Storage storage;
    private static TaskList taskList;
    private static int numOfTask = 0;
    public static String filePath = "duke_list.txt";

    /**
     * Sets up the required objects, loads up the storage file and prints the welcome message to the user.
     *
     * @param filePath the location of the storage file.
     */
    public Duke(String filePath){
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.loadFile());
            numOfTask = taskList.getNumOfTask();
        } catch (FileNotFoundException e) {
            ui.showCreatingFileMessage();
            storage.createFile(filePath);
        }
        ui.showWelcomeMessage(taskList);
    }

    /**
     * Processes the user inputs and executes it until the user issues the exit command.
     *
     * @param userInput full user input string.
     * @return true if bye command is issued by user, false otherwise.
     * @throws DukeException if command issued by user is not recognized.
     * @throws IOException if user input string is not recognized.
     */
     public static boolean shouldExit(String userInput) throws DukeException, IOException {
        Parser parser = new Parser(userInput);
        String command = parser.getCommand();
         String taskNumber = parser.getTaskNumber();
         boolean isDone = false;
         switch(command){
             case "bye":
                 ui.showGoodByeMessage();
                 return true;
             case "list":
                 ui.showTaskList(taskList);
                 return false;
             case "mark":
                 int indexMark = Integer.parseInt(taskNumber);
                 taskList.getTask(indexMark-1).markAsDone();
                 ui.showMarkedTask(taskList,indexMark);
                 storage.saveToFile(taskList.getTaskList());
                 return false;
             case "unmark":
                 int indexUnmark = Integer.parseInt(taskNumber);
                 taskList.getTask(indexUnmark-1).markAsUndone();
                 ui.showUnmarkedTask(taskList,indexUnmark);
                 storage.saveToFile(taskList.getTaskList());
                 return false;
             case "todo":
                 try {
                     ToDo toDoTask = new ToDo(parser.getDescription(), isDone);
                     numOfTask += 1;
                     taskList.addTask(toDoTask);
                     ui.showTodoTask(taskList,numOfTask);
                     taskList.updateTaskLists(numOfTask,taskList.getTaskList());
                     storage.saveToFile(taskList.getTaskList());
                 } catch (ToDoException e) {
                     ui.showInvalidTodoFormatMessage();
                 }
                 return false;
             case "deadline":
                 try {
                     Deadline deadlineTask = new Deadline(parser.getDescription(),isDone, parser.getDeadline());
                     numOfTask += 1;
                     taskList.addTask(deadlineTask);
                     ui.showDeadlineTask(taskList,numOfTask);
                     taskList.updateTaskLists(numOfTask,taskList.getTaskList());
                     storage.saveToFile(taskList.getTaskList());
                 }catch (StringIndexOutOfBoundsException e){
                     ui.showInvalidDeadlineFormatMessage();
                 }
                 return false;
             case "event":
                 try {
                     Event eventTask = new Event(parser.getDescription(), isDone, parser.getFromDate(), parser.getToDate());
                     numOfTask += 1;
                     taskList.addTask(eventTask);
                     ui.showEventTask(taskList,numOfTask);
                     taskList.updateTaskLists(numOfTask,taskList.getTaskList());
                     storage.saveToFile(taskList.getTaskList());
                 }catch(StringIndexOutOfBoundsException e){
                     ui.showInvalidEventFormatMessage();
                 }catch(EventException e){
                     ui.showEmptyToFieldMessage();
                 }
                 return false;
             case "delete":
                 int indexDelete = Integer.parseInt(taskNumber);
                 ui.showDeletedTask(taskList,indexDelete,numOfTask);
                 taskList.removeTask(indexDelete-1);
                 numOfTask -= 1;
                 taskList.updateTaskLists(numOfTask,taskList.getTaskList());
                 storage.saveToFile(taskList.getTaskList());
                 return false;
             case "find":
                 String keyword = parser.getKeyword();
                 ui.showMatchingTasksMessage();
                 taskList.keywordedTaskList(keyword,taskList.getTaskList());
                 return false;
             default:
                 throw new DukeException();
         }
     }

    /**
     * Runs the program until termination condition has been satisfied.
     *
     * @throws ArrayIndexOutOfBoundsException if task number provided does not exist.
     * @throws DukeException if command issued by user is not recognized.
     * @throws IOException is user input string is not recognized.
     */
    public void run(){
        try {
            Scanner sc = new Scanner(System.in);
            String in = sc.nextLine();
            while (shouldExit(in) == false) {
                in = sc.nextLine();
            }
        } catch (ArrayIndexOutOfBoundsException e){
            ui.showInvalidTaskNumberMessage();
        } catch (DukeException | IOException e) {
            ui.showDukeExceptionMessage();
        }
    }
    public static void main(String[] args){
        new Duke(filePath).run();
    }
}

