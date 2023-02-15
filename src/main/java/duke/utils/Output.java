package duke.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

public class Output {
    public static void printIntroduction() {
        System.out.println("\t____________________________________________________________\r\n"
                + "\t Hello! I'm Duke\r\n"
                + "\t What can I do for you?\r\n"
                + "\t____________________________________________________________\r\n");
    }

    public static void printTaskList(ArrayList<Task> tasks) {
        System.out.println("\t____________________________________________________________\r\n"
                + "\t Here are the tasks in your list:");
        if (tasks.size() == 0) {
            System.out.println("\t No tasks is currently available!");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("\t " + (i + 1) + ". " + tasks.get(i).toString());
            }
        }

        System.out.println("\n\t____________________________________________________________\r\n");
    }

    public static void printNewTaskMessage(Task task) {
        System.out.println("\t____________________________________________________________\r\n"
                + "\t Got it. I've added this task:\r\n"
                + "\t  " + task.toString() + "\r\n"
                + "\t Now you have " + Task.getNumTask() + " tasks in the list.\r\n"
                + "\t____________________________________________________________\r\n");
    }

    public static void printDeleteTaskMessage(Task task) {
        System.out.println("\t____________________________________________________________\r\n"
                + "\t Noted. I've removed this task:\r\n"
                + "\t  " + task.toString() + "\r\n"
                + "\t Now you have " + Task.getNumTask() + " tasks in the list.\r\n"
                + "\t____________________________________________________________\r\n");

    }

    public static void printTaskStatus(Task task) {
        if (task.getStatus()) {
            System.out.println("\t____________________________________________________________\r\n"
                    + "\t Nice! I've marked this task as done:\r\n"
                    + "\t " + task.toString() + "\r\n"
                    + "\t____________________________________________________________\r\n");
        } else {
            System.out.println("\t____________________________________________________________\r\n"
                    + "\t OK, I've marked this task as not done yet:\r\n"
                    + "\t " + task.toString() + "\r\n"
                    + "\t____________________________________________________________\r\n");
        }
    }


    public static void printGoodbye() {
        System.out.println("\t____________________________________________________________\r\n"
                + "\t Bye. Hope to see you again soon!\r\n"
                + "\t____________________________________________________________\r\n");
    }

    public static void printPromptEmptyError(String taskType) {
        System.out.println("\t____________________________________________________________\r\n"
                + "\t OOPS!!! The description of a " + taskType + " cannot be empty.\r\n"
                + "\t____________________________________________________________\r\n");
    }

    public static void printIncorrectFormatError(String taskType) {
        switch (taskType) {
        case "deadline":
            System.out.println("\t____________________________________________________________\r\n"
                    + "\t OOPS!!! Please enter the correct deadline format (deadline xxx /by xxx).\r\n"
                    + "\t____________________________________________________________\r\n");
            break;
        case "event":
            System.out.println("\t____________________________________________________________\r\n"
                    + "\t OOPS!!! Please enter the correct event format (event xxx /from xxx /to xxx).\r\n"
                    + "\t____________________________________________________________\r\n");

            break;
        default:
            System.out.println("\t____________________________________________________________\r\n"
                    + "\t OOPS!!! I'm sorry, but I don't know what that means :-(\r\n"
                    + "\t____________________________________________________________\r\n");
            break;
        }
    }

    public static void printTaskIndexNotIntegerError() {
        System.out.println("\t____________________________________________________________\r\n"
                + "\t OOPS!!! The task index must be an integer.\r\n"
                + "\t____________________________________________________________\r\n");
    }

    public static void printTaskIndexOutOfBoundsError() {
        System.out.println("\t____________________________________________________________\r\n"
                + "\t OOPS!!! The task index is not found.\r\n"
                + "\t____________________________________________________________\r\n");
    }

    public static void printArgumentNullError() {
        System.out.println("\t____________________________________________________________\r\n"
                + "\t OOPS!!! I'm sorry, but I don't know what that means :-(\r\n"
                + "\t____________________________________________________________\r\n");
    }
    
    public static String dateFormatter(LocalDateTime from) {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
      String formattedDate = from.format(formatter);
      return formattedDate;
    }

    public static void printWrongDateFormatError() {
      System.out.println("\t____________________________________________________________\r\n"
                  + "\t OOPS!!! Please enter the correct date format (dd-mm-yyyy hh:mm)\r\n"
                  + "\t____________________________________________________________\r\n");
    }
    
    public static void printTaskByDate(ArrayList<Task> tasks, LocalDateTime date) {
      System.out.println("\t____________________________________________________________\r\n"
                  + "\t Here are the matching tasks in your list:");
      if (tasks.size() == 0) {
              System.out.println("\t No tasks is currently available!");
     } else {
        for (int i = 0; i < tasks.size(); i++) {
          switch(tasks.get(i).getTaskType()) {
            	case "D":
            		if(((Deadline)tasks.get(i)).getEndTime().toLocalDate().equals(date.toLocalDate())) {
            			System.out.println("\t " + (i + 1) + ". " + tasks.get(i).toString());
            		}
            		break;          	
            	case "E":
            		if(((Event)tasks.get(i)).getEndTime().toLocalDate().equals(date.toLocalDate()) || ((Event)tasks.get(i)).getStartTime().toLocalDate().equals(date.toLocalDate())) {
            			System.out.println("\t " + (i + 1) + ". " + tasks.get(i).toString());
            		}
            		break;
            	}
            }
        }
        
        System.out.println("\n\t____________________________________________________________\r\n");
                
    public static void printMatchingTask(ArrayList<Task> tasks, String keyword) {
      System.out.println("\t____________________________________________________________\r\n"
                  + "\t Here are the matching tasks in your list:");
       if (tasks.size() == 0) {
          System.out.println("\t No tasks is currently available!");
       } else {
          for (int i = 0; i < tasks.size(); i++) {
            if(tasks.get(i).getTaskDescription().contains(keyword)) {
                    System.out.println("\t " + (i + 1) + ". " + tasks.get(i).toString());
                  }         	
              }
          }
          
        System.out.println("\n\t____________________________________________________________\r\n");
        
	public static void printWrongDateInputError() {
		System.out.println("\t____________________________________________________________\r\n"
                + "\t OOPS!!! Please enter the correct date format (dd-mm-yyyy)\r\n"
                + "\t____________________________________________________________\r\n");		
	}


}
