# User Guide

This is a **terminal app for creating to-do lists, optimized for use via a Command Line Interface (CLI)**. Given below are instructions on how to use it.

## Features 

### Feature 1:  3 Types of Tasks

1. **todo**

    A task item that only requires a description, no time inputs required.


2. **deadline**

    A task item that requires both a description and a deadline. No starting time is required.


3. **event**

    A task item that requires a description, a starting time as well as an ending time.


### Feature 2: add, delete
　　Add or delete a task item from the to-do list.

### Feature ３: mark, unmark
　　Mark a task item as completed or incompleted.

### Feature ４: find
　　Find all tasks that contains the input string in its description.

　　Print the list of matching tasks.

### Feature ５: list
　　Print every task in the to-do list.

### Feature 6: help
   View the list of commands and the input format.
    

## Usage

### `todo` - add a todo item to the list of tasks

Example of usage: 
`todo <todo description>`

Expected outcome:

```
>>list
1. [T][ ] read book
>> task return book
>>list
1. [T][ ] read book
2. [T][ ] return book
```
### `deadline` - add a deadline item to the list of tasks

Example of usage:
`deadline <deadline description> /by <deadline date>`

Expected outcome:

```
>>list
1. [T][ ] read book
>> deadline return book /by 11 Jan
>>list
1. [T][ ] read book
2. [D][ ] return book by: 11 Jan
```
### `event` - add an event item to the list of tasks

Example of usage:
`event <event description> /from <starting time> /to <ending time>`

Expected outcome:

```
>>list
1. [T][ ] read book
>> event project meeting /from 11 Jan 2pm /to 4pm
>>list
1. [T][ ] read book
2. [E][ ] project meeting from: 11 Jan 2pm to: 4pm
```

### `delete` - delete a task item from the list of tasks

Example of usage:
`delete <Task Index>`

Expected outcome:

```
>>list
1. [T][ ] read book
2. [T][ ] return book
>> delete 2
>>list
1. [T][ ] read book
```
### `mark` - mark a task item as done (with a 'X')

Example of usage:
`mark <Task Index>`

Expected outcome:

```
>>list
1. [T][ ] read book
>> mark 1
>>list
1. [T][X] read book
```
### `unmark` - mark a task item as not done (with a ' ')

Example of usage:
`unmark <Task Index>`

Expected outcome:

```
>>list
1. [T][X] read book
>> unmark 1
>>list
1. [T][ ] read book
```
### `find` - print all tasks that contains the keyword.

Example of usage:
`find <Keyword>`

Expected outcome:

```
>>list
1. [T][ ] read book
2. [T][ ] return book
3. [D][ ] assignment submission by: 11 Jan
4. [E][X] book club meeting from: 11 Jan 2pm to: 4pm
5. [T][ ] buy dictionary 
>> find book
1. [T][ ] read book
2. [T][ ] return book
3. [E][X] book club meeting from: 11 Jan 2pm to: 4pm
```

### `list` - print all tasks in the current task list.

Example of usage:
`list`

Expected outcome:

```
>>list
1. [T][ ] read book
2. [T][ ] return book
3. [D][ ] assignment submission by: 11 Jan
4. [E][X] book club meeting from: 11 Jan 2pm to: 4pm
5. [T][ ] buy dictionary 
```
### `help` - print all commands and input formats.

Example of usage:
`help`

Expected outcome:

```
>>help
Enter "todo 'task-name'" to add a task.
Enter "deadline 'task-name' /by 'deadline'" to add a task with a deadline.
Enter "event 'task-name' /from 'start-date' /to 'end-date'" to add a task with start and end dates.
Enter "mark 'task-index'" to mark a task as done.
Enter "unmark 'task-index'" to mark a task as not done yet.
Enter "delete 'task-index'" to delete a task from the list.
Enter "list" to obtain a list of all your tasks!.
Enter "find 'string'" to obtain a list of tasks containing the string you input.
Enter "help" to view this list of instruction again.
```