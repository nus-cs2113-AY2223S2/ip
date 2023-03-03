/**
 * Represents a virtual assistant that helps you manage your tasks.
 * A <code>Duke</code> object can execute 9 types of command
 * : bye, list, mark, unmark, delete, add todo/deadline/event, and find
 */

public class Duke {

    public static final String COMMAND_BYE = "bye";
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_MARK = "mark";
    public static final String COMMAND_UNMARK = "unmark";
    public static final String COMMAND_DELETE = "delete";
    public static final String COMMAND_ADD_TODO = "add todo";
    public static final String COMMAND_ADD_DEADLINE = "add deadline";
    public static final String COMMAND_ADD_EVENT = "add event";
    public static final String COMMAND_FIND = "find";

    private static TaskList taskList;

    public static void main(String[] args) {
        taskList = new TaskList(true);
        UI.greetUser();

        while(true){
            executeCommand();
        }
    }

    private static void executeCommand(){
        Command command = Parser.getCommand();

        if(command == null) return;

        switch(command.getType()){
            case COMMAND_BYE:
                exitProgram(); break;
            case COMMAND_LIST:
                list(); break;
            case COMMAND_MARK:
                mark(command); break;
            case COMMAND_UNMARK:
                unmark(command); break;
            case COMMAND_DELETE:
                delete(command); break;
            case COMMAND_ADD_TODO:
                add(command); break;
            case COMMAND_ADD_DEADLINE:
                add(command); break;
            case COMMAND_ADD_EVENT:
                add(command); break;
            case COMMAND_FIND:
                find(command); break;
            default:
                UI.printInputErrorComment();
        }
    }

    private static void exitProgram(){
        UI.printBye();
        System.exit(0);
    }

    private static void add(Command command){
        if (taskList.addTask(command)) {
            int totalTaskNum = taskList.getTotalTaskNum();
            UI.printAddComment(taskList.getTask(totalTaskNum), totalTaskNum);
        } else {
            UI.printInputErrorComment();
        }
    }

    private static void list(){
        UI.printTaskList(taskList);
    }

    private static void mark(Command command){
        taskList.markTask(command.getTargetTaskIndex());
        UI.printMarkComment(taskList.getTask(command.getTargetTaskIndex()));
    }

    private static void unmark(Command command) {
        taskList.unmarkTask(command.getTargetTaskIndex());
        UI.printUnmarkComment(taskList.getTask(command.getTargetTaskIndex()));
    }

    private static void delete(Command command){
        int taskNumInt = command.getTargetTaskIndex();
        if(taskNumInt > taskList.getTotalTaskNum() || taskNumInt <= 0){
            UI.printNotFoundErrorComment();
            return;
        }

        Task targetTask = taskList.getTask(command.getTargetTaskIndex());
        if(taskList.delete(taskNumInt)) {
            UI.printDeleteComment(targetTask, taskList.getTotalTaskNum());
        } else{
            UI.printInputErrorComment();
        }
    }

    private static void find(Command command){
        String targetWord = command.getTargetWord();
        TaskList targetTaskList = new TaskList(false);

        for(Task task : taskList.getTaskArray()){
            if (task.getContents().contains(targetWord)){
               targetTaskList.addTask(task);
            }
        }

        UI.printTargetTaskList(targetTaskList);
    }

}
