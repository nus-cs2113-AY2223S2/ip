
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Parser {

    private static final String FILE_PATH = "data/duke.txt";

    // basic parse function to split the input by spaces
    private String[] basicParse(String input) {
        String[] strArray = input.split(" ");
        return strArray;
    }

    // main parser function
    // takes in input from user
    // then base on the command give by the user, it will call the respective
    // functions
    public Parser() {

        Scanner in = new Scanner(System.in);
        String input = "";

        Task task = new Task();
        try {
            Storage.readFile(FILE_PATH);
        } catch (Exception e) {
            System.out.println("error reading file" + e.getMessage());
        }

        while (input != "bye") { // while input is not empty
            input = in.nextLine();
            String[] strArray = basicParse(input);
            try {
                switch (strArray[0].trim()) {
                    case "bye":
                        Ui.printBye();
                        in.close();
                        return;
                    case "list":
                        task.getItems();
                        break;
                    case "due":
                        LocalDateTime dateTime = LocalDateTime.parse(strArray[1].trim());
                        task.getDue(dateTime);
                        break;
                    case "find":
                        task.find(strArray[1].trim());
                        break;
                    case "mark":
                        int num = Integer.parseInt(strArray[1].trim());
                        task.setDone(num);
                        break;
                    case "unmark":
                        num = Integer.parseInt(strArray[1].trim());
                        task.setNotDone(num);
                        break;
                    case "delete":
                        num = Integer.parseInt(strArray[1].trim());
                        task.delete(num);
                        break;
                    case "todo":
                        new Todo();
                        break;
                    case "deadline":
                        new Deadline();
                        break;
                    case "event":
                        new Event();
                        break;
                    case "help":
                        Ui.printHelp();
                        break;
                    case "save":
                        Storage.save();
                        break;
                    default:
                        throw new InvalidInputException();
                }
            } catch (Exception e) {
                System.out.println("Invalid command. type 'help' for a list of commands");
                Ui.printseparator();
                continue;
            }
        }
    }

    public static String formatDateOut(int index) { // helper function to format date output string for printing
        String dateFrom = Task.getDateTimeFrom(index) == null ? ""
                : Task.getDateTimeFrom(index).format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        String dateTo = Task.getDateTimeTo(index) == null ? ""
                : Task.getDateTimeTo(index).format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));

        String dateOut = ""; // output of string decided by the presence and absence of dateFrom and dateTo
        if (dateFrom.equals("") && dateTo.equals("")) {
            dateOut = "";
        } else if (dateFrom.equals("") && !dateTo.equals("")) {
            dateOut = " (by: " + dateTo + ")";
        } else if (!dateFrom.equals("") && !dateTo.equals("")) {
            dateOut = " (from: " + dateFrom + " to: " + dateTo + ")";
        }

        return dateOut;
    }
}
