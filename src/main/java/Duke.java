import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;
import exception.InvalidArgumentException;
import exception.PromptCannotBeEmptyException;
import Task.Task;
import Task.Todo;
import database.DatabaseManager;
import Task.Deadline;
import Task.Event;
import IO.Input;
import IO.Output;

public class Duke {
    public static void main(String[] args) throws InvalidArgumentException, IOException, PromptCannotBeEmptyException {
        Scanner sc = new Scanner(System.in);
        Output.printIntroduction();
        ArrayList<Task> items = DatabaseManager.FileRead();
        String input = "";

        while (!input.equals("bye")) {
            input = Input.scanWord();
            try{
                int order;
            Task newTask;
            String prompt;
            String description;

            if (input.equals("mark")) {
                order = Input.scanTaskIndex(items.size());
                items.get(order).setMark(true);
                Output.printTaskStatus(items.get(order));

            } else if (input.equals("unmark")) {
                order = Input.scanTaskIndex(items.size());
                items.get(order).setMark(false);
                Output.printTaskStatus(items.get(order));
            } else if (input.equals("list")) {
                Output.printTaskList(items);
            } else if (input.equals("todo")){
                    prompt = Input.scanPrompt("todo");
                    if (prompt != null){
                        newTask = new Todo(prompt.trim());
                    items.add(newTask);
                    }
                   
                
            }
            else if (input.equals("deadline")){
                prompt = Input.scanPrompt("deadline");
                if (prompt != null){
                    int dividerPosition = prompt.indexOf("/by");
                description = prompt.substring(0, dividerPosition).trim();
                String by = prompt.substring(dividerPosition + 3).trim();
                newTask = new Deadline(description, by);
                items.add(newTask);
                }
            }
            else if (input.equals("event")){
                prompt = Input.scanPrompt("event");
                if (prompt != null){
                    int divider1Position = prompt.indexOf("/from");
                    int divider2Position = prompt.indexOf("/to");
                    description = prompt.substring(0, divider1Position).trim();
                    String from = prompt.substring(divider1Position + 5, divider2Position).trim();
                    String to = prompt.substring(divider2Position + 3).trim();
                    newTask = new Event(description, from, to);
                    items.add(newTask);
                }
            }
            else if(input.equals("delete")){
                order = Input.scanTaskIndex(items.size());
                if (order > -1) {
                    Task.setTotal(Task.getTotal() - 1);
                    Output.printDeleteTaskMessage(items.get(order));
                    items.remove(items.get(order));
                }
            }
            else{
                throw new InvalidArgumentException();
            }

            }
            catch(InvalidArgumentException e){
                Output.printArgumentNullError();
            }
            



        }
        DatabaseManager.FIleWrite(items);
        Output.printGoodbye();
    }
}
