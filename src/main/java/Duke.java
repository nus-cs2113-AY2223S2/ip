import java.util.Scanner;  // Import the Scanner class
import java.util.ArrayList; // Import the ArrayList class

public class Duke {

    //define the file path
    public static final String FILE_PATH = "data/duke.txt";

    // //Initialize the storage, tasklist and ui
    // private Storage storage;
    // private Tasks tasks;
    // private Ui ui;

    // public Duke(String filePath) {
    //     ui = new Ui();
    //     storage = new Storage(filePath);
    //     try {
    //         tasks = storage.readFile(filePath);
    //     } catch (DukeException e) {
    //         ui.showLoadingError();
    //         tasks = new TaskList();
    //     }
    // }

    // public void run() {
    //     ui.showWelcome();
    //     boolean isExit = false;
    //     while (!isExit) {
    //         try {
    //             String fullCommand = ui.readCommand();
    //             ui.showLine();
    //             Command c = Parser.parse(fullCommand);
    //             c.execute(tasks, ui, storage);
    //             isExit = c.isExit();
    //         } catch (DukeException e) {
    //             ui.showError(e.getMessage());
    //         } finally {
    //             ui.showLine();
    //         }
    //     }
    // }

    // public static void main(String[] args) {
    //     new Duke(FILE_PATH).run();
    // }



    //Initialize create a arraylist of tasks
    public static ArrayList<Task> tasks = new ArrayList<Task>();


    

