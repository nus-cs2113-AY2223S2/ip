package duke;

public class EmptyLineException {
    static void validate(String str) throws DukeException {
        if (str.equals("")) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }
}
