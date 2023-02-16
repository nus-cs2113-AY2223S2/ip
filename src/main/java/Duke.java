import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static final int MAX_TASKS = 101;

    public static void main(String[] args) {
        greetingMessage();
        try {
            readAndRespond();
        } catch (DukeException e) {
            System.out.println("--FATAL ERROR--");
        }
        exitMessage();
    }

    private static void readAndRespond() throws DukeException {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
       // Task[] listOfTasks = new Task[MAX_TASKS]; // 1-base indexing
        ArrayList<Task> listOfTasks = new ArrayList<>();
        while (!input.equals("bye")) {
            String[] words = input.split(" ");
            if (input.equals("list")) {
                printList(listOfTasks);
            } else if (input.isBlank()) {
                throw new DukeException();
            } else if (isValidUnmark(input, words)) {
                unmarkTask(listOfTasks, words);
            } else if (isValidMark(input, words)) {
                markTask(listOfTasks, words);
            } else if (isValidTodo(words)) {
                printAddedTodoMessage(input, listOfTasks);
            } else if (isValidDeadline(words)) {
                printAddedDeadlineMessage(input, listOfTasks);
            } else if (isValidEvent(words)) {
                printAddedEventMessage(input, listOfTasks);
            } else if (IsValidDelete(input, words)) {
                removeTask(listOfTasks, words);
            } else {
                printAddedTaskMessage(input, listOfTasks);
            }
            input = sc.nextLine();
        }
    }

    private static void removeTask(ArrayList<Task> listOfTasks, String[] words) {
        int number = Integer.parseInt(words[1]);
        if (number <= 0 || number >= MAX_TASKS || words.length == 1 || !isInt(words[1])) {
            System.out.println("Please delete only valid tasks! List out your tasks if you are unsure!!");
        } else {
            listOfTasks.get(number - 1).deleteTask();
            listOfTasks.remove(number - 1);
        }
    }

    private static boolean IsValidDelete(String input, String[] words) {
        return input.startsWith("delete") && words.length == 2 && isInt(words[1]);
    }

    private static boolean isValidMark(String input, String[] words) {
        return input.contains("mark") && words.length == 2 && isInt(words[1]);
    }

    private static boolean isValidUnmark(String input, String[] words) {
        return input.contains("unmark") && words.length == 2 && isInt(words[1]);
    }

    private static boolean isValidEvent(String[] words) {
        return words[0].equals("event") && words.length != 1;
    }

    private static boolean isValidDeadline(String[] words) {
        return words[0].equals("deadline") && words.length != 1;
    }

    private static boolean isValidTodo(String[] words) {
        return words[0].equals("todo") && words.length != 1;
    }

    private static void greetingMessage() {
        String logo = "Hello! I'm Duke\n" + "What can I do for you?\n";
        System.out.println(logo);
    }

    private static void exitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void printAddedTaskMessage(String input, ArrayList<Task> listOfTasks) {
        Task newTask = new Task(input);
        listOfTasks.add(newTask);
        System.out.println(newTask.addedMessage());
    }

    private static void printAddedEventMessage(String input, ArrayList<Task> listOfTasks) {
        int idxOfSlash = input.indexOf('/');
        Event newEvent = new Event(input.substring(6, idxOfSlash),
                input.substring(idxOfSlash + 6, input.indexOf('/', idxOfSlash + 1)),
                input.substring(input.indexOf('/', idxOfSlash + 1) + 4));
        listOfTasks.add(newEvent);
        System.out.println(newEvent.addedMessage());
    }

    private static void printAddedDeadlineMessage(String input, ArrayList<Task> listOfTasks) {
        int idxOfSlash = input.indexOf('/');
        Deadline newDeadline = new Deadline(input.substring(9, idxOfSlash), input.substring(idxOfSlash + 4));
        listOfTasks.add(newDeadline);
        System.out.println(newDeadline.addedMessage());
    }

    private static void printAddedTodoMessage(String input, ArrayList<Task> listOfTasks) {
        ToDo newTodo = new ToDo(input.substring(5));
        listOfTasks.add(newTodo);
        System.out.println(newTodo.addedMessage());
    }

    /**
     * Determines if the string is an integer
     *
     * @param str Str is the string we are checking
     * @return True if str is an integer, false otherwise
     * @catch NumberFormatException If str cannot be converted into integer
     */
    private static boolean isInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException exception) {
            return false;
        }
    }

    private static void markTask(ArrayList<Task> listOfTasks, String[] words) {
        int number = Integer.parseInt(words[1]);
        if (number <= 0 || number >= MAX_TASKS || listOfTasks.get(number) == null) {
            System.out.println("Please mark only valid tasks! List out your tasks if you are unsure!!");
        } else {
            listOfTasks.get(number).markAsDone();
            System.out.println("  " + listOfTasks.get(number).getStatusIcon() + " " + listOfTasks.get(number).description);
        }
    }

    private static void unmarkTask(ArrayList<Task> listOfTasks, String[] words) throws DukeException {
        int number = Integer.parseInt(words[1]);
        if (number <= 0 || number >= MAX_TASKS || listOfTasks.get(number) == null || words.length == 1 || !isInt(words[1])) {
            System.out.println("Please unmark only valid tasks! List out your tasks if you are unsure!!");
        } else {
            listOfTasks.get(number).unmarkDone();
            System.out.println(" " + listOfTasks.get(number).getStatusIcon() + " " + listOfTasks.get(number).description);
        }
    }

    public static void printList(ArrayList<Task> listOfTasks) {
        System.out.println(" Here are the tasks in your list");
        if (Task.taskCount == 0) {
            System.out.println(" Oops! It looks like your list is empty!!");
            return;
        }
        for (int i = 0; i < listOfTasks.size(); ++i) {
            System.out.println(" " + (i + 1) + "." + listOfTasks.get(i).statusMessage());
        }
    }

}
