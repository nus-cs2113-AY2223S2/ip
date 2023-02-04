import duke.task.Task;
import duke.task.ToDo;
import duke.task.Deadline;
import duke.task.Event;
import duke.util.DukeException;
import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;

public class Duke {
    static private final String HI = "\n" +
            "██████╗░░█████╗░██████╗░\n" +
            "██╔══██╗██╔══██╗██╔══██╗\n" +
            "██████╦╝██║░░██║██████╦╝\n" +
            "██╔══██╗██║░░██║██╔══██╗\n" +
            "██████╦╝╚█████╔╝██████╦╝\n" +
            "╚═════╝░░╚════╝░╚═════╝░";
    static private final String BYE = "\n" +
            "██████╗░░█████╗░██████╗░  ░██████╗░█████╗░██╗░░░██╗░██████╗  ██████╗░██╗░░░██╗███████╗\n" +
            "██╔══██╗██╔══██╗██╔══██╗  ██╔════╝██╔══██╗╚██╗░██╔╝██╔════╝  ██╔══██╗╚██╗░██╔╝██╔════╝\n" +
            "██████╦╝██║░░██║██████╦╝  ╚█████╗░███████║░╚████╔╝░╚█████╗░  ██████╦╝░╚████╔╝░█████╗░░\n" +
            "██╔══██╗██║░░██║██╔══██╗  ░╚═══██╗██╔══██║░░╚██╔╝░░░╚═══██╗  ██╔══██╗░░╚██╔╝░░██╔══╝░░\n" +
            "██████╦╝╚█████╔╝██████╦╝  ██████╔╝██║░░██║░░░██║░░░██████╔╝  ██████╦╝░░░██║░░░███████╗\n" +
            "╚═════╝░░╚════╝░╚═════╝░  ╚═════╝░╚═╝░░╚═╝░░░╚═╝░░░╚═════╝░  ╚═════╝░░░░╚═╝░░░╚══════╝";
    static private final String DIV = "\n===========================================================================\n";
    static private final ArrayList<Task> tasks = new ArrayList<>();
    static private final HashMap<String, String> helpOutputs = new HashMap<>();
    static private int taskCount = 0;

    public static void hello() {
        System.out.println(HI);
    }

    public static void shutdown() {
        System.out.println("\n" + BYE);
    }

    public static void query() {
        System.out.println(DIV + "\nWhat do you want from me boss?\n" + DIV);
    }

    public static void generateHelp() {
        helpOutputs.put("list", "");
        helpOutputs.put("todo", "");
        helpOutputs.put("deadline", "");
        helpOutputs.put("event", "");
        helpOutputs.put("mark", "");
        helpOutputs.put("unmark", "");
        helpOutputs.put("help", "");
    }

    public static void printHelp(String str) {
        for (String i:helpOutputs.keySet()) {
            System.out.println(i + ": " + helpOutputs.get(i));
        }
    }

    public static void listOut() {
        if (taskCount == 0) {
            System.out.println("*Tumbleweed passes by*\nUh oh! Looks like your list is empty!");
            return;
        } else {
            System.out.println("Here's your list boss! *Crosses arms and nods* : ");
        }
        for (int i = 0; i < taskCount; ++i) {
            System.out.println(tasks.get(i).getNumber() + "." + tasks.get(i));
        }
    }

    public static void printError() {
        System.out.println("Wrong command boss! Try again!");
    }

    public static int convertString(String str) throws DukeException {
        int num;
        try {
            num = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            System.out.println("This is not a number boss! Try again!");
            throw new DukeException();
        }
        if (num < 1 || num > taskCount) {
            System.out.println("This number is not in the list boss! Try again!");
            throw new DukeException();
        }
        return num;
    }

    public static void checkMarkStatus(int index, boolean changeTo, String type) throws DukeException{
        if (tasks.get(index - 1).getIsDone() == changeTo) {
            System.out.println("It is already " + type + "ed! *Shakes head* ");
            throw new DukeException();
        }
    }

    public static void echo() {
        System.out.println(tasks.get(taskCount));
        ++taskCount;
    }

    public static void command() {
        Scanner in = new Scanner(System.in);
        do {
            System.out.print("What do you want: ");
            String cmd = in.next();
            String checkCmd = cmd.toLowerCase();
            if (checkCmd.equals("bye")) {
                return;
            }
            System.out.println(DIV);
            switch (checkCmd) {
            case "list":
                listOut();
                break;
            case "help":
                String next = in.nextLine();
                printHelp(next.trim());
                break;
            case "mark":
                next = in.nextLine();
                int num;
                try {
                    num = convertString(next.trim());
                    checkMarkStatus(num, true, checkCmd);
                } catch (DukeException e) {
                    break;
                }
                System.out.println("Bob commends you! *Nods head* ");
                tasks.get(num - 1).setDone(true);
                System.out.println(tasks.get(num - 1));
                break;
            case "unmark":
                next = in.nextLine();
                try {
                    num = convertString(next.trim());
                    checkMarkStatus(num, false, checkCmd);
                } catch (DukeException e) {
                    break;
                }
                System.out.println("A mistake! *Shakes head* ");
                tasks.get(num - 1).setDone(false);
                System.out.println(tasks.get(num - 1));
                break;
            case "todo":
                next = in.nextLine();
                System.out.println("Understood! *Salutes* Task added!");
                tasks.add(new ToDo(next.stripLeading(), taskCount + 1, false));
                echo();
                break;
            case "deadline":
                next = in.nextLine();
                try {
                    String[] deadline = next.split("/by", 2);
                    tasks.add(new Deadline(deadline[0].trim(), taskCount + 1, false,
                            deadline[1].trim()));
                } catch (ArrayIndexOutOfBoundsException e) {
                    printError();
                    break;
                }
                System.out.println("Understood *Salutes* Task with deadline added!\n"
                        + "Remember to complete it by the deadline!");
                echo();
                break;
            case "event":
                next = in.nextLine();
                try {
                    String[] eventName = next.split("/from", 2);
                    String[] eventTime = eventName[1].split("/to", 2);
                    tasks.add(new Event(eventName[0].trim(), taskCount + 1, false,
                            eventTime[0].trim(), eventTime[1].trim()));
                } catch (ArrayIndexOutOfBoundsException e) {
                    printError();
                    break;
                }
                System.out.println("Understood *Salutes* Event added!\n"
                        + "Remember the starting time! Don't be late!");
                echo();
                break;
            default:
                printError();
                in.nextLine();
            }
            System.out.println(DIV);
        } while (true);
    }

    public static void main(String[] args) {
        hello();
        generateHelp();
        query();
        command();
        shutdown();
    }
}