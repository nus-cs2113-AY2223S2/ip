package duke;
import duke.exceptions.*;
import org.w3c.dom.Text;

import java.util.Scanner;
import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileOutputStream;
public class Duke {
    private static Task[] tasks = new Task[100];
    private static int numOfTask = 0;
    // Method to list out the tasks stored in the task list
    public static void listTasks(){
        System.out.println("Here are the tasks in your list:");
        for(int i = 1; i <= numOfTask; i+= 1){
            System.out.println(i + "." +"[" + tasks[i-1].getType() + "]" + "[" + tasks[i-1].getStatusIcon() + "] "+ tasks[i-1].getDescription()
            + tasks[i-1].getDeadline() + tasks[i-1].getPeriod());
        }
    }
    private static void printFile(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    public static void replaceSelected(String filePath, String target, String replaceWith) {
        try {
            // input the file content to the StringBuffer "input"
            BufferedReader file = new BufferedReader(new FileReader(filePath));
            StringBuffer inputBuffer = new StringBuffer();
            String line;

            while ((line = file.readLine()) != null) {
                inputBuffer.append(line);
                inputBuffer.append('\n');
            }
            file.close();
            String inputStr = inputBuffer.toString();


            inputStr = inputStr.replace(target, replaceWith);

            // write the new string with the replaced line OVER the same file
            FileOutputStream fileOut = new FileOutputStream(filePath);
            fileOut.write(inputStr.getBytes());
            fileOut.close();

        } catch (Exception e) {
            System.out.println("Problem reading file.");
        }
    }
    public static void readTasks(File f){
        numOfTask = 0;
        try {
            Scanner sc = new Scanner(f);
            while(sc.hasNext()){
                numOfTask += 1;
                String task = sc.nextLine();
                String[] taskSet = task.trim().split("\\|");
                String type = taskSet[0].trim();
                boolean isDone = taskSet[1].trim().equals("1");
                String description = taskSet[2].trim();
                switch(type){
                    case"T":
                        try {
                            ToDo toDoTask = new ToDo(description,isDone);
                            tasks[numOfTask-1] = toDoTask;
                        } catch (ToDoException e) {
                            System.out.println("The description of a todo cannot be empty!");
                        }
                        break;
                    case"D":
                        String deadline = taskSet[3].trim();
                        Deadline deadlineTask = new Deadline(description,isDone, deadline);
                        tasks[numOfTask - 1] = deadlineTask;
                        break;
                    case "E":
                        String fromDate = taskSet[3].trim();
                        String toDate = taskSet[4].trim();
                        try {
                            Event eventTask = new Event(description,isDone, fromDate, toDate);
                            tasks[numOfTask - 1] = eventTask;
                        } catch (EventException e) {
                            System.out.println("To field is empty!");
                        }
                        break;
                    default:
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

    }

    // Method used to filter the command word out of the input sentence
    public static String filterCommand(String sentence){
        String[] words = sentence.split(" ");  // splits into words
        for(String word: words){
            switch(word){
                case "bye":
                    return "bye";
                case "list":
                    return "list";
                case "mark":
                    return "mark";
                case "unmark":
                    return "unmark";
                case "todo":
                    return "todo";
                case "deadline":
                    return "deadline";
                case "event":
                    return "event";
                default:
            }
        }
        return sentence;
    }

    // Method to remove the command word from the input and return the description
    public static String filterDescription(String sentence) {
        String command = filterCommand(sentence);
        String description = sentence.replaceAll(command,"");
        return description.trim();
    }
    // Method to remove the command word, deadline and return the description
    public static String[] filterDescriptionAndDeadline(String sentence){
        String[] output = new String[2];
        String str = filterDescription(sentence);
        int dividerPosition = str.indexOf("/");
        String description = str.substring(0,dividerPosition-1);
        output[0] = description.trim();
        String deadline = str.substring(dividerPosition + 1,str.length());
        String byDate = deadline.replaceAll("by", "");
        output[1] = byDate.trim();
        return output;
    }
    // Method to remove the command word, time period and return the description
    public static String[] filterDescriptionAndTimePeriod(String sentence){
        String[] output = new String[3];
        String str = filterDescription(sentence);
        int dividerPosition = str.indexOf("/");
        String description = str.substring(0,dividerPosition-1);
        output[0] = description.trim();
        String deadline = str.substring(dividerPosition + 1,str.length());
        String fromPeriod = deadline.replaceAll("from", "");
        String fromDate = fromPeriod.substring(0,fromPeriod.indexOf("/"));
        output[1] = fromDate.trim();
        String toPeriod = fromPeriod.substring(fromPeriod.indexOf("/")+1, fromPeriod.length());
        String toDate = toPeriod.replaceAll("to", "");
        output[2] = toDate.trim();
        return output;
    }

     public static boolean shouldExit(String args) throws DukeException {
         String command = filterCommand(args);
         int dividerPosition = args.indexOf(" ");
         String taskNumber = args.substring(dividerPosition + 1, args.length());  //Used for mark and unmark command
         String filePath = "src/duke_list.txt";
         boolean isDone = false;
         switch(command){
             case "bye":
                 System.out.println("Bye. Hope to see you again soon!");
                 return true;
             case "list":
                 listTasks();
                 return false;
             case "mark":
                 int indexMark = Integer.parseInt(taskNumber);
                 String typeM = tasks[indexMark-1].getType();
                 switch(typeM) {
                     case "T":
                         String targetT =  tasks[indexMark-1].getType() + " | " +tasks[indexMark-1].getStatusIconSave() + " | " + tasks[indexMark-1].getDescription();
                         tasks[indexMark-1].markAsDone();
                         String replaceWithT =  tasks[indexMark-1].getType() + " | " + tasks[indexMark-1].getStatusIconSave() + " | " + tasks[indexMark-1].getDescription();
                         replaceSelected(filePath, targetT, replaceWithT);
                         break;
                     case "D":
                         String targetD =  tasks[indexMark-1].getType() + " | " +tasks[indexMark-1].getStatusIconSave() + " | " + tasks[indexMark-1].getDescription() + " | " +
                                 tasks[indexMark-1].getDeadlineSave();
                         tasks[indexMark-1].markAsDone();
                         String replaceWithD =  tasks[indexMark-1].getType() + " | " +tasks[indexMark-1].getStatusIconSave() + " | " + tasks[indexMark-1].getDescription() + " | " +
                                 tasks[indexMark-1].getDeadlineSave();
                         replaceSelected(filePath, targetD,replaceWithD);
                         break;
                     case "E":
                         String targetE =  tasks[indexMark-1].getType() + " | " +tasks[indexMark-1].getStatusIconSave() + " | " + tasks[indexMark-1].getDescription() + " | " +
                                 tasks[indexMark-1].getPeriodSave();
                         tasks[indexMark-1].markAsDone();
                         String replaceWithE =  tasks[indexMark-1].getType() + " | " +tasks[indexMark-1].getStatusIconSave() + " | " + tasks[indexMark-1].getDescription() + " | " +
                                 tasks[indexMark-1].getPeriodSave();
                         replaceSelected(filePath, targetE, replaceWithE);
                         break;
                 }
                 System.out.println("OK, I've marked this task as done:");
                 System.out.println("  [" + tasks[indexMark-1].getStatusIcon() + "] " + tasks[indexMark-1].getDescription());

                 return false;
             case "unmark":
                 int indexUnmark = Integer.parseInt(taskNumber);
                 String typeU = tasks[indexUnmark-1].getType();
                 switch(typeU) {
                     case "T":
                         String targetT =  tasks[indexUnmark-1].getType() + " | " + tasks[indexUnmark-1].getStatusIconSave() + " | " + tasks[indexUnmark-1].getDescription() ;
                         tasks[indexUnmark-1].markAsUndone();
                         String replaceWithT =  tasks[indexUnmark-1].getType() + " | " + tasks[indexUnmark-1].getStatusIconSave() + " | " + tasks[indexUnmark-1].getDescription() ;
                         replaceSelected(filePath, targetT, replaceWithT);
                         break;
                     case "D":
                         String targetD =  tasks[indexUnmark-1].getType() + " | " + tasks[indexUnmark-1].getStatusIconSave() + " | " + tasks[indexUnmark-1].getDescription() + " | " +
                                 tasks[indexUnmark-1].getDeadlineSave();
                         tasks[indexUnmark-1].markAsUndone();
                         String replaceWithD =  tasks[indexUnmark-1].getType() + " | " + tasks[indexUnmark-1].getStatusIconSave() + " | " + tasks[indexUnmark-1].getDescription() + " | " +
                                 tasks[indexUnmark-1].getDeadlineSave();
                         replaceSelected(filePath, targetD,replaceWithD);
                         break;
                     case "E":
                         String targetE =  tasks[indexUnmark-1].getType() + " | " + tasks[indexUnmark-1].getStatusIconSave() + " | " + tasks[indexUnmark-1].getDescription() + " | " +
                                 tasks[indexUnmark-1].getPeriodSave();
                         tasks[indexUnmark-1].markAsUndone();
                         String replaceWithE =  tasks[indexUnmark-1].getType() + " | " + tasks[indexUnmark-1].getStatusIconSave() + " | " + tasks[indexUnmark-1].getDescription() + " | " +
                                 tasks[indexUnmark-1].getPeriodSave();
                         replaceSelected(filePath, targetE, replaceWithE);
                         break;
                 }
                 System.out.println("OK, I've marked this task as not done yet:");
                 System.out.println("  [" + tasks[indexUnmark-1].getStatusIcon() + "] " + tasks[indexUnmark-1].getDescription());
                 return false;
             case "todo":
                 try {
                     ToDo toDoTask = new ToDo(filterDescription(args), isDone);
                     numOfTask += 1;
                     tasks[numOfTask-1] = toDoTask;
                     System.out.println(tasks[numOfTask-1]);
                     System.out.println("Now you have " + numOfTask + " task in the list.");
                     String text = tasks[numOfTask-1].getType() + " | " + tasks[numOfTask-1].getStatusIconSave() + " | "+ tasks[numOfTask-1].getDescription()
                             + System.lineSeparator();
                     appendToFile(filePath, text);
                 } catch (ToDoException e) {
                     System.out.println("The description of a todo cannot be empty!");
                 } catch (IOException e) {
                     System.out.println("Something went wrong: " + e.getMessage());
                 }
                 return false;
             case "deadline":
                 try {
                     String[] deadline = filterDescriptionAndDeadline(args);
                     Deadline deadlineTask = new Deadline(deadline[0],isDone, deadline[1]);
                     numOfTask += 1;
                     tasks[numOfTask - 1] = deadlineTask;
                     System.out.println(tasks[numOfTask - 1]);
                     System.out.println("Now you have " + numOfTask + " task in the list.");
                     String text = tasks[numOfTask-1].getType() + " | " +  tasks[numOfTask-1].getStatusIconSave() + " | " + tasks[numOfTask-1].getDescription()
                             + " | " + tasks[numOfTask-1].getDeadlineSave() + System.lineSeparator();

                     appendToFile(filePath,text);
                 }catch (StringIndexOutOfBoundsException e){
                     System.out.println("The description of deadline and/or date of deadline cannot be empty!");
                 } catch (IOException e) {
                     System.out.println("Something went wrong: " + e.getMessage());
                 }
                 return false;
             case "event":
                 try {
                     String[] event = filterDescriptionAndTimePeriod(args);
                     Event eventTask = new Event(event[0],isDone, event[1], event[2]);
                     numOfTask += 1;
                     tasks[numOfTask - 1] = eventTask;
                     System.out.println(tasks[numOfTask - 1]);
                     System.out.println("Now you have " + numOfTask + " task in the list.");
                     String text =tasks[numOfTask-1].getType() + " | " + tasks[numOfTask-1].getStatusIconSave() + " | " + tasks[numOfTask-1].getDescription()
                             + " | " + tasks[numOfTask-1].getPeriodSave() + System.lineSeparator();
                     appendToFile(filePath,text);
                 }catch(StringIndexOutOfBoundsException e){
                     System.out.println("The description of the event and/or period of it cannot be empty!");
                 }catch(EventException e){
                     System.out.println("To field is empty!");
                 } catch (IOException e) {
                     System.out.println("Something went wrong: " + e.getMessage());
                 }
                 return false;
             default:
                 throw new DukeException();
         }
     }

    public static void main(String[] args) {
        Greetings welcome = new Greetings();
        System.out.println(welcome);
        File list = new File("src/duke_list.txt");
        readTasks(list);
        try {
            System.out.println("Here's the current state of your list");
            printFile("src/duke_list.txt");
            Scanner sc = new Scanner(System.in);
            String in = sc.nextLine();
            while (shouldExit(in) == false) {
                in = sc.nextLine();
            }
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("This task number does not exist!");
        } catch (DukeException e){
            System.out.println("Sorry I don't understand what this means!");
        } catch (FileNotFoundException e){
            System.out.println("Sorry this file does not exist!");
        }
    }
}
