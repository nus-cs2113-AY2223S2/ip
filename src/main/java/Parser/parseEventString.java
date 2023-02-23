package Parser;

public class parseEventString {

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
