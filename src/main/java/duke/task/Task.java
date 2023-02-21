package duke.task;
import duke.exception.IllegalCommandException;
import java.time.LocalDate;
import java.time.LocalTime;

public class Task {
    private String description;
    private boolean isDone;

    /*
     * Gets command used to initialise the task
     *
     * @return Returns the initialisation command
     */
    public String getInitCommand() {
        return initCommand;
    }

    /*
     * Sets command used to initialise the task
     *
     * @param setInitCommand Command used to initialise the task
     */
    public void setInitCommand(String initCommand) {
        this.initCommand = initCommand;
    }
    /*
     * Gets string to pass to save file
     *
     * @return String for save file
     */
    public String getSaveString(){
        return initCommand + " " + ( isDone() ? "1" : "0") + System.lineSeparator();
    }

    private String initCommand;
    /*
     * Sets is done to false and description to empty
     */
    public Task(){
        this.isDone = false;
        this.description = "";
    }
    /*
     * Sets is done to false and description to provided description
     *
     * @param description Description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /*
     * Gets "X" if the task is done or an empty character if the task is not done
     *
     * @return "X" if task is done, " " if task is not done
     */
    public String getStatusIcon() {
        return (isDone() ? "X" : " ");
    }

    /*
     * Gets description of the task
     *
     * @return description of task
     */
    public String getDescription() {
        return description;
    }

    /*
     * Sets description of the task
     *
     * @param description Description of the task
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /*
     * Returns true if task is done, false if task is not done
     */
    public boolean isDone() {
        return isDone;
    }

    /*
     * Sets the done or not done status of the task
     *
     * @param isDone The done status of the task
     * @throws IllegalCommandException if task is already done and trying to be set to done, and vice versa.
     */
    public void setDone(boolean isDone) throws IllegalCommandException {
        if(this.isDone == isDone){
            throw new IllegalCommandException("No change in done state");
        }
        this.isDone = isDone;
    }
    /*
     * Checks if a substring of user input is a valid time string
     *
     * @userInput User time input
     * @return true if input is a valid time string, false otherwise
     */
    private static boolean isTime(String userInput){
        try{
            LocalTime time = LocalTime.parse(userInput);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    /*
     * Checks if a substring of user input is a valid date string
     *
     * @userInput User date input
     * @return true if input is a valid date string, false otherwise
     */
    private static boolean isDate(String userInput){
        try{
            LocalDate date = LocalDate.parse(userInput);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    /*
     * Parses the date string from the user
     *
     * @userInput User date input
     * @return Formatted date if the input is valid, the original input otherwise
     */
    private static String parseDate(String userInput){
        try{
            LocalDate date = LocalDate.parse(userInput);
            return date.getDayOfWeek() + ", " + date.getMonth() + " " + date.getDayOfMonth() + ", " + date.getYear();
        }catch(Exception e){
            return userInput;
        }
    }
    /*
     * Changes 24 hour time integer to 12 hour time integer
     *
     * @param hour 24 hour time integer
     * @return 12 hour time integer
     */
    private static int adjustHour(int hour){
        if(hour%12==0){
            return 12;
        }
        return hour%12;
    }
    /*
     * Parses the time string from the user
     *
     * @userInput User time input
     * @return Formatted time if the input is valid, the original input otherwise
     */
    private static String parseTime(String userInput){
        try{
            LocalTime time = LocalTime.parse(userInput);
            LocalTime noon = LocalTime.parse("12:00");
            return adjustHour(time.getHour()) + ":" + (time.getMinute()<10 ? "0" : "") + time.getMinute() + " " + (time.isBefore(noon)?"AM":"PM");
        }catch(Exception e){
            return userInput;
        }
    }
    /*
     * Parses the user date time input and returns a reformatted version if possible
     *
     * @userInput User date time input
     * @return Reformatted string wherever possible, original user input if string is in an invalid format
     */
    public static String parseDateTimeString(String userInput){
        userInput = userInput.trim();
        switch(userInput.split(" ").length){
        case 1:
            if(isDate(userInput)){
                return parseDate(userInput);
            }else if(isTime(userInput)){
                return parseTime(userInput);
            }else{
                return userInput;
            }
        case 2:
            String[] userInputs = userInput.split(" ");
            if(isTime(userInputs[0]) && isDate(userInputs[1])){
                return parseTime(userInputs[0])+ ", " + parseDate(userInputs[1]);
            }else if(isTime(userInputs[1]) && isDate(userInputs[0])){
                return parseTime(userInputs[1])+ ", " + parseDate(userInputs[0]);
            }else{
                return userInput;
            }
        default:
            return userInput;
        }
    }
    @Override
    /*
     * Retrieves description of task
     *
     * @return String of description
     */
    public String toString(){
        return description;
    }
}
