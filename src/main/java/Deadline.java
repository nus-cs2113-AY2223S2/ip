import java.time.LocalDateTime;
import java.util.Scanner;

public class Deadline extends Task {
    public String description;
    public LocalDateTime date = null;
    Scanner in = new Scanner(System.in);
    String input;

    // This is the constructor for Deadline class
    // It takes in the 2 user input, description of deadline & datetime of deadline.
    // then stores inside an array and parses it into the respective variables
    // then adds the variables into the respective arraylists, Tasks, Marked, Items,
    // DateTimeFrom, DateTimeTo
    // with null values for dateTimeFrom because deadline has no start date/time
    // associated with it
    // before printing a message to show the most recent task
    public Deadline() throws InvalidInputException {
        String[] strArray = new String[2];
        Ui.printEnterDescription();
        strArray[0] = in.nextLine();
        this.description = strArray[0].trim();
        while (this.date == null) {
            Ui.printDeadlineDue();
            strArray[1] = in.nextLine();
            try {
                this.date = LocalDateTime.parse(strArray[1].trim());
            } catch (Exception e) {
                Ui.printInvalidDate();
            }
        }
        setAddDescArray(description);
        setAddMarkArray(false);
        setAddTypeArray(TaskType.DEADLINE);
        setAddDateFromArray(null);
        setAddDateToArray(date);
        print();
    }

}
