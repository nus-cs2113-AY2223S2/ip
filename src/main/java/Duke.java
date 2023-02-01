import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        DukePrinter.printGreeting();
        Scanner in = new Scanner(System.in);
        while(true){
            String line = in.nextLine();
            line = line.trim();
            String[] command = line.split(" ");
            String lineRemainder = line.substring(line.indexOf(" ")+1);
            lineRemainder = lineRemainder.trim();
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
                    id = Integer.parseInt(lineRemainder);
                } catch (NumberFormatException integerException) {
                    System.err.println("Sorry, the id is invalid!");
                    DukePrinter.printLine();
                    break;
                }
                DukeList.markDone(id-1);
                break;
            case "unmark":
                try {
                    id = Integer.parseInt(lineRemainder);
                } catch (NumberFormatException integerException) {
                    System.err.println("Sorry, the id is invalid!");
                    DukePrinter.printLine();
                    break;
                }
                DukeList.unmarkDone(id-1);
                break;
            case "todo":
                if(lineRemainder.equals("")) {
                    System.err.println("Sorry, the description of a todo cannot be empty.");
                    DukePrinter.printLine();
                    break;
                }
                DukeList.addTask(new DukeTask(lineRemainder));
                break;
            case "deadline":
                try {
                    String deadlineName = lineRemainder.substring(0, lineRemainder.indexOf("/by"));
                    deadlineName = deadlineName.trim();
                    String deadlineTime = lineRemainder.substring(lineRemainder.indexOf("/by")+4);
                    deadlineTime = deadlineTime.trim();
                    DukeList.addTask(new DukeDeadline(deadlineName, deadlineTime));
                } catch (StringIndexOutOfBoundsException e) {
                    System.err.println("Sorry, the deadline time/name is invalid!");
                    DukePrinter.printLine();
                }
                break;
            case "event":
                try {
                    String eventName = lineRemainder.substring(0, lineRemainder.indexOf("/from")-1);
                    eventName = eventName.trim();
                    String eventTimeFrom = lineRemainder.substring(lineRemainder.indexOf("/from")+6, lineRemainder.indexOf("/to")-1);
                    eventTimeFrom = eventTimeFrom.trim();
                    String eventTimeTo = lineRemainder.substring(lineRemainder.indexOf("/to")+4);
                    eventTimeTo = eventTimeTo.trim();
                    DukeList.addTask(new DukeEvent(eventName, eventTimeFrom, eventTimeTo));
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
