import java.time.LocalDateTime;
import java.util.Scanner;


public class Deadline extends Task {
    public String description;
    public  LocalDateTime date;
    Scanner in = new Scanner(System.in);
    String input;


    public Deadline(String input) throws InvalidInputException{
        String[] strArray = new String[2];
        System.out.println("enter description: " +"\n");
        strArray[0] = in.nextLine();
        System.out.println("enter deadline: " +("eg: 2012-05-02T06:30 = May 02 2023 6.30am") +"\n");
        strArray[1] = in.nextLine();
        if (strArray.length != 2 || !strArray[1].startsWith("by")) {
            throw new InvalidInputException();
        }
        this.description = strArray[0].substring(8).trim();
        try {
            this.date = LocalDateTime.parse(strArray[1].trim());
        } catch (Exception e) {
            System.out.println("Invalid date format");
        }
        items.add(description);
        marked.add(false);
        tasks.add(TaskType.DEADLINE);
        dateTimeFrom.add(null);
        dateTimeTo.add(date);
        System.out.println(dateTimeFrom.size());
        print();
    }

}
