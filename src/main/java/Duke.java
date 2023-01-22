import java.util.Scanner;
public class Duke {
    public static boolean isSinglish = false;
    public static String[] listOfItems = new String[100];
    public static int index = 0;

    public static void changeLanguage() {
        if (isSinglish) {
            isSinglish = false;
            System.out.println("You want Duke to help you instead? Can can I go call him now");
            System.out.println("Singlish mode = OFF");
            sayHello();
        } else {
            isSinglish = true;
            System.out.println("Changing language mode to Singlish...");
            System.out.println("Singlish mode = ON");
            sayHello();
        }
    }

    public static void horizontalLines() {
        if (isSinglish) {
            System.out.println("************************************************************");
        } else {
            System.out.println("____________________________________________________________");
        }
    }

    public static void sayGoodbye() {
        if (isSinglish) {
            System.out.println("Ok bye bye, come back soon ah!");
        } else {
            System.out.println("Bye. Hope to see you again soon!");
        }
        horizontalLines();
    }

    public static void sayHello() {
        horizontalLines();
        if (isSinglish) {
            System.out.println("Hello, my name is Uncle Simon, call me Simon can liao");
            System.out.println("You need my help?");
            System.out.println("(To turn off Singlish mode, type \"change lang\")");
        } else {
            System.out.println("Hello, I'm Duke");
            System.out.println("What can I do for you?");
            System.out.println("(To turn on Singlish mode, type \"change lang\")");
        }
        horizontalLines();
    }

    public static void returnList() {
        int counter = 1;
        for (String item : listOfItems) {
            if (item == null) {
                break;
            }
            System.out.println(counter + ". " + item);
            counter++;
        }
        horizontalLines();
    }
    public static void main(String[] args) {
        sayHello();

        while (true) {
            Scanner in = new Scanner(System.in);
            String line = in.nextLine();

            String[] commands = line.split(" ");
            if (commands[0].equals("bye")) {
                sayGoodbye();
                break;
            } else if (commands[0].equals("change") && commands.length == 2 && commands[1].equals("lang")) {
                changeLanguage();
            } else if (commands[0].equals("list") && commands.length == 1) {
                returnList();
            } else {
                if (index < 100 && !line.isEmpty()) {
                    listOfItems[index] = line;
                    index++;
                    horizontalLines();
                } else if (index == 100){
                    if (isSinglish) {
                        System.out.println("Sorry ah, the list full already");
                    } else {
                        System.out.println("Sorry, the list is full.");
                    }
                }
            }
        }
    }
}
