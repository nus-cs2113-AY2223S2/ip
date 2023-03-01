package errors;

/**
 * This is an error class that stores all the texts meant for storing most
 * output text for printing
 */
public class ErrorMessages {

    /**
     * The following are meant for Tasks
     */
    public static String errorTaskEmptyDescriptionText() {
        return "Description cannot be empty :<,"
                + " please write a description after the task keyword (i.e. todo/deadline/event)";
    }

    public static String errorTaskNoDeadlineParamsText() {
        return "You did not include the /by parameter, please split include it for Pikapi!";
    }

    public String errorTaskEmptyDescriptionForDeadlineText() {
        return "Description cannot be empty :<, please write a description after the word deadline";
    }

    public String errorTaskEmptyDueDateDeadlineText() {
        return "DueDate cannot be empty :<, please write a the description of a deadline";
    }

    public String errorTaskEmptyStartDateEventText() {
        return "Start Date cannot be empty :<";
    }

    public String errorTaskEmptyEndDateEventText() {
        return "End Date cannot be empty :<";
    }

    public String errorTaskWrongEventFormatText() {
        return "You did not key in a correct format for events :< ";
    }

    public String errorTaskWrongEventParamsText() {
        return "Pikapi's events should include /to and /from fields!";
    }

    public String errorWrongTaskNameText() {
        return "Pikapi does not understand this task, please give Pikapi a todo, deadline or event task";
    }

    /**
     * @return Provides error text when trying to access an empty list
     */
    public String errorEmptyListText() {
        return "PIKAPII the list is currently empty! "
                + "You can't mark/unmark an empty list! "
                + "Please input some tasks for Pikapi to add :3";
    }

    /**
     * This is a message that prints a message when someone inputs an index out of
     * the bounds of the task list
     */
    public String errorExceedListLengthText(int listLength) {
        if (listLength == 1) {
            return "PIKAPII you dont have that many tasks! You only have 1 task hehe :3";
        }
        return "PIKAPII you dont have that many tasks! Please write a number between 1 and " + listLength;
    }

    public String errorInvalidNumberText() {
        return "PIKAPII we dont have negative or tasks indexed as 0 >:<";
    }

    public String errorNoNumberText() {
        return "PIKAPII you did not input a number beside your marking action!";
    }

    /**
     * @return Provides error text when a String is provided as input instead of a
     *         number
     */
    public String errorStringAsNumber() {
        return "Pikapiii you did not give me an index(number)! "
                + "Please give me a number after the mark/unmark/delete keyword please :3 UwU";
    }

    /**
     * @return Provides error text when a specific line of data in listData.txt is
     *         not of correct format
     */
    public static String errorCorruptDataErrorText() {
        return "PIKAPII There seems to be something wrong with this task in your save file,"
                + " not loading a specific task into the task list";
    }

    public static String errorDeleteContentErrorText() {
        return "For some reason I cant delete the content of the folder";
    }

    public static String errorLoadInvalidTaskErrorText() {
        return "For some reason the current file is not of a known subclass of Task, please check implementation";
    }

    public static String errorUnableToWriteToFileText() {
        return "Unable to append to file";
    }

    public static String errorUnableToFindListData() {
        return "For some reason, listData is still unable to be found";
    }

    // The following are meant for wrong command errors
    public String errorWrongCommandText() {
        return "Pikapi is unable to find that command, please type in a correct command";
    }

    public String errorCorruptDataText() {
        return "A specific line in data is corrupted, not adding corrupted data";
    }

    public String errorNoTaskKeywordInput() {
        return "No Task keyword provided to find, Pikapi tried :3";
    }
}
