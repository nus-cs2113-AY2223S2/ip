# User Guide

Shizuka is a desktop app for managing tasks, optimized for use via a Command Line Interface (CLI) while simulating a
Graphical User Interface (GUI). If you can type fast, Shizuka can get your task management done just as fast as
traditional GUI apps.

## Quick Start

1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest shizuka.jar from here.
3. Copy the file to the folder you want to use as the home folder for your Shizuka.
4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app
   contains some sample data.<br>
5. Type the command in the command box and press Enter to execute it.<br>
   Some example commands you can try:
    * `list`: Lists all tasks.
    * `todo`: Adds a todo task.
    * `delete`: Deletes a task.
    * `bye`: Exits the app.
6. Refer to the Features below for details of each command.

## Features

### Adding a todo task: `todo`

Adds a todo task to the task list. <br>
Format: `todo DESCRIPTION` <br>
Example: `todo read book`

### Adding a deadline task: `deadline`

Adds a deadline task to the task list.<br>
Format: `deadline DESCRIPTION /by DEADLINE`<br>
Example: `deadline return book /by 2021-08-25`

### Adding an event task: `event`

Adds an event task to the task list. <br>
Format: `event DESCRIPTION /from START /to END`<br> 
Example: `event project meeting /from 2021-08-25 14:00 /to 2021-08-25 16:00`

### Listing all tasks: `list`

Shows a list of all tasks in the task list. <br>
Format: `list` <br>

### Marking a task as done: `mark`

Marks a task as done in the task list. <br>
Format: `mark TASK_NUMBER` <br>
Example: `mark 1`

### Marking a task as not done: `unmark`

Marks a task as not done in the task list. <br>
Format: `unmark TASK_NUMBER` <br>
Example: `unmark 1`

### Deleting a task: `delete`

Deletes the specified task from the task list. <br>
Format: `delete TASK_NUMBER` <br>
Example: `delete 1`

### Finding tasks by keyword: `find`

Find tasks whose description contains the given keyword. <br>
Format: `find KEYWORD` <br>
Example: `find book`

### Saving the Todo List: `save`

Saves the current task list to a file. <br>
Format: `save`

### Exiting the program: `bye`

Exits the program. <br>
Format: `bye`

