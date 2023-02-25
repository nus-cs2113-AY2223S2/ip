# Duke User Guide

**Duke** is a **Task Tracking Application** that helps the program user keep track of his / her tasks. **Duke**
is used specifically via a **Command Line Interface(CLI)** and thus may work better than applications that uses
a Graphical User Interface(GUI) for users who can type fast.

### Table of Contents:

1. Quick Start
2. Features
    1. List all tasks: `list`
    2. Find specific tasks: `find`
    3. Mark task as done: `mark`
    4. Mark task as not done: `unmark`
    5. Remove a task: `delete`
    6. Create a "todo" task: `todo`
    7. Create a "deadline" task: `deadline`
    8. Create an "event" task: `event`
    9. Terminate program:`bye`
    10. Saving the data
3. Command Summary

## Quick Start

1. Upon downloading `duke.jar`, transfer the `duke.jar` file to an empty folder in your computer using **File Explorer**.
2. While viewing `duke.jar` inside its own folder, go to the **Address Bar** of the **File Explorer Tab** and type `cmd`
   to open the command prompt.
3. Inside the command prompt, type `java -jar duke.jar` to launch the **Duke** Program. Note that the command prompt
   should be displaying the file address of the `duke.jar` file.
4. Upon launching the **Duke** Program, the program should inform the user that a `.txt` file has not yet been created
   for file storage, and will attempt to create a new file. User's screen should look as followed:

    ```
   ___________________________________________________________________________________
   File cannot be found! Creating new file for data storage...
   ___________________________________________________________________________________
   ___________________________________________________________________________________
   File Created Successfully! File stored at: C:\Users\ng_ka\OneDrive\Documents\ip\duke.txt
   ___________________________________________________________________________________
   ___________________________________________________________________________________
   Hello! I'm Duke
   What can I do for you?
   ___________________________________________________________________________________
    ```
5. Users can now type a series of commands to instruct **Duke** on what to do next.
6. Refer to the **Features** below to find out more about the available commands in the **Duke** Program.

## Features

### List all tasks: `list`
Shows a list of all tasks stored in the database, regardless of whether the task is recently added or already found in the database.

Format: `list`

### Find specific tasks: `find`
Scan through the database to generate a list of tasks that contains the keyword inputted by the user. The list generated tells the
user the total number of tasks containing the keyword, as well as the `INDEX` of the task in the entire database.

Format: `find KEYWORD`
- Searches the list for the `KEYWORD`.
- `KEYWORD` refers to the word or letter contained in the task description of the user's choosing.
- The `KEYWORD` **cannot** be empty.

Examples:
- `find homework`: Searches the list for tasks with the description containing the keyword `homework`.
- `find paint the`: Searches the list for tasks whose description contains the two keywords `paint the`.

### Mark task as done: `mark`
Marks a specific task found in the list of tasks as **completed**.

Format: `mark INDEX`
- Marks the task at `INDEX` as **done**.
- The `INDEX` refers to the position of the task in the list.
- `INDEX` has to be a **positive integer** and **less than or equal to** the total tasks found in the list.

Examples:
- `mark 1`: **Marks** the task of `INDEX` 1 (The first task in the list) with a cross, signifying that it is completed.
- `mark 7`: **Marks** the task of `INDEX` 7 (The seventh task in the list) with a cross, signifying that it is completed.

### Mark task as not done: `unmark`
Marks a specific task found in the list of tasks as **NOT completed**.

Format: `mark INDEX`
- Marks the task at `INDEX` as **NOT done**.
- The `INDEX` refers to the position of the task in the list.
- `INDEX` has to be a **positive integer** and **less than or equal to** the total tasks found in the list.
- It does not matter whether task found at `INDEX` is already unmarked.

Examples:
- `unmark 2`: **Removes** the cross found on task of `INDEX` 2 (The second task in the list), signifying that it is NOT completed.
- `unmark 5`: **Removes** the cross found on task of `INDEX` 5 (The fifth task in the list), signifying that it is NOT completed.

### Remove a task: `delete`
Deletes a specific task found in the list.

Format: `delete INDEX`
- Deletes the task found at `INDEX`.
- `INDEX` refers to the position of the task in the task list.
- `INDEX` has to be a **positive integer** and **less than or equal to** the total tasks found in the list.

