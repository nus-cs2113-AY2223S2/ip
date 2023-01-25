import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n"
                + "__________________________\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! Do you need anything from me?\n"
                + "I have only been trained to greet, echo and list you so far.\n"
                + "Once my owner is more proficient in what he does, he will give me more functions!\n"
                + "Key in a number based on the function\n 1) Echo \n 2) List");
        //To be added to make sure user input is read.
        String[] items = new String[100];
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        int action = Integer.parseInt(line);
        switch (action) {
            case 1:
                line = in.nextLine();
                while (!line.equals("Bye")) {
                    System.out.println(line);
                    line = in.nextLine();
                }
                break;
            case 2:
                int itemCounter = 0;
                System.out.println("You may type anything to add to a list of a 100 items.\n"
                        + "Type List to list down everything you have added so far.\n"
                        + "Type Bye to exit.");
                line = in.nextLine();
                while (!line.equals("Bye")) {
                    if (line.equals("List")) {
                        for (int iterator = 0; iterator < itemCounter; iterator++) {
                            System.out.println(iterator + 1 + ")" + items[iterator]);
                        }
                        line = in.nextLine();
                    } else {
                        System.out.println("Added: " + line);
                        items[itemCounter] = line;
                        itemCounter++;
                        line = in.nextLine();
                    }
                }
                break;
        }
        System.out.println("That's all from me! Goodbye!");
    }
}
