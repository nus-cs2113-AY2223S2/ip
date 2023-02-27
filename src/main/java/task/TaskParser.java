package task;

import common.Messages;
import storage.Storage;
import ui.TextUi;

import java.io.IOException;
import java.util.ArrayList;



public class TaskParser {
    private static ArrayList<Task> listOfTasks = new ArrayList<>();

    public TaskParser(ArrayList<Task> listOfTasks) {
        TaskParser.listOfTasks = listOfTasks;
    }
    public Todo createTodoTask(String description) {
        return new Todo(description);
    }
    public Deadline createDeadlineTask(String description, String by) {
        return new Deadline(description, by);
    }
    public Event createEventTask(String description, String time) {
        return new Event(description, time);
    }
    public static void markTask(TextUi ui, Storage storage, int taskNumberInList) {
        Task task = listOfTasks.get(taskNumberInList);
        task.markAsDone();
        try {
            storage.writeToFile(listOfTasks);
        } catch(IOException e){
            //TODO: Add error message
        }
        ui.printMessage(Messages.MESSAGE_COMMAND_MARK);
        System.out.println(task);
    }
    public static void unmarkTask(TextUi ui, Storage storage, int taskNumberInList) {
        Task task = listOfTasks.get(taskNumberInList);
        task.markAsUndone();
        try {
            storage.writeToFile(listOfTasks);
        } catch(IOException e){
            //TODO: Add error message
        }
        ui.printMessage(Messages.MESSAGE_COMMAND_UNMARK);
        System.out.println(task);
    }

    public static void deleteAndPrintTask(TextUi ui, Storage storage, int taskNumberInList) {
        Task task = listOfTasks.get(taskNumberInList);
        listOfTasks.remove(taskNumberInList);
        try {
            storage.writeToFile(listOfTasks);
        } catch(IOException e){
            //TODO: Add error message
        }
        ui.printMessage(Messages.MESSAGE_COMMAND_DELETE);
        System.out.println(task);
        ui.printMessage(String.format(Messages.MESSAGE_COMMAND_LIST_SIZE, listOfTasks.size()));
    }
    public static void addAndPrintTask(Task task, TextUi ui, Storage storage) {
        listOfTasks.add(task);
        ui.printMessage(Messages.MESSAGE_COMMAND_ADD_TASK);
        System.out.println(task);
        ui.printMessage(String.format(Messages.MESSAGE_COMMAND_LIST_SIZE, listOfTasks.size()));
        try {
            storage.writeToFile(listOfTasks);
        }catch(IOException e){
            //TODO: Add error message
        }
    }
    public static void listTasks(TextUi ui) {
        if (listOfTasks.isEmpty()){
            ui.printMessage(Messages.MESSAGE_COMMAND_LIST_EMPTY);
        } else {
            ui.printMessage(Messages.MESSAGE_COMMAND_LIST_NOT_EMPTY);
            Task task;
            for (int i = 0; i < listOfTasks.size(); i++) {
                task = listOfTasks.get(i);
                System.out.println(i + 1 + "." + task);
            }
        }
    }
    public static boolean isValidTaskNumber(int taskNumberInList) {
        if(taskNumberInList >= 0 && taskNumberInList < listOfTasks.size()){
            return true;
        }
        return false;
    }
}
