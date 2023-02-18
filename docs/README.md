# User Guide - Duke

**Duke** is a Command Line Interface (CLI) based desktop application. It is a **Personal Assistant Chatbot** which can help the user to keep a track of their scheduled tasks and store the data.

## Features 

### Add task

Adds a task to the current list of tasks. The application supports three types of tasks: todo, deadline and event.

### Delete task

Deletes a task from the current list of tasks.

### Mark task

Marks a particular task as done in the task list.

### Unmark task

Marks a particular task as not done in the task list.

### List tasks

Displays all the tasks in the current task list.

### Find task

Finds all tasks in the task list that contain the word entered by the user.


## Usage

### `todo`
creates a ToDo and adds it to the task list

**Format:**

`todo <description>`

**Example:**

`todo read book`

**Expected outcome:**

'read book' will be added to the task list as a todo and currently be marked as not done.
```
____________________________________________________________
Got it. I've added this task:
	[T][ ] read book
Now you have 1 tasks in the list.
____________________________________________________________
```

### `deadline`
creates a Deadline and adds it to the task list

**Format:**

`deadline <description> /by <deadline>`

**Example:**

`deadline return book /by Sunday`

**Expected outcome:**

'return book' will be added to the task list as a deadline and currently be marked as not done. The deadline of the event will be stored as 'Sunday'.
```
____________________________________________________________
Got it. I've added this task:
	[D][ ] return book (by: Sunday)
Now you have 2 tasks in the list.
____________________________________________________________
```
### `event`
creates an Event and adds it to the task list

**Format:**

`event <description> /from <start date/time> /to <end date/time>`

**Example:**

`event project meeting /from Mon 2pm /to 4pm`

**Expected outcome:**

'project meeting' will be added to the task list as event and currently be marked as not done. The start date/time will be stored as 'Mon 2pm' and end date/time will be stored as '4pm'.
```
____________________________________________________________
Got it. I've added this task:
	[E][ ] project meeting (from: Mon 2pm to: 4pm)
Now you have 3 tasks in the list.
____________________________________________________________
```
### `delete`
removes a task from the list

**Format:**

`delete <index>`

**Example:**

`delete 1`

**Expected outcome:**

The task at index 1 is a todo: 'return book'. This task will be deleted.
```
____________________________________________________________
Noted. I've removed this task:
	[T][ ] read book
Now you have 2 tasks in your list.
____________________________________________________________
```
### `mark`
marks a task as done

**Format:**

`mark <index>`

**Example:**

`mark 2`

**Expected outcome:**

The task at index 2 is an event: 'project meeting'. This task will marked as done.
```
____________________________________________________________
Great! I have marked this task as done:
	[E][X] project meeting (from: Mon 2pm to: 4pm)
____________________________________________________________
```
### `unmark`
marks a task as not done

**Format:**

`unmark <index>`

**Example:**

`unmark 2`

**Expected outcome:**

The task at index 2 is an event: 'project meeting'. This task will marked as not done.
```
____________________________________________________________
OK, I've marked this task as not done yet:
	[E][ ] project meeting (from: Mon 2pm to: 4pm)
____________________________________________________________
```
### `list`
displays all tasks in the current task list

**Format:**

`list`

**Example:**

`list`

**Expected outcome:**

The current list has 2 tasks which will be displayed.
```
____________________________________________________________
Here are the tasks in your list:
	1.[D][ ] return book (by: Sunday)
	2.[E][ ] project meeting (from: Mon 2pm to: 4pm)
____________________________________________________________
```
### `find`
displays all tasks in the current task list containing the specified keyword

**Format:**

`find <substring>`

**Example:**

`find book`

**Expected outcome:**

The first task is the only task which contains the keyword 'book' so that will be displayed.
```
____________________________________________________________
Here are the matching tasks in your list:
	1.[D][ ] return book (by: Sunday)
____________________________________________________________
```
### `bye`
exits the application

**Format:**

`bye`

**Example:**

`bye`

**Expected outcome:**

Greets the user with a bye message and exits the application.
```
____________________________________________________________
Bye. Hope to see you again soon! :)
____________________________________________________________
```