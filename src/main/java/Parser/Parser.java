package Parser;

import TaskList.ProcessLine;
import Ui.Print;
import duke.Task;
import java.util.ArrayList;


public class Parser {

  public Parser () {

  }

  /**
   * Processes the input and follows the command.
   *
   * @param line is the user's input
   * @param tasks is the task list
   * @param i is the number of tasks in the task list currently
   * @return the updated number of tasks in the task list currently
   */
  public static int Processor(String line, ArrayList<Task> tasks, int i) {
    ProcessLine ProcessLine = new ProcessLine(line,tasks);
    Print ui;
    ui = new Print();

    if (line.toLowerCase().contains("unmark") || (line.toLowerCase().contains("mark")) || line.toLowerCase().contains("delete")) {
      String[] find_index = line.split(" ");

      int index_for_edit=Integer.parseInt(find_index[1]);
      i=ProcessLine.editTask(index_for_edit, tasks, i);

    } else if (line.toLowerCase().contains("todo") || line.toLowerCase().contains("deadline") || line.toLowerCase().contains("event")) {

      i=ProcessLine.createTask(i,line);

    } else if (line.toLowerCase().contains("find")) {
      String[] findWord=line.split(" ");
      ProcessLine.find(i,findWord[1]);

    } else if (line.equalsIgnoreCase("list")) {

      ui.List(tasks, i);

    } else {
      ui.wrongInput();
    }
    return i;

  }

}