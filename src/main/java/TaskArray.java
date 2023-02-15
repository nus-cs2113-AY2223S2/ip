import java.io.File;

public class TaskArray {

    static Task[] storedTasks = new Task[100];

    static int count;


    public TaskArray(){
    }

    public static void createToDo(String userInput){
        if (userInput == null){
            System.out.println(UI.LINE + "☹ OOPS!!! The description of a todo cannot be empty.\n" + UI.LINE);
        }else {
            storedTasks[count] = new Todo(userInput);
        }
    }
    public static void createDeadline(String userInput){
        try {
            String[] parameters = userInput.split("by", 2);
            storedTasks[count] = new Deadline(parameters[0], parameters[1]);
        }catch (NullPointerException | ArrayIndexOutOfBoundsException e){
            System.out.println(UI.LINE + "Please follow the format:[deadline <Task Name> by <Deadline Date>]\n" + UI.LINE);
        }
    }

    public static int getCount(){
        return count;
    }
    public static void delete(String userInput) {
        String index = userInput.trim();
        int indexInt = Integer.parseInt(index) - 1;
        try {
            count--;
            System.out.println(UI.LINE + "Noted. I've removed this task:\n" + storedTasks[indexInt].toString());
            System.out.println("Now you have " + count + " tasks in the list.\n" + UI.LINE);
            Task[] temp = new Task[100];

            for (int i = 0, k = 0; i < storedTasks.length; i++) {
                if (i == indexInt) {
                    continue;
                }

                temp[k++] = storedTasks[i];
            }
            storedTasks = temp;
        }catch (NullPointerException e) {
            System.out.println(UI.LINE + "Task doesn't exist!\n" + UI.LINE);
        }
    }

    public static void createEvent(String userInput){
        try {
            String[] temp = userInput.split(" from ", 2);
            String[] parameters = temp[1].split(" to ", 2);
            storedTasks[count] = new Event(temp[0], parameters[0], parameters[1]);
        }catch (NullPointerException | ArrayIndexOutOfBoundsException e){
            System.out.println(UI.LINE + "Please follow the format:[event <Task Name> from <Start Time> to <End Time>]\n" + UI.LINE);
        }
    }

    public static void print(){
        System.out.println(UI.LINE);
        for (int i = 0; i < count; i++) {
            System.out.println(i + 1 + "." + storedTasks[i].toString());
        }
        System.out.println(UI.LINE);
    }

    public static void mark(String userInput){
        String index = userInput.trim();
        int indexInt = Integer.parseInt(index) - 1;
        storedTasks[indexInt].markAsDone();
        System.out.println(UI.LINE + "     Nice! I've marked this task as done:\n [X] " + storedTasks[indexInt].description + "\n "+ UI.LINE);
    }

    public static void unmark(String userInput){
        String index = userInput.trim();
        int indexInt = Integer.parseInt(index) - 1;
        storedTasks[indexInt].unmarkTask();
        System.out.println(UI.LINE + "     OK, I've marked this task as not done yet:\n [ ] " + storedTasks[indexInt].description + "\n " + UI.LINE);
    }
}
