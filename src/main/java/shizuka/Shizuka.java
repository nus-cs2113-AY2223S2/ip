package shizuka;

import java.io.IOException;
import java.util.Scanner;

public class Shizuka {

    public static void main(String[] args) {
        UI.intro();
        Scanner in = new Scanner(System.in);
        String line, lineTrimmed;
        TodoList list0 = new TodoList();
        try {
            Storage.load(list0);
            UI.fileLoaded();
        } catch (IOException e) {
            UI.fileNotFound();
        }

        do {
            line = in.nextLine();
            lineTrimmed = line.trim();
            String[] command = Parser.parseCommand(lineTrimmed);
            int taskNum;
            switch (command[0]) {
            case "bye":
                break;
            case "list":
                list0.list();
                break;
            case "mark":
                try {
                    taskNum = Parser.parseNumber(command[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    UI.noArgsError();
                    break;
                }
                list0.mark(taskNum);
                break;
            case "unmark":
                try {
                    taskNum = Parser.parseNumber(command[1]);
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
                    taskNum = Parser.parseNumber(command[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    UI.noArgsError();
                    break;
                }
                list0.deleteTask(taskNum);
                break;
            case "save":
                try {
                    Storage.save(list0.listWriter());
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
