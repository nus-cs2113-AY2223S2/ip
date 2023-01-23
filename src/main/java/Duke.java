import java.util.Scanner;
public class Duke {

    public static void drawLine () {
        System.out.println("=====================================================================================");
    }

    public static void dukeChatBox(){
        System.out.println("Duke:\n");
    }

    public static void userChatBox(String name){
        System.out.println(name + ":\n");
    }
    public static String greetAndAskName () {
        drawLine();
        dukeChatBox();
        System.out.println("Hello! I'm Duke\n" + "What is your name?");
        drawLine();

        System.out.print("Please enter your name here: ");
        String name;
        Scanner in = new Scanner(System.in);
        name = in.nextLine();
        drawLine();

        dukeChatBox();
        System.out.println("Nice to meet you! " + name + "!");
        System.out.println("What can I do for you?");
        drawLine();

        return name;
    }

    public static void sayGoodbye(String name) {
        drawLine();
        dukeChatBox();
        System.out.println("Goodbye! " + name + " Hope to see you again soon!");
        drawLine();
    }

    public static void echo(String echoWords){
        drawLine();
        dukeChatBox();
        System.out.println(echoWords);
        drawLine();
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String name = greetAndAskName();

        String line;
        Scanner in = new Scanner(System.in);
        userChatBox(name);
        line = in.nextLine();

        while(line.equals("bye") == false){
            echo(line);
            userChatBox(name);
            line = in.nextLine();
        }
        sayGoodbye(name);
    }
}