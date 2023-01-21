import java.util.Scanner;
public class Duke {

    /**
     * Prints out the greeting message to the user
     */
    public static void greetUser(){
        String greetMessage = "Hello! I'm Duke\n"
                + "I will echo whatever you type\n"
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

    /**
     * Prints out the echoed message to user
     */
    public static void echoUserInput(String userInput){
        System.out.println(userInput);
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
        while(!userInput.equals("bye")){
            userInput = input.nextLine();
            echoUserInput(userInput);
        }
        exitProgram();
    }
}
