import java.util.Arrays;
public class DukeException extends Exception {
    public DukeException() {
    }

}

class catchError {

    static final String[] INSTRUCTIONS = new String[] {"todo", "deadline", "event", "mark", "unmark", "bye", "list"};
    static void validateEmpty (String line) throws DukeException {
        String[] lineList = line.split(" ");
        if (lineList.length <= 1 && !lineList[0].equals("list") && !lineList[0].equals("bye")) {
            throw new DukeException();
        }
    }

    static void validateMeaningful (String instruction) throws DukeException {
        if (!Arrays.asList(INSTRUCTIONS).contains(instruction)) {
            throw new DukeException();
        }
    }
}


