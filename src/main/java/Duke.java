import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        greetingMessage();
        readAndRespond();
        exitMessage();
    }

    private static void readAndRespond() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        Task[] listOfTasks = new Task[101]; // 1-base indexing
        while (!input.equals("bye")) {
            String[] words = input.split(" ");
            if (input.equals("list")) {
                printList(listOfTasks);
            } else if (input.isBlank()) {
                System.out.println("Please enter a non-empty string!");
            } else if (input.contains("unmark") && words.length == 2 && isInt(words[1])) {
                unmarkTask(listOfTasks, words);
            } else if (input.contains("mark") && words.length == 2 && isInt(words[1])) {
                markTask(listOfTasks, words);
            } else if (words[0].equals("todo") && words.length != 1) {
                printAddedTodoMessage(input, listOfTasks);
            } else if (words[0].equals("deadline") && words.length != 1) {
                printAddedDeadlineMessage(input, listOfTasks);
            } else if (words[0].equals("event") && words.length != 1) {
                printAddedEventMessage(input, listOfTasks);
            } else {
                printAddedTaskMessage(input, listOfTasks);
            }
            input = sc.nextLine();
        }
    }

    private static void greetingMessage() {
        String logo = "Hello! I'm Duke\n" + "What can I do for you?\n";
        System.out.println(logo);
    }

    private static void exitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void printAddedTaskMessage(String input, Task[] listOfTasks) {
        Task newTask = new Task(input);
        listOfTasks[Task.taskCount] = newTask;
        System.out.println(newTask.addedMessage());
    }

    private static void printAddedEventMessage(String input, Task[] listOfTasks) {
        int idxOfSlash = input.indexOf('/');
        Event newEvent = new Event(input.substring(6, idxOfSlash),
                input.substring(idxOfSlash + 6, input.indexOf('/', idxOfSlash + 1)),
                input.substring(input.indexOf('/', idxOfSlash + 1) + 4));
        listOfTasks[Task.taskCount] = newEvent;
        System.out.println(newEvent.addedMessage());
    }

    private static void printAddedDeadlineMessage(String input, Task[] listOfTasks) {
        int idxOfSlash = input.indexOf('/');
        Deadline newDeadline = new Deadline(input.substring(9, idxOfSlash), input.substring(idxOfSlash + 4));
        listOfTasks[Task.taskCount] = newDeadline;
        System.out.println(newDeadline.addedMessage());
    }

    private static void printAddedTodoMessage(String input, Task[] listOfTasks) {
        ToDo newTodo = new ToDo(input.substring(5));
        listOfTasks[Task.taskCount] = newTodo;
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

    private static void markTask(Task[] listOfTasks, String[] words) {
        int number = Integer.parseInt(words[1]);
        if (number <= 0 || number > 100 || listOfTasks[number] == null) {
            System.out.println("Please mark only valid tasks!");
        } else {
            listOfTasks[number].markAsDone();
            System.out.println("  " + listOfTasks[number].getStatusIcon() + " " + listOfTasks[number].description);
        }
    }

    private static void unmarkTask(Task[] listOfTasks, String[] words) {
        int number = Integer.parseInt(words[1]);
        if (number <= 0 || number > 100 || listOfTasks[number] == null || words.length == 1 || !isInt(words[1])) {
            System.out.println("Please unmark only valid tasks!");
        } else {
            listOfTasks[number].unmarkDone();
            System.out.println(" " + listOfTasks[number].getStatusIcon() + " " + listOfTasks[number].description);
        }
    }

    public static void printList(Task[] listOfTasks) {
        System.out.println(" Here are the tasks in your list");
        for (int i = 1; i <= 100; ++i) {
            if (listOfTasks[i] == null) {
                break;
            }
            System.out.println(" " + i + "." + listOfTasks[i].statusMessage());
        }
    }

}
