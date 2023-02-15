package duke;

import duke.exceptions.EmptyTaskException;
import duke.exceptions.InvalidCommandException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.util.ArrayList;
import java.util.Scanner;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Duke {

    public static void printBorder() {
        String border = "____________________________________________________________ \n";
        System.out.println(border);
    }

    public static void printGreeting(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String border = "____________________________________________________________ \n";
        String greeting = "Hello! I'm duke.Duke\n" + "What can I do for you?\n";
        System.out.println(logo + border+ greeting);
    }

    //prints exit statement
    public static void printExit(){
        String exit = "Bye. Hope to see you again soon! \n";
        System.out.println(exit);
    }


    //add a new to do
    public static void addTodo(ArrayList<Task> tasks, String name) throws EmptyTaskException {
        if(name.equals(" ")){
            throw new EmptyTaskException();
        }
        tasks.add(new Todo(name, false));
        printBorder();
        System.out.println("added: " + name + "\n");
        printBorder();
    }

    //add a new deadline task
    public static void addDeadline(ArrayList<Task> tasks, String name, String deadline){
        tasks.add(new Deadline(name, deadline, false));
        printBorder();
        System.out.println("added: " + name + "\n");
        printBorder();
    }

    //add a new event
    public static void addEvent(ArrayList<Task> tasks, String name, String start, String end){
        tasks.add(new Event(name, start, end, false));
        printBorder();
        System.out.println("added: " + name + "\n");
        printBorder();
    }

    //print list of duke.tasks
    public static void printList(ArrayList<Task> tasks){
        printBorder();

        int counter = 1;
        for (Task t : tasks) {
            if (t == null) {
                break;
            } else {
                System.out.println(counter + ". " + t);
                counter++;
            }
        }
        System.out.println("You have " + tasks.size() + " tasks in your list.");
        printBorder();
    }

    //mark task as done
    public static void markTask(ArrayList<Task> tasks, int taskIndex){
        tasks.get(taskIndex).setStatus("mark");
        System.out.println("Nice! I've marked this task as done: \n");
        System.out.println(tasks.get(taskIndex) + "\n");
        printBorder();
    }

    //mark task as undone
    public static void unmarkTask(ArrayList<Task> tasks, int taskIndex){
        tasks.get(taskIndex).setStatus("unmark");
        System.out.println("OK, I've marked this task as not done yet: \n");
        System.out.println(tasks.get(taskIndex) + "\n");
        printBorder();
    }

//    delete task
    public static void deleteTask(ArrayList<Task> tasks, int taskIndex){
        Task temp = tasks.get(taskIndex);
        tasks.remove(taskIndex);
        System.out.println("Noted. I've removed this task: \n");
        System.out.println(temp);
        System.out.println("Now you have " + tasks.size() + " tasks in the list." );
        printBorder();
    }

    //write to file
    public static final String filePath = "duke.txt";
    public static void writeToFile(ArrayList<Task> tasks){
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task task : tasks) {
                fw.write(task.toTextFileFormat());
                fw.write('\n');
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public static void readFileData(ArrayList<Task> tasks){
        try {
            File f = new File(filePath); // create a File for the given file path
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            String data;
            while (s.hasNext()) {
                data = s.nextLine();
                addFileDataToList(tasks, data);
            }
        }catch (FileNotFoundException e){
            System.out.println("File not found");
        }
    }

    public static void addFileDataToList(ArrayList<Task> tasks, String data){
        String[] inputData = data.split("/");
        String taskType = inputData[0];
        Task loadedTask = null;
        switch (taskType){
            case "todo":
                loadedTask = new Todo(inputData[1], Boolean.parseBoolean(inputData[2]));
                break;

            case "deadline":
                loadedTask = new Deadline(inputData[1], inputData[3], Boolean.parseBoolean(inputData[2]));
                break;

            case "event":
                loadedTask = new Event(inputData[1], inputData[3], inputData[4], Boolean.parseBoolean(inputData[2]));
        }
        tasks.add(loadedTask);
    }

    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();

        printGreeting();
        printBorder();

        readFileData(tasks);


        Scanner input = new Scanner(System.in);
        String entry = input.nextLine();



        do {

            String[] userInput = entry.split(" ", 2);

            try {


                switch (userInput[0]) {
                    case "list":
                        printList(tasks);
                        entry = input.nextLine();
                        break;


                    case "mark":
                        Integer index = Integer.parseInt(userInput[1]);
                        try{
                            markTask(tasks, index - 1);
                        } catch(NullPointerException e){
                            System.out.println("Error! Specify a valid task index!");
                            printBorder();
                        }
                        entry = input.nextLine();
                        break;


                    case "unmark":
                        Integer index_um = Integer.parseInt(userInput[1]);
                        try{
                            unmarkTask(tasks, index_um - 1);
                        } catch(NullPointerException e){
                            System.out.println("Error! Specify a valid task index!");
                            printBorder();
                        }
                        entry = input.nextLine();
                        break;

                    case "deadline":
                        try {
                            String[] info = userInput[1].split("/by", 2);
                            addDeadline(tasks, info[0], info[1]);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("OOPS! The description of task cannot be empty");
                            printBorder();
                        }
                        entry = input.nextLine();
                        break;


                    case "todo":
                        try {
                            addTodo(tasks, userInput[1]);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("OOPS! The description of task cannot be empty");
                            printBorder();
                        } catch (EmptyTaskException e) {
                            e.printErrorMessage();
                            printBorder();
                        }
                        entry = input.nextLine();
                        break;

                    case "event":
                        try {
                            String[] info_e = userInput[1].split("/from|/to", 3);
                            addEvent(tasks, info_e[0], info_e[1], info_e[2]);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("OOPS! The description of task cannot be empty");
                            printBorder();
                        }
                        entry = input.nextLine();
                        break;

                    case "delete":
                        Integer index_dl = Integer.parseInt(userInput[1]);
                        try {
                            deleteTask(tasks, index_dl - 1);
                        } catch(IndexOutOfBoundsException e){
                            System.out.println("Error! Specify a valid task index!");
                            printBorder();
                        }
                        entry = input.nextLine();
                        break;


                    default:
                        throw new InvalidCommandException();

                }
            } catch(InvalidCommandException e){
                e.printErrorMessage();
                printBorder();
                entry = input.nextLine();
            }
        }while(!entry.equals("bye"));

        writeToFile(tasks);

        printBorder();
        printExit();
        printBorder();
    }
}
