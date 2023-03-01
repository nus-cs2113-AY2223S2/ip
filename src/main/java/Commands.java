import java.util.Scanner;

public class Commands {
    public static void invalidCommand() {
        System.out.println("I didn't get that!");
    }
    public static void showList() {
        if (TaskList.getNumItems() == 0) {
            System.out.println("We are free! Let's go play!");
        } else {
            System.out.println("Here's what we've gotta do:");
            TaskList.viewList();
            System.out.println("We currently have " + TaskList.getNumItems() + " tasks");
        }
    }

    public static void markTask(String userInput, int statusType) {
        String itemDescription = Ui.getItemDescription(userInput);
        int itemNum = 0;
        try {
            itemNum = Integer.parseInt(itemDescription);
        } catch (NumberFormatException e) {
            Scanner in = new Scanner(System.in);
            System.out.println("Sorry! What number is that on the list?");
            itemNum = in.nextInt();
        } finally {
            if (itemNum > 0 && itemNum <= TaskList.getNumItems()) {
                if (statusType == 1) {
                    TaskList.markDone(itemNum - 1);
                    System.out.println("Okay I've marked item " + itemNum + " as done:");
                    TaskList.printItem(itemNum - 1);
                } else {
                    TaskList.markNotDone(itemNum - 1);
                    System.out.println("Oh we aren't done with item " + itemNum + "?");
                    TaskList.printItem(itemNum - 1);
                }
            } else {
                System.out.println("Item does not exist!");
            }
        }
    }

}
