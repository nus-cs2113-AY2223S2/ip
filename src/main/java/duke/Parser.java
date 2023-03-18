package duke;

/**
 * Takes in userInput and breaks it down
 * Splits it into two parts, firstWord and remainingWord
 */
public class Parser {

    protected String userInput;
    protected String firstWord;
    protected String remainingWords;

    public Parser(String userInput) {
        this.userInput = userInput;
    }

    /**
     * This method is used to call the first word from the input
     * @param  userInput String obtained from user
     * @return firstWord
     */
    public String getFirstWord(String userInput) {
        if (userInput.contains(" ")) {
            return userInput.substring(0,userInput.indexOf(" "));
        } else {
            return userInput;
        }
    }

    /**
     * This method is used to call all the words after the first from input
     * @param userInput String obtained from user
     * @return remainingWords
     */
    public String getRemainingWords(String userInput) {
        if (userInput.contains(" ")) {
            return userInput.substring(userInput.indexOf(" ") + 1);
        } else {
            return null;
        }
    }
}
