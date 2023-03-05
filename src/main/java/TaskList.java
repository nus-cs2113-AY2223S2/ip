import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {
    public static ArrayList<Task> list;

    /**
     * Read the file and convert to taskList
     * @param filePath the filepath of the data location
     */
    public TaskList(String filePath) throws IOException{
        list=new ArrayList<>();
        File myObj = new File(filePath);
        try {
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                Task t=null;
                String type=data.substring(1,2);
                String mark=data.substring(4,5);
                if(type.equals("T")){
                    String context=data.substring(7);
                    t=new Todo(context);
                } else if (type.equals("D")) {
                    String context=data.substring(7,data.indexOf("(")-1);
                    String by=data.substring(data.indexOf("by")+3,data.indexOf(")"));
                    t=new Deadline(context,by);
                } else if (type.equals("E")) {
                    String context=data.substring(7,data.indexOf("(")-1);
                    String from=data.substring(data.indexOf("from")+5,data.indexOf("to"));
                    String to=data.substring(data.indexOf("to")+3,data.indexOf(")"));
                    t=new Event(context,from,to);
                }
                if(mark.equals("X")){
                    t.markAsDone();
                }
                list.add(t);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            myObj.createNewFile();

        }
    }
    public static void taskListAdd(Task t) {
        list.add(t);
        System.out.println("Got it. I've added this task:");
        System.out.println('\t' + t.toString());
        System.out.println("Now you have " + list.size() + " tasks in the list." + '\n' + UI.lineBreak);
    }

    public static void taskListDelete(int t) {
        System.out.println("Noted. I've removed this task:");
        System.out.println('\t' + list.get(t).toString());
        list.remove(t);
        System.out.println("Now you have " + list.size() + " tasks in the list." + '\n' + UI.lineBreak);
    }
}
