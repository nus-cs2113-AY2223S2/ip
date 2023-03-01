package duke;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;

public class Duke {


    private Storage store;
    private Ui ui;
    private TaskList arrayLL;
    private Parser parser;

    public Duke() {
        this.store = new Storage();
        this.ui = new Ui();
        this.arrayLL = new TaskList();
        this.parser = new Parser(ui, store, arrayLL);
    }

    public static void main(String[] args) throws IOException {

        List<String> arrayList = new ArrayList<String>();
        List<Task> arrayL = new ArrayList<Task>();
        String userText;

        Task[] tasks = new Task[100];
        Duke duke = new Duke();
        duke.ui.printStart();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Please enter items you want to add to the list, if you want to quit enter bye");
            System.out.println("");
            System.out.print("Enter Your command: ");
            userText = sc.nextLine();
            System.out.println("");
            if (userText.equals("bye") || userText.equals("Bye")) {
                System.out.println("Exiting chatbot! Hope to see you again");
                break;
            }

            duke.parser.checkText(userText);
        }
    }
}


    /*private static void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
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
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAppend + "\n");
        fw.close();
    }

    private static void editToFile(String filePath, List tasks) throws IOException{
        for (int i = 0; i < tasks.size(); i++) {
            String todoList = tasks.get(i).toString();
            if(i == 0){
                FileWriter fw = new FileWriter(filePath);
                fw.write(todoList + "\n");
                fw.close();
            }else{
                FileWriter fw = new FileWriter(filePath, true);
                fw.write(todoList + "\n");
                fw.close();
            }
        }
    }
     */

 /*   public static void main(String[] args) throws IOException {
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
        List<Task> tasks = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        while (!line.equals("bye")) {
            System.out.println("____________________________________________________________\n");

            if (line.equals("list") && !tasks.isEmpty()) {
                System.out.println("Here are the tasks in your list: \n ");
                printFileContents(f.getAbsolutePath());
                System.out.println("____________________________________________________________\n");
            }

            if (line.contains("delete") && !tasks.isEmpty()){
                int index = line.indexOf(" ");
                if (!line.equals("delete")) {
                    String str = line.substring(index + 1);
                    int pos = Integer.parseInt(str);
                    System.out.println("Noted! The task has been removed \n");
                    System.out.println("\t" + tasks.get(pos - 1));
                    tasks.remove(pos - 1);
                    System.out.println("You have " + tasks.size() + " number of tasks in you list.");
                    System.out.println("____________________________________________________________\n");
                    editToFile(f.getAbsolutePath(), tasks);
                }

            }else if (line.contains("unmark")) {
                int index = line.indexOf(" ");
                if (!line.equals("unmark")){
                    String str = line.substring(index + 1);
                    int pos = Integer.parseInt(str);
                    Task taskUnmarked = tasks.get(pos - 1);
                    taskUnmarked.setStatusIcon(false);
                    System.out.printf("" + "Okay, I've marked this task as not done yet:\n" + "\t" + " " + "[" +
                            taskUnmarked.getStatusIcon() + "]" + " " + taskUnmarked.description + "\n");
                    System.out.println("____________________________________________________________\n");
                    editToFile(f.getAbsolutePath(), tasks);


                } else{
                    System.out.println(":(( Sorry please drink some coffee and enter valid unmark command");
                    System.out.println("____________________________________________________________\n");
                }

            } else if (line.contains("mark")) {
                int index = line.indexOf(" ");
                if (!line.equals("mark")){
                    String str = line.substring(index + 1);
                    int pos = Integer.parseInt(str);
                    Task taskMarked = tasks.get(pos - 1);
                    taskMarked.setStatusIcon(true);
                    System.out.printf(" " + "Nice! I've marked this task as done:\n" + "\t" + " " + "[" +
                            taskMarked.getStatusIcon() + "]" + " " + taskMarked.description + "\n");
                    System.out.println("____________________________________________________________\n");
                    editToFile(f.getAbsolutePath(), tasks);

                }else{
                    System.out.println(":(( Sorry please drink some coffee and enter valid mark command");
                    System.out.println("____________________________________________________________\n");
                }

            } else if (line.contains("todo")) {
                if (!line.equals("todo")){
                    int index = line.indexOf(" ");
                    String activity = line.substring(index, line.length());
                    Todo toDo = new Todo(activity);
                    tasks.add(toDo);
                    System.out.println("Added: " + "\n" + toDo + "\n");
                    System.out.println("You have " + tasks.size() + " number of tasks in you list.");
                    System.out.println("____________________________________________________________\n");
                    appendToFile(f.getAbsolutePath(), toDo + "\n");

                } else {
                    System.out.println(":(( Drink coffee and enter a valid todo command");
                    System.out.println("____________________________________________________________\n");
                }

            } else if (line.contains("deadline")) {
                if (!line.equals("deadline") && line.contains("/")){
                    int index = line.indexOf(" ");
                    int pos = line.indexOf("/");
                    String activity = line.substring(index, pos - 1);
                    String dueDate = line.substring(pos + 3, line.length());
                    Deadline deadline = new Deadline(activity, dueDate.trim());
                    tasks.add(deadline);
                    System.out.println("Added: " + "\n" + deadline + "\n");
                    System.out.println("You have " + tasks.size() + " number of tasks in you list.");
                    System.out.println("____________________________________________________________\n");
                    appendToFile(f.getAbsolutePath(), deadline + "\n");

                }else{
                    System.out.println(":(( Drink coffee and enter a valid deadline command");
                    System.out.println("____________________________________________________________\n");
                }

            } else if (line.contains("event")) {
                if (!line.equals("event") && line.contains("/")){
                    int index = line.indexOf(" ");
                    int slash = line.indexOf("/");
                    String activity = line.substring(index, slash);
                    String timeFrame = line.substring(slash + 3, line.length());
                    String startDate = timeFrame.substring(0, 11);
                    String endDate = timeFrame.substring(11, timeFrame.length());
                    Event event = new Event(activity, startDate.trim(), endDate.trim());
                    tasks.add(event);
                    System.out.println("Added: " + "\n" + event + "\n");
                    System.out.println("You have " + tasks.size() + " number of tasks in you list.");
                    System.out.println("____________________________________________________________\n");
                    appendToFile(f.getAbsolutePath(), event + "\n");
                }else{
                    System.out.println(":(( Drink coffee and enter a valid event command");
                    System.out.println("____________________________________________________________\n");
                }

            }else if(tasks.isEmpty()){
                System.out.println(":(( The list is empty. Do something in order to have tasks in your list");
                System.out.println("____________________________________________________________\n");

            } else if (!line.equals("list")){
                System.out.println(":(( Sorry enter a valid Duke command");
                System.out.println("____________________________________________________________\n");
            }
            line = in.nextLine();
        }
        System.out.println("____________________________________________________________\n");
        System.out.println(" Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n");
    }
}
*/
