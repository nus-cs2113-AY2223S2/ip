import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Duke {
    /**
     * Determines if the string is an integer
     * @param str Str is the string we are checking
     * @return True if str is an integer, false otherwise
     * @catch NumberFormatException If str cannot be converted into integer
     */
    public static boolean isInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException exception) {
            return false;
        }
    }

    public static void main(String[] args) {
        String logo = "Hello! I'm Duke\n" + "What can I do for you?\n";
        System.out.println(logo);
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        Task[] listOfTasks = new Task[100];
        int idx = 0;
        while (!input.equals("bye")) {
            String[] words = input.split(" ");
            if (input.equals("list")) {
                for (int i = 0; i < 100; ++i) {
                    if (listOfTasks[i] == null) {
                        break;
                    }
                    System.out.println(i + 1 + "." + listOfTasks[i].getStatusIcon() + " " + listOfTasks[i].description);
                }
            } else if (input.contains("unmark") && words.length == 2 && isInt(words[1])) {
                //
                int number = Integer.parseInt(words[1]);
                if (number <= 0 || number > 100 || listOfTasks[number - 1] == null) {
                    System.out.println("Please unmark only valid tasks");
                } else {
                    listOfTasks[number - 1].unmarkDone();
                    System.out.println(" " + listOfTasks[number - 1].getStatusIcon() + " " + listOfTasks[number - 1].description);
                }
            } else if (input.contains("mark") && words.length == 2 && isInt(words[1])) {
                int number = Integer.parseInt(words[1]);
                if (number <= 0 || number > 100 || listOfTasks[number - 1] == null) {
                    System.out.println("Please mark only valid tasks");
                } else {
                    listOfTasks[number - 1].markAsDone();
                    System.out.println("  " + listOfTasks[number - 1].getStatusIcon() + " " + listOfTasks[number - 1].description);
                }
            } else {
                Task newTodo = new Task(input);
                listOfTasks[idx] = newTodo;
                ++idx;
                System.out.println("added: " + input);
            }
            input = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
