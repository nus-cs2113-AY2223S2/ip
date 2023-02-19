package duke;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;

public class Duke {


    private static void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException, IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend + "\n");
        fw.close();
    }
    public static void main(String[] args) throws IOException{
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Duke\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n");


        File f = new File("/Users/navya/Documents/Navya/NUS/data/Duke.txt");
        boolean isPresent = f.exists();
        if (!isPresent) {
            f.getParentFile().mkdir();
            f.createNewFile();
        }

        String line;
        List<Task> tasks = new ArrayList<Task>();
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        while (!line.equals("bye")) {
            System.out.println("____________________________________________________________\n");

            if (line.equals("list") && !tasks.isEmpty()) {
                System.out.println("Here are the tasks in your list: \n ");
                /*for (int i = 0; i < tasks.size(); i++) {
                    Task todoList = tasks.get(i);
                    System.out.println("\t" + (i + 1) + ". " + todoList.toString());
                }
                 */
                printFileContents(f.getAbsolutePath());
                System.out.println("____________________________________________________________\n");
            }
<<<<<<< HEAD
=======

            if (line.contains("delete") && !tasks.isEmpty()){
                int index = line.indexOf(" ");
                if (line.length() > 7) {
                    String str = line.substring(index + 1);
                    int pos = Integer.parseInt(str);
                    System.out.println("Noted! The task has been removed \n");
                    System.out.println("\t" + tasks.get(pos - 1));
                    tasks.remove(pos - 1);
                    System.out.println("You have " + tasks.size() + " number of tasks in you list.");
                    System.out.println("____________________________________________________________\n");
                }
            }
>>>>>>> branch-Level-6

            else if (line.contains("unmark")) {
                int index = line.indexOf(" ");
                if (line.length() > 7){
                    String str = line.substring(index + 1);
                    int pos = Integer.parseInt(str);
                    Task taskUnmarked = tasks.get(pos - 1);
                    taskUnmarked.setStatusIcon(false);
                    System.out.printf("" + "Okay, I've marked this task as not done yet:\n" + "\t" + " " + "[" +
                            taskUnmarked.getStatusIcon() + "]" + " " + taskUnmarked.description + "\n");
                    System.out.println("____________________________________________________________\n");
                }
                else{
                    System.out.println("🤪Sorry please drink some coffee and enter valid unmark command");
                    System.out.println("____________________________________________________________\n");
                }
            }

            else if (line.contains("mark")) {
                int index = line.indexOf(" ");
                if (line.length() > 5){
                    String str = line.substring(index + 1);
                    int pos = Integer.parseInt(str);
                    Task taskMarked = tasks.get(pos - 1);
                    taskMarked.setStatusIcon(true);
                    System.out.printf(" " + "Nice! I've marked this task as done:\n" + "\t" + " " + "[" +
                            taskMarked.getStatusIcon() + "]" + " " + taskMarked.description + "\n");
                    System.out.println("____________________________________________________________\n");
                }
                else{
                    System.out.println("🤪Sorry please drink some coffee and enter valid mark command");
                    System.out.println("____________________________________________________________\n");
                }
            }

            else if (line.contains("todo")) {
                if (line.length() > 5){
                    int index = line.indexOf(" ");
                    String activity = line.substring(index, line.length());
                    Todo toDo = new Todo(activity);
                    tasks.add(toDo);
                    System.out.println("Added: " + "\n" + toDo.toString() + "\n");
                    System.out.println("You have " + tasks.size() + " number of tasks in you list.");
                    System.out.println("____________________________________________________________\n");
                    appendToFile(f.getAbsolutePath(), toDo.toString() + "\n");
                }
                else {
                    System.out.println("🤪Drink coffee and enter a valid todo command");
                    System.out.println("____________________________________________________________\n");
                }
            }

            else if (line.contains("deadline")) {
                if (line.length() > 9 && line.contains("/")){
                    int index = line.indexOf(" ");
                    int pos = line.indexOf("/");
                    String activity = line.substring(index, pos - 1);
                    String dueDate = line.substring(pos + 3, line.length());
                    Deadline deadline = new Deadline(activity, dueDate);
                    tasks.add(deadline);
                    System.out.println("Added: " + "\n" + deadline.toString() + "\n");
                    System.out.println("You have " + tasks.size() + " number of tasks in you list.");
                    System.out.println("____________________________________________________________\n");
                    appendToFile(f.getAbsolutePath(), deadline.toString() + "\n");
                }
                else{
                    System.out.println("🤪Drink coffee and enter a valid deadline command");
                    System.out.println("____________________________________________________________\n");
                }
            }

            else if (line.contains("event")) {
                if (line.length() > 6 && line.contains("/")){
                    int index = line.indexOf(" ");
                    int startDate = line.indexOf("/");
                    String activity = line.substring(index, startDate);
                    String timeFrame = line.substring(startDate + 3, line.length());
                    Event event = new Event(activity, timeFrame);
                    tasks.add(event);
                    System.out.println("Added: " + "\n" + event.toString() + "\n");
                    System.out.println("You have " + tasks.size() + " number of tasks in you list.");
                    System.out.println("____________________________________________________________\n");
                    appendToFile(f.getAbsolutePath(), event.toString() + "\n");
                }
                else{
                    System.out.println("🤪Drink coffee and enter a valid event command");
                    System.out.println("____________________________________________________________\n");
                }
            }

            else if(tasks.isEmpty()){
                System.out.println("🤪The list is empty. Do something in order to have tasks in your list");
                System.out.println("____________________________________________________________\n");
            }

            else if (!line.equals("list")){
                System.out.println("🤪Sorry enter a valid Duke command");
                System.out.println("____________________________________________________________\n");
            }
            line = in.nextLine();
        }
        System.out.println("____________________________________________________________\n");
        System.out.println(" Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n");
    }
}
