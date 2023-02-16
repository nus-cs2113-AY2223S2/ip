import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import exception.RolexException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

public class Rolex {

    static List<Task> task =  new ArrayList<>();
    public static void printLines(){
        System.out.println("--------------------------------------------------");
    }

    public static void rolexGreetsUser(){
        System.out.println("Hello! I'm ROLEX");
        System.out.println("What can I do for you?\n");
    }

    public static void inputIsBye(){
        printLines();
        System.out.println("Bye. Hope to see you again soon!\n");
        printLines();
    }

    public static void inputIsList(){
        if(task.size()>=1){
            printLines();
            int indexNum = 1;
            System.out.println("Here are the tasks in your list:");
            for (Task value : task) {
                System.out.println(indexNum + ". " + value);
                indexNum++;
            }
            printLines();
        } else{
            RolexException.detectError("list");
        }
    }

    public static void inputIsMark(String userInput){
        int index = Integer.parseInt(userInput.substring(5));
        if(index>0 && index<= task.size()) {
            task.get(index - 1).MarkTask();
        } else{
            RolexException.detectError(userInput);
        }
    }

    public static void inputIsUnmark(String userInput){
        int index = Integer.parseInt(userInput.substring(7));
        if(index>0 && index<=task.size()) {
            task.get(index - 1).unMarkTask();
        } else{
            RolexException.detectError(userInput);
        }
    }

    public static void addPrintTask(){
        printLines();
        System.out.println("Got it. I've added this task:");
        System.out.println(task.get(task.size() - 1));
        System.out.println("\nNow you have " + task.size() + " tasks in the list.");
        printLines();
    }

    public static void inputIsTodo(String userInput){
        if(userInput.startsWith("todo") && userInput.length() <= 5){
            RolexException.detectError(userInput);
        } else{
            String todoName = userInput.substring(5);
            task.add(new Todo(todoName));
            addPrintTask();
        }
    }

    public static void inputIsDelete(String userInput, List<Task> task){
        if(userInput.equalsIgnoreCase("delete")){
            RolexException.detectError(userInput);
        } else{
            int index = Integer.parseInt(userInput.substring(7));
            if(index==-1){
                System.out.println("HELLO");
                RolexException.detectError(userInput);
            } else if(task.size()>0){
                Task.deleteTask(task, index);
            } else{
                printLines();
                System.out.println("Invalid index. Please enter valid index number!");
                printLines();
            }
        }
    }

    public static void inputIsDeadline(String userInput){
        int indexOfBy = userInput.indexOf("/by");
        if(indexOfBy==-1){
            RolexException.detectError(userInput);
        } else{
            String deadlineName = userInput.substring(9,indexOfBy-1);
            String by = userInput.substring(indexOfBy+3);
            task.add(new Deadline(deadlineName, by));
            addPrintTask();
        }
    }

    public static void inputIsEvent(String userInput){
        int indexOfFrom = userInput.indexOf("/from");
        int indexOfTo = userInput.indexOf("/to");
        if(indexOfFrom == -1 || indexOfTo == -1){
            RolexException.detectError(userInput);
        } else{
            String eventName = userInput.substring(6,indexOfFrom-1);
            String startTime = userInput.substring(indexOfFrom+6,indexOfTo-1);
            String endTime = userInput.substring(indexOfTo+4);
            task.add(new Event(eventName, startTime, endTime));
            addPrintTask();
        }
    }

    public static void main(String[] args) {

        System.out.println("HELLO WORLD");
        rolexGreetsUser();
        Scanner readInput = new Scanner(System.in);

        while(readInput.hasNextLine()){
            String userInput = readInput.nextLine();

            if(userInput.equalsIgnoreCase("bye")){
                inputIsBye();
                System.exit(0);
            } else if(userInput.startsWith("list")){
                inputIsList();
            } else if(userInput.startsWith("mark")){
                inputIsMark(userInput);
            } else if(userInput.startsWith("unmark")){
                inputIsUnmark(userInput);
            } else if(userInput.startsWith("todo")){
                inputIsTodo(userInput);
            } else if(userInput.startsWith("deadline")){
                inputIsDeadline(userInput);
            } else if(userInput.startsWith("event")){
                inputIsEvent(userInput);
            } else if(userInput.startsWith("delete")){
                inputIsDelete(userInput, task);
            } else{
                RolexException.detectError(userInput);
            }

        } //while() ends here

    } // main() ends here

} // rolex.Rolex class ends here