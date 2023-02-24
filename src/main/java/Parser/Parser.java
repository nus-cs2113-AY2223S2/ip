package Parser;

import TaskList.ProcessLine;
import Ui.Print;
import duke.Task;
import java.util.ArrayList;

//Processes user input
public class Parser {

  protected String line;

  public Parser (String line){
    this.line=line;
  }

  public static int Processor(String filepath,String line, ArrayList<Task> tasks, int i) {
    ProcessLine ProcessLine = new ProcessLine(line, tasks);

    if (line.toLowerCase().contains("unmark") || (line.toLowerCase().contains("mark")) || line.toLowerCase().contains("delete")) {
      String[] find_index = line.split(" ");

      int index_for_edit=Integer.parseInt(find_index[1]);
      i=ProcessLine.editTask(filepath,index_for_edit, tasks, i);

    } else if (line.toLowerCase().contains("todo") || line.toLowerCase().contains("deadline") || line.toLowerCase().contains("event")) {

      i=ProcessLine.createTask(i,line);

    } else if(line.equalsIgnoreCase("list")){

      Print ui;
      ui=new Print(line);
      ui.List(tasks,i);

    }
    return i;

  }

}