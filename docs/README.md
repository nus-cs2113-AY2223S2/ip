# User Guide

## 1.0 Introduction

Keqing todo list is a desktop app that allows for an easy and straightforward way for students to manage their schedule ranging from events, deadlines to other to-do items. User can add/delete, mark/unmark tasks or search for relevant tasks using keywords through a Command Line Interface (CLI), which enables a quick and sleek method of getting your schedule in check.

## 2.0 Quick Start

1. make sure you have java 11 or above installed on your system
2. Download the latest `Keqing.jar` file.
3. Open you terminal can the dictionary where you install the application
4. run `java -jar [CS2113T-T09-3][Quotesify].jar ` to launch the app
5. Type commands in and press `ENTER` to execute them, you should refer to 4.0 Usage for the details of commands

## 3.0 Features 

- ### add Task

Users can add three kinds of tasks to the Keqing todo list, which are Event, Deadline and Todo.

- ### list all the tasks

Keqing todo list can show the current tasks with details: description, status (done or not) and their relevant time information (deadlines, time of events).

- ### delete tasks

Users can delete the existing tasks in the current todo list.

- ### mark tasks

Keqing todo list enables users to mark the tasks they have finnished.

- ### unmark tasks

Keqing todo list enables users to unmark the tasks that they previously has marked.

- ### find relevant tasks

Keqing todo list enables users to search relevant tasks according to the keywords they give.

- ### storage

Keqing todo list can automatically store the current tasks and their status after users' every action.

## 4.0 Usage

### `todo` - add a todo task

This command adds a `todo` task to your tasks list.

- format: **todo** [task description]

- Example of usage: 

> todo check mailbox

This command add a todo with description `check mailbox` to the task list.

- Expected outcome from Keqing:

```
---------------------------------------------------------
Got it. I've added this task:
   > [T][ ] check mailbox
Now you have 5 tasks in the list.
---------------------------------------------------------
```

### `Event` - add a event task

This command adds a `Event` task to your tasks list.

- format: **event** [description] **/at** [YYYY-MM-DD] [hh:mm] (the specific time on that day is optional)

- Example of usage 1: 

> event group meeting /at 2023-03-05 12:00

This command add a event with description `group meeting` with time `2023-03-05 12:00` to the task list.

- Expected outcome from Keqing:

```
---------------------------------------------------------
Got it. I've added this task:
   > [E][ ] group meeting (Mar/05/2023 12:00)
Now you have 6 tasks in the list.
---------------------------------------------------------
```

- Example of usage 2:

> event group meeting /at 2023-03-05 12:00

- expected outcome from Keqing:

```
---------------------------------------------------------
Got it. I've added this task:
   > [E][ ] group meeting (Mar/05/2023)
Now you have 7 tasks in the list.
---------------------------------------------------------
```

### `Deadline` - add a task with deadline

This command adds a `Deadline` task to your tasks list.

- format: **deadline** [description] **/by** [YYYY-MM-DD] [hh:mm] (the specific time on that day is optional)

- Example of usage 1: 

> deadline return book /by 2023-03-09 16:00

This command add a task with description `return book` with deadline `2023-03-09 16:00` to the task list.

- Expected outcome from Keqing:

```
---------------------------------------------------------
Got it. I've added this task:
   > [D][ ] return book (Mar/09/2023 16:00)
Now you have 8 tasks in the list.
---------------------------------------------------------
```

- Example of usage 2:

> deadline return book /by 2023-03-09

- expected outcome from Keqing:

```
---------------------------------------------------------
Got it. I've added this task:
   > [D][ ] return book (Mar/09/2023)
Now you have 9 tasks in the list.
---------------------------------------------------------
```

### `list` - show all the tasks

This command shows all the tasks and their status.

- format: **list**

- Example of usage: 

> list

- Expected outcome from Keqing:

```
---------------------------------------------------------
   > 1.[T][ ] borrow book
   > 2.[D][ ] return book (Mar/30/2023)
   > 3.[E][ ] meeting (Feb/02/2023 12:00)
   > 4.[T][ ] check mailbox
---------------------------------------------------------
```

### `mark` - mark a task 

This command mark a task as finished. Users can check the index of tasks using `list`.

- format: **mark** [index of task]

- Example of usage: 

> mark 1

This command marks the task with index 1 as done. If the task is already marked as finished, this command will not have any effect on it.

- Expected outcome from Keqing:

```
---------------------------------------------------------
   > Nice! I've marked this task as done:
   > [T][X] borrow book
---------------------------------------------------------
```

### `unmark` - unmark a task 

This command marks a task as unfinished. Users can check the index of tasks using `list`.

- format: **unmark** [index of task]

- Example of usage: 

> unmark 1

This command the marks the task with index 1 as unfinished. If the task is not marked as done, this command will not have any effect on it.

- Expected outcome from Keqing:

```
---------------------------------------------------------
   > Noted. I've unmarked this task:
   > [T][ ] borrow book
---------------------------------------------------------
```

### `delete` - delete a task 

This command marks a task as unfinished. Users can check the index of tasks using `list`.

- format: **delete** [index of task]

- Example of usage: 

> delete 4

This command deletes the tasks with index 4 from the task list.

- Expected outcome from Keqing:

```
---------------------------------------------------------
Noted. I've removed this task:
   > [T][ ] check mailbox
Now you have 4 tasks in the list.
---------------------------------------------------------
```

### `find` - find relevant task 

This command finds and shows the tasks that contain the given keyword.

- format: **find** [keyword]

- Example of usage: 

> find book

This command finds all the tasks contain word "book".

- Expected outcome from Keqing:

```
---------------------------------------------------------
   > 1.[T][ ] borrow book
   > 2.[D][ ] return book (Mar/30/2023)
---------------------------------------------------------
```

### `bye` - exit the app 

This command terminates the applcation.

- format: bye

- Example of usage: 

> bye

- Expected outcome from Keqing:

```
---------------------------------------------------------
    > Bye. Hope to see you again soon!
---------------------------------------------------------
```

- ### 5.0 quick reference

| Instruction | Description |
| ----------- | ----------- |
| `todo` | Add a ToDo task |
| `event` | Add an Event task |
| `deadline` | Add a Deadline task |
| `list` | list all the tasks |
| `mark` | Mark a task as done |
| `unmark` | Mark a task as undone |
| `delete` | Delete a task |
| `find` | Find tasks |
| `bye` | Exit the bot |