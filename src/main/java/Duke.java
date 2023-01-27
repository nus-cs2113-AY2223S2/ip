import java.util.Scanner;

public class Duke {
    static String line = "\t____________________________________________________________";
    static String commandBye = "bye";
    static String commandList = "list";
    static String commandMark = "mark";
    static String commandUnmark = "unmark";
    static String commandEvent = "event";
    static String commandTodo = "todo";
    static String commandDeadline = "deadline";
    static Task[] tasks = new Task[100];
    static int textCount = 0;

    public static void commandType(String userCommand) {
        String[] extract_firstWord = userCommand.split(" ", 2);
        String firstWord = extract_firstWord[0];
        if (firstWord.equals(commandMark)) {
            int taskNum = Integer.parseInt(extract_firstWord[1]);
            tasks[--taskNum].setStatus(true);
            System.out.println(line);
            System.out.println("\tNoted. Task " + (taskNum + 1) + " has been marked as \"complete\":");
            System.out.println("\t  " + tasks[taskNum].getTaskNameAndStatus());
            System.out.println(line);
        } else if (firstWord.equals(commandUnmark)) {
            int taskNum = Integer.parseInt(extract_firstWord[1]);
            tasks[--taskNum].setStatus(false);
            System.out.println(line);
            System.out.println("\tOh, ok. Task " + (taskNum + 1) + " has been marked as \"incomplete\":");
            System.out.println("\t  " + tasks[taskNum].getTaskNameAndStatus());
            System.out.println(line);
        } else if (firstWord.equals(commandList)) {
            System.out.println(line);
            System.out.println("\tHere are your tasks:");
            int count = 1;
            for (int index = 0; index < textCount; index++) {
                System.out.print("\t" + count + ".");
                System.out.println(tasks[index]);
                count++;
            }
            System.out.println(line);
        } else if (firstWord.equals(commandBye)) {
            Bye();
        } else if (firstWord.equals(commandTodo)) {
            tasks[textCount] = new Todo(extract_firstWord[1]);
            textCount++;
            System.out.println(line);
            System.out.println("\t" + "Task added!");
            System.out.println("\t  " + tasks[textCount - 1]);
            System.out.println("\t" + "Now you have " + textCount + " tasks in this list.");
            System.out.println(line);
        } else if (firstWord.equals(commandDeadline)) {
            int index = extract_firstWord[1].indexOf("/by");
            tasks[textCount] = new Deadline(extract_firstWord[1].substring(0, index), extract_firstWord[1].substring(index + 4));
            textCount++;
            System.out.println(line);
            System.out.println("\t" + "Task added!");
            System.out.println("\t  " + tasks[textCount - 1]);
            System.out.println("\t" + "Now you have " + textCount + " tasks in this list.");
            System.out.println(line);
        } else if (firstWord.equals(commandEvent)) {
            int index = extract_firstWord[1].indexOf("/from");
            int index2 = extract_firstWord[1].indexOf("/to");
            tasks[textCount] = new Event(extract_firstWord[1].substring(0, index), extract_firstWord[1].substring(index + 6, index2 - 1), extract_firstWord[1].substring(index2 + 4));
            textCount++;
            System.out.println(line);
            System.out.println("\t" + "Task added!");
            System.out.println("\t  " + tasks[textCount - 1]);
            System.out.println("\t" + "Now you have " + textCount + " tasks in this list.");
            System.out.println(line);
        } else {
            System.out.println("Please input a valid command!");
        }
    }

    public static void Greet() {
        System.out.println(line);
        System.out.println("\tHello! I'm Percy.");
        System.out.println("\tHow can I help you today?\n");
        System.out.println(line);
    }

    public static void Bye() {
        System.out.println(line);
        System.out.println("\tBye! Remember to finish your tasks.\n");
        System.out.println(line);
    }

    public static void main(String[] args) {
        Greet();
        Scanner in = new Scanner(System.in);
        String userCommand;

        do {
            userCommand = in.nextLine();
            commandType(userCommand);
            /*
            if (checker == 0 || checker == 1) {
                if (checker == 0) {
                    int taskNum = Integer.parseInt(userCommand.substring(userCommand.indexOf(" ") + 1));
                    tasks[--taskNum].setStatus(true);
                    System.out.println(line);
                    System.out.println("\tNoted. Task " + (taskNum + 1) + " has been marked as \"complete\":");
                    System.out.println("\t  " + tasks[taskNum].getTaskNameAndStatus());
                    System.out.println(line);
                }
                if (checker == 1) {
                    int taskNum = Integer.parseInt(userCommand.substring(userCommand.indexOf(" ") + 1));
                    tasks[--taskNum].setStatus(false);
                    System.out.println(line);
                    System.out.println("\tOh, ok. Task " + (taskNum + 1) + " has been marked as \"incomplete\":");
                    System.out.println("\t  " + tasks[taskNum].getTaskNameAndStatus());
                    System.out.println(line);
                }
            } else if (checker == 2) {
                System.out.println(line);
                System.out.println("\tHere are your tasks:");
                int count = 1;
                for (int index = 0; index < textCount; index++) {
                    System.out.print("\t" + count + ".");
                    System.out.println(tasks[index].getTaskNameAndStatus());
                    count++;
                }
                System.out.println(line);
            } else if (checker == 3) {
                Bye();
            } else {
                tasks[textCount] = new Task(userCommand);
                textCount++;
                System.out.println(line);
                System.out.println("\t" + "Task added: " + userCommand);
                System.out.println(line);
            }*/
        } while (!userCommand.equals(commandBye));
    }
}
