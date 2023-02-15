package Duke;

import Duke.Exception.EmptyDeadlineException;
import Duke.Exception.EmptyEventsException;
import Duke.Exception.EmptyToDoException;
import Duke.Exception.NullCommandException;
import Duke.Task.Deadlines;
import Duke.Task.Events;
import Duke.Task.Task;
import Duke.Task.ToDos;

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

    private static final int MARK_INDEX = 5;
    private static final int UNMARK_INDEX = 7;

    public static void greetLine() {
        System.out.println("How may I be of service?");
    }

    public static void echo() {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!line.equals("bye")) {
            System.out.println(line);
            line = in.nextLine();
        }
    }

    public static void exitLine() {
        System.out.println("Glad I could be of help!");
    }

    public static void addList() {
        Task[] list = new Task[100];
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        int numOfItems = 0;
        while (!line.equals("bye")) { // condition to shut down program
            if (line.equals("list")) { // users wants to know all text so far
                printCurrentList(list, numOfItems);
            } else if (line.startsWith("mark")) {
                mark(line, list);
                printCurrentList(list, numOfItems);
            } else if (line.startsWith("unmark")) {
                unmark(line, list);
                printCurrentList(list, numOfItems);
            } else {// new tasks keyed in by user
                try {
                    Task newTask = new Task(line);
                    if (line.startsWith("todo")) {
                        newTask = new ToDos(line);
                    } else if (line.startsWith("deadline")) {
                        newTask = new Deadlines(line);
                    } else if (line.startsWith("event")){
                        newTask = new Events(line);
                    } else {
                        throw new NullCommandException();
                    }
                    list[numOfItems] = newTask;
                    ++numOfItems;
                } catch (EmptyToDoException e) {
                    System.out.println("Sire, you have yet to tell me what is it you want to do.");
                } catch (EmptyDeadlineException e) {
                    System.out.println("Sire, what is it that is due your specified time?");
                } catch (EmptyEventsException e) {
                    System.out.println("Sire, your event is unclear. Please specify.");
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("Don't hold back Sire. I am here to serve.");
                } catch (NullCommandException e) {
                    System.out.println("Sire, I am not trained to understand gibberish.");
                }
            }
            line = in.nextLine(); // read in next line of text
        }
    }

    public static void mark(String task, Task[] list) {
        String indexOfTask = task.substring(MARK_INDEX); // get the number of task to be marked
        Task taskToBeMarked = list[Integer.parseInt(indexOfTask) - 1]; // convert from 1-based to 0-based
        taskToBeMarked.markTask();
        System.out.println("Sir, your task has been marked as completed.");
    }

    public static void unmark(String task, Task[] list) {
        String indexOfTask = task.substring(UNMARK_INDEX);
        Task taskToBeMarked = list[Integer.parseInt(indexOfTask) - 1]; // convert from 1-based to 0-based
        taskToBeMarked.unmarkTask();
        System.out.println("Sir, your task has been unmarked as requested.");
    }

    public static void printCurrentList(Task[] list, int numOfItems) {
        Task[] subList = Arrays.copyOf(list, numOfItems);
        System.out.println("Your current list of items as requested, sir.");
        for (int i = 0; i < subList.length; ++i) {
            System.out.println(Integer.toString(i + 1) + "." + subList[i]);
        }
    }
}
