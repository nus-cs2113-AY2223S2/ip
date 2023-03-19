package nova;

import java.io.FileNotFoundException;
import nova.exception.EmptyInputsException;
import nova.exception.ExceptionChecker;

public class Nova {

    public static void main(String[] args) throws EmptyInputsException {
        try {
            Ui.greet_user();
            Storage.checkFile();
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist.");
        }

        String[] command = Parser.getCommand();

        while (!"bye".equals(command[0])) {
            try {
                switch (command[0]) {
                case "todo":
                    Tasklist.todo(command[1]);
                    break;
                case "deadline":
                    String[] d = command[1].split("/by", 2);
                    String d_description = d[0];
                    String d_by = d[1];
                    ExceptionChecker.checkEmptyString(d_description);
                    ExceptionChecker.checkEmptyString(d_by);
                    Tasklist.deadline(d_description, d_by);
                    break;
                case "event":
                    String[] er = command[1].split("/from|/to", 3);
                    String e_description = er[0];
                    String e_start = er[1];
                    String e_end = er[2];
                    ExceptionChecker.checkEmptyString(e_description);
                    ExceptionChecker.checkEmptyString(e_start);
                    ExceptionChecker.checkEmptyString(e_end);
                    Tasklist.event(e_description, e_start, e_end);
                    break;
                case "list":
                    Tasklist.list();
                    break;
                case "mark":
                    Integer m_index = Integer.valueOf(command[1]);
                    Tasklist.markTask(m_index);
                    break;
                case "unmark":
                    Integer u_index = Integer.valueOf(command[1]);
                    Tasklist.unmarkTask(u_index);
                    break;
                case "delete":
                    Integer d_index = Integer.valueOf(command[1]);
                    Tasklist.delete(d_index);
                    break;
                case "find":
                    String keyword = command[1];
                    Tasklist.find(keyword);
                    break;
                default:
                    System.out.println("Command not recognised. Please try again");
                    break;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Command is incomplete. Please try again!");
            } catch (EmptyInputsException a) {
                System.out.println(a.getMessage());
            }
            command = Parser.getCommand();
        }
        Ui.goodbye_user();
        Tasklist.saveFile();
    }
}