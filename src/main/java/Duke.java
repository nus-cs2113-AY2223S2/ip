
// import java.util.Arrays;
import java.util.Scanner;

public class Duke {

    public static void listTask(Task[] list) {
        if (list[0] == null) {
            System.out.println("You have no tasks in your list yet!");
            return;
        }

        int count = 1;
        for (Task item : list) {
            if (item != null) {
                System.out.println(count + ". " + item.describeTask());
                count++;
            } else {
                System.out.println("\n");
                break;
            }
        }
    }

    public static String getFirstWord(String s) {
        String[] words = s.split(" ");
        return words[0];
    }

    public static String getSecondWord(String s) {
        int index = s.indexOf(" ");
        String sub = s.substring(index + 1);
        return sub;
    }

    public static void markTask(Task[] list, int index) {
        list[index - 1].markAsDone();
        System.out.println("Nice! I've marked this task as done:\n" + list[index - 1].describeTask() + "\n");
    }

    public static void unmarkTask(Task[] list, int index) {
        list[index - 1].unmarkAsDone();
        System.out.println("OK, Ive marked this task as not done yet:\n" + list[index - 1].describeTask() + "\n");
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        Scanner in = new Scanner(System.in);
        int count = 0;
        boolean isComplete = false;
        Task[] taskList = new Task[100];

        while (!isComplete) {

            String line = in.nextLine();
            String firstWord = getFirstWord(line);

            switch (firstWord) {
                case "mark":
                    markTask(taskList, Integer.parseInt(getSecondWord(line)));
                    break;
                case "unmark":
                    unmarkTask(taskList, Integer.parseInt(getSecondWord(line)));
                    break;
                default:
                    switch (line) {
                        case "list":
                            listTask(taskList);
                            break;
                        case "bye":
                            isComplete = true;
                            break;
                        default:
                            taskList[count] = new Task(line);
                            count++;
                            System.out.println("added: " + line + "\n");
                    }
            }
        }

        in.close();
        System.out.println("Bye. Hope to see you again soon!");
    }
}
