# Duke - User Guide

## About

Duke is an application which uses a command-line interface(CLI). The user can use Duke's CLI to keep track of various
tasks such as *Todo*, *Deadline* and *Event* tasks.

## Quick Start

1. Ensure you have Java 11 or above installed in your computer.
2. Download the latest *duke.jar* from [here](https://github.com/KN-CY/ip/releases).
3. Copy the *duke.jar* file into your preferred empty folder.
4. Launch your terminal or command prompt and navigate to the folder containing the *duke.jar* file.
5. Use the `java -jar duke.jar` command to start the application.

## Features

#### Add task

Add a task into the task list.

#### Delete task

Delete the specified task from the task list.

#### Mark task as complete/incomplete

Mark a specified task as complete or incomplete.

#### List tasks

List all tasks in the task list.

#### Find tasks

Filters a list of tasks which task description matches a provided query.

#### Save tasks

Saves the task list into a file.

#### Load tasks

Loads the task list from a file.

## Usage

### `todo` - Add a todo task to the task list.

Adds a *Task* of type *Todo* to the task list, with the task description provided by the user input.

#### Format: `todo <task description>`

#### Example of usage:

```
todo adopt dog
____________________________________________________________
 Got it. I've added this task:
  [T][ ] adopt dog
 Now you have 1 tasks in the list.
____________________________________________________________
```

### `deadline` - Add a deadline task to the task list.

Adds a *Task* of type *Deadline* to the task list, with the task description and due date provided by the user input.

#### Format: `deadline <task description> /by <due date>`

#### Example of usage:

```
deadline feed doggo /by dinner time
____________________________________________________________
 Got it. I've added this task:
  [D][ ] feed doggo (by: dinner time)
 Now you have 2 tasks in the list.
____________________________________________________________
```

### `event` - Add an event task to the task list.

Adds a *Task* of type *Event* to the task list, with the task description, start time and end time provided by the user
input.

#### Format: `event <task description> /from <start time> /to <end time>`

#### Example of usage:

```
event play with doggo /from today 4pm /to tomorrow 6am
____________________________________________________________
 Got it. I've added this task:
  [E][ ] play with doggo (from: today 4pm to: tomorrow 6am)
 Now you have 3 tasks in the list.
____________________________________________________________
```

### `mark` - Mark a task as complete.

Marks a task of a specified index as complete.

#### Format: `mark <task index>`

#### Example of usage:

```
mark 1
____________________________________________________________
 Nice! I've marked this task as done:
   [T][X] adopt dog
____________________________________________________________
```

### `unmark` - Mark a task as incomplete.

Marks a task of a specified index as incomplete.

#### Format: `unmark <task index>`

#### Example of usage:

```
unmark 1
____________________________________________________________
 OK, I've marked this task as not done yet:
   [T][ ] adopt dog
____________________________________________________________
```

### `list` - List all tasks

Lists all tasks in the task list. Indicates the index of the task, the type of task, the completion status of the task
and the task description.

#### Format: `list`

#### Example of usage:

```
list
____________________________________________________________
 Here are the tasks in your list:
 1.[T][X] adopt dog
 2.[D][ ] feed doggo (by: dinner time)
 3.[E][ ] play with doggo (from: today 4pm to: tomorrow 6am)
____________________________________________________________
```

### `find` - Filters task list

Filters a list of tasks which task description matches a query provided by the user.

#### Format: `find <query>`

#### Example of usage:

```
find doggo
____________________________________________________________
 Here are the matching tasks in your list:
 1.[D][ ] feed doggo (by: dinner time)
 2.[E][ ] play with doggo (from: today 4pm to: tomorrow 6am)
____________________________________________________________
```

### `delete` - Deletes a task

Deletes a task of a specified index from the task list.

#### Format: `delete <task index>`

#### Example of usage:

```
delete 3
____________________________________________________________
 Noted. I've removed this task:
   [E][ ] play with doggo
 Now you have 2 tasks in the list.
____________________________________________________________
```

### Save tasks

The task list is saved automatically into the *savedlist.txt* file in the same folder as the *duke.jar* file. Do ensure
that you perform a normal exit using the `bye` command to save the file.

### Load tasks

Loads the task list from the *savedlist.txt* file in the same folder as the *duke.jar* file if it exists. If such a file
does not exist or is corrupted, the application will start with an empty task list.

### `bye` - Exits the application

Exits the application and saves the task list.

#### Format: `bye`

#### Example of usage:

```
bye
____________________________________________________________
File successfully saved to: ./savedlist.txt
____________________________________________________________
 Bye. Hope to see you again soon!
____________________________________________________________
```
