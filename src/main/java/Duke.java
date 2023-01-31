import java.util.Scanner;

public class Duke {
    public static void exitMessage() {
        System.out.println("Go away Anna");
        System.out.println("O-kay bye......");
    }

    public static void main(String[] args) {
        System.out.println("Hi it's Anna!\nWhat do you need to do?");
        Scanner in = new Scanner(System.in);

        boolean exit = false;
        while (!exit) {
            String input = (in.nextLine()).trim();
            String inputCMD, inputItem;
            if (input.contains(" ")) {
                inputCMD = input.split(" ", 2)[0];
                inputItem = input.split(" ", 2)[1];
            } else {
                inputCMD = input;
                inputItem = null;
            }

            switch (inputCMD) {
            case "bye":
                exitMessage();
                exit = true;
                break;
            case "list":
                if (ToDoList.getNumItems() == 0) {
                    System.out.println("We are free! Let's go play!");
                } else {
                    System.out.println("Here's what we've gotta do:");
                    ToDoList.viewList();
                }
                break;
            case "mark":
                if (inputItem == null) {
                    System.out.println("What should I mark?");
                    ToDoList.viewList();
                    inputItem = in.nextLine().trim();
                }

                ToDoList.markDone(Integer.parseInt(inputItem)-1);
                System.out.println("Okay I've marked item " + inputItem + " as done:");
                ToDoList.printItem(Integer.parseInt(inputItem)-1);
                break;

            case "unmark":
                if (inputItem == null) {
                    System.out.println("What should I unmark?");
                    //TODO: add in handling if input is "unmark 2" again
                    ToDoList.viewList();
                    inputItem = in.nextLine().trim();
                }

                ToDoList.markNotDone(Integer.parseInt(inputItem)-1);
                System.out.println("Oh no! Are we not done with " + inputItem + " after all?");
                ToDoList.printItem(Integer.parseInt(inputItem)-1);
                break;
            case "add":
                ToDoList.addItem(inputItem);
                break;
            default:
                System.out.println("I didn't get that!");
                break;
            }
        }
    }
}




