import java.util.Scanner;

public class Shizuka {
    public static String parseCommand(String args) {
        String command;
        int endIndex = args.indexOf(' ') + 1;
        if (endIndex != 0) {
            command = args.substring(0, endIndex - 1);
        } else {
            command = args;
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
        System.out.println(LINE_BREAK + LOGO + LINE_BREAK + GREETING + LINE_BREAK);
        Scanner in = new Scanner(System.in);
        String line, lineTrimmed;
        do {
            line = in.nextLine();
            lineTrimmed = line.trim();
            int taskNum;
            switch (parseCommand(line)) {
            case "bye":
                break;
            case "list":
                TodoList.list();
                break;
            case "mark":
                taskNum = parseNumber(lineTrimmed);
                TodoList.mark(taskNum);
                break;
            case "unmark":
                taskNum = parseNumber(lineTrimmed);
                TodoList.unmark(taskNum);
                break;
            default:
                TodoList.add(lineTrimmed);
            }
        }
        while (!lineTrimmed.equals("bye"));
        System.out.println(LINE_BREAK + EXIT + LINE_BREAK);
    }
}
