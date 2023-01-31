import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public Duke() {

    }

    public static void main(String[] args) {

        Duke duke = new Duke();
        ArrayList<String> userInputs = new ArrayList<>();
        boolean isRun = true;
        Scanner scan = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        greetUser();

        while (true) {
            String input = scan.nextLine();
            if (input.equals("bye")) {
                break;
            }
            if (input.equals("list")) {
                listOut(userInputs);
            } else if (input.length() >= 4 && input.substring(0, 4).equals("mark")) {
                String[] tmpArr = input.split(" ");
                markDone(Integer.parseInt(tmpArr[1]), userInputs, true);
            } else if (input.length() >= 6 && input.substring(0, 6).equals("unmark")) {
                String[] tmpArr = input.split(" ");
                markDone(Integer.parseInt(tmpArr[1]), userInputs, false);
            } else {
                addToList(input, userInputs);
            }
            //echoCmd(input);


        }
        exit();
    }
    public static void markDone(int index, ArrayList<String> userInputs, boolean isMark) {
        if (isMark) {
            String org = userInputs.get(index - 1);
            String newStr = org.replace("[ ]", "[X]");
            System.out.println(newStr);
            userInputs.set(index - 1, newStr);
        } else {
            String org = userInputs.get(index - 1);
            String newStr = org.replace("[X]", "[ ]");
            System.out.println(newStr);
            userInputs.set(index - 1, newStr);
        }
        System.out.println("\t____________________________________________________________");
        if (isMark) {
            System.out.println("\t Nice! I've marked this task as done:");
        } else {
            System.out.println("\t OK, I've marked this task as not done yet:");
        }
        int idx = userInputs.get(index - 1).indexOf("[");
        System.out.println("\t" + "  " + userInputs.get(index - 1).substring(idx));
        System.out.println("\t____________________________________________________________");
    }
    public static void addToList(String cmd, ArrayList<String> userInputs) {
        System.out.println("\t____________________________________________________________");
        System.out.println("\tadded: " + cmd);
        System.out.println("\t____________________________________________________________");
        userInputs.add(cmd);
        userInputs.set(userInputs.size() - 1, userInputs.size() + ". [ ] " + userInputs.get(userInputs.size() - 1));
    }

    public static void listOut(ArrayList<String> userInputs) {
        System.out.println("\t____________________________________________________________");
        for (int i = 0; i < userInputs.size(); i++) {
            System.out.println("\t" + userInputs.get(i));
        }
        System.out.println("\t____________________________________________________________");

    }
    public static void echoCmd(String cmd) {
        System.out.println("\t____________________________________________________________");
        System.out.println("\t " + cmd);
        System.out.println("\t____________________________________________________________");

    }

    public static void greetUser() {
        System.out.println("\t____________________________________________________________");
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        System.out.println("\t____________________________________________________________");

    }

    public static void exit() {
        System.out.println("\t____________________________________________________________");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t____________________________________________________________");

    }

}
