import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greetLine();
        addList();
        exitLine();
    }
    public static void greetLine(){
        System.out.println("How may I be of service?");
    }
    public static void echo()
    {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while(!line.equals("bye")) {
            System.out.println(line);
            line = in.nextLine();
        }
    }
    public static void exitLine(){
        System.out.println("Glad I could be of help!");
    }

    public static void addList()
    {
        String[] list = new String[100];
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        int numOfItems = 0;
        while(!line.equals("bye")) {
            if(line.equals("list")) { // users wants to know all text so far
                printCurrentList(list, numOfItems);
            }
            else if(line.startsWith("mark")){
                mark(line, list);
                printCurrentList(list, numOfItems);
            }
            else if(line.startsWith("unmark")){
                unmark(line,list);
                printCurrentList(list, numOfItems);
            }
            else { // text from user
                String item = (numOfItems + 1) + ".[ ] " + line; // convert to 1-based
                list[numOfItems] = item;
                ++numOfItems;
            }
            line = in.nextLine(); // read in next line of text
        }
    }

    public static void mark(String task, String[] list)
    {
        String locationOfTask = task.substring(5); // get the number of task to be marked
        String taskToBeMarked = list[Integer.parseInt(locationOfTask) - 1];
        String taskMarked = taskToBeMarked.replace("[ ]", "[X]");
        list[Integer.parseInt(locationOfTask) - 1] = taskMarked;
        System.out.println("Sir, your task has been marked as completed.");
    }

    public static void unmark(String task, String[] list)
    {
        String locationOfTask = task.substring(7);
        String taskToBeUnmarked = list[Integer.parseInt(locationOfTask) - 1];
        String taskUnmarked = taskToBeUnmarked.replace("[X]", "[ ]");
        list[Integer.parseInt(locationOfTask) - 1] = taskUnmarked;
        System.out.println("Sir, your task has been unmarked as requested.");
    }

    public static void printCurrentList(String[] list, int numOfItems)
    {
        String[] subList = Arrays.copyOf(list, numOfItems);
        for(int i = 0; i < subList.length; ++i)
        {
            System.out.println(subList[i]);
        }
    }
}
