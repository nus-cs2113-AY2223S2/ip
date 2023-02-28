package duke.parser;

import duke.ui.DukeMessages;
import duke.util.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * This object takes in the user input <code>String</code> and converts it into a format that is readable by the
 * application.
 * This object also verifies the validity of the user input and throws error messages for invalid inputs.
 */
public class Parser {

    private final DukeMessages ui;
    private final Scanner in;
    private String next;
    private String cmd;
    private int num;

    /**
     * Initializes this object with the user input from <code>System.in</code> and with the text UI being the
     * application's text UI.
     * @param ui The text UI of the application.
     */
    public Parser(DukeMessages ui) {
        this.ui = ui;
        this.in = new Scanner(System.in);
    }

    /**
     * Converts user input <code>String</code> into a valid number id for task objects currently stored within the
     * application.
     * @param str The user input <code>String</code>.
     * @param taskCount The upper-bound for valid number id.
     * @throws DukeException Throws an exception for invalid user input.
     */
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

    /**
     * Converts user input <code>String</code> into a valid positive number.
     *
     * @param str The user input to convert into a number.
     * @throws DukeException Throws an exception if the user input is invalid.
     */
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

    /**
     * Takes in the next user input and ignores it.
     */
    public void ignoreLine() {
        in.nextLine();
    }

    /**
     * Takes in a single <code>String</code> as a command and prints messages for prompting user.
     *
     * @return The command as a single <code>String</code>.
     */
    public String run() {
        ui.printPrompt();
        cmd = in.next().toLowerCase();
        System.out.print("\n");
        return cmd;
    }

    public int getNum() {
        return num;
    }

    /**
     * Checks for commands which need additional user input and ignores the additional user input if additional
     * user input is not required.
     *
     * @return The additional user input.
     * @throws DukeException Throws exception if additional user input not required.
     */
    public String check() throws DukeException {
        switch (cmd) {
        case "list":
        case "clear":
            ignoreLine();
            break;
        case "mark":
        case "unmark":
        case "todo":
        case "deadline":
        case "event":
        case "delete":
        case "find":
        case "date":
            next = in.nextLine();
            break;
        default:
            ignoreLine();
            throw new DukeException();
        }
        return next;
    }

    /**
     * Checks if the user input <code>String</code> can be converted into a <code>LocalDate</code> and returns the
     * <code>LocalDate</code> after conversion.
     * Returns <code>null</code> otherwise.
     * Valid keywords such as <code>tomorrow</code> can be converted into <code>LocalDate</code> as well.
     *
     * @param byDate The user input <code>String</code> to convert into <code>LocalDate</code>.
     * @return The converted user input.
     * @throws DukeException Throws exception for user inputs not able to be converted into <code>LocalDate</code>.
     */
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
            switch (temp[0].trim()){
            case "next":
                try {
                    switch (temp[1].trim()) {
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
                break;
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
