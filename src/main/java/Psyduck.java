import java.util.Scanner;

public class Psyduck {

    private static Task[] tasks = new Task[100];

    private static int taskCount = 0;

    public static void addToDo(String description) {
        ToDo newTask = new ToDo(description);
        tasks[taskCount] = newTask;
        taskCount++;
    }
    public static void listTasks() {
        linePrint();
        for (int i = 0; i < taskCount; i ++) {
            System.out.print(Integer.toString(i+1) + ".");
            System.out.println(tasks[i]);
        }
        linePrint();
    }
    public static void linePrint() {
        for (int i = 0; i < 100; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        String input;
        int taskNum;
        boolean shouldExit = false;
        Scanner in = new Scanner(System.in);
        linePrint();
        System.out.println("Hi I am Psyduck! How can I help you?");
        linePrint();
        do {
            input = in.nextLine();
            int space = input.indexOf(" ");
            String command;
            if (space == -1) {
                command = input;
            } else {
                command = input.substring(0, space);
            }
            switch (command) {
            case "bye":
                shouldExit = true;
                break;
            case "exit":
                shouldExit = true;
                break;
            case "list":
                listTasks();
                break;
            case "mark":
                taskNum = Integer.parseInt(input.substring(space + 1));
                tasks[taskNum-1].markDone();
                linePrint();
                System.out.println("You have marked the task: " + tasks[taskNum-1].getDescription());
                linePrint();
                break;
            case "unmark":
                taskNum = Integer.parseInt(input.substring(space + 1));
                tasks[taskNum-1].unmarkDone();
                linePrint();
                System.out.println("You have unmarked the task: " + tasks[taskNum-1].getDescription());
                linePrint();
                break;
            default:
                String description = input;
                addToDo(description);
                linePrint();
                System.out.println("Psyduck has added the task: " + description);
                linePrint();
            }
        } while (shouldExit == false);
        System.out.println("Buh Bye");
        linePrint();
    }
}
