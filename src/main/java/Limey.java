import java.util.Scanner;

public class Limey {
    public static final int MAX_NUM_TASKS = 100;

    public static void main(String[] args) {
        //initialise variables
        Task[] tasks = new Task[MAX_NUM_TASKS];
        String inLine;
        String firstWord;
        String[] wordList;
        int taskIndex;
        boolean isValidInt;
        boolean isValidIndex;

        //start user interface
        Speech.sayHi();
        Scanner in = new Scanner(System.in);
        inLine = in.nextLine().trim();
        wordList = Parser.splitInput(inLine);
        firstWord = Parser.getFirstWord(inLine);

        //loop until input 'bye'
        while (!firstWord.equals("bye")) {
            //switch case to decide what to do
            switch (firstWord) {
            case "list":
                Speech.printTaskList(tasks, Task.numTasks);
                break;
            case "mark":
                if (wordList.length > 1) { //input has at least 2 space separated strings
                    inLine = wordList[1];
                    isValidInt = Parser.isParsable(inLine);
                    if (!isValidInt) {
                        Speech.invalidMessage();
                        break;
                    }
                    taskIndex = Integer.parseInt(inLine) - 1;
                    isValidIndex = (taskIndex >= 0 && taskIndex < Task.numTasks);
                    if (!isValidIndex) {
                        Speech.invalidMessage();
                        break;
                    }
                    tasks[taskIndex].setDone(true);
                    Speech.printMarked(tasks[taskIndex]);
                }
                break;
            case "unmark":
                inLine = wordList[1];
                isValidInt = Parser.isParsable(inLine);
                if (!isValidInt) {
                    Speech.invalidMessage();
                    break;
                }
                taskIndex = Integer.parseInt(inLine) - 1;
                isValidIndex = (taskIndex >= 0 && taskIndex < Task.numTasks);
                if (!isValidIndex) {
                    Speech.invalidMessage();
                    break;
                }
                tasks[taskIndex].setDone(false);
                Speech.printUnmarked(tasks[taskIndex]);
                break;
            default:
                makeNewTask(tasks, inLine, firstWord);
                break;
            }
            //update new line for next iteration
            inLine = in.nextLine().trim();
            wordList = Parser.splitInput(inLine);
            firstWord = wordList[0];
        }
        Speech.sayBye();
    }

    private static void makeNewTask(Task[] tasks, String inLine, String firstWord) {
        Task taskIn;
        switch (firstWord) {
        case "deadline":
            taskIn = new Deadline(inLine);
            break;
        case "event":
            taskIn = new Event(inLine);
            break;
        case "todo": // currently default create a todo object
            taskIn = new Todo(inLine);
            break;
        default:
            Speech.invalidMessage();
            return;
        }
        tasks[Task.numTasks] = taskIn;
        Task.numTasks++;
        Speech.printAdded(taskIn, Task.numTasks);
    }
}

