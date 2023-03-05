package duke.entity;

public class Parser {
    private String input;

    public void setInput(String input) {
        this.input = input;
    }

    public int parseMarkIndex() {
        String stringListNumber = input.substring(5);
        return Integer.parseInt(stringListNumber) - 1;
    }

    public int parseDeleteIndex() {
        String stringListNumber = input.substring(7);
        return Integer.parseInt(stringListNumber) - 1;
    }

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
