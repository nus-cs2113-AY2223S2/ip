package duke.classes;

import java.util.Objects;

/**
 * A class that parses input commands for a task manager.
 */
public class Parser {
    /** the input command to be parsed */
    protected String input;

    /**
     * Constructs a new Parser object with the specified input command.
     * @param input the input command to be parsed
     */
    public Parser(String input) {
        this.input = input;
    }

    /**
     * Returns the type of the input command.
     *
     * @return the type of the input command
     */
    public String getInputType() {
        if(Objects.equals(input, "bye")) {
            return "bye";
        } else if (Objects.equals(input, "list")) {
            return "list";
        } else if (input.length() > 5 && (input.substring(0,5)).equals("mark ") && input.substring(5, input.length()).matches("[0-9]+")) {
            return "mark";
        } else if (input.length() > 7 && (input.substring(0,7)).equals("unmark ") && input.substring(7, input.length()).matches("[0-9]+")) {
            return "unmark";
        } else if (input.length() > 7 && input.substring(0,7).equals("delete ") && input.substring(7, input.length()).matches("[0-9]+")) {
            return "delete";
        } else {
            if (input.length() > 4 && input.substring(0, 5).equals("todo ")) {
                return "todo";
            } else if (input.length() > 8 && input.substring(0, 9).equals("deadline ")) {
                return "deadline";
            } else if (input.length() > 5 && input.substring(0, 6).equals("event ")) {
                return "event";
            } else {
                return null;
            }
        }
    }

    /**
     * Returns the order number for the "mark" command.
     *
     * @param input the input command for the "mark" command
     * @return the order number for the "mark" command
     */
    public Integer getOrderMark(String input) {
        return Integer.valueOf(input.substring(5, input.length()));
    }

    /**
     * Returns the order number for the "unmark" command.
     *
     * @param input the input command for the "unmark" command
     * @return the order number for the "unmark" command
     */
    public Integer getOrderUnmark(String input) {
        return Integer.valueOf(input.substring(7, input.length()));
    }

    /**
     * Returns the order number for the "delete" command.
     *
     * @param input the input command for the "delete" command
     * @return the order number for the "delete" command
     */
    public Integer getOrderDelete(String input) {
        return Integer.valueOf(input.substring(7, input.length()));
    }

    /**
     * Returns the information for a todo task.
     *
     * @param input the input command for a todo task
     * @return the information for the todo task
     */
    public String getTodoInfo(String input) {
            return input.substring(5,input.length());
    }

    /**
     * Returns the information for a deadline task.
     *
     * @param input the input command for a deadline task
     * @return the information for the deadline task
     */
    public String getDeadlineInfo(String input) {
        return input.substring(9,input.indexOf("/"));
    }

    /**
     * Returns the deadline time for a deadline task.
     *
     * @param input the input command for a deadline task
     * @return the deadline time for the deadline task
     */
    public String getDeadlineTimeBy(String input) {
        return input.substring(input.indexOf("/")+1, input.length());
    }

    /**
     * Returns the information for an event task.
     *
     * @param input the input command for an event task
     * @return the information for the event task
     */
    public String getEventInfo(String input) {
        return input.substring(6,input.indexOf("/"));
    }

    /**
     * Returns the start time for an event task.
     *
     * @param input the input command
     * @return the start time for an event task
     */
    public String getEventTimeFrom(String input) {
        return input.substring(input.indexOf("/")+1, input.lastIndexOf("/") - 1);
    }

    /**
     * Returns the end time for an event task.
     *
     * @param input the input command
     * @return the end time for an event task
     */
    public String getEventTimeBy(String input) {
        return input.substring(input.lastIndexOf("/")+1, input.length());
    }
}
