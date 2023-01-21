import java.util.Scanner;

public class Duke {
    public static String printLogo(){
        return ("Hello I'm\n" +
                "    ____        _        \n" +
                "   |  _ \\ _   _| | _____ \n" +
                "   | | | | | | | |/ / _ \\\n" +
                "   | |_| | |_| |   <  __/\n" +
                "   |____/ \\__,_|_|\\_\\___|");
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line;

        Task[] tasks = new Task[100];
        int indexCount = 0;
        boolean isRunning = true;
        String lineBreak = "____________________________________________________________\n";
        System.out.println(lineBreak + Duke.printLogo() + "\nWhat can I do for you?\n" +
                "Input your tasks and I'll keep track of them!\n" + lineBreak);

        do {
            System.out.print("Enter Your Task/Command Here: ");
            line = in.nextLine();
            System.out.println(lineBreak);

            if (line.equals("bye")) {
                System.out.println("Aww you're going? Hope to see you again soon!\n" + lineBreak);
                isRunning = false;
            } else {
                String command = line.contains(" ") ? line.split(" ")[0] : line;
                switch (command) {
                case "":
                    System.out.println("Please input a task!\n" + lineBreak);
                    break;

                case "list":
                    for (int i = 0; i < indexCount; i++) {
                        String check = (tasks[i].isDone()) ? "X" : " ";
                        System.out.println(i + 1 + ". [" + check + "] " + tasks[i].getTaskName());
                    }
                    System.out.println(lineBreak);
                    break;

                case "mark":
                    String done = line.split(" ")[1];
                    int index = Integer.parseInt(done) - 1;
                    tasks[index].setDone(true);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  [X] " + tasks[index].getTaskName() + "\n" + lineBreak);
                    break;

                case "unmark":
                    String undone = line.split(" ")[1];
                    int index1 = Integer.parseInt(undone) - 1;
                    tasks[index1].setDone(false);
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  [ ] " + tasks[index1].getTaskName() + "\n" + lineBreak);
                    break;

                default:
                    Task newTask = new Task(line);
                    tasks[indexCount++] = newTask;
                    System.out.println("\"" + line + "\" has been added to the list\n" + lineBreak);
                }
            }

        } while (isRunning);

    }
}
