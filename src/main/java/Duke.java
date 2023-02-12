import duke.task.Task;
import duke.task.ToDo;
import duke.task.Deadline;
import duke.task.Event;
import duke.util.DukeException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
    static private final ArrayList<String> storedData = new ArrayList<>();
//    static private final HashMap<String, String> helpOutputs = new HashMap<>();
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

//    public static void generateHelp() {
//        helpOutputs.put("list", "");
//        helpOutputs.put("todo", "");
//        helpOutputs.put("deadline", "");
//        helpOutputs.put("event", "");
//        helpOutputs.put("mark", "");
//        helpOutputs.put("unmark", "");
//        helpOutputs.put("help", "");
//    }

//    public static void printHelp(String str) {
//        for (String i:helpOutputs.keySet()) {
//            System.out.println(i + ": " + helpOutputs.get(i));
//        }
//    }

    public static void listOut() {
        if (taskCount == 0) {
            System.out.println("*Tumbleweed passes by*\nUh oh! Looks like your list is empty!");
            return;
        } else {
            System.out.println("Here's your list boss! *Crosses arms and nods* : ");
        }
        for (int i = 0; i < taskCount; ++i) {
            System.out.println(i + 1 + "." + tasks.get(i));
        }
        if (taskCount == 1) {
            System.out.println("Looks like you have " + taskCount + " thing on your list!");
        } else {
            System.out.println("Looks like you have " + taskCount + " things on your list!");
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

    public static void handleMark(String checkCmd, String next) throws DukeException {
        int num;
        try {
            num = convertString(next.trim());
            checkMarkStatus(num, true, checkCmd);
        } catch (DukeException e) {
            throw new DukeException();
        }
        System.out.println("Bob commends you! *Nods head* ");
        tasks.get(num - 1).setDone(true);
        System.out.println(tasks.get(num - 1));
    }

    public static void handleUnmark (String checkCmd, String next) throws DukeException {
        int num;
        try {
            num = convertString(next.trim());
            checkMarkStatus(num, false, checkCmd);
        } catch (DukeException e) {
            throw new DukeException();
        }
        System.out.println("A mistake! *Shakes head* ");
        tasks.get(num - 1).setDone(false);
        System.out.println(tasks.get(num - 1));
    }

    public static void handleTodo(String next) {
        System.out.println("Understood! *Salutes* Task added!");
        tasks.add(new ToDo(next.stripLeading(), false));
        echo();
    }

    public static void handleDeadline(String next) throws DukeException {
        try {
            String[] deadline = next.split("/by", 2);
            tasks.add(new Deadline(deadline[0].trim(), false,
                    deadline[1].trim()));
        } catch (ArrayIndexOutOfBoundsException e) {
            printError();
            throw new DukeException();
        }
        System.out.println("Understood *Salutes* Task with deadline added!\n"
                + "Remember to complete it by the deadline!");
        echo();
    }

    public static void handleEvent(String next) throws DukeException {
        try {
            String[] eventName = next.split("/from", 2);
            String[] eventTime = eventName[1].split("/to", 2);
            tasks.add(new Event(eventName[0].trim(), false,
                    eventTime[0].trim(), eventTime[1].trim()));
        } catch (ArrayIndexOutOfBoundsException e) {
            printError();
            throw new DukeException();
        }
        System.out.println("Understood *Salutes* Event added!\n"
                + "Remember the starting time! Don't be late!");
        echo();
    }

    public static void readFile(String path) throws FileNotFoundException {
        File f = new File(path);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            storedData.add(s.nextLine());
        }
    }

    public static void writeFile(String path) throws IOException {
        //Overwrites previous text in file
        FileWriter fw = new FileWriter(path, false);
        for (Task i: tasks) {
            String t = i.checkType();
            char done = i.checkDone().charAt(1);
            String w = t.charAt(1) + " % " + done + " % " + i.getTask() + "\n";
            fw.write(w);
        }
        fw.close();
    }

    public static void createFile(String path) {
        try {
            Path dir = Paths.get("data");
            Files.createDirectories(dir);
            File newFile = new File(path);
            if (newFile.createNewFile()) {
                System.out.println("File created.");
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  static void handleFile() throws DukeException {
        for (String str: storedData) {
            String[] temp = str.split("%",3);
            System.out.println(temp[2]);
            switch (temp[0].trim()) {
            case ("T"):
                handleTodo(temp[2].trim());
                if (temp[1].trim().equals("X")) {
                    handleMark("task", temp[2].trim());
                }
                break;
            case ("D"):
                try {
                    handleDeadline(temp[2].trim());
                    if (temp[1].trim().equals("X")) {
                        handleMark("task", temp[2].trim());
                    }
                } catch (DukeException e) {
                    throw new DukeException();
                }
                break;
            case ("E"):
                try {
                    handleEvent(temp[2].trim());
                    if (temp[1].trim().equals("X")) {
                        handleMark("task", temp[2].trim());
                    }
                } catch (DukeException e) {
                    throw new DukeException();
                }
                break;
            }
        }
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
//            case "help":
//                String next = in.nextLine();
//                printHelp(next.trim());
//                break;
            case "mark":
                String next = in.nextLine();
                int num;
                try {
                    handleMark(checkCmd, next);
                } catch (DukeException e) {
                    break;
                }
                break;
            case "unmark":
                next = in.nextLine();
                try {
                    handleUnmark(checkCmd, next);
                } catch (DukeException e) {
                    break;
                }
                break;
            case "todo":
                next = in.nextLine();
                handleTodo(next);
                break;
            case "deadline":
                next = in.nextLine();
                try {
                    handleDeadline(next);
                } catch (DukeException e) {
                    break;
                }
                break;
            case "event":
                next = in.nextLine();
                try {
                    handleEvent(next);
                } catch (DukeException e) {
                    break;
                }
                break;
            case "delete":
                next = in.nextLine();
                try {
                    num = convertString(next.trim());
                } catch (DukeException e) {
                    break;
                }
                System.out.println("Roger!" + tasks.get(num - 1) + " removed!");
                tasks.remove(tasks.get(num - 1));
                --taskCount;
                break;
            default:
                printError();
                in.nextLine();
            }
            System.out.println(DIV);
        } while (true);
    }

    public static void main(String[] args) {
        String path = "data\\tasks.txt";
        try {
            readFile(path);
        } catch (FileNotFoundException e) {
            System.out.println("File not found\nCreating file");
            createFile(path);
        }
        try {
            handleFile();
        } catch (DukeException e) {
            System.out.println("Reading file failed");
        }
        hello();
//        generateHelp();
        query();
        command();
        try {
            writeFile(path);
        } catch (IOException e) {
            System.out.println("Unforeseen error occurred! List not saved!");
        }
        shutdown();
    }
}