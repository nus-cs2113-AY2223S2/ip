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


    /***
     * This method takes in the user input and extracts the information from
     * task 'TODO'.
     * It then creates an object of class type Todo.
     *
     * @param userInput: the input from user
     */
    public static void inputIsTodo(String userInput) {
        if (userInput.startsWith("todo") && userInput.length() <= 5) {
            RolexException.detectError(userInput);
        } else {
            String todoName = Parser.taskName(userInput, 5);
            Task.addPrintTask(new Todo(todoName), task);
        }
    }


    /***
     * This method takes in the user input and extracts the information from
     * task 'DEADLINE'.
     * It then creates an object of class type Deadline.
     *
     * @param userInput: the input from user
     */
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


    /***
     * This method takes in the user input and extracts the information from
     * task 'EVENT'.
     * It then creates an object of class type Event.
     *
     * @param userInput: the input from user
     */
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


    /***
     * This method is designed to list all the tasks entered by the user
     */
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


    /**
     * This method is designed to display all the tasks that match to a
     * particular keyword entered by the user.
     *
     * @param userInput: the input entered by user
     */
    public static void inputIsFind(String userInput){
        if(userInput.startsWith("find") && userInput.length() <= 5) {
            RolexException.detectError(userInput);
        } else if(task.size() >= 1){
            Ui.printLines();
            int indexNum = 1;
            int flag = 0;
            String keyword = Parser.taskName(userInput,5);
            for(Task value: task){
                if(value.giveTaskName().contains(keyword)){
                    flag += 1;
                    if(indexNum == 1){
                        System.out.println("Here are the matching tasks in your list:");
                        indexNum += 1;
                    }
                        System.out.println(indexNum + ". " + value);
                }
            }
            Ui.printLines();
            if(flag==0){
                System.out.println("No such tasks with matching keyword: " + keyword + " !");
            }

        } else{
            Ui.printLines();
            System.out.println("There are no tasks for me to list!☹");
            Ui.printLines();
        }
    }


    /**
     * This method is designed to mark a task.
     *
     * @param userInput: input entered by user
     */
    public static void inputIsMark(String userInput) {
        int index = Parser.taskIndex(userInput, 5);
        if (index > 0 && index <= task.size()) {
            task.get(index - 1).markTask();
        } else {
            RolexException.detectError(userInput);
        }
    }


    /**
     * This method is designed to unmark a task.
     *
     * @param userInput: input entered by user
     */
    public static void inputIsUnmark(String userInput) {
        int index = Parser.taskIndex(userInput, 7);
        if (index > 0 && index <= task.size()) {
            task.get(index-1).unMarkTask();
        } else {
            RolexException.detectError(userInput);
        }
    }


    /**
     * This method is designed to delete a task.
     *
     * @param userInput: input entered by user
     */
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

}

