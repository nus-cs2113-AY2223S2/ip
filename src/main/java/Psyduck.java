import java.util.Scanner;

public class Psyduck {


    public static void linePrint() {
        for (long i = 0; i < 100; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        String input;
        int taskNum;
        boolean shouldExit = false;
        Scanner in = new Scanner(System.in);
        ToDoList list = new ToDoList();
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
                linePrint();
                list.listTasks();
                linePrint();
                break;
            case "mark":
                taskNum = Integer.parseInt(input.substring(space + 1));
                list.markTask(taskNum);
                linePrint();
                System.out.println("You have marked the task: " + list.getTaskDescription(taskNum));
                linePrint();
                break;
            case "unmark":
                taskNum = Integer.parseInt(input.substring(space + 1));
                list.unmarkTask(taskNum);
                linePrint();
                System.out.println("You have unmarked the task: " + list.getTaskDescription(taskNum));
                linePrint();
                break;
            default:
                String description = input;
                list.createTask(description);
                linePrint();
                System.out.println("Psyduck has added the task: " + description);
                linePrint();
            }
        } while (shouldExit == false);
        System.out.println("Buh Bye");
        linePrint();
    }
}
