import java.util.Scanner;

public class Duke {

    private static Task[] taskList = new Task[100];
    private static int counter = 0;
    private static final String LINE = "___________________________________________";

    public static void printGreeting() {
        System.out.println("\t" + LINE);
        System.out.println("\t Hello I'm Duke, your personal chatbot.");
        System.out.println("\t Is there anything I can do for you");
        System.out.println("\t" + LINE);
    }
    public static void printList() {
        System.out.println("\t" + LINE);
        System.out.println("\t Here are the tasks for your list: ");
        for(int i=0; i< counter; i++) {
            System.out.println("\t " + (i+1) + "." + taskList[i]  +  taskList[i].description);
        }
        System.out.println("\t" + LINE);
    }

    public static void printTask(Task task) {
        System.out.println("\t" + LINE);
        System.out.println("\t Got it. I've added this task:");
        System.out.println("\t  " + task);
        System.out.println("\t Now you have " + counter + " tasks in your list.");
        System.out.println("\t" + LINE);
    }

    public static void printBye() {
        System.out.println("\t" + LINE);
        System.out.println("\t Bye! Do let me know if you need any further assistance");
        System.out.println("\t" + LINE);
    }
    public static void markTaskAndPrint(int taskIndex) {
        if(taskIndex < 0  || taskList[taskIndex] == null) {
            System.out.println("\t" + LINE);
            System.out.println("\t Please ensure that you enter the correct task number");
            System.out.println("\t" + LINE);
            return;
        }
        taskList[taskIndex].isDone = true;
        System.out.println("\t" + LINE);
        System.out.println("\t Nice, I have marked this task as done: ");
        System.out.println("\t [" + taskList[taskIndex].getStatusIcon() + "] " +  taskList[taskIndex].description);
        System.out.println("\t" + LINE);
    }

    public static void unmarkTaskAndPrint(int taskIndex) {
        if(taskIndex < 0 || taskList[taskIndex] == null) {
            System.out.println("\t" + LINE);
            System.out.println("\t Please ensure that you enter the correct task number");
            System.out.println("\t" + LINE);
            return;
        }
        taskList[taskIndex].isDone = false;
        System.out.println("\t" + LINE);
        System.out.println("\t Ouch, I have unmarked this task: ");
        System.out.println("\t [" + taskList[taskIndex].getStatusIcon() + "] " +  taskList[taskIndex].description);
        System.out.println("\t" + LINE);
    }

    public static void main(String[] args) {
        printGreeting();
        String input;
        Scanner in = new Scanner(System.in);

        while(true) {
            input = in.nextLine();

            if (input.equals("bye")) {
                printBye();
                break;
            }
            if (input.equals("list")) {
                printList();
                continue;
            }

            int spaceIndex=  input.indexOf(' ');
            String firstWord = input.substring(0,spaceIndex);

            if (firstWord.equals("mark")){
                int indexToMark = Integer.parseInt(input.substring(5))-1; //convert to 0-based index
                markTaskAndPrint(indexToMark);
                continue;
            }
            if (firstWord.equals("unmark")){
                int indexToUnmark = Integer.parseInt(input.substring(7))-1; //convert to 0-based index
                unmarkTaskAndPrint(indexToUnmark);
                continue;
            }


            String remainingWords = input.substring(spaceIndex);
            String[] taskInfoArr = remainingWords.split(" /",3);
            String taskDescription = taskInfoArr[0];


            if (firstWord.equals("todo")) {
                Task newTask = new Task(taskDescription);
                taskList[counter] = newTask;
                counter++;
                printTask(newTask);
                continue;
            }

            if (firstWord.equals("deadline" )) {
                String dueBy = taskInfoArr[1].substring(3);
                Deadline newDeadline = new Deadline(taskDescription, dueBy);
                taskList[counter] = newDeadline;
                counter++;
                printTask(newDeadline);
                continue;
            }

            if (firstWord.equals("event")) {
                String fromStr = taskInfoArr[1].substring(5);
                String toStr = taskInfoArr[2].substring(3);
                Event newEvent = new Event(taskDescription, fromStr, toStr);
                taskList[counter] = newEvent;
                counter++;
                printTask(newEvent);
                continue;
            }
        }
    }
}
