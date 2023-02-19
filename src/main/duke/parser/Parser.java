package duke.parser;

import duke.ui.DukeMessages;
import duke.util.DukeException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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

    public void convertNum (String str) throws DukeException {
        try {
            num = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            throw new DukeException();
        }
        if (num < 1) {
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
            next = in.nextLine();
            break;
        default:
            ignoreLine();
            throw new DukeException();
        }
        return next;
    }

    public LocalDate processDate (String byDate) throws DukeException {
        LocalDate localByDate = LocalDate.now();
        try {
            localByDate = LocalDate.parse(byDate);
        } catch (DateTimeParseException e) {
            String str = byDate.toLowerCase().trim();
            String[] temp = str.split(" ",3);
            if (temp.length == 3) {
                return localByDate;
            }
            switch (temp[0]){
            case "next":
                try {
                    switch (temp[1]) {
                    case "day":
                        localByDate = localByDate.plusDays(1);
                        break;
                    case "week":
                        localByDate = localByDate.plusWeeks(1);
                        break;
                    case "month":
                        localByDate = localByDate.plusMonths(1);
                        break;
                    case "year":
                        localByDate = localByDate.plusYears(1);
                        break;
                    case "decade":
                        localByDate = localByDate.plusYears(10);
                        break;
                    default:
                        throw new DukeException();
                    }
                } catch (IndexOutOfBoundsException f) {
                    throw new DukeException();
                }
            case "tomorrow":
                localByDate = localByDate.plusDays(1);
                break;
            default:
                try {
                    convertNum(temp[0]);
                } catch (DukeException f) {
                    throw new DukeException();
                }
                try {
                    switch (temp[1]) {
                    case "day":
                    case "days":
                        localByDate = localByDate.plusDays(num);
                        break;
                    case "week":
                    case "weeks":
                        localByDate = localByDate.plusWeeks(num);
                        break;
                    case "month":
                    case "months":
                        localByDate = localByDate.plusMonths(num);
                        break;
                    case "year":
                    case "years":
                        localByDate = localByDate.plusYears(num);
                        break;
                    default:
                        throw new DukeException();
                    }
                } catch (IndexOutOfBoundsException f) {
                    throw new DukeException();
                }
            }
        }
        return localByDate;
    }

}
