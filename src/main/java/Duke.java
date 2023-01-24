import java.util.Scanner;

public class Duke {
    public static String lineBreak = "____________________________________________________________\n";

    public static void greet(){
        System.out.println(lineBreak + "Hello I'm\n" +
                "    ____        _        \n" +
                "   |  _ \\ _   _| | _____ \n" +
                "   | | | | | | | |/ / _ \\\n" +
                "   | |_| | |_| |   <  __/\n" +
                "   |____/ \\__,_|_|\\_\\___|" + "\nWhat can I do for you?\n" +
                "Input your tasks and I'll keep track of them!\n" + lineBreak);
    }

    public static void emptyList(){
        System.out.println("Your list is empty!\n" + lineBreak);
    }

    public static void invalidMark(){
        System.out.println("Please input a valid task number!\n" + lineBreak);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line;

        Task[] tasks = new Task[100];
        int indexCount = 0;
        boolean isRunning = true;
        Duke.greet();

        do {
            System.out.print("Enter Your Task/Command Here: ");
            line = in.nextLine().trim();
            System.out.println(lineBreak);

            if (line.equalsIgnoreCase("bye")) {
                System.out.println("Aww you're going? Hope to see you again soon!\n" + lineBreak);
                isRunning = false;
            } else {
                String command = line.contains(" ") ? line.split(" ")[0] : line;
                int index;
                switch (command) {

                case "":
                    System.out.println("Please input a task!\n" + lineBreak);
                    break;

                case "list":
                    if (indexCount == 0){
                        Duke.emptyList();
                        break;
                    }

                    for (int i = 0; i < indexCount; i++) {
                        String check = (tasks[i].isDone()) ? "X" : " ";
                        System.out.println(i + 1 + ". [" + check + "] " + tasks[i].getTaskName());
                    }
                    System.out.println(lineBreak);
                    break;

                case "mark":
                    String done = line.split(" ")[1];
                    if (indexCount == 0){
                        Duke.emptyList();
                        break;
                    }

                    try{
                        index = Integer.parseInt(done) - 1;

                        if (index < 0 || index > indexCount){
                            Duke.invalidMark();
                            break;
                        }
                        tasks[index].setDone(true);
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println("  [X] " + tasks[index].getTaskName() + "\n" + lineBreak);
                    } catch (Exception e){
                        Duke.invalidMark();
                    }


                    break;

                case "unmark":
                    String undone = line.split(" ")[1];
                    if (indexCount == 0){
                        Duke.emptyList();
                        break;
                    }

                    try{
                        index = Integer.parseInt(undone) - 1;
                        if (index < 0 || index > indexCount){
                            Duke.invalidMark();
                            break;
                        }
                        tasks[index].setDone(false);
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println("  [ ] " + tasks[index].getTaskName() + "\n" + lineBreak);
                    } catch (Exception e){
                        Duke.invalidMark();
                    }
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
