import java.util.Scanner;

public class Duke {
    public static String lineBreak = "____________________________________________________________\n";
    public static Task[] tasks = new Task[100];

    public static void greet() {
        System.out.println(lineBreak + "Hello I'm\n" +
                "    ____        _        \n" +
                "   |  _ \\ _   _| | _____ \n" +
                "   | | | | | | | |/ / _ \\\n" +
                "   | |_| | |_| |   <  __/\n" +
                "   |____/ \\__,_|_|\\_\\___|" + "\nWhat can I do for you?\n" +
                "Input your tasks and I'll keep track of them!\n" + lineBreak);
    }

    public static void sayBye(){
        System.out.println("Aww you're going? Hope to see you again soon!\n" + lineBreak);
    }

    public static void emptyList() {
        System.out.println("Your list is empty!\n" + lineBreak);
    }

    public static void invalidInput() {
        System.out.println("Please enter a valid command!\n" + lineBreak);
    }

    public static void inputFunction(){
        Scanner in = new Scanner(System.in);
        String line;

        int indexCount;
        boolean isRunning = true;
        String description;
        String deadline;

        do {
            indexCount = Task.getIndexCount();
            System.out.print("Enter Your Task/Command Here: ");
            line = in.nextLine().trim();
            System.out.println(lineBreak);

            if (line.equalsIgnoreCase("bye")) {
                isRunning = false;
            } else {

                String command = line.contains(" ") ? line.split(" ")[0] : line;
                int markIndex;
                int markIndex1;
                int indexSelect;

                switch (command) {

                case "todo":
                    try{
                        description = line.substring(line.indexOf(' ')+1).trim();

                        if (description.equals("")){
                            Duke.invalidInput();
                            break;
                        }

                        ToDo newTask = new ToDo(description);
                        tasks[indexCount] = newTask;
                        newTask.printAdded();
                        System.out.println(lineBreak);
                    } catch (Exception e){
                        Duke.invalidInput();
                    }
                    break;

                case "deadline":
                    try{
                        description = line.substring(line.indexOf(' ') + 1, line.indexOf('/')).trim();
                        markIndex = line.indexOf("/by");
                        if (markIndex == -1){
                            Duke.invalidInput();
                            break;
                        }

                        deadline = line.substring(markIndex+3).trim();

                        if (deadline.equals("")){
                            Duke.invalidInput();
                            break;
                        }

                        ToDo newDeadline = new Deadline(description, deadline);
                        tasks[indexCount] = newDeadline;

                        newDeadline.printAdded();
                        System.out.println(lineBreak);
                    } catch (Exception e){
                        Duke.invalidInput();
                    }
                    break;

                case "event":
                    try{
                        String from;
                        String to;

                        description = line.substring(line.indexOf(' ') + 1, line.indexOf('/')).trim();
                        markIndex = line.indexOf("/from");
                        markIndex1 = line.indexOf("/to");
                        if (markIndex == -1 || markIndex1 == -1){
                            Duke.invalidInput();
                            break;
                        }

                        from = line.substring(markIndex+5, markIndex1).trim();
                        to = line.substring(markIndex1+3).trim();

                        if (from.equals("")){
                            Duke.invalidInput();
                            break;
                        }

                        ToDo newDeadline = new Events(description, from, to);
                        tasks[indexCount] = newDeadline;

                        newDeadline.printAdded();
                        System.out.println(lineBreak);
                    } catch (Exception e){
                        Duke.invalidInput();
                    }
                    break;

                case "":
                    System.out.println("Please input a task!\n" + lineBreak);
                    break;

                case "list":
                    if (indexCount == 0) {
                        Duke.emptyList();
                        break;
                    }

                    for (int i = 0; i < indexCount; i++) {
                        System.out.println(i+ 1 + ". " + tasks[i].listDescription());
                    }
                    System.out.println(lineBreak);
                    break;

                case "mark":
                    try {

                        String done = line.split(" ")[1];
                        if (indexCount == 0) {
                            Duke.emptyList();
                            break;
                        }
                        indexSelect = Integer.parseInt(done) - 1;

                        if (indexSelect < 0 || indexSelect > indexCount) {
                            Duke.invalidInput();
                            break;
                        }
                        tasks[indexSelect].setDone(true);
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(tasks[indexSelect].listDescription());
                        System.out.println(lineBreak);
                    } catch (Exception e) {
                        Duke.invalidInput();
                    }

                    break;

                case "unmark":
                    try {
                        String undone = line.split(" ")[1];
                        if (indexCount == 0) {
                            Duke.emptyList();
                            break;
                        }

                        indexSelect = Integer.parseInt(undone) - 1;
                        if (indexSelect < 0 || indexSelect > indexCount) {
                            Duke.invalidInput();
                            break;
                        }

                        tasks[indexSelect].setDone(false);
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println(tasks[indexSelect].listDescription());
                        System.out.println(lineBreak);
                    } catch (Exception e) {
                        Duke.invalidInput();
                    }
                    break;

                case "help":

                default:
                    Duke.invalidInput();
                }
            }

        } while (isRunning);

    }

    public static void main(String[] args) {
        Duke.greet();
        Duke.inputFunction();
        Duke.sayBye();
    }
}
