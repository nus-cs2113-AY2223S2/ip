import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        DukePrinter.printGreeting();
        Scanner in = new Scanner(System.in);
        while (true) {
            //todo not follow SLAP here, fixed it later.
            String line = in.nextLine();
            line = line.trim();
            String[] command = line.split(" ");
            String lineRemaining = line.substring(line.indexOf(" ")+1);
            lineRemaining = lineRemaining.trim();
            int id;
            switch (command[0]){
            case "bye":
                DukePrinter.printStringln("Bye. Hope to see you again soon!");
                DukePrinter.printGoodbyeLogo();
                return;
            case "list":
                DukeList.listTask();
                break;
            case "mark":
                try {
                    id = Integer.parseInt(lineRemaining);
                } catch (NumberFormatException integerException) {
                      DukePrinter.printErrorln("Sorry, the id is invalid!");
                    break;
                }
                DukeList.markDone(id-1);
                break;
            case "unmark":
                try {
                    id = Integer.parseInt(lineRemaining);
                } catch (NumberFormatException integerException) {
                    DukePrinter.printErrorln("Sorry, the id is invalid!");
                    break;
                }
                DukeList.unmarkDone(id-1);
                break;
            case "todo":
                if(lineRemaining.equals("")) {
                    DukePrinter.printErrorln("Sorry, the description of a todo cannot be empty.");
                    break;
                }
                DukeList.addTask(new DukeTask(lineRemaining));
                break;
            case "deadline":
                try {
                    //todo not follow SLAP here, fixed it later.
                    String deadlineName = lineRemaining.substring(0, lineRemaining.indexOf("/by"));
                    deadlineName = deadlineName.trim();
                    String deadlineTime = lineRemaining.substring(lineRemaining.indexOf("/by")+4);
                    deadlineTime = deadlineTime.trim();
                    DukeList.addTask(new DukeDeadline(deadlineName, deadlineTime));
                } catch (StringIndexOutOfBoundsException e) {
                    DukePrinter.printErrorln("Please use the format: " +
                            "deadline <task name> /by <deadline time>");
                }
                break;
            case "event":
                try {
                    //todo not follow SLAP here, fixed it later.
                    String eventName = lineRemaining.substring(0, lineRemaining.indexOf("/from")-1);
                    eventName = eventName.trim();
                    String eventTimeFrom = lineRemaining.substring(lineRemaining.indexOf("/from")+6,
                            lineRemaining.indexOf("/to")-1);
                    eventTimeFrom = eventTimeFrom.trim();
                    String eventTimeTo = lineRemaining.substring(lineRemaining.indexOf("/to")+4);
                    eventTimeTo = eventTimeTo.trim();
                    DukeList.addTask(new DukeEvent(eventName, eventTimeFrom, eventTimeTo));
                } catch (StringIndexOutOfBoundsException e) {
                    DukePrinter.printErrorln("Please use the format: " +
                            "event <task name> /from <event time from> /to <event time to>");
                }
                break;
            default:
                DukePrinter.printErrorln("Sorry, I don't know what that means :-( ("
                        + command[0] + ")");
                break;
            }
        }
    }
}
