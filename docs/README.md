# User Guide

Duke is a useful tool to keep track of a variety of tasks, helping you to plan your day and boost your productivity.  
It leverages optimised CLI performance to bring to you all the best features of any task manager in a small but powerful package!!s

## Features 

### Stores a list of tasks

Input tasks through a CLI which are stored locally on your hard drive across instances of the program

### Mark tasks as done/not done

Mark specific tasks as done or not done

### Store dates related to tasks

Store an event or deadline with specific dates tied to the task

### Find tasks of a specific keyword

Find all tasks in the list which contains the name passed into the program

### View all your tasks

View all completed and uncompleted tasks

### Delete a task

Delete a task from the list

## Usage

### `todo` - Adds a todo task to the list

A todo task is one that has no associated date parameters.  
Adds a named todo task to the task list, stored on your local device

Example of usage: 

`todo study for CS2113`

Expected outcome:

A todo task of name "study for CS2113" is added to the task list

```
Got it. I've added this task:
  [T][ ] study for CS2113
Now you have 1 task in the list
```

### `deadline` - Adds a deadline task to the list

A deadline task is one that has a due by date parameter associated with it  
Adds a named deadline task to the task list, stored on your local device  
Date parameter is indicated in the form `/by yyyy-mm-dd`

Example of usage: 

`deadline RSVP to wedding /by 2023-03-13`

Expected outcome:

A deadline task of name "RSVP to wedding" is added to the task list  
An associated date of Mar 13 2023 is stored

```
Got it. I've added this task:
  [D][ ] RSVP to wedding (by: Mar 13 2023)
Now you have 2 tasks in the list.
```

### `event` - Adds an event task to the list

An event task is one that has a from and to date parameter associated with it  
Adds a named event task to the task list, stored on your local device  
Date parameter is indicated in the form `/from yyyy-mm-dd /to yyyy-mm-dd`

Example of usage: 

`event Ops Manning /from 2023-01-26 /to 2023-02-09`

Expected outcome:

An event task of name "Ops Manning" is added to the task list  
An associated date of Jan 26 2023 and Feb 9 2023 is stored as the from and to date respectively

```
Got it. I've added this task:
  [E][ ] Ops Manning (from: Jan 26 2023 to: Feb 9 2023)
Now you have 3 tasks in the list.
```

### `list` - Prints all the tasks to the user

Shows all the tasks currently stored in the programme along with completion status and associated parameters

Example of usage: 

`list`

Expected outcome:

All tasks, with associated parameters and completion status are displayed to the user

```
Here are the tasks in your list:
1.[T][ ] study for CS2113
2.[D][ ] RSVP to wedding (by: Mar 13 2023)
3.[E][ ] Ops Manning (from: Jan 26 2023 to: Feb 9 2023)
```

### `mark` - Marks a specific task as done

The task indicated is marked as done in the task list and the change is stored on your local device

Example of usage: 

`mark 2`

Expected outcome:

The task of number 2, following 1-based indexing, is marked as done

```
Nice! I've marked this task as done:
[D][X] RSVP to wedding (by: Mar 13 2023)
```

### `unmark` - Marks a specific task as not done

The task indicated is marked as not done in the task list and the change is stored on your local device

Example of usage: 

`unmark 2`

Expected outcome:

The task of number 2, following 1-based indexing, is marked as not done

```
OK, I've marked this task as not done yet:
[D][ ] RSVP to wedding (by: Mar 13 2023)
```

### `delete` - Deletes a specific task

The task indicated is deleted from the task list and the change is stored on your local device

Example of usage: 

`delete 1`

Expected outcome:

The task of number 1, following 1-based indexing, is deleted

```
Noted. I've removed this task:
  [T][ ] study for CS2113
Now you have 2 tasks in the list.
```

### `find` - Finds and shows all tasks in the list containing a specific keyword in their name

The list of tasks with names containing the keyword passed in is shown to the user

Example of usage: 

`find wedding`

Expected outcome:

The tasks with "wedding" in their name are displayed

```
Here are the matching tasks in your list:
1.[D][ ] RSVP to wedding (by: Mar 13 2023)
```

### `bye` - Ends the program

Closes the program, task list created is stored on your local device

Example of usage: 

`bye`

Expected outcome:

The program closes

```
Bye. Hope to see you again soon!
```
