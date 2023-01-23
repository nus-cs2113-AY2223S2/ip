public class IO {
    public static void println() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }                          
    public static void greet() {
        IO.println();
        System.out.println("  Hello! I'm GrandDuke\n" + "  What can I do for you?");
        IO.println();
    }
    public static void exit() {
        System.out.println("  Bye. Hope to see you again soon!");
        IO.println();
    }
}
