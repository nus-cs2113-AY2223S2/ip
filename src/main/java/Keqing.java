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

    public static void doCommand(Task[] tasks, String text) {
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
            Todo todoTask = new Todo(content, Task.getTaskCount());
            tasks[Task.getTaskCount() - 1] = todoTask;
            echo(tasks);
            break;
        case "deadline":
            Deadline deadlineTask = new Deadline(content, Task.getTaskCount());
            tasks[Task.getTaskCount() - 1] = deadlineTask;
            echo(tasks);
            break;
        case "event":
            Event eventTask = new Event(content, Task.getTaskCount());
            tasks[Task.getTaskCount() - 1] = eventTask;
            echo(tasks);
            break;
        default:
            System.out.println(LINE);
            System.out.println("You have done nothing...?" + System.lineSeparator()
                    + "if you need more instruction, please type 'menu'.");
            System.out.println(LINE);
            break;
        }
    }

    public static void loopCommand(Task[] tasks) {
        Scanner in = new Scanner(System.in);
        String text = in.nextLine();
        while(!text.equals("bye")){
            doCommand(tasks, text);
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
