/**
 * This class represents the storage of the task list.
 * It contains the file path of the file that stores the task list.
 * It contains the constructor that takes in the file path and creates a new file if it does not exist.
 * It contains the readFile method that reads the file and returns an arraylist of tasks.
 * It contains the writeFile method that takes in input tasks and taskCount and writes the tasks into the file.
 * @param filePath the file path of the file that stores the task list
 * @param readFile method that reads the file and returns an arraylist of tasks
 * @param writeFile method that takes in input tasks and taskCount and writes the tasks into the file
 * @throws IOException if there is an error reading the file
 */

import java.util.Scanner;  // Import the Scanner class
import java.io.File; // Import the File class
import java.io.FileWriter; // Import the FileWriter class
import java.io.BufferedWriter; // Import the BufferedWriter class
import java.io.IOException; // Import the IOException class to handle errors
import java.util.ArrayList; // Import the ArrayList class

public class Storage {

    //file path
    protected String filePath;

    //constructor that takes in file path and creates a new file if it does not exist
    public Storage(String filePath){
        //set the file path
        this.filePath = filePath;
        //check if there is a file to read from
        try{
            File file = new File(filePath);
            Scanner fileScan = new Scanner(file);
            fileScan.close();
        //otherwise create a new file
        } catch(Exception e){
            //Create a new file and directory
            try{
                File file = new File(filePath);
                file.getParentFile().mkdirs();
                file.createNewFile();
            }catch(Exception e1){
                e1.printStackTrace();
            }
        }
    }
   
    //method that tand reads the file and returns an arraylist of tasks 
    public static ArrayList<Task> readFile(String filePath){
        //new arraylist of tasks
        ArrayList<Task> tasks = new ArrayList<Task>();
  
        //File object
        File file = new File(filePath);

        //try to read the file
        try{
            //Create a scanner
            Scanner fileScan = new Scanner(file);
            //Read the file
            while(fileScan.hasNextLine()){
                //Read the line
                String line = fileScan.nextLine();
                //Split the line
                String[] lineSplit = line.split(" \\| ");
                //if the task is a todo
                if(lineSplit[0].equals("T")){
                    //create a new todo
                    Todo todo = new Todo(lineSplit[2]);
                    //if the task is done
                    if(lineSplit[1].equals("1")){
                        //mark the task as done
                        todo.markAsDone();
                    }
                    //add the task to the arraylist
                    tasks.add(todo);
                }
                //if the task is a deadline
                else if(lineSplit[0].equals("D")){
                    //create a new deadline
                    Deadline deadline = new Deadline(lineSplit[2], lineSplit[3]);
                    //if the task is done
                    if(lineSplit[1].equals("1")){
                        //mark the task as done
                        deadline.markAsDone();
                    }
                    //add the task to the arraylist
                    tasks.add(deadline);
                }
                //if the task is an event
                else if(lineSplit[0].equals("E")){
                    //create a new event
                    Event event = new Event(lineSplit[2], lineSplit[3]);
                    //if the task is done
                    if(lineSplit[1].equals("1")){
                        //mark the task as done
                        event.markAsDone();
                    }
                    //add the task to the arraylist
                    tasks.add(event);
                }
            }
            //close the scanner
            fileScan.close();

        } catch(IOException e){
            System.out.println("Error reading file.");
        }

        return tasks;
    }

    //method that takes in input tasks and taskCount and writes the tasks into the file
    public static void writeFile(ArrayList<Task> tasks, String filePath){
        //Write the tasks to the file
         try{
            //Create a file writer
            FileWriter fw = new FileWriter(filePath);
            //Erase the file
            fw.write("");
            //Create a buffered writer
            BufferedWriter bw = new BufferedWriter(fw);
            //Write the tasks to the file
            for(int i=0;i<tasks.size();i++){
                //if the task is a todo
                if(tasks.get(i) instanceof Todo){
                    //write the task to the file
                    bw.write("T | " + tasks.get(i).isDone + " | " + tasks.get(i).taskName);
                    bw.newLine();
                }
                //if the task is a deadline
                else if(tasks.get(i) instanceof Deadline){
                    //write the task to the file
                    bw.write("D | " + tasks.get(i).isDone + " | " + tasks.get(i).taskName + " | " + ((Deadline) tasks.get(i)).deadline);
                    bw.newLine();
                }
                //if the task is an event
                else if(tasks.get(i) instanceof Event){
                    //write the task to the file
                    bw.write("E | " + tasks.get(i).isDone + " | " + tasks.get(i).taskName + " | " + ((Event) tasks.get(i)).eventTime);
                    bw.newLine();
                }
            } 

            //Close the buffered writer
            bw.close();
    
            //Close the file writer
            fw.close();

        } catch(IOException e){
            System.out.println("Error writing to file.");
        }

    }

}
