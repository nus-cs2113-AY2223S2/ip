import java.util.Scanner;

public class Duke {

    private static void printList(Task[] s, int count) {
        System.out.println("---------------------------------------------");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < count; ++i) {
            String index = Integer.toString(i + 1);
            System.out.println(index + ".[" + s[i].getStatusIcon() + "] " + s[i].getDescription());
        }
        System.out.println("---------------------------------------------");
    }

    public static void main(String[] args) {
        System.out.println("---------------------------------------------");
        System.out.println("Hello I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("---------------------------------------------");

        boolean shouldRun = true;
        Task[] taskList = new Task[100];
        int count = 0;
        int index = 0;
        String line;

        while (shouldRun) {
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            String[] cases = line.split(" ");
            switch (cases[0]) {
            case "list":
                printList(taskList, count);
                break;
            case "mark":
                index = Integer.parseInt(cases[1]) - 1;
                if (taskList[index] == null) {
                    System.out.println("---------------------------------------------");
                    System.out.println("No such task! Try again.");
                    System.out.println("---------------------------------------------");
                    break;
                }
                taskList[index].setDone(true);
                System.out.println("---------------------------------------------");
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(" [" + taskList[index].getStatusIcon() + "] " + taskList[index].getDescription());
                System.out.println("---------------------------------------------");
                break;
            case "unmark":
                index = Integer.parseInt(cases[1]) - 1;
                if (taskList[index] == null) {
                    System.out.println("---------------------------------------------");
                    System.out.println("No such task! Try again.");
                    System.out.println("---------------------------------------------");
                    break;
                }
                taskList[index].setDone(false);
                System.out.println("---------------------------------------------");
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(" [" + taskList[index].getStatusIcon() + "] " + taskList[index].getDescription());
                System.out.println("---------------------------------------------");
                break;
            case "bye":
                shouldRun = false;
                break;
            default:
                System.out.println("---------------------------------------------");
                taskList[count] = new Task(line);
                count++;
                System.out.println("added: " + line);
                System.out.println("---------------------------------------------");
            }
        }
        System.out.println("---------------------------------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("---------------------------------------------");
        return;
    }
}
