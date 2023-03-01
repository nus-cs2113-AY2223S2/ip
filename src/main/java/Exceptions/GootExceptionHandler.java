package Exceptions;

/**
 * This class defines a few exception throwers that throw GootExceptions(custom exceptions) as well as functions that
 * print error messages
 */
public class GootExceptionHandler {
    public void unidentifiedKeyword() {
        System.out.println("I only understand stuff like \'todo have fun\' where todo is the keyword and have fun is " +
                "the additional information");
    }

    public void wrongNumberOfSlashesDeadline() {
        System.out.println("Deadlines can only contain ONE \'/\' in the form of \'/by\'");
    }

    public void wrongNumberOfSlashesEvent() {
        System.out.println("Events can only contain TWO \'/\' in the form of \'/from\' and  \'/to\'");
    }

    public static void fileNotFound() {
        System.out.println("I can't find the file :((");
    }

    public void indexOutOfBound() {
        System.out.println("there is no task with this ID! :O");
    }

    public void noInputError() {
        System.out.println("no information found after keyword!");
    }

    /**
     * this function determines if the format of the deadline is correct
     *
     * @param inputArray a string array split by "/"
     * @throws GootExceptions
     */
    public void validateDeadline(String[] inputArray) throws GootExceptions {
        if (inputArray.length != 2 || !(inputArray[1].contains("by"))) {
            throw new GootExceptions();
        }
    }

    public void validateIndex(int inputIndex, int maxIndex) throws GootExceptions {
        if (inputIndex > maxIndex) {
            throw new GootExceptions();
        }
    }

    /**
     * this function determines if the format of the event is correct
     *
     * @param inputArray a string array split by "/"
     * @throws GootExceptions
     */
    public void validateEvent(String[] inputArray) throws GootExceptions {
        if (inputArray.length != 3 || !(inputArray[1].contains("from")) || !(inputArray[2].contains("to"))) {
            throw new GootExceptions();
        }
    }

    public void whitespaceExceptionThrower(String input) throws GootExceptions {
        String[] keywordList = {"todo", "deadline", "event", "delete", "mark", "find"};
        for (String command : keywordList) {
            if (input.contains(command) && (input.trim()).equals(command)) {
                throw new GootExceptions();
            }
        }
    }

    /**
     * @param input string starting with the keyword
     * @return boolean true if there is no information provided after keyword
     */
    public boolean incompleteInformation(String input) {
        try {
            whitespaceExceptionThrower(input);
            return false;
        } catch (GootExceptions error) {
            noInputError();
            return true;
        }
    }
}
