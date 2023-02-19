package duke.tasks;
import duke.exceptions.*;

import java.util.ArrayList;

public class Task {
    
    /*
     ArrayList of all tasks
     All subclasses of Task call the parent constructor, which adds them to the list
    */

    // Task class attributes
    public String description;
    public boolean isComplete;

    // Reusable barrier
    public static final String BARRIER = "____________________________________________________________";

    // Sets default task to no description and incomplete then adds to list
    public Task() {
        this.description = "";
        this.isComplete = false;
        TaskList.add(this);
    }

    // Dynamic task constructor
    public Task(String description) {
        this.description = description;
        this.isComplete = false;
        TaskList.add(this);
    }

    // Changes task isComplete attribute to true
    public void markAsComplete() throws DukeExceptions.taskStatusException {
        try {
            if (this.isComplete) {
                throw new DukeExceptions.taskStatusException("\nBlast! This task be already complete, ye swab!\n" + BARRIER + "\n");
            }
            this.isComplete = true;
            System.out.println("\nNice! I've marked this task as done, me hearties!\n");
            System.out.println("     " + this.printTask() + "\n" + BARRIER + "\n");
        } finally {
            // Empty block to continue the program
        }
    }

    // Changes task isComplete attribute to true
    public void unmarkAsComplete() throws DukeExceptions.taskStatusException {
        try {
        if (!this.isComplete) {
            throw new DukeExceptions.taskStatusException("\nBlast! This task be already incomplete, ye bilge rat!\n" + BARRIER + "\n");
        }
        this.isComplete = false;
        System.out.println("\nAye, I've marked this task as not done yet, ye scallywag: \n");
        System.out.println("     " + this.printTask() + "\n"  + BARRIER + "\n");
        } finally {
            // Empty block to continue the program
        }
    }

    // Prints the task completion status and description
    public String printTask() {
        if (this.isComplete){
            return "[X] " + this.description;
        }
        return "[ ] " + this.description; 
    }
}