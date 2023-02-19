# User Guide

Duke CLI application is a NUS CS2113 Y2S2 individual project created to manage your tasks. There are 3 different types of task you can track. Unfortunately, there is no GUI provided right now, and we are assuming that the user is proficient in CLI.

- [Features](#features)
- [Usage](#usage)
    -    [List all task :`list`](#list---listing-all-task)
    -    [Marks a task :`mark`](#mark---mark-a-task-as-done)
    -    [Unmarks a task :`unmark`](#unmark---unmark-a-task-as-not-done)
    -    [Deletes a task :`delete`](#delete---deletes-a-task-in-list)
    -    [Finds tasks :`find`](#find---finds-task-with-keyword)
    -    [Save and exit :`bye`](#bye---save-and-exit)
    

## Features 

### Feature - Track tasking

Allows user to track different types of task <ToDo, Deadline, Event> with datetime integration.

## Usage

### `list` - Listing all task

Lists the task duke currently stores

Example of usage: 

`list`

Expected outcome:

Prints the tasks stored if there exist any. Else informs user that there is no task.

```
    _______________________________________
     1. [D][ ] return book (by: 2/12/2019 1800)
     2. [E][ ] return more books (from: 2/12/2019 2000 to: 2/12/2019 1800)
    ________________________________________
```

### `mark` - Mark a task as done

Marks the given taskIndex if it is not yet marked, else inform user that task is already marked.

Example of usage: 

`mark <int: taskIndex>`

Expected outcome:

```
    ________________________________________
     Nice! I've marked this task as done:
      [T][X] eat
    ________________________________________
```

### `unmark` - Unmark a task as not done

Unmarks the given taskIndex if it is marked, else inform user that task has not been marked.

Example of usage: 

`unmark <int: taskIndex>`

Expected outcome:

```
    ________________________________________
     Nice! I've marked this task as done:
      [T][X] eat
    ________________________________________
```

### `delete` - Deletes a task in list

Deletes a task based on the index specified. If the task index does not exist or is invalid.

Example of usage: 

`delete <int: taskIndex>`

Expected outcome:

```
    ________________________________________
     Noted I've removed this task:
     [T][ ] eat
     Now you have 0 tasks in the list.
    ________________________________________

// If there is an invalid taskIndex
     ________________________________________
     Task to be deleted is out of range!
    ________________________________________
```

### `find` - Finds task with keyword

Finds task with matching keyword.

Example of usage: 

`find <String: keyword>`

Expected outcome:

Find task that contains matching keyword in their description.

```
    ________________________________________
     1. [T][ ] eat
    ________________________________________
```

### `bye` - Save and exit

Saves tasks into text file and exits program.

Example of usage: 

`bye`

Expected outcome:

```
    ________________________________________
     Bye. Hope to see you again soon!
    ________________________________________
```