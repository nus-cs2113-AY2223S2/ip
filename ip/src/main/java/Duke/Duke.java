package Duke;

import java.util.Scanner;  // Import the Scanner class
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter; // for saving
import java.io.IOException;


/*
 * Duke is a chatbot program.
 * It can interact with users to perform various tasks such as adding, deleting, and listing tasks.
 * Event details and deadlines can also be stored.
 * Invalid input can be identified as well.
 */


public class Duke {

    public static void drawLine() {  // Draw horizontal lines
        for (int i = 0; i < 30; i++) {
            System.out.print("_");
        }
        System.out.println();
    }

    public static void hiDuke() {  // Print logo and hello message
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        drawLine();
        System.out.println("Hello! I'm Duke Bot");
        System.out.println("What can I do for you?");
        drawLine();
    }

    public static void byeDuke() {  // Print goodbye message
        System.out.println("Bye. Hope to see you again soon!");
        drawLine();
    }

    public static void listTasks(ArrayList<Task> tasks) {  // List out all the tasks
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < tasks.size(); i++) {
            System.out.println((i+1) + ". " + tasks.get(i).getType() + tasks.get(i).toString());
        }
        drawLine();
    }

    public static void markStatus(String input, ArrayList<Task> tasks) {  // Mark tasks as done/undone
        String[] arrOfInput = input.split(" ", 2);
        int index = Integer.parseInt(arrOfInput[1]);
        index = index - 1;

        if(input.startsWith("mark")){
            tasks.get(index).isDone = true;
            System.out.println("Nice! I've marked this task as done:");
        }
        else {
            tasks.get(index).isDone = false;
            System.out.println("OK, I've marked this task as not done yet:");
        }

        System.out.println("   " + tasks.get(index).getType() + tasks.get(index).toString());
        drawLine();
    }

    public static int updateTasks(String input, ArrayList<Task> tasks, int curPos) throws IOException {  // Include remarks
        String[] arrOfInput = input.split(" ", 2);
        System.out.println("Got it. I've added this task:");

        if (arrOfInput[0].equals("todo")) {
            tasks.add(new Task(arrOfInput[1]));
        }
        else if (arrOfInput[0].equals("deadline")) {
            String[] arrOfTask = arrOfInput[1].split("/by");
            tasks.add(new Deadline(arrOfTask[0], arrOfTask[1]));
        }
        else {
            String[] arrOfTask = arrOfInput[1].split("/from");
            String[] arrOfEvent = arrOfTask[1].split("/to");
            tasks.add(new Event(arrOfTask[0], arrOfEvent[0], arrOfEvent[1]));
        }

        System.out.println("   " + tasks.get(curPos).getType() + tasks.get(curPos).toString());
        curPos++;
        System.out.println("Now you have " + curPos + (curPos > 1 ? " tasks " : " task ") + "in the list.");
        saveList(tasks);
        drawLine();
        return curPos;
    }

    // @@[yuanners] [aaronxujiachen]-reused
    // With minor modification
    public static int checkInput(String input, ArrayList<Task> tasks, int curPos) throws DukeException, IOException {

        if(input.length () > 4) {
            if(input.startsWith("mark") || input.startsWith("unmark")) {
                markStatus(input, tasks);
            }
            else if(input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")) {
                curPos = updateTasks(input, tasks, curPos);
            }
            else if(input.startsWith("delete")) {
                curPos = deleteTask (input, tasks, curPos);
            }
            else if(input.startsWith("find")) {
                findTask(input, tasks);
            }
            else if(!((input.startsWith("mark")) || (input.startsWith("unmark")) || (input.startsWith("todo")) || (input.startsWith("deadline")) || (input.startsWith("event")) || (input.startsWith("delete")))) {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            else{
                throw new DukeException("☹ OOPS!!! The description of a " + input + " cannot be empty.");
            }
            
            saveList(tasks);
            
        }
        else {
            if(input.equals("list")) {
                listTasks(tasks);
            }
            /*else if (input.equals("save")) {
            	saveList(tasks);
            }*/
            else if(input.equals("mark") || input.equals("todo")) {
                throw new DukeException("☹ OOPS!!! The description of a " + input + " cannot be empty.");
            }
            else {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }

        return curPos;
    }
    // @@[yuanners]

    public static int deleteTask (String input, ArrayList<Task> tasks, int curPos) {
        String[] arrOfInput = input.split (" ", 2);
        int index = Integer.parseInt(arrOfInput[1]);
        index = index - 1;
        System.out.println("Noted. I've removed this task:");
        System.out.println("	" + tasks.get(index).getType() + tasks.get(index).toString());
        tasks.remove(index);
        curPos--;
        System.out.println("Now you have " + curPos + " tasks in the list.");
        drawLine();
        return curPos;
    }

    public static void saveList (ArrayList<Task> tasks) throws IOException {
    	FileWriter writer = new FileWriter("duke.txt");
        for(int i = 0; i < tasks.size(); i++) {
            //System.out.println((i+1) + ". " + tasks.get(i).getType() + tasks.get(i).toString());
            writer.write((i+1) + ". " + tasks.get(i).getType() + tasks.get(i).toString() + System.lineSeparator());
        }
        writer.close();
    }
    
    public static ArrayList<Task> loadList () throws IOException {
    	BufferedReader bufReader = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/duke.txt"));
    	//String filePath = System.getProperty("user.dir") + "duke.txt";
    	ArrayList<Task> tasks = new ArrayList<Task>();
    	ArrayList<String> list = new ArrayList<>();
    	
    	String line = bufReader.readLine();
    	while(line != null) {
    		list.add(line);
    		line = bufReader.readLine();
    	}
    	
    	bufReader.close();
    	//System.out.println(list.get(0));
    	
    	for(int i = 0; i < list.size(); i++) {
    		
            if (list.get(i).charAt(4) == 'T') {
                tasks.add(new Task(list.get(i).substring(9, list.get(i).length())));
            }
            else if (list.get(i).charAt(4) == 'D') {
                String[] arrOfTask = list.get(i).split("by:");
                tasks.add(new Deadline(list.get(i).substring(9,arrOfTask[0].length()-1), arrOfTask[1].substring(0, arrOfTask[1].length()-1)));
            }
            else if (list.get(i).charAt(4) == 'E') {
                String[] arrOfTask = list.get(i).split("from:");
                String[] arrOfEvent = arrOfTask[1].split("to:");
                tasks.add(new Event(list.get(i).substring(9,arrOfTask[0].length()-1), arrOfEvent[0], arrOfEvent[1].substring(0, arrOfEvent[1].length()-1)));
            }
            else {
            	System.out.println("duke.txt has invalid format");
            	System.exit(0);
            }
        }
    	
    	return tasks;
    }
    
    public static void findTask (String input, ArrayList<Task> tasks) {
    	String[] arrOfInput = input.split (" ", 2);
    	System.out.println("Here are the matching tasks in your list:");
    	int count = 1;
        for(int i = 0; i < tasks.size(); i++) {
        	if (tasks.get(i).toString().contains(arrOfInput[1])) {
        		System.out.println((count) + ". " + tasks.get(i).getType() + tasks.get(i).toString());
        		count++;
        	}
        }
        drawLine();
    }
    
    public static void main(String[] args) throws IOException {
        hiDuke();  //Print Logo
        //loadList();
        ArrayList<Task> tasks = loadList();
        int curPos = tasks.size();
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        
        while(!(input.isEmpty())) {
            drawLine();
            if(input.equals("bye")) {
                break;
            }
            else {
                try {
                    curPos = checkInput(input, tasks, curPos);
                }
                catch(DukeException duEx) {
                    System.out.println(duEx.getMessage());
                    drawLine();
                    continue;
                }
                finally {
                    input = scan.nextLine();
                }
            }
        }

        byeDuke();
    }
}
