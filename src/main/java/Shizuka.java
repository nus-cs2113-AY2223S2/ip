import java.util.Scanner;

public class Shizuka {
    public static String[] parseCommand(String args) {
        return args.split(" ", 2);
    }

    public static int parseNumber(String args) {
        int endIndex = args.indexOf(' ') + 1;
        return Integer.parseInt(args.substring(endIndex));
    }

    public static void main(String[] args) {
        Printer.intro();
        Scanner in = new Scanner(System.in);
        String line, lineTrimmed;

        do {
            line = in.nextLine();
            lineTrimmed = line.trim();
            String[] command = parseCommand(lineTrimmed);
            int taskNum;
            switch (command[0]) {
            case "bye":
                break;
            case "list":
                TodoList.list();
                break;
            case "mark":
                try {
                    taskNum = parseNumber(command[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    Printer.noArgsError();
                    break;
                }
                TodoList.mark(taskNum);
                break;
            case "unmark":
                try {
                    taskNum = parseNumber(command[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    Printer.noArgsError();
                    break;
                }
                TodoList.unmark(taskNum);
                break;
            case "todo":
                try {
                    TodoList.todo(command[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    Printer.noArgsError();
                    break;
                }
                break;
            case "deadline":
                try {
                    TodoList.deadline(command[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    Printer.noArgsError();
                    break;
                }
                break;
            case "event":
                try {
                    TodoList.event(command[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    Printer.noArgsError();
                    break;
                }
                break;
            default:
                Printer.parseError();
            }
        }
        while (!lineTrimmed.equals("bye"));
        Printer.exit();
    }
}
