package Parser;

public class Parser {

    public static String taskName(String userInput, int startIndex){
        return userInput.substring(startIndex);
    }

    public static String taskName(String userInput, int startIndex, int endIndex){
        return userInput.substring(startIndex, endIndex);
    }

    public static int taskIndex(String userInput, int index) {
        return Integer.parseInt(userInput.substring(index));
    }

    public static int indexOfSubstring(String userInput, String subString){
        return userInput.indexOf(subString);
    }
}
