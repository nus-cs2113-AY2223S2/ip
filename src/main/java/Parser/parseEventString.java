package Parser;

public class parseEventString {

    /**
     * a method that parses the Users command to create an event into a valid format to make it easier when creating a todo Class instance.
     * @param input - String from user that contains info to create a new event instance
     * @return - a newly formatted string that stores the event in a tidy manner
     */
    public static String parseString(String input) {
        int startIndex = input.indexOf("from") + 5;
        int endStartIndex = input.indexOf("to");
        int DeadlineIndex = input.indexOf("/to") + 4;
        String start = input.substring(startIndex, endStartIndex - 1).trim();
        String end = input.substring(DeadlineIndex).trim();
        input = input.substring(input.indexOf(" ") + 1, input.indexOf('/') - 1);
        input += ("  (" + "from: " + start + " to: " + end + ")");
        return input;
    }


}
