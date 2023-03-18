import java.time.LocalDateTime;
import java.util.Scanner;

public class Event extends Task {
    public String description;
    public LocalDateTime dateFrom = null;
    public LocalDateTime dateTo = null;
    Scanner in = new Scanner(System.in);
    String input;

    // This is the constructor for Event class
    // It takes in the 3 user input, description of event, start datetime of event &
    // end datetime of event.
    // then stores inside an array and parses it into the respective variables
    // then adds the variables into the respective arraylists, Tasks, Marked, Items,
    // DateTimeFrom, DateTimeTo
    // before printing a message to show the most recent task
    public Event() throws InvalidInputException {
        String[] strArray = new String[3];
        Ui.printEnterDescription();
        strArray[0] = in.nextLine();
        this.description = strArray[0].trim();
        while (dateFrom == null) {
            Ui.printEventFrom();
            strArray[1] = in.nextLine();
            try {
                this.dateFrom = LocalDateTime.parse(strArray[1].trim());
            } catch (Exception e) {
                Ui.printInvalidDate();
            }
        }
        while (dateTo == null) {
            Ui.printEventTo();
            strArray[2] = in.nextLine();
            try {
                this.dateTo = LocalDateTime.parse(strArray[2].trim());
            } catch (Exception e) {
                Ui.printInvalidDate();
            }
        }
        setAddDescArray(description);
        setAddMarkArray(false);
        setAddTypeArray(TaskType.EVENT);
        setAddDateFromArray(dateFrom);
        setAddDateToArray(dateTo);
        print();

    }

}
