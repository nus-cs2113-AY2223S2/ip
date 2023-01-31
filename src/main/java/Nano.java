import java.util.Scanner;

public class Nano {

    final static int HORIZONTAL_LINE_LENGTH = 100;
    final static int MAX_TASK_COUNT = 100;

    //print horizontal line
    public static void printHorizontalLine() {
        for (int i = 0; i < HORIZONTAL_LINE_LENGTH / 10; i += 1) {
            System.out.print("__________");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        //chatbot startup
        printHorizontalLine();
        final String NANO_LOGO = "| \\  ||   / \\   | \\  |||  __  |\n"
                + "||\\\\ ||  / _ \\  ||\\\\ ||| |  | |\n"
                + "|| \\\\|| //   \\\\ || \\\\||| |__| |\n"
                + "||  \\_|//     \\\\||  \\_||______|\n";
        System.out.println(NANO_LOGO);
        System.out.println("Serial number: 034-4532-5893.....");
        System.out.println("Activating the 7th generation Nano Machine of the Chan Corporation.....");
        printHorizontalLine();
        System.out.println("How may I assist you?");
        printHorizontalLine();

        // initialise list of tasks 1-index
        Task[] tasks = new Task[MAX_TASK_COUNT + 1];

        //receive commands
        String text;
        boolean exit = false;
        while (!exit) {
            Scanner in = new Scanner(System.in);
            text = in.nextLine();

            if (text.equals("bye")) {
                //chatbot exit when user types "bye"
                exit = true;
            } else if (text.startsWith("/")) {
                // input is a command
                text = text.replace("/", "").trim();
                int dividerIndex = text.indexOf(" ");
                if (dividerIndex == -1 && text.equals("list")) {
                    // display list
                    System.out.println("You have completed " + Task.getCompletedTaskCount() + " tasks. " + (Task.getTaskCount() - Task.getCompletedTaskCount()) + " more to go!");
                    for (int i = 1; i <= Task.getTaskCount(); i += 1) {
                        System.out.print(i + ". ");
                        if (tasks[i].getTaskStatus()) {
                            System.out.print("[x] ");
                        } else {
                            System.out.print("[] ");
                        }
                        System.out.println(tasks[i].getTaskName());
                    }
                } else {
                    // split command and taskName into 2 strings
                    String command = text.substring(0, dividerIndex);
                    String taskName = text.substring(dividerIndex + 1);

                    switch (command) {
                    case "mark":
                        // mark task as completed
                        for (int i = 1; i <= Task.getTaskCount(); i += 1) {
                            if (tasks[i].getTaskName().equals(taskName) && !tasks[i].getTaskStatus()) {
                                tasks[i].markTask();
                                System.out.println("Marked " + tasks[i].getTaskName());
                                break;
                            }
                        }
                        break;
                    case "unmark":
                        // unmark task completed status
                        for (int i = 1; i <= Task.getTaskCount(); i += 1) {
                            if (tasks[i].getTaskName().equals(taskName) && tasks[i].getTaskStatus()) {
                                tasks[i].unmarkTask();
                                System.out.println("Unmarked " + tasks[i].getTaskName());
                                break;
                            }
                        }
                        break;
                    }
                }
            } else {
                // add task to list
                Task newTask = new Task(text);
                tasks[Task.getTaskCount()] = newTask;
                System.out.println("Added " + newTask.getTaskName() + " to list.");
            }
        }

        //chatbot exit message
        System.out.println("Sleep mode activated.");
    }
}
