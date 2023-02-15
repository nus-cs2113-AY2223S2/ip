package Duke;

import Duke.Exception.EmptyDeadlineException;
import Duke.Exception.EmptyEventsException;
import Duke.Exception.EmptyToDoException;
import Duke.Exception.NullCommandException;
import Duke.Task.Deadlines;
import Duke.Task.Events;
import Duke.Task.Task;
import Duke.Task.ToDos;

import java.util.ArrayList;
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
    private static final int DELETE_INDEX = 7;

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
        ArrayList<Task> list = new ArrayList<>();
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!line.equals("bye")) { // condition to shut down program
            if (line.equals("list")) { // users wants to know all text so far
                printCurrentList(list);
            } else if (line.startsWith("mark")) {
                mark(line, list);
            } else if (line.startsWith("unmark")) {
                unmark(line, list);
            } else if (line.startsWith("delete")) {
                delete(line, list);
            } else {// new tasks keyed in by user
                try {
                    Task newTask = new Task(line);
                    if (line.startsWith("todo")) {
                        newTask = new ToDos(line);
                        list.add(newTask);
                    } else if (line.startsWith("deadline")) {
                        newTask = new Deadlines(line);
                        list.add(newTask);
                    } else if (line.startsWith("event")) {
                        newTask = new Events(line);
                        list.add(newTask);
                    } else {
                        throw new NullCommandException();
                    }
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

    public static void mark(String task, ArrayList<Task> list) {
        String indexOfTask = task.substring(MARK_INDEX); // get the number of task to be marked
        Task taskToBeMarked = list.get(Integer.parseInt(indexOfTask) - 1); // convert from 1-based to 0-based
        taskToBeMarked.markTask();
        System.out.println("Sir, your task has been marked as completed.");
        System.out.println(list.get(Integer.parseInt(indexOfTask) - 1));
    }

    public static void unmark(String task, ArrayList<Task> list) {
        String indexOfTask = task.substring(UNMARK_INDEX);
        Task taskToBeUnmarked = list.get(Integer.parseInt(indexOfTask) - 1);
        taskToBeUnmarked.unmarkTask();
        System.out.println("Sir, your task has been unmarked as requested.");
        System.out.println(list.get(Integer.parseInt(indexOfTask) - 1));
    }

    public static void printCurrentList(ArrayList<Task> list) {
        System.out.println("Your current list of items as requested, sir.");
        for (int i = 0; i < list.size(); ++i) {
            System.out.println((i + 1) + "." + list.get(i));
        }
    }

    public static void delete(String task, ArrayList<Task> list) {
        String indexOfTask = task.substring(DELETE_INDEX);
        System.out.println("Sire, I have removed this task from your schedule");
        System.out.println(list.get(Integer.parseInt(indexOfTask) - 1));
        list.remove(Integer.parseInt(indexOfTask) - 1);
        System.out.println("You now have " + list.size() + " items left");
    }
}
