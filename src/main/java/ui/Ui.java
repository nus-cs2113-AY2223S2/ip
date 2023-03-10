package ui;

import task.*;

import java.util.Scanner;

public class Ui {


    public void showLine(){
        System.out.println("------------------------------------------------------------");
    }

    /**
     * Show welcome figure and messages.
     */
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

    /**
     * Show help messages.
     */
    public void showHelp(){
        String helpMessage = "For help, here is a description for all the commands:\n" +
                "> list\n" +
                "List all the tasks in the list.\n\n" +
                "> todo [description]\n" +
                "Add a todo task to the list.\n\n" +
                "> deadline [description] /by [time]\n" +
                "Add a deadline task to the list. Date format YYYY-MM-DD is encouraged.\n\n" +
                "> event [description] /from [time] /to [time]\n" +
                "Add a event task to the list. Date format YYYY-MM-DD is encouraged.\n\n" +
                "> mark [index]\n" +
                "Mark a specified object according to index.\n\n" +
                "> unmark [index]\n" +
                "Unmark a specified object according to index.\n\n" +
                "> delete [index]\n" +
                "Delete a specified object according to index.\n\n" +
                "> find [keyword]\n" +
                "Find the tasks according to the keyword.\n\n" +
                "> bye\n" +
                "End the program.";
        System.out.println(helpMessage);
    }
    /**
     * If the program does not find the local file when initializing, show this message.
     * and create a new file.
     */
    public void showFileNotFoundException() {
        System.out.println("There is no past record in the disk.");
    }

    /**
     * If the local file did not follow the format, show that the program meets a parse error.
     */
    public void showFileLineParseException(){
        System.out.println("FileLineParseError: Cannot parse file into taskList.");
    }

    /**
     * Show the message when an I/O error happens.
     */
    public void showIOException(){
        System.out.println("I/O Error: Cannot write the record to the file");
    }

    /**
     * Show that the user's input cannot be formatted to an index.
     */
    public void showTaskIndexFormatError(){
        System.out.println("TaskIndexFormatError: Cannot format your input to a task.Task Index!");
    }

    /**
     * Show that the input index is not valid, perhaps beyond the size of the tasklist.
     */
    public void showTaskIndexNotFoundError(){
        System.out.println("TaskIndexNotFoundError: Task index is invalid!");
    }


    /**
     * Show when the program meet a null pointer. This mostly happens when
     * the user misses the params for the command.
     */
    public void showNullPointerException(){
        System.out.println("ParamsError: Maybe because you didn't input anything.");
    }

    /**
     * Show that the user's input did not follow the format of the deadline command.
     */
    public void showDeadlineParmsFormat(){
        System.out.println("ParamsError: Please input in the format: [String] /by [time]");
    }

    /**
     * Show that the user's input did not follow the format of the event command.
     */
    public void showEventParamsFormat(){
        System.out.println("ParamsError: Please input in the format: [String] /[from] /[to]");
    }

    /**
     * List all the tasks' information in the task list.
     * @param tasks The tasks to show.
     */
    public void showTaskList(TaskList tasks){
        if(tasks.size() == 0){
            System.out.println("There is nothing in the tasksList.");
            return;
        }
        for(int i = 0; i < tasks.size() ; i++){
            System.out.println((i + 1) + "." + tasks.get(i).toString() );
        }
    }

    /**
     * Show that there are matching tasks when executing a find command.
     */
    public void showFindNotEmpty(){
        System.out.println("Here are the matching tasks in your list:");
    }

    /**
     * Show that there is no matching task when executing a find command.
     */
    public void showFindEmpty(){
        System.out.println("Cannot find anything matching the keyword!");
    }

    /**
     * Show that the add command has been executed successfully. Print the task the user added.
     * @param newTaskObject The new task user added.
     * @param tasks The task list.
     */
    public void showAddTaskDone(Task newTaskObject, TaskList tasks){
        String feedback = "Got it. I've added this task:\n"
                + newTaskObject.toString() + "\n"
                + "Now you have " + tasks.size() +" tasks in the list";
        System.out.println(feedback);
    }

    /**
     * Show that the mark/unmark command has been executed successfully, and
     * print the task the user marked/unmarked.
     * @param taskToMark The object the user marked/unmarked.
     * @param isMarkAsDone The variable distinguishing mark/unmark.
     */
    public void showMarkUnmarkTaskDone(Task taskToMark, boolean isMarkAsDone) {
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

    /**
     * Show that the todo command has been executed successfully, and
     * print the todo task user added.
     * @param newTodoObject The new todo task user added.
     * @param tasks The task list.
     */
    public void showTodoTaskDone(Todo newTodoObject, TaskList tasks) {
        String feedback = "Got it. I've added this task:\n"
                + newTodoObject.toString() + "\n"
                + "Now you have " + tasks.size() + " tasks in the list";
        System.out.println(feedback);
    }

    /**
     * Show that the deadline command has been executed successfully, and
     * print the deadline task user added.
     * @param newDeadlineObject The new deadline task user added.
     * @param tasks The task list.
     */
    public void showDeadlineTask(Deadline newDeadlineObject, TaskList tasks){
        String feedback =  "Got it. I've added this task:\n"
                + newDeadlineObject.toString() + "\n"
                + "Now you have " + tasks.size() +" tasks in the list";
        System.out.println(feedback);
    }

    /**
     * Show that the event command has been executed successfully, and
     * print the event task the user added.
     * @param newEventObject The new event task the user added.
     * @param tasks The task list.
     */
    public void showEventTask(Event newEventObject, TaskList tasks) {
        String feedback = "Got it. I've added this task:\n"
                + newEventObject.toString() + "\n"
                + "Now you have " + tasks.size() + " tasks in the list";
        System.out.println(feedback);
    }

    /**
     * Show that the delete command has been executed successfully, and
     * print the event task the user deleted.
     * @param tasks The task list.
     * @param index The index of the deleted task.
     */
    public void showDeleteTask(TaskList tasks, int index){
        int newTasksSize = tasks.size()-1;
        String feedback = "Noted. I've removed this task:\n"
                + tasks.get(index).toString()
                + "\nNow you have "+ newTasksSize + " tasks in the list.";
        System.out.println(feedback);
    }

    /**
     * Show that the program meets an unknown command.
     * @param userCommand The unknown command the user input.
     */
    public void showUnknownCommand(String userCommand){
        String feedback = "CommandError: I can't understand \"" + userCommand +"\"!";
        System.out.println(feedback);
    }

    /**
     * Show good bye message.
     */
    public void showGoodBye(){
        System.out.println("See you again soon!");
    }

    /**
     * Return the line the user input.
     * @return A new line.
     */
    public String readCommand(){
            Scanner in = new Scanner(System.in);
            String line = in.nextLine();
            return line;
    }

}
