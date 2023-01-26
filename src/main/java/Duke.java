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
            default:
                DukeList.addTask(line);
            }
        }
    }
}
