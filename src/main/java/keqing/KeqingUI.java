package keqing;

import static keqing.tasks.Task.getTaskCount;

public class KeqingUI {
    public static final String LINE = "____________________________________________________________\n";

    //reused from https://www.ascii-art-generator.org/
    public static final String logo = "                    /                                       /                   \n"
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

    /**
     * To print the greeting message at the start.
     */
    public static void printStartingGreet() {
        System.out.println("Hello from\n" + logo);
        System.out.println(LINE);
        System.out.println("Hello! I'm Keqin");
        System.out.println("What can I do for you?" + System.lineSeparator() + "Type 'menu' to know the commands.");
        System.out.println(LINE);
    }

    /**
     * To print the greeting message at the end.
     */
    public static void printExitingGreet() {
        System.out.println(LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    /**
     * To print the menu of simple instructions.
     */
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

    /**
     * To echo the command after successfully adding a task.
     */
    public static void echoAdd() {
        System.out.println(LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println("  added: " + KeqingArrayList.tasks.get(getTaskCount() - 1).toString());
        System.out.println("Now you have " + getTaskCount() + " tasks in your list.");
        System.out.println(LINE);
    }

    /**
     * To echo the command after successfully deleting a task.
     * @param index the index of the task being deleted
     */
    public static void echoDelete(int index) {
        System.out.println(LINE);
        System.out.println("Got it. I've deleted this task:");
        System.out.println("  deleted: " + KeqingArrayList.tasks.get(index).toString());
        System.out.println("Now you have " + getTaskCount() + " tasks in your list.");
        System.out.println(LINE);
    }
}