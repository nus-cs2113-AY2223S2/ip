# User Guide

## Introduction
Duke is a chatbot that helps you keep track of your tasks. It is optimized for use via a Command Line Interface (CLI).

## Features 

### Add a task to your list
The task is divided into 3 types: Todo, Deadline and Event.

Todo is a task without any date/time attached to it e.g., visit new theme park

Deadline is a task that need to be done before a specific date/time e.g., submit report by 11/10/2019 5pm

Event is a task that start at a specific time and ends at a specific time e.g., team project meeting on 2/10/2019 2-4pm

### Mark a task as done/not done yet
Mark a specific task as done or not done yet.

### Show all tasks in your list
Show all tasks in your list including the type of task, description, date/time and the status of task.

### Delete a task from your list
Delete a specific task from your list by its index.

### Find all tasks that contain a keyword
Find all tasks in your list that contain a keyword and show them.

### Store all the tasks in your local machine
Store all the tasks in your local machine so that you can access them later.

## Usage

### `help` - Show all commands and their usage

Guide the user on the usage of all commands and how to give a correct input for each command.

Example of usage: 

`help`

Expected outcome:

A list of all commands and their usage will be shown.

```
This is the list of our commands

help: to view the instructions for all commands
Example: help

bye: to end the program
Example: bye

list: to view all the tasks in your list
Example: list

mark: to mark a task as done
Format: mark {Number}
Example: mark 1

unmark: to unmark a task as not done yet
Format: unmark {Number}
Example: unmark 1

todo: to add a todo task in your list
Format: todo {your task}
Example: todo read book

deadline: to add a deadline task in your list
Format: deadline {your task} /by {deadline date}
Example: deadline return book /by 2020-08-08

event: to add an event task in your list
Format: event {your task} /from {begin time} /to {end time}
Example: event project meeting /from 2020-08-08 2pm /to 2020-08-08 4pm

delete: to delete a task
Format: delete {Number}
Example: delete 1

find: to find all the tasks' content that contain a piece of keyword
Format: find {keyword}
Example: find book
```

### todo - Add a todo task in your list
Add a todo task to your list and store it in your local machine.

Example of usage:

`todo read book`

Expected outcome:

A todo task "read book" will be added to your list.

```
Got it. I've added this task:
[T][ ] read book
Now you have 1 tasks in the list.
```

### deadline - Add a deadline task in your list
Add a deadline task to your list and store it in your local machine.

Example of usage:

`deadline return book /by 2020-08-08`

Expected outcome:

A deadline task "return book" with deadline date "2020-08-08" will be added to your list.

```
Got it. I've added this task:
[D][ ] return book (by: 2020-08-08)
Now you have 2 tasks in the list.
```

### event - Add an event task in your list
Add an event task to your list and store it in your local machine.

Example of usage:

`event project meeting /from 2020-08-08 2pm /to 2020-08-08 4pm`

Expected outcome:

An event task "project meeting" with begin time "2020-08-08 2pm" and end time "2020-08-08 4pm" will be added to your list.

```
Got it. I've added this task:
[E][ ] project meeting (from: 2020-08-08 2pm to 2020-08-08 4pm)
Now you have 3 tasks in the list.
```

### mark - Mark a task as done
Mark a specific task as done.

Example of usage:

`mark 1`

Expected outcome:

The first task in your list will be marked as done.

```
Nice! I've marked this task as done:
[T][X] read book
```

### unmark - Mark a task as not done yet
Mark a specific task as not done yet.

Example of usage:

`unmark 1`

Expected outcome:

The first task in your list will be marked as not done yet.

```
Nice! I've marked this task as not done yet:
[T][ ] read book
```

### list - Show all tasks in your list
Show all tasks in your list.

Example of usage:

`list`

Expected outcome:

All tasks in your list will be shown.

```
Here are the tasks in your list:
1.[T][ ] read book
2.[D][ ] return book (by: 2020-08-08)
3.[E][ ] project meeting (from: 2020-08-08 2pm to 2020-08-08 4pm)
```

### delete - Delete a task from your list
Delete a specific task from your list.

Example of usage:

`delete 1`

Expected outcome:

The first task in your list will be deleted.

```
Noted. I've removed this task:
[T][ ] read book
Now you have 2 tasks in the list.
```

### find - Find all tasks that contain a keyword
Find all tasks in your list that contain a keyword and show them.

Example of usage:

`find book`

Expected outcome:

All tasks in your list that contain the keyword "book" will be shown.

```
Here are the matching tasks in your list:
1. [D][ ] return book (by: 2020-08-08)
```

### bye - End the program
End the program.

Example of usage:

`bye`

Expected outcome:

The program ends.

```
Bye. Hope to see you again soon!
```

## Important note
You might wander where the data file is stored. It is stored in the directory 
you are currently in.

For example if you store the ip.jar in the directory `C:\Users\user\Desktop\duke`, 
and in the command prompt you are under the directory `C:\Users\user, the data
will be stored in `C:\Users\user\data\data.txt', not in `C:\Users\user\Desktop\duke\data\data.txt`.


