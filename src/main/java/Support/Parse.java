package Support;

import BasisSupport.DukeException;

import java.util.Arrays;

public class Parse {
    // Making sense of the User's input, and deal with cases when it is invalid
    // These are the possible instructions that the user will input.
    // If the user's inputs are not in the below array, then it will be invalid.
    static final String[] INSTRUCTIONS = new String[] {"todo", "deadline", "event", "mark", "unmark",
            "bye", "list", "delete", "find"};

    // Verify whether the instruction has necessary information
    public static void validateEmpty (String line) throws DukeException {
        String[] lineList = line.split(" ");
        // Note that only command "list" and "bye" do not require any further description of the task
        if (lineList.length <= 1 && !lineList[0].equals("list") && !lineList[0].equals("bye")) {
            throw new DukeException();
        }
    }

    // Verify whether the instruction is meaningful.
    public static void validateMeaningful (String instruction) throws DukeException {
        if (!Arrays.asList(INSTRUCTIONS).contains(instruction)) {
            throw new DukeException();
        }
    }
}