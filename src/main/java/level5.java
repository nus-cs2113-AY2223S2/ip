import java.text.NumberFormat;
import java.util.Scanner;

public class level5 {
    public static void mark(int i, String line, Task[] tasks, boolean[] done, String[] type) throws DukeException {
        int number = Integer.parseInt(line.substring(5));
        if (number > i || number < 1) {
            throw new DukeException();
        }
        done[number - 1] = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + type[number - 1] + "[X]" + tasks[number - 1]);
    }

    public static void unmark(int i, String line, Task[] tasks, boolean[] done, String[] type) throws DukeException {
        int number = Integer.parseInt(line.substring(7));
        if (number > i || number < 1) {
            throw new DukeException();
        }
        done[number - 1] = false;
        System.out.println("Ok, I've marked this task as not done yet:");
        System.out.println("  " + type[number - 1] + "[ ]" + tasks[number - 1]);
    }

    public static void todo(int i, String line, Task[] tasks, boolean[] done, String[] type) throws DukeException {
        if (line.equals("todo")) {
            throw new DukeException();
        }
        tasks[i] = new Task(line.substring(4));
        type[i] = "[T]";
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + type[i] + "[ ]" + tasks[i]);
        System.out.println("Now you have " + (i + 1) + " tasks in the list.");
        done[i] = false;
    }

    public static void deadline(int i, String line, Task[] tasks, boolean[] done, String[] type) {
        int slash = line.indexOf("/");
        String description = line.substring(8, slash-1);
        String by = line.substring(slash+4);
        tasks[i] = new Deadline(description, by);
        type[i] = "[D]";
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + type[i] + "[ ]" + tasks[i]);
        System.out.println("Now you have " + (i + 1) + " tasks in the list.");
        done[i] = false;
    }

    public static void event(int i, String line, Task[] tasks, boolean[] done, String[] type) {
        int slash1 = line.indexOf("/");
        int slash2 = line.indexOf("/", slash1+1);
        String description = line.substring(5, slash1-1);
        String from = line.substring(slash1+6, slash2-1);
        String to = line.substring(slash2+4);
        tasks[i] = new Event(description, from, to);
        type[i] = "[E]";
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + type[i] + "[ ]" + tasks[i]);
        System.out.println("Now you have " + (i + 1) + " tasks in the list.");
        done[i] = false;
    }
    public static void main(String[] args){
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        String line;
        Task[] tasks = new Task[100];
        boolean[] done = new boolean[100];
        String[] type = new String[100];
        int i = 0;
        while (true) {
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            if (line.startsWith("bye")) {
                break;
            }
            if (line.startsWith("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int j = 0; j < i; j++) {
                    System.out.print(j+1);
                    if (done[j]){
                        System.out.print("." + type[j] + "[X]");
                    }
                    if (!done[j]){
                        System.out.print("." + type[j] + "[ ]");
                    }
                    System.out.println(tasks[j]);
                }
            }
            else if (line.startsWith("mark")) {
                try {
                    mark(i, line, tasks, done, type);
                } catch (NumberFormatException | IndexOutOfBoundsException | DukeException e) {
                    System.out.println("Oops! That isn't a task in your list.");
                    System.out.println("Please input only one integer after 'mark', separated by a single whitespace.");
                    System.out.println("There are " + i + " tasks in your list, so do not input an integer " +
                            "smaller than 1 or larger than " + i + ".");
                }
            }
            else if (line.startsWith("unmark")) {
                 try {
                     unmark(i, line, tasks, done, type);
                 } catch (NumberFormatException | IndexOutOfBoundsException | DukeException e) {
                     System.out.println("Oops! That isn't a task in your list.");
                     System.out.println("Please input only one integer after 'mark', separated by a single whitespace.");
                     System.out.println("There are " + i + " tasks in your list, so do not input an integer " +
                             "smaller than 1 or larger than " + i + ".");
                 }
            }
            else if (line.startsWith("todo")) {
                try {
                    todo(i, line, tasks, done, type);
                    i++;
                } catch (DukeException e) {
                    System.out.println("Oops! The description of a todo cannot be left empty.");
                }
            }
            else if (line.startsWith("deadline")) {
                try {
                    deadline(i, line, tasks, done, type);
                    i++;
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Oops! Did you forget the slash?");
                    System.out.println("When specifying deadline, please follow the format below:");
                    System.out.println("deadline (task) /by (deadline)");
                }
            }
            else if (line.startsWith("event")) {
                try {
                    event(i, line, tasks, done, type);
                    i++;
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Oops! Did you forget the slash?");
                    System.out.println("When specifying timeframe, please follow the format below:");
                    System.out.println("event (task) /from (starting time) /to (ending time)");
                }
            }
            else {
                System.out.print("Oops! I'm sorry, I don't understand that command. Try one of these instead:");
                System.out.println("list   mark   unmark   todo   deadline   event");
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
