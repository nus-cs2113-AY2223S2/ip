import java.util.Scanner;

public class Duke {
    static String line = "\t____________________________________________________________";
    static String commandBye = "bye";
    static String commandList = "list";
    static String commandMark = "mark";
    static String commandUnmark = "unmark";
    static Task[] tasks = new Task[100];
    static int textCount = 0;

    public static int commandType(String userCommand) {
        String firstWord = userCommand.split(" ")[0];
        if (firstWord.equals(commandMark)) {
            return 0;
        } else if (firstWord.equals(commandUnmark)) {
            return 1;
        } else if (firstWord.equals(commandList)) {
            return 2;
        } else if (firstWord.equals(commandBye)) {
            return 3;
        } else {
            return 4;
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
        String user_command;

        do {
            user_command = in.nextLine();
            int checker = commandType(user_command);

            if (checker == 0 || checker == 1) {
                if (checker == 0) {
                    int taskNum = Integer.parseInt(user_command.substring(user_command.indexOf(" ") + 1));
                    tasks[--taskNum].setStatus(true);
                    System.out.println(line);
                    System.out.println("\tNoted. Task " + (taskNum + 1) + " has been marked as \"complete\":");
                    System.out.println("\t  " + tasks[taskNum].getTaskNameAndStatus());
                    System.out.println(line);
                }
                if (checker == 1) {
                    int taskNum = Integer.parseInt(user_command.substring(user_command.indexOf(" ") + 1));
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
                tasks[textCount] = new Task(user_command);
                textCount++;
                System.out.println(line);
                System.out.println("\t" + "Task added: " + user_command);
                System.out.println(line);
            }
        } while (!user_command.equals(commandBye));
    }
}
