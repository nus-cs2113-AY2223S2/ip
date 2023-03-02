import java.time.LocalDateTime;
import java.util.Scanner;

public class Event extends Task{
    public String description;
    public LocalDateTime dateFrom;
    public LocalDateTime dateTo;
    Scanner in = new Scanner(System.in);
    String input;

    public Event() throws InvalidInputException {
        String[] strArray = new String[3];
        //prompt user for input
        System.out.println("enter description: ");
        strArray[0] = in.nextLine();
        System.out.println("enter from datetime: " + ("eg: '2012-05-02T06:30' is equivalent  May 02 2023 6.30am"));
        strArray[1] = in.nextLine();
        System.out.println("enter to datetime: " + ("eg: '2012-05-02T06:30' is equivalent  May 02 2023 6.30am"));
        strArray[2] = in.nextLine();

        this.description = strArray[0].trim();
        try {

            this.dateFrom = LocalDateTime.parse(strArray[1].trim());
            this.dateTo = LocalDateTime.parse(strArray[2].trim());
        } catch (Exception e) {
            System.out.println("Invalid date format");
            return;
        }
        items.add(description);
        marked.add(false);
        tasks.add(TaskType.EVENT);
        dateTimeFrom.add(dateFrom);
        dateTimeTo.add(dateTo);
        print(); // from Task class
    }
}
