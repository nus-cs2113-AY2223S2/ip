package keqing;

import keqing.exceptions.IllegalInputException;
import keqing.tasks.Deadline;
import keqing.tasks.Event;
import keqing.tasks.Task;
import keqing.tasks.ToDo;

import java.util.ArrayList;
import java.util.Scanner;

import static keqing.tasks.Task.getTaskCount;

public class Keqing {

    public static final String LINE = "____________________________________________________________\n";

    public static void echoAdd() {
        System.out.println(LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println("  added: " + tasks.get(getTaskCount() - 1).toString());
        System.out.println("Now you have " + getTaskCount() + " tasks in your list.");
        System.out.println(LINE);
    }

    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public static void echoDelete(int index) {
            System.out.println(LINE);
            System.out.println("Got it. I've deleted this task:");
            System.out.println("  deleted: " + tasks.get(index).toString());
            System.out.println("Now you have " + getTaskCount() + " tasks in your list.");
            System.out.println(LINE);
    }

    public static void printTaskList() {
        System.out.println(LINE);
        if (getTaskCount() == 0) {
            System.out.println("The list is empty...!");
        }
        for (int i = 0; i < getTaskCount(); i++) {
            System.out.print((i + 1) + ".");
            System.out.println(tasks.get(i).toString());
        }
        System.out.println(LINE);
    }

    public static void markTask(int currentID, boolean isDone) {
        System.out.println(LINE);
        if (currentID < 0 || currentID >= getTaskCount()) {
            System.out.println("Cannot find this task!");
        }
        else if (isDone) {
            tasks.get(currentID).setDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("   " + tasks.get(currentID).toString());
        }
        else {
            tasks.get(currentID).seUndone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("   " + tasks.get(currentID).toString());
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

    public static void readToDo(String content) throws IllegalInputException {
        if (content.equals("todo")) {
            throw new IllegalInputException("Keqing doesn't understand what you actually want to do...");
        }
        else {
            ToDo toDoTask = new ToDo(content, getTaskCount());
            tasks.add(toDoTask);
            echoAdd();
        }
    }

    public static void readDeadline(String content) throws IllegalInputException {
        if (content.contains("/by")) {
            int indexOfBy = content.indexOf("/by");
            if (indexOfBy + 3 < content.length()) {
                String by = content.substring(indexOfBy + 3).trim();
                Deadline deadlineTask = new Deadline(content, getTaskCount(), by);
                tasks.add(deadlineTask);
                echoAdd();
            }
            else {
                throw new IllegalInputException("Keqing doesn't think your input makes sense...");
            }
        }
        else {
            throw new IllegalInputException("Please check if you have typed in a valid deadline.");
        }
    }

    public static void readEvent(String content) throws IllegalInputException {
        if (content.contains("./from") && content.contains("./to")) {
            int indexOfFrom = content.indexOf("/from");
            int indexOfTo = content.indexOf("/to");
            if (indexOfFrom < indexOfTo) {
                String from = content.substring(indexOfFrom + 5, indexOfTo).trim();
                String to = content.substring(indexOfTo + 3).trim();
                Event eventTask = new Event(content, getTaskCount(), from, to);
                tasks.add(eventTask);
                echoAdd();
            }
            else {
                throw new IllegalInputException("Keqing doens't think your input makes sense...");
            }
        }
        else {
            throw new IllegalInputException("Please check if you have typed in the event duration in a valid form...");
        }
    }

    public static void deleteTask(String content) throws IllegalInputException{
        if(isNumeric(content)){
            int index = Integer.parseInt(content) - 1;    //switch to 0-based.
            if (index < getTaskCount()) {
                echoDelete(index);
                tasks.remove(index);
                Task.setTaskCount(getTaskCount() - 1);
            }
            else {
                throw new IllegalInputException("It's out of bound!!");
            }
        }
    }

    public static void doCommand(String text) throws IllegalInputException {
        String splittedText[] = text.split(" ", 2);
        String command = splittedText[0];
        String content = splittedText[splittedText.length - 1];
        switch (command) {
        case "list":
            printTaskList();
            break;
        case "menu":
            printMenu();
            break;
        case "mark":
        case "unmark":
            int currentID = Integer.parseInt(text.substring(text.length() - 1)) - 1;
            boolean isDone;
            isDone = !text.contains("unmark");
            markTask(currentID, isDone);
            break;
        case "todo":
            readToDo(content);
            break;
        case "deadline":
            readDeadline(content);
            break;
        case "event":
            readEvent(content);
            break;
        case "delete":
            deleteTask(content);
            break;
        default:
            throw new IllegalInputException("Keqing doesn't understand your input...?");
        }
    }

    public static void loopCommand() {
        Scanner in = new Scanner(System.in);
        String text = in.nextLine();
        while(!text.equals("bye")){
            try {
                doCommand(text);
            } catch (IllegalInputException e) {
                System.out.println(LINE + System.lineSeparator() + e + System.lineSeparator()
                        + "if you need more instruction, please type 'menu'."+ System.lineSeparator() + LINE);
            }
            text = in.nextLine();
        }
    }
    public static ArrayList<Task> tasks = new ArrayList<Task>();
    public static void main(String[] args) {
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
        loopCommand();
        System.out.println(LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }
}
