# Duke Task Manager

This is a simple task manager program that allows users to add, mark, unmark, delete and save tasks. These tasks are of three types: todo, deadline and event.
These tasks are stored in a file, where users can access this list of tasks at any time.

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

### How to Use

1. Clone the repository to your local machine
2. Open a terminal window and navigate to the directory containing the files
3. Run the program by running the following command `java -jar Duke.jar`
4. The program will display a welcome message and ask for user input.
5. Use these commands to interact with the task manager: 
   1. `list`: Lists all tasks in the task list.
   2. `todo <description>`: Adds a to-do task to the task list with the given description.
   3. `deadline <description> /by <datetime>`: Adds a deadline task to the task list with the given description and datetime.
   4. `event <description> /from <datetime> /to <datetime>`: Adds an event task to the task list with the given description and datetimes.
   5. `mark <task number>`: Marks the task with the given number.
   6. `unmark <task number>`: Unmarks the task with the given number.
   7. `delete <task number>`: Deletes the task with the given number.
   8. `find <keyword>`: Lists all the tasks in the task list that contains the keyword.
   9. `bye`: Exits the program and saves the task list to a file.
6. After each command, Duke will display a message indicating whether the command was successful or if there was an error.

### File Storage System

Duke stores the list of tasks in a file called duke_list.txt in the src directory. The program will create the file if it does not already exist. When the program exits, it will save the current list of tasks to the file. If the file already exists, the program will read the list of tasks from the file when it starts up.
