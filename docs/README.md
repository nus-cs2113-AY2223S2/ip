# User Guide

Dude is a light-weight task-managing application that can be used on a command line interface (CLI). This is a summary on how it works.

## Quick Start

1. Ensure you have `Java 11` or above installed in your computer.
2. Download the latest `ip.jar` from [here](https://github.com/Rayleigh47/ip/releases/).
3. Copy the file to the folder you want to use as your home folder.
4. Open a command line terminal and change directory into the folder.
5. Run the application by entering into the terminal `java -jar ip.jar`

## Features 

### 1. List out all tasks: `list`

Format: `list`

Displays the full list being tracked.

Use case: `list`

### 2. Add a Todo task: `todo`

Format: `todo {DESCRIPTION} `

Adds a todo to the list with a description of the task.

Use case: `todo homework tonight`

### 3. Add a Deadline task: `deadline`

Format: `deadline {DESCRIPTION} /by {DEADLINE}`

Adds a deadline to the list with a description of the task and a deadline. 

`{DEADLINE}` should be in the format `yyyy-mm-dd`.

Use case: `deadline homework /by 2023-03-04`

### 4. Add a Event task: `event`

Format: `event {DESCRIPTION} /from {START} /to {END}`

Adds an event to the list with a description of the task, a start time and an end time.

Use case: `event homework /from 5pm /to 8pm`

### 5. Mark task as done: `mark`

Format: `mark {TASK_ID}`

Marks the task with a specified id in the list as done.

`{TASK_ID}` should be in the format of a positive integer.

Use case: `mark 1`

### 6. Mark task as not done: `unmark`

Format: `unmark {TASK_ID}`

Marks the task with a specified id in the list as not done.

`{TASK_ID}` should be in the format of a positive integer.

Use case: `unmark 2`

### 7. Delete a task: `delete`

Format: `delete {TASK_ID}`

Deletes the task with a specified id in the list and removes it from the list.

`{TASK_ID}` should be in the format of a positive integer.

Use case: `delete 1`

### 8. Search for task using keyword: `find`

Format: `find {KEYWORD}`

Displays the list of tasks that contains a given keyword from the user.

`{KEYWORD}` should be a single word and is case-insensitive

Use case: `find home`

### 9. Exit program: `bye`

Format: `bye`

Exits the program.

Use case: `bye`

### 10. Loading and Saving of data

The task list is updated as a `duke.txt` file and saved in `data/duke.txt` when the task list
is changed. 

When Dude is launched, it is able to retrieve the latest save data, else it will
create a new folder and file for subsequent use.
