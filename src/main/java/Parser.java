import java.io.IOException;
import java.util.Scanner;

public class Parser {
    private static final String DIVIDER  = "______________________________";
    private boolean isFinished = false; //to return to main program
    //Commands
    static final String COMMAND_EXIT = "bye";
    static final String COMMAND_LIST = "list";
    static final String COMMAND_MARK = "mark";
    static final String COMMAND_UNMARK = "unmark";
    static final String COMMAND_TODO = "todo";
    static final String COMMAND_EVENT = "event";
    static final String COMMAND_DEADLINE = "deadline";
    static final String COMMAND_DELETE = "delete";
    static final String COMMAND_FIND = "find";

    public void parse(String input, TaskList taskList) {
        String[] inputText = input.split(" ");
        String command = inputText[0];
        String taskDesc = "";
        for (int i = 1; i < inputText.length; i++) {
            taskDesc = taskDesc + " " + inputText[i];
        }

        switch(command) {
        case COMMAND_EXIT:
            isFinished = true;
            break;
        case COMMAND_LIST:
            taskList.printList();
            //old command commented out.
            //printList();
            break;
        case COMMAND_MARK:
            try {
                if (taskDesc.length() == 0) {
                    throw new EmptyDescriptionException();
                }
                int taskNum = Integer.parseInt(inputText[1]);
                if (taskNum > taskList.getSize() || taskNum <= 0) {
                    throw new ArrayIndexOutOfBoundsException();
                }
                taskList.markTask(taskNum);
            } catch (EmptyDescriptionException e) {
                Ui.emptyDescriptionNumber();
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(DIVIDER + System.lineSeparator()
                        + "Unable to mark task as task does not exist!" + System.lineSeparator() + DIVIDER);
            } catch (NumberFormatException e) {
                Ui.invalidTaskNumberError();
            }
            break;
        case COMMAND_UNMARK:
            try {
                if (taskDesc.length() == 0) {
                    throw new EmptyDescriptionException();
                }
                int taskNum = Integer.parseInt(inputText[1]);
                if (taskNum > taskList.getSize() || taskNum <= 0) {
                    throw new ArrayIndexOutOfBoundsException();
                }
                taskList.unmarkTask(taskNum);
            } catch (EmptyDescriptionException e) {
                Ui.emptyDescriptionNumber();
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(DIVIDER + System.lineSeparator()
                        + "Unable to unmark task as task does not exist!" + System.lineSeparator() + DIVIDER);
            } catch (NumberFormatException e) {
                Ui.invalidTaskNumberError();
            }
            break;
        case COMMAND_TODO:
            try {
                if (taskDesc.length() == 0) {
                    throw new EmptyDescriptionException();
                }
                //OLD COMMAND
                //taskList.addTask(new Todo(taskDesc));
                taskList.addTodo(taskDesc);
                taskList.acknowledgementMessage();
            } catch (EmptyDescriptionException e) {
                Ui.emptyDescriptionTodo();
            }
            break;
        case COMMAND_EVENT:
            try {
                if (taskDesc.length() == 0) {
                    throw new EmptyDescriptionException();
                }
                //Old command for adding event
                    /*//use string.split to split the string into their different descriptions
                    String[] eventInput = taskDesc.split(COMMAND_FROM);
                    //split into task description and duration
                    String eventTaskDesc = eventInput[0];
                    String eventDuration = eventInput[1];
                    String[] eventStartAndEnd = eventDuration.split(COMMAND_TO);
                    String eventStart = eventStartAndEnd[0];
                    String eventEnd = eventStartAndEnd[1];
                    taskList.addTask(new Event(eventTaskDesc, eventStart, eventEnd));*/

                //NEW COMMAND HERE
                taskList.addEvent(taskDesc);
                taskList.acknowledgementMessage();
            } catch (EmptyDescriptionException e) {
                Ui.emptyDescriptionEvent();
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(DIVIDER + System.lineSeparator()
                        + "The event you keyed in was invalid!" + System.lineSeparator()
                        + "Key in a valid event by including description followed by /from and /to keywords!"
                        + System.lineSeparator() + DIVIDER);
            }
            break;
        case COMMAND_DEADLINE:
            try {
                if (taskDesc.length() == 0) {
                    throw new EmptyDescriptionException();
                }
                //old command
                    /*//use string.split to split the string into their different descriptions
                    String[] deadlineInput = taskDesc.split(COMMAND_BY);
                    //split into task description and duration
                    String deadlineTaskDesc = deadlineInput[0];
                    String deadlineDuration = deadlineInput[1];
                    taskList.addTask(new Deadline(deadlineTaskDesc, deadlineDuration));*/

                //NEW COMMAND HERE
                taskList.addDeadline(taskDesc);
                taskList.acknowledgementMessage();
            } catch (EmptyDescriptionException e) {
                Ui.emptyDescriptionDeadline();
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(DIVIDER + System.lineSeparator()
                        + "The deadline you keyed in was invalid!" + System.lineSeparator()
                        + "Key in a valid deadline by including description followed by /by keyword!"
                        + System.lineSeparator() + DIVIDER);
            }
            break;
        case COMMAND_DELETE:
            try {
                if (taskDesc.length() == 0) {
                    throw new EmptyDescriptionException();
                }
                int taskNum = Integer.parseInt(inputText[1]);
                if (taskNum > taskList.getSize() || taskNum <= 0) {
                    throw new ArrayIndexOutOfBoundsException();
                }
                taskList.deleteTask(taskNum);
            } catch (EmptyDescriptionException e) {
                Ui.emptyDescriptionNumber();
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(DIVIDER + System.lineSeparator()
                        + "Unable to delete task as task does not exist!" + System.lineSeparator() + DIVIDER);
            } catch (NumberFormatException e) {
                Ui.invalidTaskNumberError();
            }
            break;
        case COMMAND_FIND:
            //add in find function.
            //use indexOf to find.

            break;
        default:
            Ui.unknownTaskError();
            break;
        }
    }
    public boolean checkProgrammeCompletion() {
        return isFinished;
    }
}
