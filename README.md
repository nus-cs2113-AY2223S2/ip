# Duke Project User Guide

This is a project template for a greenfield Java project. It's named after the Java mascot _Duke_. Given below are instructions on how to use it.

## Setting up Project in Intellij IDE

Prerequisites: JDK 11, Update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
2. Open the project into Intellij as follows:
   1. Click `Open`.
   2. Select the project directory, and click `OK`.
   3. If there are any further prompts, accept the defaults.
3. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
4. After that, locate the `src/main/java/Duke.java` file, right-click it, and choose `Run Duke.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:

   ```
   Hello from
    ____        _        
   |  _ \ _   _| | _____ 
   | | | | | | | |/ / _ \
   | |_| | |_| |   <  __/
   |____/ \__,_|_|\_\___|
   ______________________________________________________________________
    Hello! I'm Duke, your personal task manager.
    What can I do for you?
   ______________________________________________________________________
   ```
## Duke Supported Commands

Here is the list of all operations supported by Duke Task Manager:

1. The `list` Operation
   1. Displays the List of all Tasks in Duke Task List.
   2. **Command Format:** list
   
      ```
      list
      ______________________________________________________________________
       Here are the tasks in your list:
        1.  [T][X] read book
        2.  [D][ ] submit report (by Friday 3pm)
        3.  [E][ ] disco party (from 3pm to 5pm)
        4.  [D][X] return book (by June 6th)
      ______________________________________________________________________
      ```
2. The `mark` Operation
   1. Marks the Task as **Complete** in Duke Task List.
   2. **Command Format:** mark <enter_task_index>
   
      ```
      mark 2
      ______________________________________________________________________
       Nice! I've marked this task as done:
        [D][X] submit report (by Friday 3pm)
      ______________________________________________________________________
      ```
3. The `unmark` Operation
   1. Marks the Task as **Not Complete** in Duke Task List.
   2. **Command Format:** unmark <enter_task_index>
   
      ```
      unmark 2
      ______________________________________________________________________
       Okay, I've marked this task as not done yet:
        [D][ ] submit report (by Friday 3pm)
      ______________________________________________________________________
      ```
4. The `todo` Operation
   1. Creates a **Todo Task** in Duke Task List.
   2. **Command Format:** todo <enter_todo_description>
   
      ```
      todo dance practice
      ______________________________________________________________________
       Got it. I've added this task:
        [T][ ] dance practice
       Now you have 5 tasks in your list.
      ______________________________________________________________________
      ```
5. The `deadline` Operation
   1. Creates a **Deadline Task** in Duke Task List.
   2. **Command Format:** deadline <enter_deadline_description> /by <enter_deadline_due_date>
   
      ```
      deadline submit report final draft /by Tuesday 12pm
      ______________________________________________________________________
       Got it. I've added this task:
        [D][ ] submit report final draft (by Tuesday 12pm)
       Now you have 6 tasks in your list.
      ______________________________________________________________________
      ```
6. The `event` Operation
   1. Creates an **Event Task** in Duke Task List.
   2. **Command Format:** event <enter_event_description> /from <enter_event_start> /to <enter_event_end>
   
      ```
      event Birthday Party /from Wednesday 2pm /to 5pm
      ______________________________________________________________________
       Got it. I've added this task:
        [E][ ] Birthday Party (from Wednesday 2pm to 5pm)
       Now you have 7 tasks in your list.
      ______________________________________________________________________
      ```
7. The `delete` Operation
   1. Deletes the specified task from the Duke Task List given the **Task Index**.
   2. **Command Format:** delete <enter_task_index>
   
      ```
      delete 7
      ______________________________________________________________________
       Noted. I've deleted this task:
        [E][ ] Birthday Party (from Wednesday 2pm to 5pm)
       Now you have 6 tasks in your list.
      ______________________________________________________________________
      ```
8. The `find` Operation
   1. Finds and displays the list of task from Duke Task List matching the **Search Keyword**.
   2. **Command Format:** find <search_keyword>
   
      ```
      find book
      ______________________________________________________________________
       Here are the matching tasks in your list:
        1.  [T][X] read book
        2.  [D][X] return book (by June 6th)
      ______________________________________________________________________
      ```
9. The `bye` Operation
   1. **Exits** the Duke Program.
   2. **Command Format:** bye
   
      ```
      bye
      ______________________________________________________________________
       Bye. Hope to see you again soon!
      ______________________________________________________________________
      ```