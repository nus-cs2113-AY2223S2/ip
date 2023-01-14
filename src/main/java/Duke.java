public class Duke {

    public static void welcomeMessage() {
        separator();
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
    }

    public static void endingMessage() {
        separator();
        System.out.println(" Bye. Hope to see you again soon!");
    }

    public static void separator() {
        System.out.println("____________________________________________________________");
    }


    public static void main(String[] args) {
        welcomeMessage();
        endingMessage();
        separator();
    }
}
