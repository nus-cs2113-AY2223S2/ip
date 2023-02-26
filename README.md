# DukeRunner User Guide

DukeRunner is a simple task manager that allows you to add, view, and manage your tasks. You can add todos, deadlines, and events to the list. This program also allows you to save tasks into your local hard drive and read from the file created on startup.. Given below are instructions on how to use it.

## Getting Started
1. Download the DukeRunner jar file.
2. Launch your command line and go to the directory of the downloaded jar file.
3. Run the jar file using `java -jar DukeRunner.jar`. If the setup is correct, you should see something like the below as the output:
   ```
   
   ________         __         __________                                  
   \______ \  __ __|  | __ ____\______   \__ __  ____   ____   ___________ 
   |    |  \|  |  \  |/ // __ \|       _/  |  \/    \ /    \_/ __ \_  __ \
   |    `   \  |  /    <\  ___/|    |   \  |  /   |  \   |  \  ___/|  | \/
   /_______  /____/|__|_ \\___  >____|_  /____/|___|  /___|  /\___  >__|   
         \/           \/    \/       \/           \/     \/     \/
   Hello! I'm DukeRunner
   What can I do for you?

   ==============================
   ```

## Adding Tasks
To add a task, type the type of task followed by the description. For example, to add a todo, type `todo` followed by the description of the task:
`todo math homework`

To add a deadline, type `deadline` followed by the description and `/by` followed by the due date:
`deadline do science homework /by monday`

To add an event, type `event` followed by the description, `/from` followed by the start time, and `/to` followed by the end time:
`event school carnival /from Monday 9am /to 12pm`

## Viewing Tasks
To view all the tasks in the list, type `list`. DukeRunner will display all the tasks in the order they were added.

## Marking Tasks
You can mark tasks as completed and unmark them as uncompleted. To mark a task as completed, type `mark` followed by the task number
To unmark a task as uncompleted, type `unmark` followed by the task number:

## Deleting Tasks
To delete a task from the list, type `delete` followed by the task number:

## Finding Tasks
You can find tasks by typing `find` followed by the keyword you want to search for:

## Saving and Loading Tasks
DukeRunner automatically saves your tasks to a file on your local hard drive when the program ends. On startup, DukeRunner will read from the file and display the previously recorded tasks.

## Conclusion
Congratulations! You should now be able to use DukeRunner to manage your tasks more efficiently. If you have any further questions, please refer to the DukeRunner documentation or contact the DukeRunner support team.