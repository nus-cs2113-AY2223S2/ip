import java.util.Scanner;

public class Duke {
    private static void displayList(Task[] tasks, int numberOfTasks) {
        for (int i = 0; i < numberOfTasks; i++) {
            if (tasks[i] != null) {
                System.out.println((i + 1) + ". " + "[" + tasks[i].getStatusIcon() + "] " + tasks[i].description);
            }
        }
    }
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        Scanner input = new Scanner(System.in);
        String text = input.nextLine(); // input the whole sentence into text
        String[] splitText = text.split(" ");
        Task[] tasks = new Task[100];
        int numberOfTasks = 1;
        while (!splitText[0].equals("bye")) {
            switch (splitText[0]) {
            case "mark":
                tasks[Integer.valueOf(splitText[1]) - 1].setDone();
                System.out.println("Nice! I've marked this task as done:");
                displayList(tasks, numberOfTasks);
                break;
            case "unmark":
                tasks[Integer.valueOf(splitText[1]) - 1].setUndone();
                System.out.println("OK, I've marked this task as not done yet:");
                displayList(tasks, numberOfTasks);
                break;
            case "list":
                displayList(tasks, numberOfTasks);
                break;
            default:
                tasks[numberOfTasks - 1] = new Task(text);
                numberOfTasks += 1;
                break;
            }
            text = input.nextLine();
            splitText = text.split(" ");
        }
        System.out.println("Bye! Hope to see you again soon!");
    }
}