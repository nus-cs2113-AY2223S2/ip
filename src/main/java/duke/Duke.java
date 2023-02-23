package duke;

import duke.task.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
public class Duke {

    public static void main(String[] args) {
        TaskList taskList;
        final File taskDataDirectory = new File("data");
        final File taskDataFile = new File("data/task-data.txt");
        if(!taskDataDirectory.exists()){
            try{
                taskDataDirectory.mkdir();
            }catch (SecurityException e){
                UI.printToUser("An error occurred while creating the data directory: " + e.getMessage());
                return;
            }
        }
        try{
            ArrayList<Task> tasks = Storage.retrieveTaskData(taskDataFile);
            taskList = new TaskList(tasks);
        }catch (Exception e) {
            UI.printToUser("An error occurred while retrieving task data: " + e.getMessage());
            return;
        }
        Scanner in = new Scanner(System.in);
        UI.printGreeting();
        boolean isProgramRunning = true;
        while(isProgramRunning)
        {
            String userInput = UI.getUserInput();
            Command command;
            try{
                command = Parser.parseCommand(userInput);
                switch(command.getCommandType()){
                case ADD_TODO_COMMAND:
                    taskList.addNewTask(command.getAdditionalParameters(), TaskType.TODO);
                    Storage.storeTaskData(taskDataFile, taskList.getTaskArrayList());
                    break;
                case ADD_DEADLINE_COMMAND:
                    taskList.addNewTask(command.getAdditionalParameters(), TaskType.DEADLINE);
                    Storage.storeTaskData(taskDataFile, taskList.getTaskArrayList());
                    break;
                case ADD_EVENT_COMMAND:
                    taskList.addNewTask(command.getAdditionalParameters(), TaskType.EVENT);
                    Storage.storeTaskData(taskDataFile, taskList.getTaskArrayList());
                    break;
                case LIST_TASKS_COMMAND:
                    UI.printTasklist(taskList.getTaskArrayList(),
                            "Here are the tasks in your list:");
                    break;
                case MARK_TASK_COMMAND:
                    taskList.changeTaskStatus(command.getAdditionalParameters(), true);
                    Storage.storeTaskData(taskDataFile, taskList.getTaskArrayList());
                    break;
                case UNMARK_TASK_COMMAND:
                    taskList.changeTaskStatus(command.getAdditionalParameters(), false);
                    Storage.storeTaskData(taskDataFile, taskList.getTaskArrayList());
                    break;
                case DELETE_TASK_COMMAND:
                    taskList.deleteTask(command.getAdditionalParameters());
                    Storage.storeTaskData(taskDataFile, taskList.getTaskArrayList());
                    break;
                case FIND_TASK_COMMAND:
                    UI.printTasklist(taskList.tasksWithKeyword(command.getAdditionalParameters()[0]),
                            "Here are the matching tasks in your list:");
                    break;
                case END_PROGRAM_COMMAND:
                    UI.printFarewell();
                    isProgramRunning = false;
                    break;
                case UNKNOWN_COMMAND:
                default:
                    UI.printToUser("Unknown task or task parameters received. Please try again.");
                    break;
                }
            }catch (DukeException e){
                UI.printToUser(e.getMessage());
            }catch (IOException e){
                UI.printToUser("An error occurred while storing your task data: " + e.getMessage());
                UI.printToUser("Your data will not be saved");
            }
        }
    }
}
