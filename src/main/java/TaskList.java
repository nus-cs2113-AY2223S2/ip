public class TaskList {
    private Task[] taskArray = new Task[100];
    private int totalTaskNum = 0;

    public boolean addTask(String userInput){
        String[] userInputSplited = userInput.split("/");
        String[] userCommand = userInputSplited[0].split(" ");
        switch(userCommand[0]){
            case "todo":
                return addTodo(userInputSplited);
            case "deadline":
                return addDeadline(userInputSplited);
            case "event":
                return addEvent(userInputSplited);
            default:
                System.out.println("Failed to add: Invalid Task format"); return false;
        }
    }

    public boolean addTodo(String[] userInputSplited){
        try{
            String contents = userInputSplited[0].replace("todo ", "");
            Todo newTodo = new Todo(contents);
            taskArray[totalTaskNum++] = newTodo;
            return true;
        } catch(Exception e) {
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
            return false;
        }
    }

    public boolean addDeadline(String[] userInputSplited){
        try{
            String contents = userInputSplited[0].replace("deadline ", "");
            String end = userInputSplited[1].replace("/by ", "");
            Deadline newDeadline = new Deadline (contents, end);
            taskArray[totalTaskNum++] = newDeadline;
            return true;
        } catch(Exception e){
            System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
            return false;
        }
    }

    public boolean addEvent(String[] userInputSplited){
        try{
            String contents = userInputSplited[0].replace("event ", "");
            String start = userInputSplited[1].replace("/from ", "");
            String end = userInputSplited[2].replace("/to ", "");
            Event newEvent = new Event(contents, start, end);
            taskArray[totalTaskNum++] = newEvent;
            return true;
        } catch(Exception e){
            System.out.println("☹ OOPS!!! The description of an event cannot be empty.");
            return false;
        }
    }


    @Override
    public String toString(){
        String str = "";
        for(int i=1; i<totalTaskNum+1; i++){
            str = str.concat(i + "." + taskArray[i-1] + "\n");
        }
        return str;
    }

    public Task[] getTaskArray(){
        return taskArray;
    }

    public int getTotalTaskNum(){
        return totalTaskNum;
    }
}

