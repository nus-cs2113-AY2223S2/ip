import java.util.Scanner;

public class Shizuka {
    public static String[] parseCommand(String args) {
        String[] command = new String[2];
        int endIndex = args.indexOf(' ') + 1;
        if (endIndex != 0) {
            command[0] = args.substring(0, endIndex - 1);
            command[1] = args.substring(endIndex);
        } else {
            command[0] = args;
        }
        return command;
    }

    public static int parseNumber(String args) {
        int endIndex = args.indexOf(' ') + 1;
        return Integer.parseInt(args.substring(endIndex));
    }

    public static void main(String[] args) {
        Printer.intro();
        Scanner in = new Scanner(System.in);
        String line, lineTrimmed, command, commandArgs;

        do {
            line = in.nextLine();
            lineTrimmed = line.trim();
            String[] commandArray = parseCommand(lineTrimmed);
            command = commandArray[0];
            commandArgs = commandArray[1];
            int taskNum;
            switch (command) {
            case "bye":
                break;
            case "list":
                TodoList.list();
                break;
            case "mark":
                taskNum = parseNumber(commandArgs);
                TodoList.mark(taskNum);
                break;
            case "unmark":
                taskNum = parseNumber(commandArgs);
                TodoList.unmark(taskNum);
                break;
            case "todo":
                TodoList.add(commandArray);
                break;
            case "deadline":
                TodoList.add(commandArray);
                break;
            case "event":
                TodoList.add(commandArray);
                break;
            default:
                Printer.parseError();
            }
        }
        while (!lineTrimmed.equals("bye"));
        Printer.exit();
    }
}
