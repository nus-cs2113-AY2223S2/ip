# User Guide

## How to launch
1. Download the jar file provided on Github
2. Run the jar file using the absolute path (eg C:..\ip_jar\ip.jar)

## Features

### Feature - Add Tasks (ToDo, Deadline and Event)

Users are able to add different tasks to a list and save them.

### Feature - Delete Tasks

Users are able to delete existing tasks if the tasks are not needed.

### Feature - List Tasks

Users are able to display all existing tasks in the list.

### Feature - Mark/Unmark Tasks

Users are able to mark/unmark tasks to represent completion status.

### Feature - Find Tasks

Users are able to find tasks related to provided keyword.


## Usage

### `todo` - Add todo

A todo task will be added into the list.

Example of usage:

`todo homework`

Expected outcome:

"homework" will be added into the list as a todo task.

```
 Got it. I've added this task:
   [T][ ] homework
 Now you have 1 task in the list.
```
---
### `event` - Add event

An event task will be added into the list.

Example of usage:

`event CS2113 lecture /from Friday 4 /to 6pm`

Expected outcome:

"CS2113 lecture" will be added into the list as an event task.

```
 Got it. I've added this task:
   [E][ ] CS2113 lecture (from: Friday 4 to: 6pm)
 Now you have 2 tasks in the list.
```
---
### `deadline` - Add deadline

A deadline task will be added into the list.

Example of usage:

`deadline weekly CS2113 quiz /by Monday 9pm`

Expected outcome:

"weekly CS2113 quiz" will be added into the list as a deadline task.

```
 Got it. I've added this task:
   [D][ ] weekly CS2113 quiz (by: Monday 9pm)
 Now you have 3 tasks in the list.
```
---
### `list` - List all existing tasks

All existing tasks in the list will be displayed.

Example of usage:

`list`

Expected outcome:

```
Here are the tasks in your list
 1.[T][ ] homework
 2.[E][ ] CS2113 lecture (from: Friday 4 to: 6pm)
 3.[D][ ] weekly CS2113 quiz (by: Monday 9pm)
```
---
### `mark` - Mark task

Marks a task in the list.

Example of usage:

`mark 1`

Expected outcome:

"homework" will be marked with an X.

```
Nice! I've marked this task as done:
  [X] homework
```
---
### `unmark` - Unmark task

Unmarks a task in the list.

Example of usage:

`unmark 1`

Expected outcome:

"homework" will be unmarked.

```
OK, I've marked this task as not done yet:
  [ ] homework
```
---
### `delete` - Delete task

Deletes a task from the list.

Example of usage:

`delete 1`

Expected outcome:

"homework" will be deleted.

```
Noted. I've removed this task: 
  [T][ ] homework
Now you have 2 tasks in the list.
```
---
### `find` - Find tasks

Find tasks based on the given keyword.

Example of usage:

`find lecture`

Expected outcome:

Tasks that contains the word "lecture" will be displayed.

```
 Here are the matching tasks in your list:
 1.[E][ ] CS2113 lecture (from: Friday 4 to: 6pm)
```
---