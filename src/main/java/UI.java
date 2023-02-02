public class UI {
    public static final String HORIZONTAL_LINE = "____________________________________________________________";

    public static void printMessage(String message){
        System.out.println(message);
    }

    public static void showWelcomeMessage(){
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        System.out.println(HORIZONTAL_LINE);
    }

    public static void showByeMessage(){
        System.out.println("Bye. Hope to see you again soon!");
    }


}
