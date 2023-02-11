import java.util.Scanner;  // Import the Scanner class
import java.io.File; // Import the File class
import java.io.FileWriter; // Import the FileWriter class
import java.io.BufferedWriter; // Import the BufferedWriter class
import java.io.IOException; // Import the IOException class to handle errors
import java.util.ArrayList; // Import the ArrayList class

public class Storage {
   
    //method that tand reads the file and returns an arraylist of tasks 
    public static ArrayList<Task> readFile(){
        //new arraylist of tasks
        ArrayList<Task> tasks = new ArrayList<Task>();
        //new taskCount
        int taskCount = 0;
        //check if there is a file to read from
        try{
            File file = new File("data/duke.txt");
            Scanner fileScan = new Scanner(file);
            while(fileScan.hasNextLine()){
                String line = fileScan.nextLine();
                String[] lineSplit = line.split(" \\| ");
                if(lineSplit[0].equals("T")){
                    tasks.add(new Todo(lineSplit[2]));
                    if(lineSplit.length > 3){
                        throw new DukeException("Your data file is corrupted");
                    }
                    if(lineSplit[1].equals("true")){
                        tasks.get(taskCount).markAsDone();
                    }
                    taskCount++;
                }else if(lineSplit[0].equals("D")){
                    if(lineSplit.length > 4){
                        throw new DukeException("Your data file is corrupted");
                    }
                    tasks.add(new Deadline(lineSplit[2], lineSplit[3]));
                    if(lineSplit[1].equals("true")){
                        tasks.get(taskCount).markAsDone();
                    }
                    taskCount++;
                }else if(lineSplit[0].equals("E")){
                    if(lineSplit.length > 4){
                        throw new DukeException("Your data file is corrupted");
                    }
                    tasks.add(new Event(lineSplit[2], lineSplit[3]));
                    if(lineSplit[1].equals("true")){
                        tasks.get(taskCount).markAsDone();
                    }
                    taskCount++;
                }
            }
            
            fileScan.close();
        //otherwise create a new file
        } catch(Exception e){
            //Create a new file and directory
            try{
                File file = new File("data/duke.txt");
                file.getParentFile().mkdirs();
                file.createNewFile();
            }catch(Exception e1){
                e1.printStackTrace();
            }
        }

        return tasks;
        
    }

    //method that takes in input tasks and taskCount and writes the tasks into the file
    public static void writeFile(ArrayList<Task> tasks, int taskCount){
        //Write the tasks to the file
         try{
            //Create a file writer
            FileWriter fw = new FileWriter("data/duke.txt");
            //Erase the file
            fw.write("");
            //Create a buffered writer
            BufferedWriter bw = new BufferedWriter(fw);
            //Write the tasks to the file
            for(int i=0;i<taskCount;i++){
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
