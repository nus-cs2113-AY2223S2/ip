package duke;

public class DukeException extends Exception {
    public DukeException(String inputString) {
        if (inputString == "") {
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

}
