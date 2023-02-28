package ui;

import exceptions.FileLineParseException;
import task.*;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Ui {

    public void showLoadingError(){
        System.out.println("LoadingError: Cannot load local file!");
    }
    public void showLine(){
        System.out.println("------------------------------------------------------------\n");
    }
    public void showWelcome(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String greeting_word="------------------------------------------------------------\n"
                + "Hello! I'm miniJohn\n"
                + "What can I do for you?\n"
                + "------------------------------------------------------------";
        System.out.println(greeting_word);
    }

    public void showFileNotFoundException() {
        System.out.println("There is no past record in the disk.");
    }
    public void showFileLineParseException(){
        System.out.println("FileLineParseError: Cannot parse file into taskList.");
    }

    public void showIOException(){
        System.out.println("I/O Error: Cannot write the record to the file");
    }

    public void showTaskIndexFormatError(){
        System.out.println("TaskIndexFormatError: Cannot format your input to a task.Task Index!");
    }

    public void showTaskIndexNotFoundError(){
        System.out.println("TaskIndexNotFoundError: Task index is invalid!");
    }


    public void showNullPointerException(){
        System.out.println("ParamsError: Maybe because you didn't input anything.");
    }

    public void showDeadlineParmsFormat(){
        System.out.println("ParamsError: Please input in the format: [String] /by [time]");
    }

    public void showEventParamsFormat(){
        System.out.println("ParamsError: Please input in the format: [String] /[from] /[to]");
    }

    public void showTaskList(TaskList tasks){
        if(tasks.size() == 0){
            System.out.println("There is nothing in the tasksList.");
            return;
        }
        for(int i = 0; i < tasks.size() ; i++){
            System.out.println((i + 1) + "." + tasks.get(i).toString() + "\n");
        }
    }


    public void showAddTask(Task newTaskObject, TaskList tasks){
        String feedback = "Got it. I've added this task:\n"
                + newTaskObject.toString() + "\n"
                + "Now you have " + tasks.size() +" tasks in the list";
        System.out.println(feedback);
    }

    public void showMarkUnmarkTask(Task taskToMark, boolean isMarkAsDone) {
        String feedback;
        if (isMarkAsDone == true) {
            feedback = "Nice! I've marked this task as done:\n"
                    + taskToMark.toString();
        } else {
            feedback = "OK, I've marked this task as not done yet:\n"
                    + taskToMark.toString();
        }
        System.out.println(feedback);
    }

    public void showTodoTask(Todo newTodoObject, TaskList tasks) {
        String feedback = "Got it. I've added this task:\n"
                + newTodoObject.toString() + "\n"
                + "Now you have " + tasks.size() + " tasks in the list";
        System.out.println(feedback);
    }

    public void showDeadlineTask(Deadline newDeadlineObject, TaskList tasks){
        String feedback =  "Got it. I've added this task:\n"
                + newDeadlineObject.toString() + "\n"
                + "Now you have " + tasks.size() +" tasks in the list";
        System.out.println(feedback);
    }

    public void showEventTask(Event newEventObject, TaskList tasks) {
        String feedback = "Got it. I've added this task:\n"
                + newEventObject.toString() + "\n"
                + "Now you have " + tasks.size() + " tasks in the list";
        System.out.println(feedback);
    }

    public void showDeleteTask(TaskList tasks, int index){
        String feedback = "Noted. I've removed this task:\n"
                + tasks.get(index).toString()
                + "\nNow you have 4 tasks in the list.\n";
        System.out.println(feedback);
    }
    public void showUnknownCommand(String userCommand){
        String feedback = "CommandError: I can't understand \"" + userCommand +"\"!";
        System.out.println(feedback);
    }

    public void showGoodBye(){
        System.out.println("See you again soon!");
    }
    public String readCommand(){
            Scanner in = new Scanner(System.in);
            String line = in.nextLine();
            return line;
    }

    public static void showResultToUser(String feedback){
        if(feedback == ""){
            return;
        }
        //print String feedback line by line
        System.out.println("\t------------------------------------------------------------");
        Scanner scanner = new Scanner(feedback);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            System.out.println('\t'+line);
        }
        scanner.close();
        System.out.println("\t------------------------------------------------------------");
    }
}
