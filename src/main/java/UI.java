import java.util.Scanner;

public class UI {



    private Scanner scanner;

    public UI() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    static void printIntro() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello I am Duke your personal life assistant \n" + logo);
        System.out.println("What can I do for you today?");
    }

    static void printSize() {
        System.out.println("Now you have " + TasksList.tasks.size() + " tasks in your list");
    }

    static void printAddMark() {
        System.out.println("You are crushing it,1 task down!");
    }
    static void printUnmark(){
        System.out.println("I have unchecked it for you");
    }
    static void printDeleteMessage(){
        System.out.println("Noted. I've removed this task:");
    }

     static void printMarking(int i) {
        System.out.println(String.format(" [%s] [%s] %s",
                TasksList.tasks.get(i).getTypeIcon(), TasksList.tasks.get(i).getStatusIcon(), TasksList.tasks.get(i).getDescription()));
    }



    static void printBye(){
        System.out.println("Bye see you again!");
    }

    public void showErrorMessage(String error) {
        System.out.println("Error: " + error);
    }

}
