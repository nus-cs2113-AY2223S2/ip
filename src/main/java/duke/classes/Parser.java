package duke.classes;

import java.util.Objects;

public class Parser {
    protected String input;

    public Parser(String input) {
        this.input = input;
    }

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
        } else if (input.length() > 5 && (input.substring(0,5)).equals("find ")) {
            return "find";
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

    public Integer getOrderMark(String input) {
        return Integer.valueOf(input.substring(5, input.length()));
    }

    public Integer getOrderUnmark(String input) {
        return Integer.valueOf(input.substring(7, input.length()));
    }

    public Integer getOrderDelete(String input) {
        return Integer.valueOf(input.substring(7, input.length()));
    }

    public String getTodoInfo(String input) {
            return input.substring(5,input.length());
    }

    public String getDeadlineInfo(String input) {
        return input.substring(9,input.indexOf("/"));
    }

    public String getDeadlineTimeBy(String input) {
        return input.substring(input.indexOf("/")+1, input.length());
    }

    public String getEventInfo(String input) {
        return input.substring(6,input.indexOf("/"));
    }

    public String getEventTimeFrom(String input) {
        return input.substring(input.indexOf("/")+1, input.lastIndexOf("/") - 1);
    }

    public String getEventTimeBy(String input) {
        return input.substring(input.lastIndexOf("/")+1, input.length());
    }

    public String getFindKeyWord(String input) {
        return input.substring(5, input.length());
    }
}
