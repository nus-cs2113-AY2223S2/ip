import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        DukePrinter.printGreeting();
        Scanner in = new Scanner(System.in);
        while(true){
            String line = in.nextLine();
            line = line.trim();
            String[] command = line.split(" ");
            String LineRemainder = line.substring(line.indexOf(" ")+1);
            LineRemainder = LineRemainder.trim();
            int id;
            switch (command[0]){
            case "bye":
                DukePrinter.printString("Bye. Hope to see you again soon!");
                DukePrinter.printGoodbyeLogo();
                return;
            case "list":
                DukeList.listTask();
                break;
            case "mark":
                try {
                    id = Integer.parseInt(LineRemainder);
                } catch (NumberFormatException integerException) {
                    System.err.println("Sorry, the id is invalid!");
                    DukePrinter.printLine();
                    break;
                }
                DukeList.markDone(id-1);
                break;
            case "unmark":
                try {
                    id = Integer.parseInt(LineRemainder);
                } catch (NumberFormatException integerException) {
                    System.err.println("Sorry, the id is invalid!");
                    DukePrinter.printLine();
                    break;
                }
                DukeList.unmarkDone(id-1);
                break;
            case "todo":
                if(LineRemainder.equals("")) {
                    System.err.println("Sorry, the description of a todo cannot be empty.");
                    DukePrinter.printLine();
                    break;
                }
                DukeList.addTask(new DukeTask(LineRemainder));
                break;
            case "deadline":
                try {
                    String DeadlineName = LineRemainder.substring(0, LineRemainder.indexOf("/by"));
                    DeadlineName = DeadlineName.trim();
                    String DeadlineTime = LineRemainder.substring(LineRemainder.indexOf("/by")+4);
                    DeadlineTime = DeadlineTime.trim();
                    DukeList.addTask(new DukeDeadline(DeadlineName, DeadlineTime));
                } catch (StringIndexOutOfBoundsException e) {
                    System.err.println("Sorry, the deadline time/name is invalid!");
                    DukePrinter.printLine();
                }
                break;
            case "event":
                try {
                    String EventName = LineRemainder.substring(0, LineRemainder.indexOf("/from")-1);
                    EventName = EventName.trim();
                    String EventTimeFrom = LineRemainder.substring(LineRemainder.indexOf("/from")+6, LineRemainder.indexOf("/to")-1);
                    EventTimeFrom = EventTimeFrom.trim();
                    String EventTimeTo = LineRemainder.substring(LineRemainder.indexOf("/to")+4);
                    EventTimeTo = EventTimeTo.trim();
                    DukeList.addTask(new DukeEvent(EventName, EventTimeFrom, EventTimeTo));
                } catch (StringIndexOutOfBoundsException e) {
                    System.err.println("Sorry, the event time/name is invalid!");
                    DukePrinter.printLine();
                }
                break;
            default:
                System.err.println("Sorry, I don't understand \"" + command[0] + "\"!");
                DukePrinter.printLine();
                break;
            }
        }
    }
}
