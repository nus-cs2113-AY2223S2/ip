package shizuka;

import java.io.IOException;
import java.util.Scanner;

public class Shizuka {
    static final String FILE_PATH = "shizuka.txt";

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
        TodoList list0 = new TodoList();

        do {
            line = in.nextLine();
            lineTrimmed = line.trim();
            String[] command = parseCommand(lineTrimmed);
            int taskNum;
            switch (command[0]) {
            case "bye":
                break;
            case "list":
                list0.list();
                break;
            case "mark":
                try {
                    taskNum = parseNumber(command[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    Printer.noArgsError();
                    break;
                }
                list0.mark(taskNum);
                break;
            case "unmark":
                try {
                    taskNum = parseNumber(command[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    Printer.noArgsError();
                    break;
                }
                list0.unmark(taskNum);
                break;
            case "todo":
                try {
                    list0.addTodo(command[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    Printer.noArgsError();
                    break;
                }
                break;
            case "deadline":
                try {
                    list0.addDeadline(command[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    Printer.noArgsError();
                    break;
                }
                break;
            case "event":
                try {
                    list0.addEvent(command[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    Printer.noArgsError();
                    break;
                }
                break;
            case "delete":
                try {
                    taskNum = parseNumber(command[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    Printer.noArgsError();
                    break;
                }
                list0.deleteTask(taskNum);
                break;
            case "save":
                try {
                    FileManager.save(FILE_PATH, list0.listWriter());
                    Printer.saveSuccess();
                    break;
                } catch (IOException e) {
                    Printer.ioError();
                    break;
                }
            default:
                Printer.parseError();
            }
        }
        while (!lineTrimmed.equals("bye"));
        Printer.exit();
    }
}
