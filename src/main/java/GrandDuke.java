public class GrandDuke {
    public static void greet() {
        System.out.println("____________________________________________________________");
        System.out.println("  Hello! I'm GrandDuke\n" + "  What can I do for you?");
        System.out.println("____________________________________________________________");
    }
    public static void exit() {
        System.out.println("  Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
    public static void main(String[] args) {
        String logo = "  _____                     _______       _        \n"
        + "|  __ \\                   | |  _  \\     | |       \n"
        + "| |  \\/_ __ __ _ _ __   __| | | | |_   _| | _____ \n"
        + "| | __| '__/ _` | '_ \\ / _` | | | | | | | |/ / _ \\ \n"
        + "| |_\\ \\ | | (_| | | | | (_| | |/ /| |_| |   <  __/\n"
        + " \\____/_|  \\__,_|_| |_|\\__,_|___/  \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        greet();
        exit();
    }
}
