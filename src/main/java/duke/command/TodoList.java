package duke.command;

import duke.task.Deadline;
import duke.task.Task;
import duke.task.Todo;
import duke.task.Event;
import duke.DukeException;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;

public class TodoList {
    
    private static final int MAXLISTNUM = 100;
    private static final String SPLITTER = "    ____________________________________________________________";
    private static final String DATA_PATH = "data/duke.txt";
    private ArrayList<Task> tasks = new ArrayList<>();
    private int listnum;

    public TodoList() throws IOException, DukeException{
        listnum = 0;
        this.loadList();
    }

    public int getListnum(){
        return listnum;
    }

    public int addItem(Task task) throws DukeException{
        if(listnum < MAXLISTNUM){
            tasks.add(task);
            listnum++;
            System.out.println(SPLITTER);
            System.out.println("    " + "Got it. I've added this task:");
            System.out.println("      " + task.toString());
            System.out.println("    " + "Now you have " + listnum + " task" + ((listnum>1) ? "s" : "") + " in the list.");
            System.out.println(SPLITTER);
            System.out.println();
            
            // save to file
            try {
                this.saveList();
            } catch (IOException e) {
                e.printStackTrace();
                return 0;
            }
            return 1;
        }else{
            throw new DukeException("This task does not exist!");
        }
    }

    public int deleteItem(int num) throws DukeException{
        if(tasks.size() > num){
            System.out.println(SPLITTER);
            System.out.println("    " + "Noted. I've removed this task:");
            System.out.println("      " + tasks.get(num));
            System.out.println("    " + "Now you have " + --listnum + " task" + ((listnum>1) ? "s" : "") + " in the list.");
            System.out.println(SPLITTER);
            System.out.println();
            tasks.remove(num);

            // save to file
            try {
                this.saveList();
            } catch (IOException e) {
                e.printStackTrace();
                return 0;
            }
            return 1;
        }else{
            throw new DukeException("This task does not exist!");
        }
    }

    public int markItem(int num, boolean mark) throws DukeException{
        if(num < listnum && num >= 0){
            tasks.get(num).mark(mark);
            if(mark){
                System.out.println(SPLITTER);
                System.out.println("    Nice! I've marked this task as done:");
            }else{
                System.out.println(SPLITTER);
                System.out.println("    OK, I've marked this task as not done yet:");
            }
            System.out.print("      ");
            System.out.println(tasks.get(num));
            System.out.println(SPLITTER);
            System.out.println();

            // save to file
            try {
                this.saveList();
            } catch (IOException e) {
                e.printStackTrace();
                return 0;
            }
            return 1;
        }else{
            throw new DukeException("Index out of bound!");
        }
    }

    public void showList(){
        System.out.println(SPLITTER);
        for(int i = 0; i < listnum; i++){
            System.out.println("    " + (i + 1) + ". " + tasks.get(i));
        }
        System.out.println(SPLITTER);
        System.out.println();
    }

    public static void accessFile(File f) throws IOException{
        // create file `f` if it does not exist
        if(!f.exists()){
            if(!f.getParentFile().exists()){
                f.getParentFile().mkdirs();
            }
            f.createNewFile();
        }
    }

    public void saveList() throws IOException{
        File listFile = new File(DATA_PATH);

        accessFile(listFile);

        FileWriter saver = new FileWriter(listFile);
        for(Task task : tasks){
            // construct one line in file
            String line = task.getTypeIcon() + " | " 
                        + (task.getStatusIcon().equals("X") ? "1" : "0") + " | " 
                        + task.getDescription();
            if(task.getTypeIcon() == 'D' || task.getTypeIcon() == 'E'){
                line += " | " + task.getTimeBound();
            }

            saver.append(line + System.lineSeparator());
        }
        saver.close();
    }

    public void loadList() throws IOException, DukeException{
        File listFile = new File(DATA_PATH);

        accessFile(listFile);

        Scanner in = new Scanner(listFile);
        while(in.hasNext()){
            String line = in.nextLine();
            Task task;

            int typeIdx = line.indexOf(" | ");
            String type = line.substring(0, typeIdx);

            int doneIdx = line.indexOf(" | ", typeIdx + " | ".length());
            String done = line.substring(typeIdx + " | ".length(), doneIdx);

            if(type.equals("T")){
                String desc = line.substring(doneIdx + " | ".length());
                task = new Todo(desc);
            }else if(type.equals("D")){
                int descIdx = line.indexOf(" | ", doneIdx + " | ".length());
                String desc = line.substring(doneIdx + " | ".length(), descIdx);
                
                String by = line.substring(descIdx + " | ".length());
                task = new Deadline(desc, by);
            }else if(type.equals("E")){
                int descIdx = line.indexOf(" | ", doneIdx + " | ".length());
                String desc = line.substring(doneIdx + " | ".length(), descIdx);

                int fromIdx = line.indexOf("-", descIdx + " | ".length());
                String from = line.substring(descIdx + " | ".length(), fromIdx);

                String to = line.substring(fromIdx + "-".length());
                task = new Event(desc, from, to);
            }else{
                in.close();
                throw new DukeException("Unknown task type!");
            }

            task.setIsDone(done.equals("1") ? true : false);
            tasks.add(task);
            listnum++;
            // this.addItem(task);
        }
        in.close();
    }
}
