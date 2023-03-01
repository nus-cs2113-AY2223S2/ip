# User Guide

## Features 

### Add ToDo tasks

User can add ToDo tasks (tasks without any dates involved) to the list managed by ChatTPG.

### Add Deadline tasks

User can add Deadline tasks to the list managed by ChatTPG.

### Add Event tasks

User can add Event tasks to the list managed by ChatTPG.

### Mark tasks

User can mark tasks to the list managed by ChatTPG.

### Unmark tasks

User can unmark tasks to the list managed by ChatTPG.

### Delete tasks

User can delete tasks from the list managed by ChatTPG.

### Find tasks

User can filter tasks in the list managed by ChatTPG according to the keyword specified.

## Usage

### `mark` - Marks a specified task as done

Specifies a task to be marked as done in the list.

Example of usage: 

`mark <taskNumber>`

Expected outcome:

Specified task will be marked as done in the list.

```
Nice! I've marked this task as done:
[T][X] read book
```
### `unmark` - Marks a specified task as not done

Specifies a task to be marked as not done in the list.

Example of usage:

`unmark <taskNumber>`

Expected outcome:

Specified task will be marked as not done in the list.

```
OK, I've marked this task as not done yet:
[T][ ] read book
```
### `todo` - Creates a ToDo task

Creates a new ToDo task and adds it to the list.

Example of usage:

`todo <taskDescription>`

Expected outcome:

A new ToDo task will be created with the specified description and added to the list.

```
Got it. I've added this task:
[T][ ] read book
Now you have 1 task in the list.
```
### `deadline` - Creates a Deadline task

Creates a new Deadline task and adds it to the list.

Example of usage:

`deadline <taskDescription> /by: <deadline>`

Expected outcome:

A new Deadline task will be created with the specified description and deadline, and added to the list.

```
Got it. I've added this task:
[D][ ] return book (by: Jan 01 2023)
Now you have 1 task in the list.
```
### `event`- Creates an Event task

Creates a new Event task and adds it to the list.

Example of usage:

`event <taskDescription> /from: <startDate> /to: <endDate>`

Expected outcome:

A new Event task will be created with the specified description, startDate and endDate, and added to the list.

```
Got it. I've added this task:
[E][ ] A (from: Jan 01 2023 to: Feb 02 2023)
Now you have 1 task in the list.
```
### `delete` - Deletes a specified task from the list

Specifies a task to be removed from the list.

Example of usage:

`delete <taskNumber>`

Expected outcome:

Specified task will be removed from the list.

```
Noted. I've removed this task:
[T][ ] read book
```
### `find` - Filter tasks from the list

Filter tasks from the list using the specified keyword

Example of usage:

`find <keyword>`

Expected outcome:

List of tasks whose descriptions contain the keyword will be displayed.

```
Here are the matching tasks in your list:
[T][ ] read book
[D][ ] return book (by: Jan 01 2023)
```