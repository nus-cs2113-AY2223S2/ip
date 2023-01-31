import java.util.Arrays;
import java.util.Scanner;

public class Limey {
    public static final int MAX_NUM_TASKS = 100;
    public static void main(String[] args) {
        Task[] tasks = new Task[MAX_NUM_TASKS];
        //numTasks = 0;
        String inLine;
        String[] wordList;
        Speech.sayHi();
        Scanner in = new Scanner(System.in);
        inLine = in.nextLine().trim();
        wordList = Parser.splitInput(inLine);
        String firstWord = wordList[0];
        int firstSpace = inLine.indexOf(' ');
        //loop until input 'bye'
        while (!firstWord.equals("bye")) {
            //switch case to decide what to do
            switch (firstWord) {
            case "list":
                Speech.printTaskList(tasks, Task.numTasks);
                break;
            case "mark":
                int taskIndex = -1;
                if(wordList.length > 1) { //input has at least 2 space separated strings
                    inLine = inLine.substring(firstSpace + 1);
                    taskIndex = Integer.parseInt(inLine) - 1;
                }
                if (taskIndex >= 0 && taskIndex + 1 <= Task.numTasks) {
                    tasks[taskIndex].setDone(true);
                    Speech.printMarked(tasks[taskIndex]);
                } else {
                    Speech.invalidMessage();
                }
                break;
            case "unmark":
                taskIndex = -1;
                if(wordList.length > 1) { //input has at least 2 space separated strings
                    inLine = inLine.substring(firstSpace + 1);
                    taskIndex = Integer.parseInt(inLine) - 1;
                }
                if (taskIndex + 1 <= Task.numTasks) {
                    tasks[taskIndex].setDone(false);
                    Speech.printUnmarked(tasks[taskIndex]);
                } else {
                    Speech.invalidMessage();
                }

                break;
            default:
                makeNewTask(tasks, inLine, firstWord);
                break;
            }
            //update new line for next iteration
            inLine = in.nextLine().trim();
            wordList = Parser.splitInput(inLine);
            firstWord = wordList[0];
            firstSpace = inLine.indexOf(' ');
        }
        Speech.sayBye();
    }

    private static void makeNewTask(Task[] tasks,String inLine, String firstWord) {
        Task taskIn;
        switch (firstWord) {
        case "deadline":
            taskIn = new Deadline(inLine);
            break;
        case "event":
            taskIn = new Event(inLine);
            break;
        default: // currently default create a todo object
            taskIn = new Todo(inLine);
            break;
        }
        tasks[Task.numTasks] = taskIn;
        Task.numTasks++;
        Speech.printAdded(taskIn, Task.numTasks);
    }


}

