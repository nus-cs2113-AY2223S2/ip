package shizuka;

import java.io.IOException;
import java.util.Scanner;

public class Shizuka {
    static final String FILE_PATH = "shizuka.txt";

    /**
     * Splits the input string into command keyword and arguments.
     * @param args User input string
     * @return Array of size 2, with command keyword at index 0 and arguments at index 1
     */
    public static String[] parseCommand(String args) {
        return args.split(" ", 2);
    }

    /**
     * Parses the task number from the input string.
     * @param args User input string
     * @return Task number
     */
    public static int parseNumber(String args) {
        int endIndex = args.indexOf(' ') + 1;
        return Integer.parseInt(args.substring(endIndex));
    }

    public static void main(String[] args) {
        UI.intro();
        Scanner in = new Scanner(System.in);
        String line, lineTrimmed;
        TodoList list0 = new TodoList();
        try {
            FileManager.load(FILE_PATH, list0);
            UI.fileLoaded();
        } catch (IOException e) {
            UI.fileNotFound();
        }

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
                    UI.noArgsError();
                    break;
                }
                list0.mark(taskNum);
                break;
            case "unmark":
                try {
                    taskNum = parseNumber(command[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    UI.noArgsError();
                    break;
                }
                list0.unmark(taskNum);
                break;
            case "todo":
                try {
                    list0.addTodo(command[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    UI.noArgsError();
                    break;
                }
                break;
            case "deadline":
                try {
                    list0.addDeadline(command[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    UI.noArgsError();
                    break;
                }
                break;
            case "event":
                try {
                    list0.addEvent(command[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    UI.noArgsError();
                    break;
                }
                break;
            case "delete":
                try {
                    taskNum = parseNumber(command[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    UI.noArgsError();
                    break;
                }
                list0.deleteTask(taskNum);
                break;
            case "save":
                try {
                    FileManager.save(FILE_PATH, list0.listWriter());
                    UI.saveSuccess();
                    break;
                } catch (IOException e) {
                    UI.ioError();
                    break;
                }
            case "find":
                list0.find(command[1]);
                break;
            default:
                UI.parseError();
            }
        }
        while (!lineTrimmed.equals("bye"));
        UI.exit();
    }
}
