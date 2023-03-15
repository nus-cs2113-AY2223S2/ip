
public class Parser {

    String command;
    String taskDescription;
    String deadline;
    String startDate;
    String endDate;
    String taskNumber;
    boolean shouldQuit = false;


    public Parser (String userInput) {
        String[] tokens = userInput.split(" ");
        command = tokens[0];

        switch(command) {
            case "list":

        }
    }
}
