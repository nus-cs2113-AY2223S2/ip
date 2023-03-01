# Duke project template

This is a project template for a greenfield Java project. It's named after the Java mascot _Duke_. Given below are instructions on how to use it.

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
   ____________________________________________________________
    ____        _        
   |  _ \ _   _| | _____ 
   | | | | | | | |/ / _ \
   | |_| | |_| |   <  __/
   |____/ \__,_|_|\_\___|
   Hello! I'm Duke
   Welcome to Your To-do List!
   Enter "todo 'task-name'" to add a task."
   Enter "deadline 'task-name' /by 'deadline'" to add a task with a deadline."
   Enter "event 'task-name' /from 'start-date' /to 'end-date'" to add a task with start and end dates."
   Enter " mark 'task-index' " to mark a task as done."
   Enter " unmark 'task-index' " to mark a task as not done yet."
   Enter " delete 'task-index' " to delete a task from the list."
   Enter " list " to obtain a list of all your tasks!."
   ____________________________________________________________
   Data file already exists. You list will be updated.
   ```
