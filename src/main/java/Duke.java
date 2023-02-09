import java.util.Scanner;

public class Duke {
    public static void exitMessage() {
        System.out.println("Go away Anna");
        System.out.println("O-kay bye......");
    }

    public static String getItemDescription(String[] input) {
        Scanner in = new Scanner(System.in);
        if (input.length != 1) {
            return input[1];
        } else {
            System.out.println("What are you referring to?");
            return in.nextLine().trim();
        }
    }

    public static String getDueDate(String[] input) {
        Scanner in = new Scanner(System.in);
        if (input[1].contains("/by")) {
            return input[1].split("/by",2)[1];
        } else {
            System.out.println("When is this due by?"); {
                return in.nextLine().trim();
            }
        }
    }
    public static String[] getStartEndDates(String[] input) {
        Scanner in = new Scanner(System.in);
        String[] StartEndDates = new String[2];

        if (input[1].contains("/from") && input[1].contains("/to")) {
            StartEndDates[0] = input[1].substring(input[1].indexOf("/from"),input[1].indexOf("/to")).trim();
            StartEndDates[1] = input[1].substring(input[1].indexOf("/to")).trim();
        } else if (input[1].contains("/from")) {
            StartEndDates[0] = input[1].substring(input[1].indexOf("/from")).trim();
            System.out.println("When does this event end?");
            StartEndDates[1] = in.nextLine().trim();
        } else if (input[1].contains("/to")) {
            System.out.println("When does this event start?");
            StartEndDates[0] = in.nextLine().trim();
            StartEndDates[1] = input[1].substring(input[1].indexOf("/to")).trim();
        } else {
            System.out.println("When does this event start?");
            StartEndDates[0] = in.nextLine().trim();
            System.out.println("When does this event end?");
            StartEndDates[1] = in.nextLine().trim();
        }
        return StartEndDates;
    }
    public static void main(String[] args) {
        System.out.println("Hi it's Anna!\nWhat do you need to do?");
        Scanner in = new Scanner(System.in);

        while (true) {
            String userInput = in.nextLine().trim();
            //String[] input = new String[2];
            String[] input = userInput.split(" ",2);

            String inputCommand = input[0];
            switch (inputCommand) {
            case "bye":
                exitMessage();
                return;
            case "list":
                if (TaskList.getNumItems() == 0) {
                    System.out.println("We are free! Let's go play!");
                } else {
                    System.out.println("Here's what we've gotta do:");
                    TaskList.viewList();
                }
                break;
            case "mark": {
                String itemNum = getItemDescription(input);

                TaskList.markDone(Integer.parseInt(itemNum) - 1);
                System.out.println("Okay I've marked item " + itemNum + " as done:");
                TaskList.printItem(Integer.parseInt(itemNum) - 1);
                break;
            }

            case "unmark": {
                String itemNum = getItemDescription(input);

                TaskList.markNotDone(Integer.parseInt(itemNum) - 1);
                System.out.println("Oh no! Are we not done with " + itemNum + " after all?");
                TaskList.printItem(Integer.parseInt(itemNum) - 1);
                break;
            }

            case "add": {
                String itemDescription = getItemDescription(input);
                Task newTask = new Task(itemDescription);
                TaskList.addItem(newTask);
                break;
            }
            case "todo": {
                String itemDescription = getItemDescription(input);
                Todo newTask = new Todo(itemDescription);
                TaskList.addItem(newTask);
                break;
            }

            case "deadline": {
                String itemDescription = getItemDescription(input);
                String dueDate = getDueDate(input);
                Deadline newTask = new Deadline(itemDescription,dueDate);
                TaskList.addItem(newTask);
                break;
            }

            case "event":
                String itemDescription = getItemDescription(input);
                String[] StartEndDates = getStartEndDates(input);
                String startDate = StartEndDates[0];
                String endDate = StartEndDates[1];
                Event newTask = new Event(itemDescription,startDate,endDate);
                TaskList.addItem(newTask);
                break;

            default:
                System.out.println("I didn't get that!");
                break;

            }
        }
    }
}




