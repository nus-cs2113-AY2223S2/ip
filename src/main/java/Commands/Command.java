package Commands;

import Ui.Ui;
import Parser.Parser;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;
import Exception.RolexException;
import java.util.ArrayList;

public class Command {

    public static ArrayList<Task> task = new ArrayList<>();

    public static void getTaskList(ArrayList<Task> currentList) {
        task = currentList;
    }

    public static ArrayList<Task> giveTaskList(){
        return task;
    }

    public static void inputIsList() {
        if (task.size() >= 1) {
            Ui.printLines();
            int indexNum = 1;
            System.out.println("Here are the tasks in your list:");
            for (Task value : task) {
                System.out.println(indexNum + ". " + value);
                indexNum++;
            }
            Ui.printLines();
        } else {
            RolexException.detectError("list");
        }
    }

    public static void inputIsMark(String userInput) {
        int index = Parser.taskIndex(userInput, 5);
        if (index > 0 && index <= task.size()) {
            task.get(index - 1).markTask();
        } else {
            RolexException.detectError(userInput);
        }
    }

    public static void inputIsUnmark(String userInput) {
        int index = Parser.taskIndex(userInput, 7);
        if (index > 0 && index <= task.size()) {
            task.get(index-1).unMarkTask();
        } else {
            RolexException.detectError(userInput);
        }
    }

    public static void inputIsTodo(String userInput) {
        if (userInput.startsWith("todo") && userInput.length() <= 5) {
            RolexException.detectError(userInput);
        } else {
            String todoName = Parser.taskName(userInput, 5);
            Task.addPrintTask(new Todo(todoName), task);
        }
    }

    public static void inputIsDelete(String userInput) {
        if (userInput.equalsIgnoreCase("delete")) {
            RolexException.detectError(userInput);
        } else {
            int index = Parser.taskIndex(userInput, 7);
            if (index == -1) {
                RolexException.detectError(userInput);
            } else if (task.size()>0 && index<=task.size() && index>=1) {
                task.get(index-1).deleteTask(index, task.size());
                task.remove(index-1);
            } else {
                Ui.printInvalidNumber();
            }
        }
    }

    public static void inputIsDeadline(String userInput) {
        int indexOfBy = Parser.indexOfSubstring(userInput, "/by");
        if (indexOfBy == -1) {
            RolexException.detectError(userInput);
        } else {
            String deadlineName = Parser.taskName(userInput, 9, indexOfBy - 1);
            String by = Parser.taskName(userInput, indexOfBy + 4);
            by = Parser.deadlineDate(by);
            Task.addPrintTask(new Deadline(deadlineName, by), task);
        }
    }

    public static void inputIsEvent(String userInput) {
        int indexOfFrom = Parser.indexOfSubstring(userInput, "/from");
        int indexOfTo = Parser.indexOfSubstring(userInput, "/to");
        if (indexOfFrom == -1 || indexOfTo == -1) {
            RolexException.detectError(userInput);
        } else {
            String eventName = Parser.taskName(userInput, 6, indexOfFrom - 1);
            String startTime = Parser.taskName(userInput, indexOfFrom + 6, indexOfTo - 1);
            String endTime = Parser.taskName(userInput, indexOfTo + 4);
            Task.addPrintTask(new Event(eventName, startTime, endTime), task);
        }
    }

}

