package helpers;

import corefunctionalities.TaskList;

/**
 * The Parser Class is responsible for parsing and processing data.
 *
 * @author Muthya Narayanachary Akhil
 */
public class Parser {
    /**
     * An empty constructor to initialize the Parser Class
     */
    public Parser () {}

    /**
     * Method splits the userinput into a {@link String}[] ,individual phrases separated by " ".
     *
     * @param userInput The userinput which needs to be split
     * @return {@link String} array containing space seperated words
     */
    public String[] getTodo(String userInput) {
        return userInput.split(" ");
    }

    /**
     * Method processes the data such that the user input is split based on the delimiter "/by"
     *
     * @param userInput The user input which needs to be split
     * @return {@link String} array separated based on the "/by" regex
     */
    public String[] getDeadline(String userInput) {
        String intermediateStage = userInput.replace("deadline ", "");
        String[] deadlineAndDescription = intermediateStage.split("/by ");
        return deadlineAndDescription;
    }

    /**
     * Method processes the data such that the user input is split based on the delimiter "/from" or "/to"
     *
     * @param userInput The input which needs to be processed
     * @return A {@link String} Array consisting of phrases separated by either "/from" or "/to".
     */

    public String[] getEvent(String userInput) {
        String intermediateStage = userInput.replace("event ", "");
        String[] eventDescription = intermediateStage.split("/from | /to ");
        return eventDescription;
    }

    /**
     * Compares the user input with a word and returns whether they are the same or not
     *
     * @param userInput The user input which needs to be compared
     * @param toCompare The phrase the user input needs to be compared to
     * @return Whether it is true or false
     */
    public boolean isTheSame(String userInput, String toCompare) {
        return userInput.split(" ")[0].equals(toCompare);
    }

    /**
     * Checks whether the user input is in the valid range or not.
     *
     * @param userInput The user input that needs to be checked for
     * @param taskList The {@link java.util.ArrayList<dataypes.Task>} containing all the <codes>Tasks</codes>
     * @return A boolean to indicate whether it is true or false
     * @throws NumberFormatException In the case the user input is not numerical.
     */
    public boolean isInRange(String userInput, TaskList taskList) throws NumberFormatException{
        boolean isReturn = false;
        isReturn = Integer.parseInt(userInput.split(" ")[1])>0 && Integer.parseInt(userInput.split(" ")[1])<taskList.getSize()+1;
        return (isReturn);
    }

    /**
     * Cleans the user input by removing "find "
     *
     * @param userInput The {@link String} that needs to be cleaned
     * @return A {@link String} containing the phrase alone, without "find "
     */
    public String withoutFind(String userInput) {
        return userInput.replace("find ", "");
    }

}
