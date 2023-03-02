import java.time.LocalDateTime;
import java.util.Scanner;


public class Deadline extends Task {
    public String description;
    public  LocalDateTime date;
    Scanner in = new Scanner(System.in);
    String input;


    public Deadline() throws InvalidInputException{
        String[] strArray = new String[2];
        //prompt user for input
        System.out.println("enter description: ");
        strArray[0] = in.nextLine();
        System.out.println("enter deadline: " + ("eg: '2012-05-02T06:30' is equivalent  May 02 2023 6.30am"));
        strArray[1] = in.nextLine();
        
        this.description = strArray[0].trim();
        try {
            this.date = LocalDateTime.parse(strArray[1].trim());
        } catch (Exception e) {
            System.out.println("Invalid date format");
            return;
        }
        items.add(description);
        marked.add(false);
        tasks.add(TaskType.DEADLINE);
        dateTimeFrom.add(null);
        dateTimeTo.add(date);
        print(); //from Task class
    }

}
