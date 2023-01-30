import java.util.Scanner;

public class Psyduck {

    private static Task[] tasks = new Task[100];

    private static int taskCount = 0;

    public static void addToDo(String description) {
        ToDo newTask = new ToDo(description);
        tasks[taskCount] = newTask;
        taskCount++;
    }

    public static void addDeadline(String description, String by) {
        Deadline newTask = new Deadline(description, by);
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
            String description;
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
            case "todo":
                if (space == -1) {
                    linePrint();
                    System.out.println("Todo task cannot be empty! >:(");
                    linePrint();
                    break;
                }
                description = input.substring(space+1);
                addToDo(description);
                linePrint();
                System.out.println("Psyduck has added the task: " + tasks[taskCount-1]);
                linePrint();
                break;
            case "deadline":
                if (space == -1) {
                    System.out.println("Deadline task cannot be empty! >:(");
                    linePrint();
                    break;
                }
                int slashPos = input.indexOf("/by");
                if (slashPos == -1) {
                    linePrint();
                    System.out.println("Psyduck needs a deadline.");
                    System.out.println("Remember to use the <deadline> <description> </by> <date> format for your input");
                    linePrint();
                    break;
                }
                description = input.substring(space + 1, slashPos);
                String by = input.substring(slashPos + 4);
                addDeadline(description, by);
                linePrint();
                System.out.println("Psyduck has added the task: " + tasks[taskCount-1]);
                linePrint();
                break;
            default:
                linePrint();
                System.out.println("Invalid command, Psyduck does not understand.");
                System.out.println("Please enter a valid command.");
                linePrint();
            }
        } while (shouldExit == false);
        System.out.println("Buh Bye");
        linePrint();
    }
}
