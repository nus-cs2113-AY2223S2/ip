package BasisSupport;

import java.util.Arrays;

public class CatchError {
    static final String[] INSTRUCTIONS = new String[] {"todo", "deadline", "event", "mark", "unmark",
            "bye", "list", "delete", "find"};
    public static void validateEmpty (String line) throws DukeException {
        String[] lineList = line.split(" ");
        if (lineList.length <= 1 && !lineList[0].equals("list") && !lineList[0].equals("bye")) {
            throw new DukeException();
        }
    }

    public static void validateMeaningful (String instruction) throws DukeException {
        if (!Arrays.asList(INSTRUCTIONS).contains(instruction)) {
            throw new DukeException();
        }
    }
}