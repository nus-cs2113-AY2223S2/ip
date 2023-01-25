import java.util.Scanner;
public class Duke {
    public static Scanner input = new Scanner(System.in);
    //public static String[] list = new String[100];

    public static Task[] list= new Task[100];
    public static Boolean exit = false;
    public static String[] marker = new String[2];

    public static int currentTask = 0;

    public static void printList(int currentTask){
        int currentPrintedTask = 0;
        int tempPos = currentTask;
        if (tempPos == 0){
            System.out.println("No Task!");
        }else {
            while (tempPos > 0) {
                System.out.println(currentPrintedTask + 1 + ". " + list[currentPrintedTask].taskStatus());
                currentPrintedTask++;
                tempPos--;
            }
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String lineBreaker = "____________________________________________________________\n";
        String greeting ="____________________________________________________________\n" +
                " Hi! I'm Duke\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";
        String farewellMessage =
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n";
        String errorMessage = lineBreaker + "Invalid input. Please try again!\n" + lineBreaker;
        System.out.println("Hello from\n" + logo);
        System.out.println(greeting);

        while (!exit){
            String userInput = input.nextLine();  // Read user input

            if (userInput.startsWith("mark")){
                marker = userInput.split(" ");
                int posOfTask = Integer.parseInt(marker[1]) - 1;

                if (posOfTask < 0 || posOfTask > currentTask){
                    System.out.println(errorMessage);
                }else{
                    System.out.println(lineBreaker);
                    list[posOfTask].markAsDone();
                    System.out.println(lineBreaker);
                }
            }else if (userInput.startsWith("unmark")){
                marker = userInput.split(" ");
                int posOfTask = Integer.parseInt(marker[1]) - 1;
                if (posOfTask < 0 || posOfTask > currentTask){
                    System.out.println(errorMessage);
                }else {
                    System.out.println(lineBreaker);
                    list[posOfTask].markAsUndone();
                    System.out.println(lineBreaker);
                }
            }
            else{
                switch(userInput) {
                case "list":
                    System.out.println(lineBreaker);
                    System.out.println("Here is your list!");
                    printList(currentTask);
                    System.out.println(lineBreaker);
                    break;
                case "bye":
                    System.out.println(farewellMessage);
                    exit = true;
                    break;
                default:
                    System.out.println(lineBreaker);
                    Task t = new Task(userInput);
                    list[currentTask] = t;
                    currentTask++;
                    System.out.println("Added! " + userInput);
                    System.out.println(lineBreaker);
                }
            }
        }
    }
}
