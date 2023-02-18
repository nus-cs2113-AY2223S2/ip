package luke.task;

public interface StringManipulation {
    /**
     * Extracts out the first word of an input string.
     *
     * @param userInput The input string.
     * @return The first word of the string.
     */
    static String getFirstWord(String userInput) {
        String[] processedInputs = userInput.split(" ", 2);
        return processedInputs[0];
    }

    /**
     * Removes the first word of an input string and returns the remaining string.
     * Returns null if the string only consist of one word.
     *
     * @param userInput The input string.
     * @return The input string with the first word removed.
     */
    static String removeFirstWord(String userInput) {
        String[] processedInputs = userInput.split(" ", 2);
        if (processedInputs.length == 1) {
            return null;
        }
        return processedInputs[1];
    }
}
