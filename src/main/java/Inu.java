import java.util.Scanner;

public class Inu {

    public TaskList taskList;

    public Inu() {

        TaskList taskList = new TaskList();

        Ui.printGreeting();
        UserCommands.parseCommand(taskList);
        Ui.printFarewell();

    }

    public static void main(String[] args) {

        new Inu();

    }
}
