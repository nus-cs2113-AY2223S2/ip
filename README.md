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
   Save file loaded!
    ____        _        
   |  _ \ _   _| | _____ 
   | | | | | | | |/ / _ \
   | |_| | |_| |   <  __/
   |____/ \__,_|_|\_\___|
   Hello! I'm Duke!
   What can I do for you?
   ```

## Command List
1. todo (insert task description)
2. deadline (insert task description) /by (insert deadline)
3. event (insert task description) /from (insert start) /to (insert end)
4. find (insert search term)
5. mark (insert task number)
6. unmark (insert task number)
7. delete (insert task number)
8. list
9. help
10. bye

## Adding todo task: todo
Adds todo task to the task list.\
Format: todo task_description
Examples:
1. todo homework
2. todo CS2113 individual project

## Adding deadline task: deadline
Adds deadline task to the task list.\
Format: deadline task_description /by deadline
Examples:
1. deadline CS2113 individual project /by Friday 23 59
2. deadline That project due last week /by Last Friday night

## Adding event task: event
Adds events task to the task list.\
Format: event task_description /from start /to end
Examples:
1. event CS2113 Lecture /from Friday 16 00 /to 18 00
2. event EE2026 Midterms /from Sat 14 00 /to 15 30

## Searching task list: find
Searches task list for matching items.\
Format: find search_term
Examples:
1. find me a break time
2. find nap time

## Marking task as done: mark
Marks task on task list as done.\
Format: mark index_number
Examples:
1. mark 6
2. mark 9

## Marking task as not done: unmark
Marks task on task list as not done.\
Format: unmark index_number
Examples:
1. unmark 0
2. unmark 0
3. unmark 7

## Deleting task from task list: delete
Deletes task from task list.\
Format: delete index_number
Examples:
1. delete 2
2. delete 1

## Listing tasks from task list: list
Lists out task from task list.\
Format: list

## Listing commands from command list: help
Lists out commands.\
Format: help

## Closing Duke: bye
Ends the program.\
Format: bye