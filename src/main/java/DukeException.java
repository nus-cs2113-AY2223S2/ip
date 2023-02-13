public class DukeException {

    public static void detectError(String userInput){
        Duke.printLines();
        if(userInput.startsWith("todo")){
            runTodoError();
        } else if(userInput.startsWith("event")){
            runEventError();
        } else if (userInput.startsWith("deadline")){
            runDeadlineError();
        } else if (userInput.startsWith("mark")){
            runMarkError();
        } else if (userInput.startsWith("unmark")){
            runUnmarkError();
        } else if(userInput.startsWith("list")){
            runListError();
        } else{
            System.out.println("OOPS!! I cannot understand the input! ☹ ");
        }
        Duke.printLines();
    }

    public static void runTodoError(){
        System.out.println("OOPS!! The description of todo cannot be empty! ☹");
    }

    public static void runEventError(){
        System.out.println("OOPS!! The description of event cannot be empty! ☹");
    }

    public static void runDeadlineError(){
        System.out.println("OOPS!! The description of deadline cannot be empty! ☹");
    }

    public static void runMarkError(){
        System.out.println("OOPS!! I don't know what to mark! ☹");
    }

    public static void runUnmarkError(){
        System.out.println("I don't know what to unmark! ☹");
    }

    public static void runListError(){
        System.out.println("OOPS!! There are no tasks for me to list here!");
    }

}