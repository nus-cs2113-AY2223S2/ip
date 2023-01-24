import java.util.Scanner;
public class Duke {
    public static boolean isSinglish = false;
    public static Task[] tasks = new Task[100];
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

    public static void sayHello() {
        horizontalLines();
        if (isSinglish) {
            System.out.println("Hello, my name is Uncle Simon, call me Simon can liao");
            System.out.println("You need my help?");
            System.out.println("(To turn off Singlish mode, type \"change lang\")");
        } else {
            System.out.println("Hello, I'm Duke.");
            System.out.println("What can I do for you?");
            System.out.println("(To turn on Singlish mode, type \"change lang\").");
        }
        horizontalLines();
    }

    public static void sayGoodbye() {
        if (isSinglish) {
            System.out.println("Ok bye bye, come back soon ah!");
        } else {
            System.out.println("Bye. Hope to see you again soon!");
        }
        horizontalLines();
    }

    public static void addToList(String line) {
        if (index < 100 && !line.isEmpty()) {
            Task item = new Task(line);
            tasks[index] = item;
            index++;
        } else if (index == 100){
            if (isSinglish) {
                System.out.println("Sorry ah, the list full already");
            } else {
                System.out.println("Sorry, the list is full.");
            }
        }
        horizontalLines();
    }

    public static void returnList() {
        for (int i = 0; i < tasks.length; i++) {
            if (tasks[i] == null) {
                break;
            }
            System.out.println(i + 1 + ".[" + tasks[i].getStatusIcon() + "] " + tasks[i].getDescription());
        }
        horizontalLines();
    }

    public static void markTask(int index, boolean isMark) {
        index--;
        if (index < 0 || index > 99 || tasks[index] == null) {
            if (isSinglish) {
                System.out.println("Eh, your list dun have a task at that index lah");
            } else {
                System.out.println(("The list does not have a task of that index"));
            }
        } else {
            if (tasks[index].getIsDone() != isMark) {
                tasks[index].switchIsDone();
            }

            if (isSinglish) {
                System.out.println("Ok I updated it:");
            } else {
                System.out.println("Updated the following task:");
            }
            System.out.println(index + 1 + ".[" + tasks[index].getStatusIcon() + "] " + tasks[index].getDescription());
        }

    }

    public static void wrongSyntax() {
        if (isSinglish) {
            System.out.println("Eh you typed wrongly, can try typing again?");
        } else {
            System.out.println("Invalid syntax, please try again");
        }
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
            } else if ((commands[0].equals("mark") || commands[0].equals("unmark")) && commands.length == 2) {
                if (commands[1].matches("\\d+?")) {
                    boolean isMark = commands[0].equals("mark");
                    markTask(Integer.parseInt(commands[1]), isMark);
                } else {
                    wrongSyntax();
                }
            } else {
                addToList(line);
            }
        }
    }
}
