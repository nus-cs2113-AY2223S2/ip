import jdk.jfr.Event;

import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greetLine();
        addList();
        exitLine();
    }
    public static void greetLine(){
        System.out.println("How may I be of service?");
    }
    public static void echo()
    {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while(!line.equals("bye")) {
            System.out.println(line);
            line = in.nextLine();
        }
    }
    public static void exitLine(){
        System.out.println("Glad I could be of help!");
    }

    public static void addList()
    {
        Task[] list = new Task[100];
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        int numOfItems = 0;
        while(!line.equals("bye")) { // condition to shut down program
            if(line.equals("list")) { // users wants to know all text so far
                printCurrentList(list, numOfItems);
            }
            else if(line.startsWith("mark")){
                mark(line, list);
                printCurrentList(list, numOfItems);
            }
            else if(line.startsWith("unmark")){
                unmark(line,list);
                printCurrentList(list, numOfItems);
            }
            else { // new tasks keyed in by user
                Task newTask = new Task(line);
                if(line.startsWith("todo")){
                    newTask = new ToDos(line);
                }
                else if(line.startsWith("deadline")){
                    newTask = new Deadlines(line);
                }
                else{
                    newTask = new Events(line);
                }
                list[numOfItems] = newTask;
                ++numOfItems;
            }
            line = in.nextLine(); // read in next line of text
        }
    }

    public static void mark(String task, Task[] list)
    {
        String indexOfTask = task.substring(5); // get the number of task to be marked
        Task taskToBeMarked = list[Integer.parseInt(indexOfTask) - 1]; // convert from 1-based to 0-based
        taskToBeMarked.markTask();
        System.out.println("Sir, your task has been marked as completed.");
    }

    public static void unmark(String task, Task[] list)
    {
        String indexOfTask = task.substring(7);
        Task taskToBeMarked = list[Integer.parseInt(indexOfTask) - 1]; // convert from 1-based to 0-based
        taskToBeMarked.unmarkTask();
        System.out.println("Sir, your task has been unmarked as requested.");
    }

    public static void printCurrentList(Task[] list, int numOfItems)
    {
        Task[] subList = Arrays.copyOf(list, numOfItems);
        System.out.println("Your current list of items as requested, sir.");
        for(int i = 0; i < subList.length; ++i)
        {
            System.out.println(Integer.toString(i+1) + "." + subList[i]);
        }
    }
}
