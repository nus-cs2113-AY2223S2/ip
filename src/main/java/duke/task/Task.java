package duke.task;
import duke.exception.IllegalCommandException;
import java.time.LocalDate;
import java.time.LocalTime;

public class Task {
    private String description;
    private boolean isDone;

    public String getInitCommand() {
        return initCommand;
    }

    public void setInitCommand(String initCommand) {
        this.initCommand = initCommand;
    }
    public String getSaveString(){
        return initCommand + " " + ( isDone() ? "1" : "0") + System.lineSeparator();
    }

    private String initCommand;
    public Task(){
        this.isDone = false;
        this.description = "";
    }
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone() ? "X" : " ");
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean isDone) throws IllegalCommandException {
        if(this.isDone == isDone){
            throw new IllegalCommandException("No change in done state");
        }
        this.isDone = isDone;
    }
    private static boolean isTime(String userInput){
        try{
            LocalTime time = LocalTime.parse(userInput);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    private static boolean isDate(String userInput){
        try{
            LocalDate date = LocalDate.parse(userInput);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    private static String parseDate(String userInput){
        try{
            LocalDate date = LocalDate.parse(userInput);
            return date.getDayOfWeek() + ", " + date.getMonth() + " " + date.getDayOfMonth() + ", " + date.getYear();
        }catch(Exception e){
            return userInput;
        }
    }
    private static int adjustHour(int hour){
        if(hour%12==0){
            return 12;
        }
        return hour%12;
    }
    private static String parseTime(String userInput){
        try{
            LocalTime time = LocalTime.parse(userInput);
            LocalTime noon = LocalTime.parse("12:00");
            return adjustHour(time.getHour()) + ":" + time.getMinute() + " " + (time.isBefore(noon)?"AM":"PM");
        }catch(Exception e){
            return userInput;
        }
    }
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
    public String toString(){
        return description;
    }
}