    public static void main(String[] args) {

        //read the file
        tasks = Storage.readFile(FILE_PATH);

        //Print the welcome message
        Ui.printWelcomeMessage();
        

        //Get user input
        Scanner userScan = new Scanner(System.in);  // Create scanner object
        String userInput = userScan.nextLine();  // Get user input

        //Check the arguments provided unless it is "bye" which quits the program
        while( !(Check.isBye(userInput)) ){
            
            try{
                //If userInput is "list" print all tasks
                if(Check.isList(userInput)){
                    //Print out the list of tasks
                    Ui.printTaskList(tasks);
                    //Get user input again
                    userInput = userScan.nextLine();  
                }

                //If userInput is "unmark" get the task number and unmark the task as done
                else if(Check.isUnmark(userInput)){
                    //get the task number
                    int taskNumber = Integer.parseInt(userInput.substring(7));
                    //if the task number is greater than the task count throw an exception
                    if(taskNumber>tasks.size()){
                        throw new IllegalArgumentException("The task number is greater than the number of tasks.");
                    }
                    //if the task number is less than 1 throw an exception
                    if(taskNumber<1){
                        throw new IllegalArgumentException("The task number is less than 1.");
                    }
                    //if there is no task at the task number throw an exception
                    if(tasks.get(taskNumber-1)==null){
                        throw new IllegalArgumentException("There is no task at the task number.");
                    }
                    //if the task is already not done throw an exception
                    if(tasks.get(taskNumber-1).isDone==false){
                        throw new IllegalArgumentException("The task is already not done.");
                    }
                    //mark the task as done
                    tasks.get(taskNumber-1).markAsNotDone();
                    //print out the task that was marked as done
                    Ui.printUndoneTask(tasks.get(taskNumber-1));
                    //Get user input again
                    userInput = userScan.nextLine();          
                }

                //If userInput is "mark" get the task number and mark the task as done
                else if(Check.isMark(userInput)){
                    //get the task number
                    int taskNumber = Integer.parseInt(userInput.substring(5));
                    //if the task number is greater than the task count throw an exception
                    if(taskNumber>tasks.size()){
                        throw new IllegalArgumentException("The task number is greater than the number of tasks.");
                    }
                    //if the task number is less than 1 throw an exception
                    if(taskNumber<1){
                        throw new IllegalArgumentException("The task number is less than 1.");
                    }
                    //if there is no task at the task number throw an exception
                    if(tasks.get(taskNumber-1)==null){
                        throw new IllegalArgumentException("There is no task at the task number.");
                    }
                    //if the task is already done throw an exception
                    if(tasks.get(taskNumber-1).isDone==true){
                        throw new IllegalArgumentException("The task is already done.");
                    }
                    //mark the task as done
                    tasks.get(taskNumber-1).markAsDone();
                    //print out the task that was marked as done
                    Ui.printDoneTask(tasks.get(taskNumber-1));
                    //Get user input again
                    userInput = userScan.nextLine();              
                }

                //If userInput is "todo" add a todo task to the list
                else if(Check.isTodo(userInput)){
                    //get the task name
                    String taskName = userInput.substring(5);
                    //If the task name is empty throw an exception
                    if(taskName.equals("")){
                        throw new IllegalArgumentException("The description of a todo cannot be empty.");
                    }
                    //create a todo task
                    tasks.add(new Todo(taskName));
                    //print out the task that was added
                    Ui.printAddedTask(tasks.get(tasks.size()-1), tasks.size());
                    //Get user input again
                    userInput = userScan.nextLine();  
                }

                //If userInput is "deadline" add a deadline task to the list
                else if(Check.isDeadline(userInput)){
                    //get the task name
                    String taskName = userInput.substring(9,userInput.indexOf("/"));
                    //get the deadline string without any "/"
                    String deadline = userInput.substring(userInput.indexOf("/")).replace("/","");
                    //If the task name is empty throw an exception
                    if(taskName.equals("")){
                        throw new IllegalArgumentException("The description of a deadline cannot be empty.");
                    }
                    //If the deadline is empty throw an exception
                    if(deadline.equals("")){
                        throw new IllegalArgumentException("The deadline of a deadline cannot be empty. Add a / argument to specify time.");
                    }
                    //create a deadline task
                    tasks.add(new Deadline(taskName,deadline));
                    //print out the task that was added
                    Ui.printAddedTask(tasks.get(tasks.size()-1), tasks.size());
                    //Get user input again
                    userInput = userScan.nextLine();  
                }

                //If userInput is "event" add an event task to the list
                else if(Check.isEvent(userInput)){
                    //get the task name
                    String taskName = userInput.substring(6,userInput.indexOf("/"));
                    //get the event time without any "/"
                    String eventTime = userInput.substring(userInput.indexOf("/")).replace("/","");
                    //create an event task
                    //if the taskname is empty throw an exception
                    if(taskName.equals("")){
                        throw new IllegalArgumentException("The description of an event cannot be empty.");
                    }
                    //if the event time is empty throw an exception
                    if(eventTime.equals("")){
                        throw new IllegalArgumentException("The event time of an event cannot be empty. Add a / argument to specify time.");
                    }
                    //create an event task
                    tasks.add(new Event(taskName,eventTime));
                    //print out the task that was added
                    Ui.printAddedTask(tasks.get(tasks.size()-1), tasks.size());
                    //Get user input again
                    userInput = userScan.nextLine();  
                }

                //Add delete keyword and function
                else if(Check.isDelete(userInput)){
                    //get the task number
                    int taskNumber = Integer.parseInt(userInput.substring(7));
                    //if the task number is greater than the task count throw an exception
                    if(taskNumber>tasks.size()){
                        throw new IllegalArgumentException("The task number is greater than the number of tasks.");
                    }
                    //if the task number is less than 1 throw an exception
                    if(taskNumber<1){
                        throw new IllegalArgumentException("The task number is less than 1.");
                    }
                    //if there is no task at the task number throw an exception
                    if(tasks.get(taskNumber-1)==null){
                        throw new IllegalArgumentException("There is no task at the task number.");
                    }
                    //delete the task
                    tasks.remove(taskNumber-1);
                    //print out the task that was deleted
                    Ui.printDeletedTask(tasks.get(taskNumber-1), tasks.size());
                    //Get user input again
                    userInput = userScan.nextLine();  
                }

                //If userInput is not any of the commands throw an illegal argument exception
                else{
                    //throw an exception
                    throw new IllegalArgumentException("Invalid command. Please try again.");     
                }

            }
            catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
                //Get user input again
                userInput = userScan.nextLine();  
            }
            catch (StringIndexOutOfBoundsException e){
               System.out.println("Invalid date entered. Please try again and enter / before the date.");
                //Get user input again
                userInput = userScan.nextLine();
            }
            catch (IndexOutOfBoundsException e){
                System.out.println("There are currently no tasks in the list. ");
                //Get user input again
                userInput = userScan.nextLine();
            }
            
            
        }

        //Print out a goodbye message using the goodbye method
        Ui.printGoodbyeMessage();
        

        //Close the scanner
        userScan.close();

        //Write the tasks to the file using the writeToFile method

        Storage.writeFile(tasks, FILE_PATH);

    }

}