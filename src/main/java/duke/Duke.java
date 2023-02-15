package duke;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import duke.exception.ArgumentNotValidException;
import duke.exception.PromptCannotBeEmptyException;
import duke.storage.StorageManager;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.utils.Input;
import duke.utils.Output;

public class Duke {
    public static void main(String[] args) throws ArgumentNotValidException, PromptCannotBeEmptyException, IOException {
        ArrayList<Task> tasks = StorageManager.readFileContents();
        int indexTask;
        Task newTask;

        String[] parsedString;
        String taskName = "";
        String description;
        String prompt;
        LocalDateTime by;
        LocalDateTime from;
        LocalDateTime to;

        Output.printIntroduction();

        while (!taskName.equals("bye")) {
            taskName = Input.scanWord();
            try {
                switch (taskName) {
                case "list":
                    Output.printTaskList(tasks);
                    break;

                case "todo":
                    prompt = Input.scanPrompt("todo");
                    if (prompt != null) {
                        newTask = new Todo(prompt.trim());
                        tasks.add(newTask);
                        Output.printNewTaskMessage(newTask);
                    }

                    break;

                case "deadline":
                    prompt = Input.scanPrompt("deadline");
                    if (prompt != null) {
                        parsedString = Input.parseDeadline(prompt);
                        if (parsedString != null) {
                            description = parsedString[0];
                            by = Input.parseDate(parsedString[1]);
                            if(by!=null) {
                            	newTask = new Deadline(description, by);
                                tasks.add(newTask);
                                Output.printNewTaskMessage(newTask);
                            }                           
                        }

                    }

                    break;

                case "event":
                    prompt = Input.scanPrompt("event");
                    if (prompt != null) {
                        parsedString = Input.parseEvent(prompt);
                        if (parsedString != null) {
                            description = parsedString[0];
                            from = Input.parseDate(parsedString[1]);
                            to = Input.parseDate(parsedString[2]);
                            if(from!=null && to!=null) {
                            	newTask = new Event(description, from, to);
                                tasks.add(newTask);
                                Output.printNewTaskMessage(newTask);
                            } 
                        }
                    }
                    break;

                case "mark":
                    indexTask = Input.scanTaskIndex(tasks.size());
                    if (indexTask != -1) {
                        tasks.get(indexTask).setStatus(true);
                        Output.printTaskStatus(tasks.get(indexTask));
                    }
                    break;

                case "unmark":
                    indexTask = Input.scanTaskIndex(tasks.size());
                    if (indexTask != -1) {
                        tasks.get(indexTask).setStatus(false);
                        Output.printTaskStatus(tasks.get(indexTask));
                    }
                    break;

                case "delete":
                    indexTask = Input.scanTaskIndex(tasks.size());
                    if (indexTask != -1) {
                        Task.setNumTask(Task.getNumTask() - 1);
                        Output.printDeleteTaskMessage(tasks.get(indexTask));
                        tasks.remove(tasks.get(indexTask));
                    }
                    break;
                    
                case "find-date":
                	LocalDateTime date = Input.scanDate();
                	if(date!=null) {
                		Output.printTaskByDate(tasks, date);
                	}               	
                    break;

                case "bye":
                    break;

                default:
                    throw new ArgumentNotValidException();
                }

            } catch (ArgumentNotValidException e) {
                Output.printArgumentNullError();
            }

        }
        StorageManager.writeToFile(tasks);
        Output.printGoodbye();
    }
}
