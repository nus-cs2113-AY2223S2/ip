# User Guide

`Luke` is a desktop application for keeping track of your daily tasks. It is optimized for
use via a Command Line Interface (CLI).

## Quick Start
- Ensure you have `Java 11` installed on your computer. You can check your `Java` version 
using the following command in the terminal.
```
java --version
```
- Download the `.jar` file from the latest release [here](https://github.com/heejet/ip/releases).
- To run `Luke`, open the terminal and change the current directory to **same one as the `jar` 
file**.
- Run the following command in the terminal.
````
java -jar luke.jar
````

## Features

### Able to handle 3 different types of tasks.

Using `Luke` you can organize your life by adding tasks of the following 3 types.

- `ToDo` tasks (`[T]`).

  - These are the most basic form of task which only have a `description`.

- `Deadline` tasks (`[D]`).

    - These are tasks that have to be completed by a specific `deadline`.
    - It contains both a `description` and a `deadline`.
  
- `Event` tasks (`[E]`).
 
  - These are tasks that take place over a specific time period.
  - It contains a `description` as well as a `start` and `end`.

### Saving of Data

Any changes to tasks are saved locally in the hard disk. You can reload the program and
any task(s) added will be saved.

## Usage

### `add` - AddTasks

Adds a task to be tracked by `Luke`.

#### ToDo Tasks

Format:

````
add todo TASK_DESCRIPTION
````

- **Compulsory:** `TASK_DESCRIPTION` corresponds to the description of the task.

Example of usage:

````
add todo This is todo task 1
````

Expected outcome:

- Adds a task with the following information to `Luke`.
    - `TASK_DESCRIPTION`: `This is todo task 1`
- A confirmation by `Luke` that the task have been added.

Description of the outcome.

```
____________________________________________________________
[Luke]: Added: This is todo task 1
____________________________________________________________
```
#### Deadline Tasks

Format:

````
add deadline TASK_DESCRIPTION | TASK_DEADLINE_DATE TASK_DEADLINE_TIME
````

- **Compulsory:** `TASK_DESCRIPTION` corresponds to the description of the task.
- **Compulsory:** `TASK_DEADLINE_DATE` corresponds to the date the task is due.
  - **Important** The format for `TASK_DEADLINE_DATE` is `YYYY-MM-DD`.
- `TASK_DEADLINE_TIME` corresponds to the time the task is due.
  - **Important** The format for `TASK_DEADLINE_TIME` is `HH:MM` where the time is in
    24 Hour clock format.
- If `TASK_DEADLINE_TIME` is left empty, it will be given a default value of `00:00`.

Example of usage:

````
add deadline This is deadline task 1 | 2023-03-01 18:00
````

Expected outcome:

- Adds a task with the following information to `Luke`.
  - `TASK_DESCRIPTION`: `This is deadline task 1`
  - `TASK_DEADLINE_DATE`: `2023-03-01`
  - `TASK_DEADLINE_TIME`: `18:00`
- A confirmation by `Luke` that the task have been added.

Description of the outcome.

```
____________________________________________________________
[Luke]: Added: This is deadline task 1
____________________________________________________________
```
#### Event Tasks

Format:

````
add event TASK_DESCRIPTION | TASK_START_DATE TASK_START_TIME | TASK_END_DATE TASK_END_TIME
````

- **Compulsory:** `TASK_DESCRIPTION` corresponds to the description of the task.
- **Compulsory:** `TASK_START_DATE` corresponds to the date the event starts.
  - **Important** The format for `TASK_START_DATE` is `YYYY-MM-DD`.
- `TASK_START_TIME` corresponds to the time the event starts.
  - **Important** The format for `TASK_START_TIME` is `HH:MM` where the time is in
    24 Hour clock format.
- **Compulsory:** `TASK_END_DATE` corresponds to the date the event ends.
  - **Important** The format for `TASK_END_DATE` is `YYYY-MM-DD`.
- `TASK_END_TIME` corresponds to the time the event ends.
  - **Important** The format for `TASK_END_TIME` is `HH:MM` where the time is in
    24 Hour clock format.
- If `TASK_START_TIME` or `TASK_END_TIME` is left empty, it will be given a default value of `00:00`.

Example of usage:

````
add event This is event task 1 | 2023-03-01 18:00 | 2023-03-01 22:00
````

Expected outcome:

- Adds a task with the following information to `Luke`.
    - `TASK_DESCRIPTION`: `This is deadline task 1`
    - `TASK_START_DATE`: `2023-03-01`
    - `TASK_START_TIME`: `18:00`
    - `TASK_END_DATE`: `2023-03-01`
    - `TASK_END_TIME`: `22:00`
- A confirmation by `Luke` that the task have been added.

Description of the outcome.

```
____________________________________________________________
[Luke]: Added: This is deadline task 1
____________________________________________________________
```
### `list` - Prints Tasks

Print a list of all the tasks that are being tracked by `Luke`.

Format:
````
list
````

Example of usage (At least 1 task being tracked by `Luke`):

````
list
````

Expected outcome:

- Prints a list of the tasks that have been added to `Luke`.
- **Important:** Tasks will be printed with a specific `serialNumber`.

Description of the outcome.

```
____________________________________________________________
[Luke]: Printing Tasks...
1. [T][ ] todoTask1
2. [D][ ] deadlineTask1 (Due: dueDate1)
____________________________________________________________
```
Example of usage (No tasks being tracked by `Luke`):

````
list
````

Expected outcome:

- A messing infroming the user that there are no tasks to list.

Description of the outcome.

```
____________________________________________________________
[Luke]: The list is empty!
____________________________________________________________
```
### `mark` - Marks Task

Marks the task, registering it as complete.

Format:
````
mark TASK_SERIAL_NUMBER
````

- **Compulsory:** `TASK_SERIAL_NUMBER` corresponds to `serialNumber` of the task when the 
`list` command is used.

Example of usage:

````
mark 1
````

Expected outcome:

- Marks the task corresponding to `TASK_SERIAL_NUMBER` as **complete**.
- Infroms the reader that the task have been **marked**.

Description of the outcome.

```
____________________________________________________________
[Luke]: todoTask1 has been marked as complete.
____________________________________________________________
```
### `unmark` - Unarks Task

Unmarks the task, registering it as incomplete.

Format:
````
umark TASK_SERIAL_NUMBER
````

- **Compulsory:** `TASK_SERIAL_NUMBER` corresponds to `serialNumber` of the task when the
  `list` command is used.

Example of usage:

````
umark 1
````

Expected outcome:

- Marks the task corresponding to `TASK_SERIAL_NUMBER` as **incomplete**.
- Informs the reader that the task have been **unmarked**.

Description of the outcome.

```
____________________________________________________________
[Luke]: todoTask1 has been marked as incomplete.
____________________________________________________________
````
### `delete` - Deletes Task

Deletes a task from `Luke`.

Format:
````
delete TASK_SERIAL_NUMBER
````

- **Compulsory:** `TASK_SERIAL_NUMBER` corresponds to `serialNumber` of the task when the
  `list` command is used.

Example of usage:

````
delete 1
````

Expected outcome:

- Deletes the task corresponding to `TASK_SERIAL_NUMBER` from `Luke`.
- Informs the reader that the task have been deleted.

Description of the outcome.

````
____________________________________________________________
[Luke]: todoDelete1 has been deleted.
____________________________________________________________
````
### `find` - Finds Task

Finds and list all tasks that contain a keyword that the user have entered.

Format:
````
find TASK_QUERY
````

- **Compulsory:** `TASK_QUERY` corresponds to a string that the user is searching for
in the `descriptions` of the tasks.

Example of usage:

````
find keyword3
````

Expected outcome:

- Prints a list of tasks with `description` matching `TASK_QUERY`.

Description of the outcome.

````
____________________________________________________________
[Luke]: Printing matching tasks...
1. [T][ ] keyword3 keyword2
2. [E][ ] keyword3 keyword1 (From: start to: end)
____________________________________________________________
````
### `bye` - Quits the Program

Exits `Luke`.

Format:
````
bye
````

Example of usage:

````
bye
````

Expected outcome:

- Prints a goodbye message from `Luke`.
- Exits the program.

Description of the outcome.

```
____________________________________________________________
[Luke]: Bye. Hope to see you again soon!
____________________________________________________________
```