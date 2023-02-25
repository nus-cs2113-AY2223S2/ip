package corefunctionalities;

import dataypes.Deadlines;
import dataypes.Events;
import dataypes.Task;
import dataypes.Todos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class handles all the functions dealing with any operation on files. Contains instances of {@link File}
 *
 * @author Muthya Narayanachary Akhil
 */
public class FileHandler {
    public String filePath;
    public File dukeFile;

    /**
     * Constructor for a FileHandler object, creates a new file (if needed) and a new file object to read/write to the
     * file. Prints the initialization sequence for Duke.
     *
     * @param filePath specifies the path to read/write/create the file at.
     */
    public FileHandler(String filePath){
        this.filePath = filePath;
        this.dukeFile = new File(filePath); //just creates a new file object, not a new file
        try {
            dukeFile.createNewFile();
        } catch (IOException e) {
            System.out.println("Whoops, couldn't create/locate file");
        }
        try {
            System.out.print("\033\143");
            Thread.sleep(250);
            System.out.println("Initializing Data collection sites......................");
            Thread.sleep(250);
            System.out.println("Initializing Duke packages..............................");
            Thread.sleep(250);
            System.out.println("Initializing corefunctioanlities........................");
            Thread.sleep(250);
            System.out.println("Initializing Duke.......................................");
            Thread.sleep(250);
            System.out.println("Initializing ExceptionHandler...........................");
            Thread.sleep(250);
            System.out.println("Initializing TaskList...................................");
            Thread.sleep(250);
            System.out.println("Initializing Ui.........................................");
            Thread.sleep(250);
            System.out.println("corefunctionalities complete............................");
            Thread.sleep(250);
            System.out.println("Initializing datatypes..................................");
            Thread.sleep(250);
            System.out.println("Initializing Tasks......................................");
            Thread.sleep(250);
            System.out.println("Initializing Events.....................................");
            Thread.sleep(250);
            System.out.println("Initializing Deadlines..................................");
            Thread.sleep(250);
            System.out.println("Initializing Todos......................................");
            Thread.sleep(250);
            System.out.println("Initializing TaskFileHandler............................");
            Thread.sleep(250);
            System.out.println("datatypes complete......................................");
            Thread.sleep(250);
            System.out.println("Initializing exceptions.................................");
            Thread.sleep(250);
            System.out.println("Initializing DeadlineIsBlank.................................");
            Thread.sleep(100);
            System.out.println("Initializing DeadlineMissingPhrase.................................");
            Thread.sleep(100);
            System.out.println("Initializing DukeException.................................");
            Thread.sleep(100);
            System.out.println("Initializing EmptyDeadline.................................");
            Thread.sleep(100);
            System.out.println("Initializing EmptyEvent.................................");
            Thread.sleep(100);
            System.out.println("Initializing EmptyTodo.................................");
            Thread.sleep(100);
            System.out.println("Initializing EventFromIsBlank.................................");
            Thread.sleep(100);
            System.out.println("Initializing EventMissingBothPhrases.................................");
            Thread.sleep(100);
            System.out.println("Initializing EventMissingForPhrase.................................");
            Thread.sleep(100);
            System.out.println("Initializing EventMissingToPhrase.................................");
            Thread.sleep(100);
            System.out.println("Initializing EventToIsBlank.................................");
            Thread.sleep(100);
            System.out.println("Initializing FromAfterTo.................................");
            Thread.sleep(100);
            System.out.println("Initializing MarkQualityException.................................");
            Thread.sleep(100);
            System.out.println("Initializing UnmarkQualityException.................................");
            Thread.sleep(100);
            System.out.println("Initializing WrongChrono.................................");
            Thread.sleep(100);
            System.out.println("exceptions complete.....................................");
            Thread.sleep(250);
            System.out.println("Initializing helpers....................................");
            Thread.sleep(250);
            System.out.println("Initializing Command....................................");
            Thread.sleep(250);
            System.out.println("Initializing ExceptionGenerator.........................");
            Thread.sleep(250);
            System.out.println("Initializing Parser.....................................");
            Thread.sleep(250);
            System.out.println("helpers complete........................................");
            Thread.sleep(250);
            System.out.println("........................................................");
            Thread.sleep(250);
            System.out.println("........................................................");
            Thread.sleep(250);
            System.out.println("You're all set to go!!\n");
            Thread.sleep(1000);
            System.out.print("\033\143");
            System.out.flush();
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Adds new data to a file, based on the value of <code>toAdd</code>. Makes use of a {@link FileWriter} instance
     * in append mode. Used by {@link helpers.Command} to append new <code>Tasks</code> to the file.
     *
     * @param toAdd A string that dictates what needs to be added to the file.
     * @throws IOException In the event {@link FileHandler#addToFile(String)} is unable to write to the file.
     */
    public void addToFile(String toAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(toAdd);
        fw.close();
    }

    /**
     * Clears the existing file by rewriting the whole file with "". This method is called by {@link FileHandler#populateFile(TaskList)}
     * in the process of updating data in the file.
     *
     * @throws IOException In the event {@link FileHandler#clearFile()} is unable to write to the file.
     */
    public void clearFile() throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write("");
        fw.close();
    }

    /**
     * Draws upon elements of <code>taskList</code> to populate the file. Makes use of {@link FileHandler#clearFile()}
     * to clear the file and {@link TaskList#addTaskListFile(FileHandler)} to update data in the file.
     *
     * @param taskList Represents the data which needs to be updated in the file
     * @throws IOException In the event {@link FileHandler#populateFile(TaskList)} is unable to write to the file.
     */
    public void populateFile(TaskList taskList) throws IOException {
        clearFile();
        taskList.addTaskListFile(this);
    }

    /**
     * Reads data from the file and converts each line into an instance of {@link Task}. Updates the {@link ArrayList<Task>}
     * and returns it. Used by the <code>constructor</code> of {@link TaskList}
     *
     * @return An {@link ArrayList<Task>} containing processed data from the file (if any). If the file is empty,
     * returns an empty {@link ArrayList<Task>}
     * @throws IOException In the event {@link FileHandler#readFile()} is unable to write to the file.
     */
    public ArrayList<Task> readFile() throws IOException{
        ArrayList<Task> arrayList = new ArrayList<Task>();
        if(dukeFile.createNewFile()==true) {
            System.out.println("Returned empty");
            return arrayList;
        }
        Scanner s = new Scanner(dukeFile);
        while(s.hasNext()) {
            String holder = s.nextLine();
            String[] compartments = holder.split(" # ");
            if( compartments[0].equals("T")) {
                Todos temp = new Todos();
                if(compartments[1].equals("false")) {
                    temp.unMarkTask();
                } else {
                    temp.markTask();
                }
                temp.setDescription(compartments[2]);
                arrayList.add(temp);
            } else if ( compartments[0].equals("D")) {
                Deadlines temp = new Deadlines();
                if(compartments[1].equals("false")) {
                    temp.unMarkTask();
                } else {
                    temp.markTask();
                }
                temp.setDescription(compartments[2]);
                temp.setDeadline(compartments[3]);
                arrayList.add(temp);
            } else if( compartments[0].equals("E")) {
                Events temp = new Events();
                if(compartments[1].equals("false")) {
                    temp.unMarkTask();
                } else {
                    temp.markTask();
                }
                temp.setDescription(compartments[2]);
                temp.setFrom(compartments[3]);
                temp.setTo(compartments[4]);
                arrayList.add(temp);
            }
        }
        return arrayList;
    }

}