Examples:
- `delete 3`: Deletes the third task in the task list.
- `delete 4`: Deletes the fourth task in the task list.

### Create a "todo" task: `todo`
Creates a task with the category type of `todo`, and stores it in the task list.

Format: `todo DESCRIPTION`
- `DESCRIPTION` refers to the name of the task to be declared as a `todo` task type.
- `DESCRIPTION` cannot be empty.

Examples:
- `todo Water the plants`: Creates a `todo` task with the description `Water the plants` and store it in the task list.
- `todo sleep`: Creates a `todo` task with the description `sleep` and store it in the task list.

### Create a "deadline" task: `deadline`
Creates a task with the category type of `deadline`, and stores it in the task list. Tasks of the `deadline` category
contain both a description of the task and the due date of the task of which it should be completed.

Format: `deadline DESCRIPTION /by DUEDATE`
- `DESCRIPTION` refers to the name of the task to be declared as a `deadline` task type.
- `DUEDATE` refers to the user determined time in which the task should be completed.
- `DESCRIPTION` and `DUEDATE` **should not** be empty.
- `/by` tells the program to store whatever typed **afterwards** as `DUEDATE`, and **everything before** as `DESCRIPTION`.
- `/by` should **only be typed once**.

Examples:
- `deadline Do CS2113 Assignment /by 1st March 2023`: Creates a `deadline` task with the description `Do CS2113 Assignment`
  and the due date recorded as `1st March 2023`, before storing it in the task list.
- `deadline Finish Revising /by Tomorrow :(`: Creates a `deadline` task with the description `Finish Revising` and the
  due date recorded as `Tomorrow :(`, before storing it in the task list.

### Create an "event" task: `event`
Creates a task with the category type of `event`, and stores it in the task list. Tasks of the `event` category contain
a description of the task, a start date and end date for the event.

Format: `event DESCRIPTION /from STARTDATE /to ENDDATE`
- `DESCRIPTION` refers to the name of the task to be declared as a `event` task type.
- `DESCRIPTION` **cannot** be empty.
- `/from` and `/to` are used for differentiating between the start and end dates inputted by the user.
- `/from` and `/to` should only be typed **once each**, but sequence in which they are typed are interchangeable.
- `STARTDATE` should be typed **after** `/from`, and `ENDDATE` should be typed **after** `/to`.
- `STARTDATE` and `ENDDATE` **should not** be empty.

Examples:
- `event Attend John's Birthday /from 6pm /to 9pm`: Creates an `event` task with the description `Attend John's Birthday`
  , **start date** as `6pm` and **end date** as `9pm`, before storing it in the task list.
- `event Holiday in France /to Next Week /from This Week`: Creates an `event` task with the description `Holiday in France`
  , **start date** as `This Week` and **end date** as `Next Week`, before storing it in the task list.

### Terminate program:`bye`
Terminates the program and saves the contents of the task list into the database.

Format: `bye`
- The command **must not** contain any unnecessary words at the back. (i.e. `bye duke`, `bye program!`)

### Saving the data
Data is automatically saved upon successful termination of the program using the command: `bye`. However, if
the **Duke** program is abruptly terminated, current progress **will not** be saved and will **revert** to the previous
version of the task list, if any.

## Command Summary
| Command    | Format                                                                                                     |
|:-----------|:-----------------------------------------------------------------------------------------------------------|
| `list`     | `list`                                                                                                     |
| `find`     | `find INDEX`<br/>e.g., `find 3`                                                                            |
| `mark`     | `mark INDEX`<br/>e.g, `mark 1`                                                                             |
| `unmark`   | `unmark INDEX`<br/>e.g., `unmark 5`                                                                        |
| `delete`   | `delete INDEX`<br/>e.g., `delete 2`                                                                        |
| `todo`     | `todo DESCRIPTION`<br/>e.g., `todo sleep`                                                                  |
| `deadline` | `deadline DESCRIPTION /by DUEDATE`<br/>e.g., `deadline Do CS2113 Assignment /by 1st March 2023`            |
| `event`    | `event DESCRIPTION /from STARTDATE /to ENDDATE`<br/>e.g., `event Attend John's Birthday /from 6pm /to 9pm` |
| `bye`      | `bye`                                                                                                      |




