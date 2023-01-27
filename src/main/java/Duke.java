import java.util.Scanner;
import java.util.Vector;
public class Duke {
    public static void exitMessage() {
        System.out.println("Go away Anna");
        System.out.println("O-kay bye......");
    }
    public static void main(String[] args) {
        System.out.println("Hi it's Anna!\nWhat do you need to do?");
        Scanner in = new Scanner(System.in);
        String input;
        boolean exit = false;
        while (!exit) {
            input = in.nextLine();
            if (input.equals("bye")) {
                exitMessage();
                exit = true;
            } else if (input.equals("list")){
                ToDoList.viewList();
            } else {
                ToDoList.addItem(input);
            }
        }
    }
}
