import java.util.Scanner;
public class Keqing {

    public static final String LINE = "____________________________________________________________\n";
    public static void echo(Task[] tasks) {
        System.out.println(LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println("  added: " + tasks[Task.getTaskCount() - 1].toString());
        System.out.println("Now you have " + Task.getTaskCount() + " tasks in your list.");
        System.out.println(LINE);
    }

    public static void printTaskList(Task[] tasks) {
        System.out.println(LINE);
        if (Task.getTaskCount() == 0) {
            System.out.println("The list is empty...!");
        }
        for (int i = 0; i < Task.getTaskCount(); i++) {
            System.out.print((i + 1) + ".");
            System.out.println(tasks[i].toString());
        }
        System.out.println(LINE);
    }

    public static void markTask(Task[] tasks, int currentID, boolean isDone) {
        System.out.println(LINE);
        if (currentID < 0 || currentID >= Task.getTaskCount()) {
            System.out.println("Cannot find this task!");
        }
        else if (isDone) {
            tasks[currentID].setDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("   " + tasks[currentID].toString());
        }
        else {
            tasks[currentID].seUndone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("   " + tasks[currentID].toString());
        }
        System.out.println(LINE);
    }

    public static void printMenu() {
        System.out.println(LINE);
        System.out.println("Try the following commands:");
        System.out.println("1. list: show the list of tasks;");
        System.out.println("2. todo: add a task without starting time/deadline to the list;");
        System.out.println("3. deadline: add a task with deadline to the list;");
        System.out.println("4. event: add a task with specific starting and ending time");
        System.out.println("5, mark: mark a task as 'done' state;");
        System.out.println("6. unmark: unmark a task from 'done' state;");
        System.out.println(LINE);
    }

    public static void readToDo(Task[] tasks, String content) throws IllegalInputException {
        if (content.equals("todo")) {
            throw new IllegalInputException("Keqing doesn't understand what you actually want to do...");
        }
        else {
            ToDo toDoTask = new ToDo(content, Task.getTaskCount());
            tasks[Task.getTaskCount() - 1] = toDoTask;
        }
    }

    public static void readDeadline(Task[] tasks, String content) throws IllegalInputException {
        if (content.contains("/by")) {
            int indexOfBy = content.indexOf("/by");
            if (indexOfBy + 3 < content.length()) {
                String by = content.substring(indexOfBy + 3).trim();
                Deadline deadlineTask = new Deadline(content, Task.getTaskCount(), by);
                tasks[Task.getTaskCount() - 1] = deadlineTask;
            }
            else {
                throw new IllegalInputException("Keqing doesn't think your input makes sense...");
            }
        }
        else {
            throw new IllegalInputException("Please check if you have typed in a valid deadline.");
        }
    }

    public static void readEvent(Task[] tasks, String content) throws IllegalInputException {
        if (content.contains("./from") && content.contains("./to")) {
            int indexOfFrom = content.indexOf("/from");
            int indexOfTo = content.indexOf("/to");
            if (indexOfFrom < indexOfTo) {
                String from = content.substring(indexOfFrom + 5, indexOfTo).trim();
                String to = content.substring(indexOfTo + 3).trim();
                Event eventTask = new Event(content, Task.getTaskCount(), from, to);
                tasks[Task.getTaskCount() - 1] = eventTask;
            }
            else {
                throw new IllegalInputException("Keqing doens't think your input makes sense...");
            }
        }
        else {
            throw new IllegalInputException("Please check if you have typed in the event duration in a valid form...");
        }
    }

    public static void doCommand(Task[] tasks, String text) throws IllegalInputException {
        String splittedText[] = text.split(" ", 2);
        String command = splittedText[0];
        String content = splittedText[splittedText.length - 1];
        switch (command) {
        case "list":
            printTaskList(tasks);
            break;
        case "menu":
            printMenu();
            break;
        case "mark":
        case "unmark":
            int currentID = Integer.parseInt(text.substring(text.length() - 1)) - 1;
            boolean isDone;
            if (text.contains("unmark")) {
                isDone = false;
                markTask(tasks, currentID, isDone);
            } else {
                isDone = true;
                markTask(tasks, currentID, isDone);
            }
            break;
        case "todo":
            readToDo(tasks, content);
            echo(tasks);
            break;
        case "deadline":
            readDeadline(tasks, content);
            echo(tasks);
            break;
        case "event":
            readEvent(tasks, content);
            echo(tasks);
            break;
        default:
            throw new IllegalInputException("Keqing doesn't understand your input...?");
        }
    }

    public static void loopCommand(Task[] tasks) {
        Scanner in = new Scanner(System.in);
        String text = in.nextLine();
        while(!text.equals("bye")){
            try {
                doCommand(tasks, text);
            } catch (IllegalInputException e) {
                System.out.println(LINE + System.lineSeparator() + e + System.lineSeparator()
                        + "if you need more instruction, please type 'menu'."+ System.lineSeparator() + LINE);
            }
            text = in.nextLine();
        }
    }
    public static void main(String[] args) {
        Task[] tasks = new Task[100];
        String logo = "                    /                                       /                   \n"
                + "                    ////(                              .(////                   \n" +
                "                    *///*(//.    .*((//((///(/,   .**/////*/,                   \n" +
                "                    .(((//((/%//////(////////////*#((*(////(.                   \n" +
                "                    ./*/*&(/////////////////(*////*/*%%*(//*.                   \n" +
                "                     .(/*#///////////////////(//(/(////#(((.                    \n" +
                "                     .@%//////////////////////#///(#////#%/**                   \n" +
                "                    ,&&//**//(*/////*/////////#*/(((/****#(//                   \n" +
                "                   .,#/(//////////#///////////#*//#(%///*(*(#*                  \n" +
                "                   *//(/////(//*/#////#(////(*,..#*%/////(/#,.                  \n" +
                "              .((((/*#(/////((//((//%,/////#(/. . **(//////,.                   \n" +
                "               ..,,,,((*////#(# #*#(,(#///(#((... .*/////((.                    \n" +
                "                    ./(//#//%%@&@@@%*#//*.( *@@@@@@(&///*%#*                    \n" +
                "                     /%(//#/(#,((#*/./...... (/(/#**/(///#//                    \n" +
                "                    (//&//((%/..................../ %///((//(                   \n" +
                "                   ////##///&,.*,...... ...........&#//((#/*//                  \n" +
                "                  //*//((#/(#((........ .........,*#*//(((*///(                 \n" +
                "                 /*///#(**#/((,**/....   ....../,,,//(%//(#*//*/                \n" +
                "               .***////*/(/(/(*/*@&#//,.. /(*@&%. (*%/(//*////***.              \n" +
                "              /**(***/*/*(((*,(/#&&&%//%%//(@&&&(/,,,((#***/***#**/             \n" +
                "            /**#****/***#/((..,,,,&&@&*%#(/@&&(*,,...(/(#********#**/           \n" +
                "          (*,((***/****/*/(, .#&%%%%%%&/ %%%%%&#%%,  ,(/*/****/***(/,*/         \n" +
                "        ,,**#,***#*@****(*..&%%#..,%/&&##(%#(//**&%%(. /#***#@,%**,,(**,,       \n" +
                "       (,*,,,,*,/(/&@@  ...,%%&****/%&*//#(%&*...*%%%.....,@@//(/,**,*,*,/      \n" +
                "      ,(,*,,*,///*((&@@....#%&,***...((///&/.,***./%&... (@@%/(/*//,,*,*,(,     \n" +
                "    .*/,,,*,,*/##/(//%&@. /&&...****.(*///#********/%,..#@%&((/(#(/**,*,,,/,    \n" +
                "    *,*./,,(*,/,(((##(*(@,(%(@/.*/..//%##%,...../***,#.,@/,%#/#(,*(/,/,,/.*,*   \n" +
                "   ,*,*#,,(/%#(#*(#,%###%#&&#%(%&(%#..........*********@%####*%///###/(,,#*,,,  \n" +
                "   /,,/*,(/*#*#*(######&%&(@(%#%##&%/*.%...........,/**&%%######(/#/#*/(,,/,,,  \n";

        System.out.println("Hello from\n" + logo);
        System.out.println(LINE);
        System.out.println("Hello! I'm Keqin");
        System.out.println("What can I do for you?" + System.lineSeparator() + "Type 'menu' to know the commands.");
        System.out.println(LINE);
        loopCommand(tasks);
        System.out.println(LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }
}
