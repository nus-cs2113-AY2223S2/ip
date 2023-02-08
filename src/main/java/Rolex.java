import java.util.Scanner;

public class Rolex {

    static Task[] task =  new Task[100];
    static int taskCount = 0;
    public static void printLines(){
        System.out.println("--------------------------------------------------");
    }

    public static void rolexGreetsUser(){
        System.out.println("Hello! I'm ROLEX");
        System.out.println("What can I do for you?\n");
    }

    public static void inputIsBye(){
        printLines();
        System.out.println("Bye. Hope to see you again soon!\n");
        printLines();
    }

    public static void inputIsList(){
        if(taskCount>=1){
            printLines();
            int indexNum = 1;
            System.out.println("Here are the tasks in your list:");
            for(int i = 0; i<taskCount; i++){
                System.out.println(indexNum + ". " + task[i]);
                indexNum++;
            }
            printLines();
        } else{
            RolexException.detectError("list");
        }
    }

    public static void inputIsMark(String userInput){
        int index = Integer.parseInt(userInput.substring(5));
        if(index>0 && index<=taskCount) {
            task[index-1].MarkTask();
        } else{
            RolexException.detectError(userInput);
        }
    }

    public static void inputIsUnmark(String userInput){
        int index = Integer.parseInt(userInput.substring(7));
        if(index>0 && index<=taskCount) {
            task[index-1].unMarkTask();
        } else{
            RolexException.detectError(userInput);
        }
    }

    public static void addPrintTask(){
        printLines();
        System.out.println("Got it. I've added this task:");
        System.out.println(task[taskCount-1]);
        System.out.println("\nNow you have " + taskCount + " tasks in the list.");
        printLines();
    }

    public static void inputIsTodo(String userInput){
        if(userInput.equalsIgnoreCase("todo")){
            RolexException.detectError(userInput);
        } else{
            String todoName = userInput.substring(5);
            task[taskCount] = new Todo(todoName);
            taskCount++;
            addPrintTask();
        }
    }

    public static void inputIsDeadline(String userInput){
        int indexOfBy = userInput.indexOf("/by");
        if(indexOfBy==-1){
            RolexException.detectError(userInput);
        } else{
            String deadlineName = userInput.substring(9,indexOfBy-1);
            String by = userInput.substring(indexOfBy+3);
            task[taskCount] = new Deadline(deadlineName, by);
            taskCount++;
            addPrintTask();
        }
    }

    public static void inputIsEvent(String userInput){
        int indexOfFrom = userInput.indexOf("/from");
        int indexOfTo = userInput.indexOf("/to");
        if(indexOfFrom == -1 || indexOfTo == -1){
            RolexException.detectError(userInput);

        } else{
            String eventName = userInput.substring(6,indexOfFrom-1);
            String startTime = userInput.substring(indexOfFrom+6,indexOfTo-1);
            String endTime = userInput.substring(indexOfTo+4);
            task[taskCount] = new Event(eventName, startTime, endTime);
            taskCount++;
            addPrintTask();
        }
    }

    public static void main(String[] args) {

        rolexGreetsUser();
        Scanner readInput = new Scanner(System.in);

        while(readInput.hasNextLine()){
            String userInput = readInput.nextLine();

            if(userInput.equalsIgnoreCase("bye")){
                inputIsBye();
                System.exit(0);
            } else if(userInput.equalsIgnoreCase("list")){
                inputIsList();
            } else if(userInput.startsWith("mark")){
                inputIsMark(userInput);
            } else if(userInput.startsWith("unmark")){
                inputIsUnmark(userInput);
            } else if(userInput.startsWith("todo")){
                inputIsTodo(userInput);
            } else if(userInput.startsWith("deadline")){
                inputIsDeadline(userInput);
            } else if(userInput.startsWith("event")){
                inputIsEvent(userInput);
            } else{
                RolexException.detectError(userInput);
            }

        } //while() ends here

    } // main() ends here

} // Rolex class ends here