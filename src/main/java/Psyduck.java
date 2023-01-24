import java.util.Scanner;

public class Psyduck {
    public static void linePrint(){
        for (long i = 0; i < 100; i++) {
            System.out.print("-");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        String command;
        int taskNum;
        boolean exitProgram = false;
        Scanner in = new Scanner(System.in);
        ToDoList list = new ToDoList();
        linePrint();
        System.out.println("Psyduck! Psy Psy Psyduck! (Hello! How can I help you?)");
        linePrint();
        do {
            command = in.nextLine();
            switch(command) {
            case "bye":
                exitProgram = true;
                break;
            case "exit":
                exitProgram = true;
                break;
            case "list":
                list.listTasks();
                break;
            case "mark":
                taskNum = in.nextInt();
                list.markTask(taskNum);
                break;
            case "unmark":
                taskNum = in.nextInt();
                list.unmarkTask(taskNum);
                break;
            default:
                list.createTask(command);
                linePrint();
                System.out.println("Psyduck has added the task: " + command);
                linePrint();
            }
        } while (exitProgram == false);
        System.out.println("Psyduck! (Buh Bye!)");
        linePrint();
    }
}
