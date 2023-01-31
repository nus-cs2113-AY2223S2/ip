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
        final String LOGO = "   _____  __     _               __         \n"
                + "  / ___/ / /_   (_)____  __  __ / /__ ____ _\n"
                + "  \\__ \\ / __ \\ / //_  / / / / // //_// __ `/\n"
                + " ___/ // / / // /  / /_/ /_/ // ,<  / /_/ / \n"
                + "/____//_/ /_//_/  /___/\\__,_//_/|_| \\__,_/  \n";
        final String LINE_BREAK = "____________________\n";
        final String GREETING = "Hello, I am Shizuka.\nHow can I be of assistance?\n";
        final String EXIT = "Goodbye.\n";
        final String PARSE_ERROR = "I'm sorry, but I don't recognise that command.\n";
        System.out.println(LINE_BREAK + LOGO + LINE_BREAK + GREETING + LINE_BREAK);
        Scanner in = new Scanner(System.in);
        String line, lineTrimmed, command, commandArgs;
        do {
            line = in.nextLine();
            lineTrimmed = line.trim();
            command = parseCommand(lineTrimmed)[0];
            commandArgs = parseCommand(lineTrimmed)[1];
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
                TodoList.addTodo(commandArgs);
                break;
            case "deadline":
                TodoList.addDeadline(commandArgs);
                break;
            case "event":
                TodoList.addEvent(commandArgs);
                break;
            default:
                System.out.println(LINE_BREAK + PARSE_ERROR + LINE_BREAK);
            }
        }
        while (!lineTrimmed.equals("bye"));
        System.out.println(LINE_BREAK + EXIT + LINE_BREAK);
    }
}
