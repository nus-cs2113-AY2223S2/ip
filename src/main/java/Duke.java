import java.util.Scanner;
public class Duke {

    /**
     * Prints out the greeting message to the user
     */
    public static void greetUser(){
        String greetMessage = "Hello! I'm Duke\n"
                + "Send me a list of things to remember!\n"
                + "Type <bye> to exit";
        System.out.println(greetMessage);
    }

    /**
     * Prints out the exit message to the user
     */
    public static void exitProgram(){
        String byeEmoji = new String(Character.toChars(0x1F44B));
        String exitMessage = "Bye. Hope to see you again soon!" + byeEmoji;
        System.out.println(exitMessage);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String logo = " .----------------.  .----------------.  .----------------.  .----------------. \n" +
                "| .--------------. || .--------------. || .--------------. || .--------------. |\n" +
                "| |  ________    | || | _____  _____ | || |  ___  ____   | || |  _________   | |\n" +
                "| | |_   ___ `.  | || ||_   _||_   _|| || | |_  ||_  _|  | || | |_   ___  |  | |\n" +
                "| |   | |   `. \\ | || |  | |    | |  | || |   | |_/ /    | || |   | |_  \\_|  | |\n" +
                "| |   | |    | | | || |  | '    ' |  | || |   |  __'.    | || |   |  _|  _   | |\n" +
                "| |  _| |___.' / | || |   \\ `--' /   | || |  _| |  \\ \\_  | || |  _| |___/ |  | |\n" +
                "| | |________.'  | || |    `.__.'    | || | |____||____| | || | |_________|  | |\n" +
                "| |              | || |              | || |              | || |              | |\n" +
                "| '--------------' || '--------------' || '--------------' || '--------------' |\n" +
                " '----------------'  '----------------'  '----------------'  '----------------' ";
        System.out.println("Hello from\n" + logo);
        greetUser();
        String userInput = "";
        ListManager myList = new ListManager();
        while(!userInput.equals("bye")){
            userInput = input.nextLine();
            if(userInput.equals("list")){
                myList.printList();
            }
            else{
                myList.addToList(userInput);
            }
        }
        exitProgram();
    }
}
