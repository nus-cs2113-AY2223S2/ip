package logic;

public class Parser {

    /**
     * splits the string into an array
     * consisting of [command, description].
     *
     * @param string input entered by user
     * @return string array consisting of the split inputs
     */
    public static String[] handleInput(String string) {
        return string.split(" ", 2);
    }

    /**
     * breaks down description into
     * [task description, dueDate].
     *
     * @param description description entered by user
     * @return string array consisting of the split inputs
     */
    public static String[] handleDeadline(String description) {
        String[] splitStrings = description.split("/", 2);
        splitStrings[1] = splitStrings[1].substring(3);

        return splitStrings;
    }

    /**
     * breaks down the description into
     * [task description, startDate, endDate].
     *
     * @param description description entered by user
     * @return string array consisting of the split inputs
     */
    public static String[] handleEvent(String description) {
        String[] splitStrings = description.split("/", 3);
        splitStrings[1] = splitStrings[1].substring(5);
        splitStrings[2] = splitStrings[2].substring(3);

        return splitStrings;
    }
}
