package keqing;

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

    public static void printStartingGreet() {
        System.out.println("Hello from\n" + logo);
        System.out.println(LINE);
        System.out.println("Hello! I'm Keqin");
        System.out.println("What can I do for you?" + System.lineSeparator() + "Type 'menu' to know the commands.");
        System.out.println(LINE);
    }

    public static void printExitingGreet() {
        System.out.println(LINE);
        System.out.println("Bye. Hope to see you again soon!");
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

}