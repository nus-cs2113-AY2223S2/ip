import java.util.Scanner;

public class Duke {

    private static Task[] taskList = new Task[100];
    private static int counter = 0;
    private static final String line = "___________________________________________";

    public static void printList() {
        System.out.println("\t" + line);
        System.out.println("\t Here are the tasks for your list: ");
        for(int i=0; i< counter; i++) {
            System.out.println("\t" + (i+1) + ". [" + taskList[i].getStatusIcon() + "] " +  taskList[i].description);
        }
        System.out.println("\t" + line);
    }

    public static void markTaskAndPrint(int taskIndex) {
        if(taskIndex < 0  || taskList[taskIndex] == null) {
            System.out.println("\t" + line);
            System.out.println("\t Please ensure that you enter the correct task number");
            System.out.println("\t" + line);
            return;
        }
        taskList[taskIndex].isDone = true;
        System.out.println("\t" + line);
        System.out.println("\t Nice, I have marked this task as done: ");
        System.out.println("\t [" + taskList[taskIndex].getStatusIcon() + "] " +  taskList[taskIndex].description);
        System.out.println("\t" + line);
    }

    public static void unmarkTaskAndPrint(int taskIndex) {
        if(taskIndex < 0 || taskList[taskIndex] == null) {
            System.out.println("\t" + line);
            System.out.println("\t Please ensure that you enter the correct task number");
            System.out.println("\t" + line);
            return;
        }
        taskList[taskIndex].isDone = false;
        System.out.println("\t" + line);
        System.out.println("\t Ouch, I have unmarked this task: ");
        System.out.println("\t [" + taskList[taskIndex].getStatusIcon() + "] " +  taskList[taskIndex].description);
        System.out.println("\t" + line);
    }

    public static void main(String[] args) {
        System.out.println("\t" + line);
        System.out.println("\t Hello I'm Duke, your personal chatbot.");
        System.out.println("\t Is there anything I can do for you");
        System.out.println("\t" + line);

        String input = "";
        Scanner in = new Scanner(System.in);

        while(true) {
            input = in.nextLine();

            if (input.equals("bye")) {
                System.out.println("\t" + line);
                System.out.println("\t Bye! Do let me know if you need any further assistance");
                System.out.println("\t" + line);
                break;
            }
            if (input.equals("list")) {
                printList();
                continue;
            } 
            if (input.length() >= 4 && input.substring(0,4).equals("mark")){
                int indexToMark = Integer.parseInt(input.substring(5))-1; //convert to 0-based index
                markTaskAndPrint(indexToMark);
                continue;
            }
            if (input.length() >= 6 && input.substring(0,6).equals("unmark")){
                int indexToUnmark = Integer.parseInt(input.substring(7))-1; //convert to 0-based index
                unmarkTaskAndPrint(indexToUnmark);
                continue;
            }

            taskList[counter] = new Task(input);
            counter++;

            System.out.println("\t" + line);
            System.out.println("\t" + "added: " + input );
            System.out.println("\t" + line);
        }
        
       
    }
}
