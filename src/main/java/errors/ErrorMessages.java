package errors;



public class ErrorMessages {

    //The following are meant for Tasks
    public static String provideTaskEmptyDescriptionText() {
        return "Description cannot be empty :<, please write a description after the task keyword (i.e. todo/deadline/event)";
    }

    public static String provideTaskNoDeadlineParamsText() {
        return "You did not include the /by parameter, please split include it for Pikapi!";
    }

    public String provideTaskEmptyDescriptionForDeadlineText() {
        return "Description cannot be empty :<, please write a description after the word deadline";
    }

    public String provideTaskEmptyDueDateDeadlineText() {
        return "DueDate cannot be empty :<, please write a the description of a deadline";
    }

    public String provideTaskEmptyStartDateEventText() {
        return "Start Date cannot be empty :<";
    }

    public String provideTaskEmptyEndDateEventText() {
        return "End Date cannot be empty :<";
    }

    public String provideTaskWrongEventFormatText() {
        return "You did not key in a correct format for events :< ";
    }

    public String provideTaskWrongEventParamsText() {
        return "Pikapi's events should include /to and /from fields!";
    }

    public String provideWrongTaskNameText() {
        return "Pikapi does not understand this task, please give Pikapi a todo, deadline or event task";
    }

    //The following are meant for Marking tasks
    public String provideEmptyListText() {
        return "PIKAPII the list is currently empty! You can't mark/unmark an empty list! Please input some tasks for Pikapi to add :3";
    }

    public String provideExceedListLengthText(int listLength) {
        if (listLength == 1) {
            return "PIKAPII you dont have that many tasks! You only have 1 task hehe :3";
        }
        return "PIKAPII you dont have that many tasks! Please write a number between 1 and " + listLength;
    }

    public String provideInvalidNumberText() {
        return "PIKAPII we dont have negative or tasks indexed as 0 >:<";
    }

    public String provideNoNumberText() {
        return "PIKAPII you did not input a number beside your marking action!";
    }

    public String provideStringAsNumber() {
        return "Pikapiii you did not give me an index(number)! Please give me a number after the mark/unmark/delete keyword please :3 UwU";
    }

    //The following are meant for Load Data errors
    public static String provideCorruptDataErrorText() {
        return "PIKAPII There seems to be something wrong with this task in your save file, not loading a specific task into the task list";
    }

    public static String provideDeleteContentErrorText() {
        return "For some reason I cant delete the content of the folder";
    }

    public static String provideLoadInvalidTaskErrorText() {
        return "For some reason the current file is not of a known subclass of Task, please check implementation";
    }

    public static String provideUnableToWriteToFileText() {
        return "Unable to append to file";
    }

    public static String provideUnableToFindListData() {
        return "For some reason, listData is still unable to be found";
    }

    //The following are meant for wrong command errors
    public String provideWrongCommandText() {
        return "Pikapi is unable to find that command, please type in a correct command";
    }

    //The following are meant for find task errors
    public String provideNoTaskKeywordInput(){
        return "Pikapi is unable to find a keyword to search the list for you, please add a word after the word \"find\"";
    }
}
