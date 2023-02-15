public class Duke {

    //define the file path
    public static final String FILE_PATH = "data/duke.txt";

    //Initialize the storage, tasklist and ui
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.readFile(filePath));
        } catch (Exception e) {
            ui.printErrorMessage(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.printWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.printLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.printErrorMessage(e.getMessage());
            } finally {
                ui.printLine();
            }
        }
        //Write the tasks to the file using the writeToFile method
        storage.writeFile(tasks.getTasks(), FILE_PATH);
    }

    public static void main(String[] args) {
        new Duke(FILE_PATH).run();
    }

    // public static void main(String[] args) {

    //     //create a ui object
    //     Ui ui = new Ui();

    //     //read the file
    //     TaskList tasks = new TaskList(Storage.readFile(FILE_PATH));

    //     //Print the welcome message
    //     ui.printWelcomeMessage();
        

    //     //Check the arguments provided unless it is "bye" which quits the program
    //     boolean isBye = false;
    //     while( !isBye ){
            
    //         try{
    //             //user input
    //             String userInput = ui.readCommand();
    //             //print a line
    //             ui.printLine();
    //             //If userInput is "list" print all tasks
    //             if(Check.isList(userInput)){
    //                 //Print out the list of tasks
    //                 ui.printTaskList(tasks.getTasks());
    //             }

    //             //If userInput is "unmark" get the task number and unmark the task as done
    //             else if(Check.isUnmark(userInput)){
    //                 //get the task number
    //                 int taskNumber = Integer.parseInt(userInput.substring(7));
    //                 //if the task number is greater than the task count throw an exception
    //                 if(taskNumber>tasks.size()){
    //                     throw new IllegalArgumentException("The task number is greater than the number of tasks.");
    //                 }
    //                 //if the task number is less than 1 throw an exception
    //                 if(taskNumber<1){
    //                     throw new IllegalArgumentException("The task number is less than 1.");
    //                 }
    //                 //if there is no task at the task number throw an exception
    //                 if(tasks.get(taskNumber)==null){
    //                     throw new IllegalArgumentException("There is no task at the task number.");
    //                 }
    //                 //if the task is already not done throw an exception
    //                 if(tasks.get(taskNumber).isDone==false){
    //                     throw new IllegalArgumentException("The task is already not done.");
    //                 }
    //                 //mark the task as done
    //                 tasks.get(taskNumber).markAsNotDone();
    //                 //print out the task that was marked as done
    //                 ui.printUndoneTask(tasks.get(taskNumber));        
    //             }

    //             //If userInput is "mark" get the task number and mark the task as done
    //             else if(Check.isMark(userInput)){
    //                 //get the task number
    //                 int taskNumber = Integer.parseInt(userInput.substring(5));
    //                 //if the task number is greater than the task count throw an exception
    //                 if(taskNumber>tasks.size()){
    //                     throw new IllegalArgumentException("The task number is greater than the number of tasks.");
    //                 }
    //                 //if the task number is less than 1 throw an exception
    //                 if(taskNumber<1){
    //                     throw new IllegalArgumentException("The task number is less than 1.");
    //                 }
    //                 //if there is no task at the task number throw an exception
    //                 if(tasks.get(taskNumber)==null){
    //                     throw new IllegalArgumentException("There is no task at the task number.");
    //                 }
    //                 //if the task is already done throw an exception
    //                 if(tasks.get(taskNumber).isDone==true){
    //                     throw new IllegalArgumentException("The task is already done.");
    //                 }
    //                 //mark the task as done
    //                 tasks.get(taskNumber).markAsDone();
    //                 //print out the task that was marked as done
    //                 ui.printDoneTask(tasks.get(taskNumber));            
    //             }

    //             //If userInput is "todo" add a todo task to the list
    //             else if(Check.isTodo(userInput)){
    //                 //get the task name
    //                 String taskName = userInput.substring(5);
    //                 //If the task name is empty throw an exception
    //                 if(taskName.equals("")){
    //                     throw new IllegalArgumentException("The description of a todo cannot be empty.");
    //                 }
    //                 //create a todo task
    //                 tasks.add(new Todo(taskName));
    //                 //print out the task that was added
    //                 ui.printAddedTask(tasks.get(tasks.size()), tasks.size());
    //             }

    //             //If userInput is "deadline" add a deadline task to the list
    //             else if(Check.isDeadline(userInput)){
    //                 //get the task name
    //                 String taskName = userInput.substring(9,userInput.indexOf("/"));
    //                 //get the deadline string without any "/"
    //                 String deadline = userInput.substring(userInput.indexOf("/")).replace("/","");
    //                 //If the task name is empty throw an exception
    //                 if(taskName.equals("")){
    //                     throw new IllegalArgumentException("The description of a deadline cannot be empty.");
    //                 }
    //                 //If the deadline is empty throw an exception
    //                 if(deadline.equals("")){
    //                     throw new IllegalArgumentException("The deadline of a deadline cannot be empty. Add a / argument to specify time.");
    //                 }
    //                 //create a deadline task
    //                 tasks.add(new Deadline(taskName,deadline));
    //                 //print out the task that was added
    //                 ui.printAddedTask(tasks.get(tasks.size()), tasks.size());
    //             }

    //             //If userInput is "event" add an event task to the list
    //             else if(Check.isEvent(userInput)){
    //                 //get the task name
    //                 String taskName = userInput.substring(6,userInput.indexOf("/"));
    //                 //get the event time without any "/"
    //                 String eventTime = userInput.substring(userInput.indexOf("/")).replace("/","");
    //                 //create an event task
    //                 //if the taskname is empty throw an exception
    //                 if(taskName.equals("")){
    //                     throw new IllegalArgumentException("The description of an event cannot be empty.");
    //                 }
    //                 //if the event time is empty throw an exception
    //                 if(eventTime.equals("")){
    //                     throw new IllegalArgumentException("The event time of an event cannot be empty. Add a / argument to specify time.");
    //                 }
    //                 //create an event task
    //                 tasks.add(new Event(taskName,eventTime));
    //                 //print out the task that was added
    //                 ui.printAddedTask(tasks.get(tasks.size()), tasks.size());
    //             }

    //             //Add delete keyword and function
    //             else if(Check.isDelete(userInput)){
    //                 //get the task number
    //                 int taskNumber = Integer.parseInt(userInput.substring(7));
    //                 //if the task number is greater than the task count throw an exception
    //                 if(taskNumber>tasks.size()){
    //                     throw new IllegalArgumentException("The task number is greater than the number of tasks.");
    //                 }
    //                 //if the task number is less than 1 throw an exception
    //                 if(taskNumber<1){
    //                     throw new IllegalArgumentException("The task number is less than 1.");
    //                 }
    //                 //if there is no task at the task number throw an exception
    //                 if(tasks.get(taskNumber)==null){
    //                     throw new IllegalArgumentException("There is no task at the task number.");
    //                 }
    //                 //delete the task
    //                 tasks.delete(taskNumber);
    //                 //print out the task that was deleted
    //                 ui.printDeletedTask(tasks.get(taskNumber), tasks.size());
    //             }

    //             //If userInput is not any of the commands throw an illegal argument exception
    //             else{
    //                 //throw an exception
    //                 throw new IllegalArgumentException("Invalid command. Please try again.");     
    //             }

    //         }
    //         catch (IllegalArgumentException e){
    //             System.out.println(e.getMessage());
    //         }
    //         catch (StringIndexOutOfBoundsException e){
    //            System.out.println("Invalid date entered. Please try again and enter / before the date.");
    //         }
    //         catch (IndexOutOfBoundsException e){
    //             System.out.println("There are currently no tasks in the list. ");
    //         }
            
            
    //     }

        //Print out a goodbye message using the goodbye method
        //ui.printGoodbyeMessage();

}