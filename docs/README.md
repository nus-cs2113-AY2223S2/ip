# Alex User Guide

Alex is the individual project component for CS2113, AY22/23 Semester 2.

```

 $$$$$$\  $$\       $$$$$$$$\ $$\   $$\ 
$$  __$$\ $$ |      $$  _____|$$ |  $$ |
$$ /  $$ |$$ |      $$ |      \$$\ $$  |
$$$$$$$$ |$$ |      $$$$$\     \$$$$  / 
$$  __$$ |$$ |      $$  __|    $$  $$<  
$$ |  $$ |$$ |      $$ |      $$  /\$$\ 
$$ |  $$ |$$$$$$$$\ $$$$$$$$\ $$ /  $$ |
\__|  \__|\________|\________|\__|  \__|
                                        
```

Alex is the less famous brother of Amazon's Alexa acting as your productivity assistant!

## Table of Contents

- [Alex User Guide](#alex-user-guide)
    - [Features](#features)
        - [Task Creation](#task-creation)
        - [Task Management](#task-management)
        - [Persistent State](#persistent-state)
    - [Usage](#usage)
        - [`todo` - Add a TODO task](#todo---add-a-todo-task)
        - [`deadline` - Add a Deadline Task](#deadline---add-a-deadline-task)
        - [`event` - Add an Event Task](#event---add-an-event-task)
        - [`list` - Get your Task List](#list---get-your-task-list)
        - [`mark` - Tag task as done](#mark---tag-task-as-done)
        - [`unmark` - Tag task as undone](#unmark---tag-task-as-undone)
        - [`delete` - Remove a Task](#delete---remove-a-task)
        - [`find` - Query for a task](#find---query-for-a-task)
        - [`bye` - Exit Alex](#exit-alex)

## Features

Alex contains a variety of features for you to manage your daily tasks efficiently via CLI.

### Task Creation

Keep track of your work by adding tasks

Alex provides three types of tasks for creation:

1. `Todo`, for all your tasks 
2. `Deadline`, for tasks that requires a deadline
3. `Event`, to manage tasks that has a start and end time such as meetings

### Task Management

Alex can help you `mark`, `unmark`, `list` and `find` your tasks easily!

- Alex allows you to tick your tasks to remind yourself you have completed it!
- Alex can list all your current tasks to provide an overview of tasks that are done or needs to be done!
- Alex allows you to look for specific tasks using a keyword!

### Persistent State

Alex automatically saves all your task data to your local hard drive.
Alex will also load up all your previous tasks into your current session automatically.

## Usage

### `todo` - Add a TODO task

Create a `todo` type of task

Syntax:   
``todo (your_task_description)``

Example of usage:

`todo workout at the gym`

Expected outcome:

Alex will read your `todo` description,
add it to your taskList and read it back to you.

```
Got it. I've added this task:
 [T][ ] workout at the gym 
Now you have 1 tasks in the list
```

### `deadline` - Add a Deadline Task

Create a `deadline` type of task for Alex to remember.  
Deadlines have a due time, a compulsory parameter that must be included.

Syntax:

`deadline (description) /by (time)`

Example of usage:

`deadline find Alexa /by tonight`

Expected outcome:

Alex will read your `deadline` description and due time,
add it to your taskList and read it back to you.

```
Got it. I've added this task:
 [D][ ] find Alexa  (by:tonight)
Now you have 1 tasks in the list
```

### `event` - Add an Event Task

Create a `event` type of task for Alex to remember.  
Events requires the compulsory parameters from and to.

Syntax:

`event (description) /from (time) /to (time)`

Example of usage:

`event cs2113 meeting /from 5 /to 6pm`

Expected outcome:

Alex will read your `event` description, start time and end time,
add it to your taskList and read it back to you.
```
Got it. I've added this task:
 [E][ ] cs2113 meeting (from:5 to:6pm)
Now you have 1 tasks in the list
```

### `list` - Get your Task List

Get your list of tasks.  
The list contains an overview of each tasks' status.

Example of usage:

`list`

Expected outcome:

Returns your task list, with tasks sorted in chronological order when it was added.

```
1.[T][ ] workout at the gym 
2.[D][ ] find Alexa  (by:tonight)
3.[E][ ] cs2113 meeting (from:5 to:6pm)
```

### `mark` - Tag task as done

Marks a task in the taskList as done.  
The task number is the number of the task from running `list`.
If the task is already done,
Alex will still carry on and mark it done again.

Syntax:  
``mark (task_number)``

Example of usage:

`mark 1`

Expected outcome:

Alex marks the task and reads it back to you.
If the task number is invalid, Alex will let you know.

```
Good job! I have marked this task as completed:
[T][X] workout at the gym 
```

### `unmark` - Tag task as undone

Marks a task in the tasklist as NOT done.  
The task number is the number of the task from running `list`.
If the task is already not done,
Alex will still carry on and mark it as undone again.

Syntax:  
`unmark (task_number)`

Example of usage:

`unmark 1`

Expected outcome:

Alex unchecks the task and reads it back to you for confirmation.

If the task number is invalid, Alex will let you know.

```
Good job! I have marked this task as not yet completed:
[T][ ] workout at the gym 
```

### `delete` - Remove a Task

Removes a task from your task list.
The task number is the number of the task from running `list`.

Syntax:  
`delete (task_number)`

Example of usage:

`delete 1`

Expected outcome:

Alex removes the task from your taskList and prints it back to you.

If the task number is invalid, Alex will let you know.

```
Noted. I've removed this task:
[T][ ] workout at the gym 
Now you have 2 tasks in the list.
```

###  `find` - Look for a task

Alex will look for the tasks that has a description containing a keyword,
regardless of your casing but spelling is important!

Syntax:

`find (keyword)`

Example of usage:

`find alexa`

Expected outcome:

Alexa finds the tasks that contains your keyword and returns it back to you!

```
Here are the matching tasks in your list:
1.[D][ ] find Alexa  (by:tonight)
```



### `bye` - Exit Alex

Say bye to Alex and exits the program.

Example of usage:

`exit`

Expected outcome:

Alex will return a bye message.

```
Bye. Hope to chat with you again soon!
```