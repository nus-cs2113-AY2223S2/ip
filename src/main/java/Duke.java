import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("____________________________________________________________\n"
                            + logo
                            + "Hello! I'm Duke!\n"
                            + "What I can do for you?\n"
                            + "____________________________________________________________\n"
                            );
        Task[] tasks = new Task[100];
        String inputText;
        String outputMessage;
        while (true) {
            inputText = scanner.nextLine();
            if (inputText.equals("list")) {
                outputMessage = Task.getListInputs(tasks);
            } else if (inputText.startsWith("mark")) {
                int taskIndex = Integer.parseInt(inputText.split(" ")[1]) - 1;
                tasks[taskIndex].markAsDone();
                outputMessage = "Nice! I've marked this task as done: " + "\n\t\t"
                        + tasks[taskIndex].toString();
            } else if (inputText.startsWith("unmark")) {
                int taskIndex = Integer.parseInt(inputText.split(" ")[1]) - 1;
                tasks[taskIndex].unmarkAsDone();
                outputMessage = "Ok, I've marked this task as not done: " + "\n\t\t"
                        + tasks[taskIndex].toString();
            } else if (inputText.equals("bye")) {
                outputMessage = "Bye. Hope to see you again soon!";
                break;
            } else {
                tasks[Task.numberOfTasks] = new Task(inputText);
                outputMessage = "added: " + inputText;
            }
            System.out.println("\t____________________________________________________________");
            System.out.println("\t" + outputMessage);
            System.out.println("\t____________________________________________________________");
        }
    }
}
