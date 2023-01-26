import java.util.Scanner;

public class Limey {
    public static void main(String[] args) {
        Task[] tasks = new Task[100];
        int numTasks = 0;
        String lineIn;

        Speech.sayHi();
        Scanner in = new Scanner(System.in);
        lineIn = in.nextLine();
        lineIn = lineIn.trim();
        int i = lineIn.indexOf(' ');
        String firstWord;
        if(i!=-1){
            firstWord = lineIn.substring(0, i);
        }
        else {
            firstWord = lineIn;
        }
        //loop until bye
        while(!firstWord.equals("bye")) {
            //switch case to decide what to do
            switch(firstWord) {
            case "list":
                Speech.printTaskList(tasks, numTasks);
                break;
            case "mark":
                lineIn = lineIn.substring(i+1);
                lineIn = lineIn.trim();
                int taskIndex = Integer.parseInt(lineIn) - 1;
                tasks[taskIndex].setDone(true);
                Speech.printMarked(tasks[taskIndex]);
                break;
            case "unmark":
                lineIn = lineIn.substring(i+1);
                lineIn = lineIn.trim();
                int taskInd = Integer.parseInt(lineIn) - 1;
                tasks[taskInd].setDone(false);
                Speech.printUnmarked(tasks[taskInd]);
                break;
            default:
                Task taskIn = new Task(lineIn);
                tasks[numTasks] = taskIn;
                Speech.printAdded(lineIn);
                numTasks++;
                break;
            }
            //update new line for next iteration
            lineIn = in.nextLine();
            lineIn = lineIn.trim();
            i = lineIn.indexOf(' ');
            if(i!=-1){
                firstWord = lineIn.substring(0, i);
            }
            else {
                firstWord = lineIn;
            }
        }
        Speech.sayBye();
    }

}

