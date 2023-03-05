# Duke project template
Welcome! This is a User Guide for the task tracker Duke!
It's named after the Java mascot _Duke_. Given below are instructions on how to use it.

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
3. After that, locate the `src/main/java/Duke.java` file, right-click it, and choose `Run Duke.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   ```
   Hello from
    ____        _        
   |  _ \ _   _| | _____ 
   | | | | | | | |/ / _ \
   | |_| | |_| |   <  __/
   |____/ \__,_|_|\_\___|
   ```

## Commands

1. list - To see the user's current list of tasks.
2. todo <task> - Simply adds a task to be done.
3. deadline <task> /by <deadline of task> - Adds a task with a specific deadline.
4. event <task> /from <start time of event> /to <end time of event> - Adds an event with a start and end time.
5. mark <task number> - To mark a task as done.
6. unmark <task number> - To mark a task as not done. 
7. delete <task number> - To delete a task from the user's current list of tasks.
8. bye - To terminate the program.

## Usage

Example:  
event do CS2113 individual project /from Monday 12pm /to Sunday 12pm

Expected Outcome:  
Got it. I've added this task:   
[E][ ] do CS2113 individual project (From: Monday 12pm to Sunday 4pm)  
Now you have 1 task(s) in the list. 

