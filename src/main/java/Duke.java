import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;
import java.io.FileWriter;
import java.io.File;

public class Duke {

    private static final String DIVIDER  = "______________________________";

    //Commands
    static final String COMMAND_EXIT = "bye";
    static final String COMMAND_LIST = "list";
    static final String COMMAND_MARK = "mark";
    static final String COMMAND_UNMARK = "unmark";
    static final String COMMAND_TODO = "todo";
    static final String COMMAND_DEADLINE = "deadline";
    static final String COMMAND_EVENT = "event";
    static final String COMMAND_DELETE = "delete";
    static final String COMMAND_FIND = "find";

    static final String COMMAND_BY = "/by";
    static final String COMMAND_FROM = "/from";
    static final String COMMAND_TO = "/to";
    static final String TODO_ICON = "T";
    static final String EVENT_ICON = "E";
    static final String DEADLINE_ICON = "D";

    //Data
    static ArrayList<Task> list = new ArrayList<>();

    private static void printList() {
        String listMessage = DIVIDER + System.lineSeparator() + "Here are the tasks in your list:";
        System.out.println(listMessage);
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i+1) + "." + list.get(i).toString());
        }
        System.out.println(DIVIDER);
    }

    private static void markTask(int taskNum) {
        list.get(taskNum-1).markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(DIVIDER + System.lineSeparator() + list.get(taskNum-1).toString()
                + System.lineSeparator() + DIVIDER);
    }

    private static void unmarkTask(int taskNum) {
        list.get(taskNum-1).markAsNotDone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(DIVIDER + System.lineSeparator() + list.get(taskNum-1).toString()
                + System.lineSeparator() + DIVIDER);
    }

    private static void acknowledgementMessage() {
        String acknowledgement = DIVIDER + System.lineSeparator()
                + "Got it. I've added this task: " + System.lineSeparator()
                + list.get(list.size()-1).toString();
        System.out.println(acknowledgement);
        System.out.println("Now you have " + (list.size()) + " task(s) in the list."
                + System.lineSeparator() + DIVIDER);
    }

    private static void deleteTask(int taskNum) {
        String acknowledge = DIVIDER + System.lineSeparator() + "Noted. I've removed this task: "
                + System.lineSeparator() + list.get(taskNum-1).toString() + System.lineSeparator()
                + "Now you have " + (list.size()-1) + " tasks in the list." + System.lineSeparator() + DIVIDER;
        System.out.println(acknowledge);
        list.remove(taskNum-1);
    }

    //Update the file
    private static void updateFile() {
        for (Task currentTask : list) {
            String statusNum = currentTask.getStatusNum();
            String taskDesc = currentTask.getTask();
            String taskType = currentTask.getTaskIcon();

            switch (taskType) {
            case TODO_ICON:
                try {
                    writeToFile(taskType + ":" + statusNum + ":"
                            + taskDesc);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case DEADLINE_ICON:
                String deadlineBy = currentTask.getDeadlineBy();
                try {
                    writeToFile(taskType + ":" + statusNum + ":"
                            + taskDesc + COMMAND_BY + deadlineBy);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case EVENT_ICON:
                String eventStart = currentTask.getEventStart();
                String eventEnd = currentTask.getEventEnd();
                try {
                    writeToFile(taskType + ":" + statusNum + ":"
                            + taskDesc + COMMAND_FROM + eventStart
                            + COMMAND_TO + eventEnd);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                break;
            default:
                System.out.println("Nothing to update!");
                break;
            }
        }
    }

    private static void clearFile() throws IOException {
        FileWriter fw = new FileWriter("duke.txt");
        fw.write("");
        fw.close();
    }

    private static void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter("duke.txt", true);
        fw.write(textToAdd+System.lineSeparator());
        fw.close();
    }

    private static void readFileContents(String filePath) throws FileNotFoundException {
        //generates the text file input to new list each time user opens program.
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String newLine = s.nextLine();
            String[] newInput = newLine.split(":");
            String taskType = newInput[0];
            String taskMark = newInput[1];
            String taskName = newInput[2];

            switch (taskType) {
            case TODO_ICON:
                list.add(new Todo(taskName));
                break;
            case DEADLINE_ICON:
                String[] deadlineSeparator = taskName.split(COMMAND_BY);
                String deadlineTask = deadlineSeparator[0];
                String deadlineBy = deadlineSeparator[1];
                list.add(new Deadline(deadlineTask, deadlineBy));
                break;
            case EVENT_ICON:
                String[] eventSeparator = taskName.split(COMMAND_FROM);
                String eventTask = eventSeparator[0];
                String eventDuration = eventSeparator[1];
                String[] eventDurationSeparator = eventDuration.split(COMMAND_TO);
                String eventStart = eventDurationSeparator[0];
                String eventEnd = eventDurationSeparator[1];
                list.add(new Event(eventTask, eventStart, eventEnd));
                break;
            default:
                System.out.println("Error.");
                break;
            }
            if (taskMark.equals("1")) {
                list.get(list.size() - 1).markAsDone();
            }
        }
    }

    private static void taskManager() {
        String userInput;
        boolean isFinished = false;
        int taskNum;

        while(!isFinished) {
            Scanner in = new Scanner(System.in);
            userInput = in.nextLine();
            String[] inputText = userInput.split(" ");
            //get the remaining task description after the command word.
            String taskDesc = "";
            for (int i = 1; i < inputText.length; i++) {
                taskDesc = taskDesc + " " + inputText[i];
            }

            switch(inputText[0]) {
            case COMMAND_EXIT:
                isFinished = true;
                break;
            case COMMAND_LIST:
                printList();
                break;
            case COMMAND_MARK:
                try {
                    if (taskDesc.length() == 0) {
                        throw new EmptyDescriptionException();
                    }
                    taskNum = Integer.parseInt(inputText[1]);
                    if (taskNum > list.size() || taskNum <= 0) {
                        throw new ArrayIndexOutOfBoundsException();
                    }
                    markTask(taskNum);
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
                    taskNum = Integer.parseInt(inputText[1]);
                    if (taskNum > list.size() || taskNum <= 0) {
                        throw new ArrayIndexOutOfBoundsException();
                    }
                    unmarkTask(taskNum);
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
                    list.add(new Todo(taskDesc));
                    acknowledgementMessage();
                } catch (EmptyDescriptionException e) {
                    Ui.emptyDescriptionTodo();
                }
                break;
            case COMMAND_EVENT:
                try {
                    if (taskDesc.length() == 0) {
                        throw new EmptyDescriptionException();
                    }
                    //use string.split to split the string into their different descriptions
                    String[] eventInput = taskDesc.split(COMMAND_FROM);
                    //split into task description and duration
                    String eventTaskDesc = eventInput[0];
                    String eventDuration = eventInput[1];
                    String[] eventStartAndEnd = eventDuration.split(COMMAND_TO);
                    String eventStart = eventStartAndEnd[0];
                    String eventEnd = eventStartAndEnd[1];
                    list.add(new Event(eventTaskDesc, eventStart, eventEnd));
                    acknowledgementMessage();
                } catch (EmptyDescriptionException e) {
                    Ui.emptyDescriptionEvent();
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(DIVIDER + System.lineSeparator()
                            + "The event you keyed in was invalid!" + System.lineSeparator()
                            + "Please key in a valid event!" + System.lineSeparator() + DIVIDER);
                }
                break;
            case COMMAND_DEADLINE:
                try {
                    if (taskDesc.length() == 0) {
                        throw new EmptyDescriptionException();
                    }
                    //use string.split to split the string into their different descriptions
                    String[] deadlineInput = taskDesc.split(COMMAND_BY);
                    //split into task description and duration
                    String deadlineTaskDesc = deadlineInput[0];
                    String deadlineDuration = deadlineInput[1];
                    list.add(new Deadline(deadlineTaskDesc, deadlineDuration));
                    acknowledgementMessage();
                } catch (EmptyDescriptionException e) {
                    Ui.emptyDescriptionDeadline();
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(DIVIDER + System.lineSeparator()
                            + "The deadline you keyed in was invalid!" + System.lineSeparator()
                            + "Please key in a valid deadline!" + System.lineSeparator() + DIVIDER);
                }
                break;
            case COMMAND_DELETE:
                try {
                    if (taskDesc.length() == 0) {
                        throw new EmptyDescriptionException();
                    }
                    taskNum = Integer.parseInt(inputText[1]);
                    if (taskNum > list.size() || taskNum <= 0) {
                        throw new ArrayIndexOutOfBoundsException();
                    }
                    deleteTask(taskNum);
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

        try {
            clearFile();
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
        updateFile();
    }

    public static void main(String[] args) {
        try {
            //readFileContents("C:\\Lip Kuang\\NUS\\Year 2\\Y2S2\\CS2113\\Individual Project\\ip\\src\\main\\java\\data\\duke.txt");
            readFileContents("duke.txt");
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }

        Ui.printLogo();
        Ui.greetUser();
        taskManager();
        Ui.sayByeToUser();
    }
}
