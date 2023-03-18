import java.util.Scanner;

public class Todo extends Task {
    String description;
    Scanner in = new Scanner(System.in);
    String input;

    // This is the constructor for Todo class
    // It takes in the 1 user input, description of todo.
    // then stores inside an array and parses it into the respective variables
    // then adds the variables into the respective arraylists, Tasks, Marked, Items,
    // DateTimeFrom, DateTimeTo
    // with null values for the date and time because todo has no date/time
    // associated with it
    // before printing a message to show the most recent task
    public Todo() throws InvalidInputException {
        Ui.printEnterDescription();
        this.description = in.nextLine();
        setAddDescArray(description);
        setAddMarkArray(false);
        setAddTypeArray(TaskType.TODO);
        setAddDateFromArray(null);
        setAddDateToArray(null);
        print();
    }

}
