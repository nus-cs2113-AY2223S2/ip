# User Guide

## Features

Duke is a chatbot app to assist you in task management via a Command Line Interface.

Quick Start
Features

Add a new Todo task: todo

Add a new Deadline task: deadline

Add a new Event task: event

List all tasks: list

Mark a task as done: done

Delete a task: delete

Finding a task: find

Exit the Duke: bye

Save the data
## How to start Duke
Verify that you have Java 11 or above installed in your computer.

Download the latest ip.jar.

Copy the file to a folder where you want to run it from.

Using the terminal, navigate to the path of ip.jar.

Run java -jar ip.jar duke.Duke ,and it should work
### Feature-Save your tasks

It will help you list down all your task, deadline, events and todo into a txt document.

## Usage

### `List` - It will help you list down your arguments

It will display all of your task in your list
=======
### Feature - Help write down your tasks

You can write down all your dealines,events,todo ,and it will be automatically saved in a txt document.



## Usage

### `List`



Example of usage:

`list`

Expected outcome:



```

Here are the tasks in your list:
1.[T][X] read book
It will list out all the tasks in your list

```
### `deadline`

Adds a Deadline to the task list.

Example of usage:

`deadline Submit movie review /by 12 feb`

Expected outcome:



```

Got it. I've added this task:
[D][X] Submit movie review by: 12 feb
Now you have 1 tasks in the list.

```
### `event`

Adds an Event to the task list.

Example of usage:

`event project meeting /at 2pm-4pm`

Expected outcome:



```

Got it. I've added this task:
[E][X] project meeting at 2pm-4pm
Now you have 1 tasks in the list.

```
### `todo`

Adds a todo to the task list.

Example of usage:

`todo read book`

Expected outcome:



```

Got it. I've added this task:
[T][X] read book
Now you have 1 tasks in the list.

```
### `delete`

Delete a task from the task list

Example of usage:

`delete 1`

Expected outcome:



```

Noted. I've removed this task
[T][X] read book
Now you have 4 tasks in the list.

```
### `mark`

Mark a task from your task list

Example of usage:

`mark 1`

Expected outcome:



```

Noted marking the task now!!
[T][V] read book

```
### `find`

find a task from your task list

Example of usage:

`find book`

Expected outcome:



```

Here are the matching tasks in your list:
1.[T][X] read book

```
### `bye`

end the duke chatbot

Example of usage:

`bye`

Expected outcome:



```

Bye. Hope to see you again soon!


```