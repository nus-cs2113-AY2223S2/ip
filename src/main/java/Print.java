public class Print {
    public static final String underline = "\t____________________________________________________________";
    public static final String todo = "\t Got it. I've added this task:";

    public static final String mark = "\t Nice! I've marked this task as done:";
    public static final String unmark = "\t OK, I've marked this task as not done yet:";
    public static final String list = "\t Here are the tasks in your list:";


    public static void printUnderline(){
        System.out.println(underline);
    }
    public static void printFormAbove(){
        printUnderline();
        System.out.println(todo);
    }
    public static void printCnt(int cnt){
        System.out.println("\t Now you have " + cnt + " tasks in the list.");
        printUnderline();
    }
    public static void printList(){
        printUnderline();
        System.out.println(list);
    }
    public static void printMark(){
        printUnderline();
        System.out.println(mark);
    }
    public static void printUnmark(){
        printUnderline();
        System.out.println(unmark);
    }
}
