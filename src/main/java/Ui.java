public class Ui {
    public static void printDash() {
        System.out.println("____________________________________________________________");
    }

    public static void printWelcome() {
        printDash();
        System.out.println("Hola! I'm Duke");
        System.out.println("Let me know your tasks for the day!");
        printDash();
    }

    public static void printExit() {
        printDash();
        System.out.println("Bye, cya soon!");
        printDash();
    }
}