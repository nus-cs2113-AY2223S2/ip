import java.util.Scanner;

public class Duke {
    public static final String COMMAND_BYE = "bye";
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_MARK = "mark";
    public static final String COMMAND_UNMARK = "unmark";
    public static final String COMMAND_DELETE = "delete";

    private static TaskList taskList;
    private static UI ui;
    private static Parser parser;

    public static void main(String[] args) {
        taskList = new TaskList();
        ui = new UI();
        parser = new Parser();

        while(true){
            executeCommand(getUserCommand());
        }
    }

    public static void exitProgram(){
        ui.printBye();
        System.exit(0);
    }

    public static String getUserCommand(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("[User] ");
        return scanner.nextLine();
    }

    public static void executeCommand(String command){
        String[] commandsSplited = command.split(" ");
        switch(commandsSplited[0]){
            case COMMAND_BYE:
                exitProgram(); break;
            case COMMAND_LIST:
                list(); break;
            case COMMAND_MARK:
                mark(commandsSplited[1]); break;
            case COMMAND_UNMARK:
                unmark(commandsSplited[1]); break;
            case COMMAND_DELETE:
                delete(commandsSplited[1]); break;
            default:
                add(command);
        }
    }

    public static void add(String userInput){
        if (taskList.addTask(userInput)) {
            int totalTaskNum = taskList.getTotalTaskNum();
            ui.printAddComment(taskList.getTask(totalTaskNum), totalTaskNum);
        } else {
            ui.printInputErrorComment();
        }
    }

    public static void list(){
        ui.printTaskList(taskList);
    }

    public static void mark(String taskNum){
        int taskNumInt = Integer.parseInt(taskNum);
        taskList.markTask(taskNumInt);
        ui.printMarkComment(taskList.getTask(taskNumInt));
    }

    public static void unmark(String taskNum) {
        int taskNumInt = Integer.parseInt(taskNum);
        taskList.unmarkTask(taskNumInt);
        ui.printUnmarkComment(taskList.getTask(taskNumInt));
    }

    public static void delete(String taskNum){
        int taskNumInt = Integer.parseInt(taskNum);
        if(taskNumInt > taskList.getTotalTaskNum() || taskNumInt <= 0){
            ui.printNotFoundErrorComment();
            return;
        }

        Task targetTask = taskList.getTaskArray().get(taskNumInt-1);
        if(taskList.delete(taskNumInt)) {
            ui.printDeleteComment(targetTask, taskList.getTotalTaskNum());
        } else{
            ui.printInputErrorComment();
        }
    }

}
