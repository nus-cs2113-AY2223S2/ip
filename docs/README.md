# Duke User Guide

## What is Duke?
Duke is a command-line interface(CLI) application. Users can use Duke to keep track of their various types of tasks such as *todo*, *deadline* and *event* tasks.

## Quick Start
1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest *duke.jar* from [here](https://github.com/ChongQiRong/ip/releases).
3. Move *duke.jar* to your desired folder.
4. Launch your terminal or command prompt.
5. Navigate to the folder containing the *duke.jar* file. 
6. Use the `java -jar duke.jar` command to start the application.

## Features 
#### 1. Add task
Adds a task of *todo*, *deadline* or *event* to the list.
#### 2. List tasks
List out all the tasks in the list.
#### 3. Mark/unmark task
Mark or unmark a particular task as completed or uncompleted respectively.
#### 4. Delete task
Remove a task from the list.
#### 5. Find task
Find tasks in the list containing particular words.
#### 6. Save tasks
Automatically saves the list into a *duke.txt* file.
#### 7. Load tasks
Loads the list from a *duke.txt* file. If it does not exist, a new file will be created.

## Commands

### `todo` - Adds a *todo* task to the list
Adds a task of type *todo* to the list. Required to provide **description**.

Format: `todo <description>`

Example of usage:
`todo buy bak kwa`

Sample Output:
```
____________________________________________________________
Got it. I've added this task:
[T][] buy bak kwa
Now you have 1 task in the list
____________________________________________________________
```

### `deadline` - Adds a *deadline* task to the list
Adds a task of type *deadline* to the list. Required to provide **description** and **deadline**.

Format: `deadline <description> /by <due date>`

Example of usage:
`deadline put bak kwa in fridge /by 8pm`

Sample Output:
```
____________________________________________________________
Got it. I've added this task:
[D][] put bak kwa in fridge (by: 8pm)
Now you have 2 tasks in the list
____________________________________________________________
```

### `event` - Adds an *event* task to the list
Adds a task of type *event* to the list. Required to provide **description**, **start time** and **end time**.

Format: `event <description> /from <start time> /to <end time>`

Example of usage:
`event project meeting /from 7am /to 10am`

Sample Output:
```
____________________________________________________________
Got it. I've added this task:
[E][] project meeting (from: 7am to: 10am)
Now you have 3 tasks in the list
____________________________________________________________
```

### `list` - List out all tasks in the list
Lists all task from the tasklists. Indicates type of task, completion status and description

Format: `list`

Example of usage:
`list`

Sample Output:
```
____________________________________________________________
1.[T][] buy bak kwa
2.[D][] put bak kwa in fridge (by: 8pm)
3.[E][] project meeting (from: 7am to: 10am)
____________________________________________________________
```

### `mark` - Mark a task as completed
Marks a task as completed and will be marked with a cross [X]. Required to provide **index**.

Format: `mark <index>`

Example of usage:
`mark 2`

Sample Output:
```
____________________________________________________________
Nice! I've marked this task as done:
[D][X] put bak kwa in fridge (by: 8pm)
____________________________________________________________
```

### `unmark` - Unmark a task as not completed
Unmarks a task as not completed and will be marked with []. Required to provide **index**.

Format: `mark <index>`

Example of usage:
`mark 2`

Sample Output:
```
____________________________________________________________
Nice! I've marked this task as done:
[D][] put bak kwa in fridge (by: 8pm)
____________________________________________________________
```

### `delete` - Deletes a task to the list
Deletes a task from the list. Required to provide **index**.

Format: `delete <index>`

Example of usage:
`delete 1`

Sample Output:
```
____________________________________________________________
Got it. I've removed this task:
[T][] buy bak kwa
Now you have 2 tasks in the list
____________________________________________________________
```

### `find` - find tasks matching description provided
Prints out a list that matches description provided. Required to provide **description**.

Format: `find <description>`

Example of usage:
`find meeting`

Sample Output:
```
____________________________________________________________
Here are the matching tasks in your list:

2.[E][] project meeting (from: 7am to: 10am)
____________________________________________________________
```

### `bye` - Exits the application
Format: `bye`

Example of usage:
`bye`

Sample Output:
```
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```