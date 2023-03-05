package duke;
import duke.exceptions.*;

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;
public class Duke {
    private static Ui ui;
    private static Storage storage;
    private static TaskList taskList;
    private static int numOfTask = 0;
    public static String filePath = "src/duke_list.txt";
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
     public static boolean shouldExit(String args) throws DukeException, IOException {
        Parser parser = new Parser(args);
        String command = parser.getCommand();
         String taskNumber = parser.getTaskNumber(); //Used for mark and unmark command
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
                     String text = taskList.getTask(numOfTask-1).getType() + " | " + taskList.getTask(numOfTask-1).getStatusIconSave() + " | "+ taskList.getTask(numOfTask-1).getDescription()
                             + System.lineSeparator();
                     taskList.updateTaskLists(numOfTask,taskList.getTaskList());
                     storage.appendToFile(text);
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
                     String text = taskList.getTask(numOfTask-1).getType() + " | " +  taskList.getTask(numOfTask-1).getStatusIconSave() + " | " + taskList.getTask(numOfTask-1).getDescription()
                             + " | " + taskList.getTask(numOfTask-1).getDeadlineSave() + System.lineSeparator();
                     taskList.updateTaskLists(numOfTask,taskList.getTaskList());
                     storage.appendToFile(text);
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
                     String text =taskList.getTask(numOfTask-1).getType() + " | " + taskList.getTask(numOfTask-1).getStatusIconSave() + " | " + taskList.getTask(numOfTask-1).getDescription()
                             + " | " + taskList.getTask(numOfTask-1).getPeriodSave() + System.lineSeparator();
                     taskList.updateTaskLists(numOfTask,taskList.getTaskList());
                     storage.appendToFile(text);
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
             default:
                 throw new DukeException();
         }
     }

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
    public static void main(String[] args) throws IOException {
        new Duke(filePath).run();
    }
}

