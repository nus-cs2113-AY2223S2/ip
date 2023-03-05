package duke.entity;

/**
 * Parses the user's inputs by making sense of the user command
 */
public class Parser {
    private String input;

    public void setInput(String input) {
        this.input = input;
    }

    /**
     * Parse the input to find the index of task to be marked
     *
     * @return index to be marked
     */
    public int parseMarkIndex() {
        String stringListNumber = input.substring(5);
        return Integer.parseInt(stringListNumber) - 1;
    }

    /**
     * Parse the input to find the index of task to be deleted
     *
     * @return index to be deleted
     */
    public int parseDeleteIndex() {
        String stringListNumber = input.substring(7);
        return Integer.parseInt(stringListNumber) - 1;
    }

    /**
     * Parse input to get deadline date
     *
     * @return string of deadline date
     */
    public String parseDeadlineBy() {
        return input.substring(input.lastIndexOf("/") + 1);
    }

    public String parseEventFrom() {
        String tempInput = input.substring(input.indexOf("/") + 1);
        return tempInput.substring(0, tempInput.indexOf("/"));
    }

    public String parseEventTo() {
        String tempInput = input.substring(input.indexOf("/") + 1);
        return tempInput.substring(tempInput.lastIndexOf("/") + 1);
    }

    public boolean validateEventInput() {
        return input.matches(".*/.*/.*");
    }
}