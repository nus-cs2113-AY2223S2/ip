public class Duke {
    static String line = "\t____________________________________________________________";

    public static void Greet() {
        System.out.println(line);
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?\n");
        System.out.println(line);
    }

    public static void  Bye() {
        System.out.println("\tBye. Hope to see you again soon!\n");
        System.out.println(line);
    }

    public static void main(String[] args) {
        Greet();
        Bye();
    }
}
