# User Guide for Alfred
Duke (Alfred Version) is a simple command line utility to help you keep
track of important tasks so that you will not forget about it.

## Features

### Save and keep track of various tasks
Alfred will allow you to keep track of different types of
tasks including Todo, Deadline and Event.

### Find specific task

With multiple tasks in your task list, Alfred will help you find a specific task.

### Mark tasks as done
Once a task is completed, Alfred can help you to mark it as done or unmark it.

### Data is persistent
All changes made to the task list is saved by Alfred and will be restored on the
next visit.

## Usage

### `todo` - Create a Todo task

Adds a new task of type Todo

Example of usage: 

`Todo <task title>`

Expected outcome:

Todo task to be added will be shown with the new updated number of tasks
```
todo Visit Gotham
Got it. I've added this task:
  [T][ ] Visit Gotham
Now you have 1 tasks in the list.
```

### `deadline` - Create a Deadline task

Adds a new task of type Deadline

Example of usage:

`Deadline <task title> /by <date/time>`

Expected outcome:

Deadline task to be added will be shown with the new updated number of tasks
```
deadline Smoke /by today
Got it. I've added this task:
  [D][ ] Smoke (by: today)
Now you have 2 tasks in the list.
```

### `Event` - Create a Event task

Adds a new task of type Event

Example of usage:

`Event <task title> /by <date/time>`

Expected outcome:

Event task to be added will be shown with the new updated number of tasks
```
event Browse for new car /from monday /to tuesday 4pm
Got it. I've added this task:
  [E][ ] Browse for new car (from: monday to: tuesday 4pm)
Now you have 3 tasks in the list.
```

### `list` - View task list

Shows all the tasks

Example of usage:

`list` 

Expected outcome:

```
list
1. [T][ ] Visit Gotham
2. [D][ ] Smoke (by: today)
3. [E][ ] Browse for new car (from: monday to: tuesday 4pm)
```

### `find` - Find specific task based on keyword

Shows all the tasks related to the keyword

Example of usage:

`find <keyword>`

Expected outcome:

```
find car
Here are the matching tasks in your list:
1. [E][ ] Browse for new car (from: monday to: tuesday 4pm)
```

### `mark` - Mark a task as done

Marks task as done

Example of usage:

`mark <index>`

Expected outcome:

```
mark 3
Nice! I've marked this task as done: 
[E][X] Browse for new car (from: monday to: tuesday 4pm)
```

### `unmark` - Unmark a task as done

Unmarks task as done

Example of usage:

`unmark <index>`

Expected outcome:

```
unmark 3
Okay, I've marked this task as not done yet: 
[E][ ] Browse for new car (from: monday to: tuesday 4pm)
```

### `delete` - Delete a task 

Removes a task from the task list

Example of usage:

`delete <index>`

Expected outcome:

```
delete 3
Noted. I've removed this task:
[E][ ] Browse for new car (from: monday to: tuesday 4pm)
Now you have 2 tasks in the list.
```

### `bye` - Exit from Alfred

Say goodbye to your butler

Example of usage:

`bye`

Expected outcome:

```
bye
 Bye. Hope to see you again soon!
____________________________________________________________
```
