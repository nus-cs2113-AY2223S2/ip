import java.util.Scanner;

public class Shizuka {
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
        String line;
        do {
            line = in.nextLine();
            String lineTrimmed = line.trim();
            int wordEnd = lineTrimmed.indexOf(' ') + 1;
            String command;
            if (wordEnd != 0) {
                command = lineTrimmed.substring(0, wordEnd - 1);
            } else {
                command = lineTrimmed;
            }
            int taskNum;
            switch (command) {
            case "bye":
                break;
            case "list":
                TodoList.list();
                break;
            case "mark":
                taskNum = Integer.parseInt(lineTrimmed.substring(wordEnd));
                TodoList.mark(taskNum);
                break;
            case "unmark":
                taskNum = Integer.parseInt(lineTrimmed.substring(wordEnd));
                TodoList.unmark(taskNum);
                break;
            default:
                TodoList.add(line);
            }
        }
        while (!line.equals("bye"));
        System.out.println(LINE_BREAK + EXIT + LINE_BREAK);
    }
}
