# User Guide for Duke Chatbot

Simple chatbot that helps you maintain a todo list with the data saved locally.
Built for CS2113 iP by Jared Oong.

## Starting up

Upon starting the program, you would be greeted by Duke who would attempt to load the previous todo list, or create a new file if it does not exist.

Here is the startup message if the file already exist:
```
----------------------------------------------------------------------------------------------------
| Hello! I'm Duke                                                                                  |
| Let me check the current list of tasks                                                           |
| Loading previous task list...                                                                    |
| Done loading previous task list:                                                                 |
| Loaded a total of 3 tasks!                                                                       |
----------------------------------------------------------------------------------------------------
```

Here is the startup message if the file does not exist:
```
----------------------------------------------------------------------------------------------------
| Hello! I'm Duke                                                                                  |
| Let me check the current list of tasks                                                           |
| Database file does not exist, creating one now!                                                  |
----------------------------------------------------------------------------------------------------
```

Here is the startup message if the file exist but is empty:
```
----------------------------------------------------------------------------------------------------
| Hello! I'm Duke                                                                                  |
| Let me check the current list of tasks                                                           |
| Database file is empty!                                                                          |
----------------------------------------------------------------------------------------------------
```

## Types of tasks

Below are the 3 types of tasks supported:

* `Todo`
    * Description of task
* `Deadline`
    * Description of task
    * Due date of task
* `Event`
    * Description of task
    * Start date of task
    * Due date of task

## Features

All the features listed below are supported for all 3 types of tasks.

### Adding tasks

Adds a new task into the todo list.

### Deleting tasks

Deletes a task from the todo list.

### Mark/Unmark tasks

Marks task from the todo list as completed/not completed.

### Find tasks

Finds task from the todo list that contains the keyword specified.

### List all tasks

Lists out all the tasks currently in the todo list.

### Exit program

Saves all the task into a local file before terminating the program.

## Usage

### `todo` - Adds a new Todo task to the list
Creates a new Todo task and adds this task to the current list. Duke will then print out the Todo added with the number of tasks in the list currently.

Method to call: `todo <task_description>`

Example of usage:
`todo Read a new book`

Expected outcome:
```
----------------------------------------------------------------------------------------------------
| Got it. I've added this todo:                                                                    |
|  [T][ ] Read a new book                                                                          |
| Now you have 4 tasks in the list.                                                                |
----------------------------------------------------------------------------------------------------
```

### `deadline` - Adds a new Deadline task to the list
Creates a new Deadline task and adds this task to the current list. Duke will then print out the Deadline added with the number of tasks in the list currently.

Method to call: `deadline <task> /by <endDate>`

Example of usage:
`deadline Borrow book from library /by today`

Expected outcome:
```
----------------------------------------------------------------------------------------------------
| Got it. I've added this deadline:                                                                |
|  [D][ ] Borrow book from library (by: today)                                                     |
| Now you have 5 tasks in the list.                                                                |
----------------------------------------------------------------------------------------------------
```

### `event` - Adds a new Event task to the list
Creates a new Event task and adds this task to the current list. Duke will then print out the Event added with the number of tasks in the list currently.

Method to call: `event <task> /from <startDate> /to <endDate>`

Example of usage:
`event Return book /from next week /to next month`

Expected outcome:
```
----------------------------------------------------------------------------------------------------
| Got it. I've added this event:                                                                   |
|  [E][ ] Return book (by: next week to: next month)                                               |
| Now you have 6 tasks in the list.                                                                |
----------------------------------------------------------------------------------------------------
```

### `list` - List out all tasks in the list
Lists out all tasks in the list with their status and start/end dates if applicable.

Method to call: `list`

Example of usage: `list`

Expected outcome:
```
----------------------------------------------------------------------------------------------------
| Here are the tasks in your list:                                                                 |
| 1.[T][ ] task 1                                                                                  |
| 2.[D][ ] task two (by: later)                                                                    |
| 3.[E][ ] task 3 (by: tomorrow to: yesterday)                                                     |
| 4.[T][ ] Read a new book                                                                         |
| 5.[D][ ] Borrow book from library (by: today)                                                    |
| 6.[E][ ] Return book (by: next week to: next month)                                              |
----------------------------------------------------------------------------------------------------
```

### `mark` - Marks task in list as completed
Sets the status of a task in the list to done.

Method to call: `mark <task_number>`

Example of usage: `mark 2`

Expected outcome:
```
----------------------------------------------------------------------------------------------------
| Nice! I've marked task 2 as done:                                                                |
| [D][X] task two (by: later)                                                                      |
----------------------------------------------------------------------------------------------------
```

### `unmark` - Marks task in list as not completed
Sets the status of a task in the list to not done.

Method to call: `unmark <task_number>`

Example of usage: `mark 2`

Expected outcome:
```
----------------------------------------------------------------------------------------------------
| OK, I've marked task 2 as not done yet:                                                          |
| [D][ ] task two (by: later)                                                                      |
----------------------------------------------------------------------------------------------------
```

### `delete` - Deletes task in the list
Deletes an existing task from the list. Duke prints out the task deleted and the new number of tasks left in the list.

Method to call: `delete <task_number>`

Example of usage: `delete 3`

Expected outcome:
```
----------------------------------------------------------------------------------------------------
| Noted. I've removed this task:                                                                   |
| [E][ ] task 3 (by: tomorrow to: yesterday)                                                       |
| Now you have 5 tasks in the list.                                                                |
----------------------------------------------------------------------------------------------------
```

### `find` - Find tasks in list that contains keyword in the description
Finds and prints out all the tasks that contains the keyword that you want to search for.
This method is case-sensitive.

Method to call: `find <keyword>`

Example of usage: `find book`

Expected outcome:
```
----------------------------------------------------------------------------------------------------
| Here are the matching tasks in your list:                                                        |
| 1.[T][ ] Read a new book                                                                         |
| 2.[D][ ] Borrow book from library (by: today)                                                    |
| 3.[E][ ] Return book (by: next week to: next month)                                              |
----------------------------------------------------------------------------------------------------
```

### `bye` - Exits the program
Duke automatically saves the current list into a local file. This database file will be used by
Duke to load this list again when it starts up again.

Method to call: `bye`

Example of usage: `bye`

Expected outcome:
```
----------------------------------------------------------------------------------------------------
| Saving current list of data into database...                                                     |
| Done saving list of tasks.                                                                       |
| Bye. Hope to see you again soon!                                                                 |
----------------------------------------------------------------------------------------------------
```
