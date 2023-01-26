import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "   _____  __     _               __         \n"
                + "  / ___/ / /_   (_)____  __  __ / /__ ____ _\n"
                + "  \\__ \\ / __ \\ / //_  / / / / // //_// __ `/\n"
                + " ___/ // / / // /  / /_/ /_/ // ,<  / /_/ / \n"
                + "/____//_/ /_//_/  /___/\\__,_//_/|_| \\__,_/  \n";
        String lineBreak = "____________________\n";
        String greetingLine = "Hello, I am Shizuka.\nHow can I be of assistance?\n";
        String exitLine = "Goodbye.\n";
        System.out.println(lineBreak + logo + lineBreak + greetingLine + lineBreak);
        Scanner in = new Scanner(System.in);
        String line;
        do {
            line = in.nextLine();
            String lineTrimmed = line.trim();
            int wordEnd = lineTrimmed.indexOf(' ')+1;
            String command;
            if (wordEnd != 0) {
                command = lineTrimmed.substring(0, wordEnd - 1);
            }
            else{
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
        System.out.println(lineBreak + exitLine + lineBreak);
    }
}
