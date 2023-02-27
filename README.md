# DukeMain.Duke project template

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
3. After that, locate the `src/main/java/DukeMain.Duke.java` file, right-click it, and choose `Run DukeMain.Duke.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   ```
   Hello from
    ____        _        
   |  _ \ _   _| | _____ 
   | | | | | | | |/ / _ \
   | |_| | |_| |   <  __/
   |____/ \__,_|_|\_\___|
   ```
## Duke User Guide

How to use Duke and What features are available:

1. `list` command: 
   --> renders a list of all the items in the Todo List

      ```
      >>list
      -------TODO-LIST------
      ----------------------
      1. [T][ ]  buy book
      2. [T][ ]  eat cookies
      3. [T][ ]  watch Tv
      ----------------------
      
      ```
   
2. `mark` command:
   --> adds an "X" to indicate that a task is completed
   --> format: mark <index of item to mark>
      ```
      >>mark 1
      Item has been marked
      1. [T][X]  buy book
      ```
   
3. `unmark` Command:
   --> unmarks an item in the todolist by removing the "X"
   --> format: umark <index of item to unmark>
      ```
      >>unmark 1
      Item has been unmarked
      1. [T][ ]  buy book
      ```
   
4. `todo` command:
   --> adds a todo item
   --> format: todo <task_description>

      ```
      >>todo eat breakfast
      Got it, Ive done the Following!
      Added: [T][ ] eat breakfast
      now you have: 4 tasks in this list.
      ```
   
5. `deadline` command
   --> adds a Deadline item
   --> format: deadline <task_description> /by <date in YYYY-MM-DD format>

      ```
      >>deadline return book /by 2019-12-01
      Got it, ive done the Following
      Added: return book (by: 2019-12-01)
      now you have: 4 tasks in this list.
      ```
   
6. `event` command:
   --> adds an Event item
   --> format: event <task_description> /from <start_date> /to <end_date>

      ```
      >>event project meeting /from Mon 2pm /to 4pm
      Got it, ive done the Following
      Added: project meeting  (from: Mon 2pm to: 4pm)
      now you have: 5 tasks in this list.
      ```

7. `delete` Command:
   --> deletes item from the todo list
   --> format: delete <index of item to delete>
      ```
      >>delete 2
      i have deleted the task: buy book
      ```
   
8. `find` Command
   --> searches for todo items that contain the search key term
      ```
      >>find buy
      -------TODO-LIST------
      ----------------------
      1. [T][ ]  buy jams
      2. [T][ ]  buy phone
      ----------------------
      ```
      
9. `bye` Command:
   --> exits the program

   ```
   >>bye
   Bye! see you soon!
   ```