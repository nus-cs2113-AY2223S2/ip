
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;



public class Buddy {
    
    public static int taskCount = 0;


    public static void main(String[] args) throws IOException {
        TaskList taskList = new TaskList();
        try {
            Storage.loadFile(taskList);

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            Storage.createFile();

        }

        System.out.println(Messages.DIVIDER);
        System.out.println(Messages.GREETING);
        System.out.println(Messages.INTRODUCTION);
        System.out.println(Messages.DIVIDER);


        String input;
        Scanner in = new Scanner(System.in);
        input = in.nextLine();
        Parser processAllCommands = new Parser();

        while (! processAllCommands.isExit(input)) {
            processAllCommands.executeLine(taskList, input);
            input = in.nextLine();
        }
        System.out.println(Messages.DIVIDER);
        System.out.println(Messages.EXITMESSAGE);
        System.out.println(Messages.DIVIDER);

    }
}
