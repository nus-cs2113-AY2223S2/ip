
import java.util.Scanner;

public class Duke {


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        CommandManager.sayHi();
        CommandManager command = new CommandManager();
        command.setUserInput(in.nextLine());
        while (!command.getUserInput().equals("bye")) {
            String[] userInput = command.getUserInput().split(" ", 2);
            String key = userInput[0];
            switch (key) {
            case "mark":
                Tasks markTask =  Tasks.getTaskList().get(Integer.parseInt(userInput[1]) - 1);
                markTask.setMarked(true);
                command.setUserOutput("mark");
                command.printOutput(markTask);
                break;
            case "unmark":
                Tasks unMarkTask = Tasks.getTaskList().get(Integer.parseInt(userInput[1]) - 1);
                unMarkTask.setMarked(false);
                command.setUserOutput("unmark");
                command.printOutput(unMarkTask);
                break;
            case "list":
                command.printOutput();
                break;
            default:
                Tasks newTask = new Tasks(command.getUserInput(), false);
                Tasks.addToList(newTask);
                command.setUserOutput("echo");
                command.printOutput(newTask);
            }
            command.setUserInput(in.nextLine());
        }
        command.sayBye();
    }
}
