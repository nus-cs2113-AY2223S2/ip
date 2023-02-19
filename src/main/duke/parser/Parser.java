package duke.parser;

import duke.ui.DukeMessages;
import duke.util.DukeException;
import java.util.Scanner;

public class Parser {

    private final DukeMessages ui;
    private final Scanner in;
    private String next;
    private String cmd;
    private int num;

    public Parser(DukeMessages ui) {
        this.ui = ui;
        this.in = new Scanner(System.in);
    }

    public void convertString(String str, int taskCount) throws DukeException {
        try {
            num = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            ui.printNotNumber();
            throw new DukeException();
        }
        if (num < 1 || num > taskCount) {
            ui.printNotInList();
            throw new DukeException();
        }
    }

    public void ignoreLine() {
        in.nextLine();
    }

    public String run() {
        ui.printPrompt();
        cmd = in.next().toLowerCase();
        System.out.print("\n");
        return cmd;
    }

    public int getNum() {
        return num;
    }
    public String check() throws DukeException {
        switch (cmd) {
        case "list":
            break;
//            case "help":
//                String next = in.nextLine();
//                printHelp(next.trim());
//                break;
        case "mark":
        case "unmark":
        case "todo":
        case "deadline":
        case "event":
        case "delete":
        case "find":
            next = in.nextLine();
            break;
        default:
            ignoreLine();
            throw new DukeException();
        }
        return next;
    }
}
