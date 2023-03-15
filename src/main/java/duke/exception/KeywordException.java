package duke.exception;

import static duke.print.Print.*;

public class KeywordException extends Exception {

    public KeywordException() {
        printOneLine();
        println("    OOPS!!! I'm sorry, but I don't know what that means :-(");
        println("    I only understand the following commands unfortunately: ");
        println("");
        println("    todo [task description]                          --> to add a todo task");
        println("    deadline [task description] /by [due date]       --> to add a deadline");
        println("    event [task description] /from [when] /to [when] --> to add an event");
        println("    mark [task number]                               --> to mark a task as done");
        println("    unmark [task number]                             --> to unmark a task (i.e. not done)");
        println("    list                                             --> to show the lists of tasks");
        println("    find [keyword]                                   --> to find tasks containing the keyword");
        println("    bye                                              --> to exit the program");
        println("");
        println("    THANKS!");
        printOneLine();
    }

}
