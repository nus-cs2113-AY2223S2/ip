import java.util.Arrays;
import java.util.Scanner;

public class Duke {

    public static final String LINE = "    ____________________________________________________________\n";
    public static final String GREET = "     past.Hello! I'm Duke\n" +
            "     Welcome to Your To-do List!\n" +
            "     Enter \"todo 'task-name'\" to add a task.\n" +
            "     Enter \"deadline 'task-name' /by 'deadline'\" to add a task with a deadline.\n" +
            "     Enter \"event 'task-name' /from 'start-date' /to 'end-date'\" to add a task with start and end dates.\n" +
            "     Enter \"mark 'task-index'\" to mark a task as done.\n" +
            "     Enter \"unmark 'task-index'\" to mark a task as not done yet.\n" +
            "     Enter \"list\" to obtain a list of all your tasks!.\n";
    public static final String BYE = "     Bye. Hope to see you again soon!\n";
    public static final String NOT_DONE = "    OK :(, I've marked this task as not done yet: \n    ";
    public static final String DONE = "    Nice! I've marked this task as done: \n    ";
    public static final String ERROR = "    Invalid command! :( Check your input and try again! \n";

    public static void printAddedTask(Task task, int total) {
        System.out.print(LINE + "    Got it. I've added this task:\n      " +
                task.toString() + "\n    Now you have " + total + " tasks in the list.\n" +
                LINE);
    }

    private static void addEvent(int count, Task[] tasks, String[] words) throws MissingDescriptionException {
        if (words.length != 2) {
            throw new MissingDescriptionException();
        }
        String[] description = words[1].split(" /from ");
        if (description.length != 2) {
            throw new MissingDescriptionException();
        }
        String[] dates = description[1].split(" /to ");
        if (dates.length != 2) {
            throw new MissingDescriptionException();
        }
        tasks[count] = new Event(description[0], "E", dates[0], dates[1]);
    }

    private static void addDeadline(int count, Task[] tasks, String[] words) throws MissingDescriptionException {
        if (words.length != 2) {
            throw new MissingDescriptionException();
        }
        String[] description = words[1].split(" /by ");
        if (description.length != 2) {
            throw new MissingDescriptionException();
        }
        tasks[count] = new Deadline(description[0], "D", description[1]);
    }

    private static void addTodo(int count, Task[] tasks, String[] words) throws MissingDescriptionException {
        if (words.length != 2) {
            throw new MissingDescriptionException();
        }
        String description = words[1];
        tasks[count] = new Task(description, "T");
    }

    private static void editMarkStatus(Task[] tasks, String[] words, String command) {
        int index = Integer.parseInt(words[1]) - 1;
        if (command.equals("unmark")) {
            tasks[index].setDone(false);
            System.out.print(LINE + NOT_DONE);
        } else {
            tasks[index].setDone(true);
            System.out.print(LINE + DONE);
        }
        System.out.print(tasks[index].toString() + "\n" + LINE);
    }

    private static void printList(int count, Task[] tasks, String[] words) throws ExcessInputsException {
        if (words.length > 1) {
            throw new ExcessInputsException();
        }
        System.out.println(LINE + "Here are the tasks in your list:");
        for (int i = 1; i <= count; i++) {
            System.out.println("    " + i + "." + tasks[i-1].toString());
        }
        System.out.print(LINE);
    }

    private static void processCommands(String line, int count, Scanner in) {
        Task[] tasks = new Task[100];
        while (!line.equals("bye")) {
            String[] words = line.split(" ", 2);
            String command = words[0];
            if (command.equals("list")) {
                try {
                    printList(count, tasks, words);
                } catch (ExcessInputsException e) {
                    System.out.println(LINE + "Too many inputs! Please only enter \"list\"\n" + LINE);
                }
            } else if (command.contains("mark")) {
                try {
                    editMarkStatus(tasks, words, command);
                } catch (NumberFormatException e){
                    System.out.println(LINE + "Mark/Unmark must only be followed by the index number!\n" +
                            "Enter \"list\" to check the index.\n" + LINE);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(LINE + "Missing task index number!\n" +
                            "Enter \"list\" to check the index.\n" + LINE);
                } catch (NullPointerException e) {
                    System.out.println(LINE + "Invalid task index number!\n" +
                            "Enter \"list\" to check the index.\n" + LINE);
                }
            } else {
                try {
                    if (command.equals("todo")) {
                        addTodo(count, tasks, words);
                    } else if (command.equals("deadline")) {
                        addDeadline(count, tasks, words);
                    } else if (command.equals("event")) {
                        addEvent(count, tasks, words);
                    } else {
                        System.out.print(LINE + ERROR + LINE);
                        line = in.nextLine();
                        continue;
                    }
                    count++;
                    printAddedTask(tasks[count -1], count);
                } catch (MissingDescriptionException e){
                    System.out.println(LINE + "Missing task description details.\n" +
                            "Please check the input format for adding events\n" + LINE);
                }
            }
            line = in.nextLine();
        }
    }

    private static void readInputs() {
        String line;
        int count = 0;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        processCommands(line, count, in);
    }

    public static void main(String[] args) {
        System.out.print(LINE + GREET + LINE);
        readInputs();
        System.out.print(LINE + BYE + LINE);
    }
}