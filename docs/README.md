# User Guide

## Table of Contents
<!-- TOC -->
* [User Guide](#user-guide)
    * [Features](#features)
        * [Task Type 1: `Todos`](#task-type-1--todos)
        * [Task Type 2: `Events`](#task-type-2--events)
        * [Task Type 3: `Deadlines`](#task-type-3--deadlines)
        * [Find Tasks By Description](#find-tasks-by-description)
        * [Task Completion Status](#task-completion-status)
        * [Task Deletion](#task-deletion)
        * [Task Data Storage](#task-data-storage)
    * [Usage](#usage)
        * [`todo` - New Todo Task](#todo---new-todo-task)
        * [`deadline` - New Deadline Task](#deadline---new-deadline-task)
        * [`event` - New Event Task](#event---new-event-task)
        * [`list` - List all Tasks](#list---list-all-tasks)
        * [`mark` - Marks a Task as done](#mark---marks-a-task-as-done)
        * [`unmark` - Marks a Task as not done](#unmark---marks-a-task-as-not-done)
        * [`delete` - Deletes a Task](#delete---deletes-a-task)
        * [`find` - Find Task by Keyword](#find---find-task-by-keyword)
        * [`bye` - Turns Rica Off](#bye---shut-down-program)
<!-- TOC -->

## Features

### Task Type 1: `Todo`

The function of 'Todo' is to record the planning, like what are the things are reuqred to be done. 

### Task Type 2: `Event`

Event can be considered as part of the Todo, but it required the **start date and/or time** and **end date and/or time**, which can be more specific.

### Task Type 3: `Deadline`

Another kind of Todo is Deadline which  has a specified **due date** so that the user can track the deadline for the task. Therefore, the priority of the tasks can be visualized.

### Find Tasks By Description

After creating many tasks, users can type a certain **keyword** to find the tasks that have been created before. As such, all the related tasks can be reviewed.

### Task Completion Status

All Tasks can be marked **done** or **not done**, so it helps to keep tracking the completion of the tasks <br><br>
The status of the tasks can be indicated by `[X]` if a task is completed or an empty box `[ ]` if the task haven't been completed yet.

### Task Deletion

Users can **delete** a task if they no longer need it in the task list.

### Task Data Storage

A list of Tasks can be recorded in computer's storage and _restore it_ the next time when the program start. <br><br>
The data memories will be stored in the data\taskList.txt file. 

## Usage

### `todo` - New Todo Task

Creates a new Todo Task with the specific requirement and uncompleted status.

**Syntax**:

`todo (task-that-need-to-be-done)`

**Example of usage**:

`todo buy food`

**Expected outcome**:

```
todo buy food
    ____________________________________________________________
     Got it. I've added this task: 
       [T][ ] buy food
     Now you have 1 tasks in the list.
    ____________________________________________________________
```

### `deadline` - New Deadline Task

Creates a new Deadline Task with the given description and due date.

**Syntax**:

`deadline (task-description) /by (deadline-with-time)`

**Example of usage**:

`deadline CS2113 Assignmentt /by 3 March`

**Expected outcome**:

Rica will remember that you have a deadline called 'Fall asleep' to meet by 10pm, which is added to your list of Tasks.

```
deadline Fall asleep /by 10pm
    ____________________________________________________________
     Got it. I've added this task: 
      [D][ ] CS2113 Assignmentt  (by: 3 March)
     Now you have 2 tasks in the list.
    ____________________________________________________________
```

### `event` - New Event Task

Creates a new Event Task with the given description, start date/time and end date/time.

**Syntax**:

`event (tassk-description) /from (start-date) /to (end-date)`

**Example of usage**:

`event shopping /from 4pm /to 8:30pm`

**Expected outcome**:

Rica will keep in mind that you want to 'Watch sunset' from 7pm to 7.15pm, and this Deadline is added to your list of Tasks.

```
event shopping /from 4pm /to 8.30pm
    ____________________________________________________________
     Got it. I've added this task: 
      [E][ ] shopping (from: 4pm to: 8:30pm)
     Now you have 1 tasks in the list.
    ____________________________________________________________
```

### `list` - List all Tasks

Lists all Tasks that Rica currently remembers along with their completion status.

**Example of usage**:

`list`

**Expected outcome**:

Description of all the Tasks that have been entered as well as which Tasks are done or not done.

```
list
    ____________________________________________________________
     Here are the task in your list: 
     1.[E][ ] shopping (from: 4pm to: 8:30pm)
    ____________________________________________________________
```

### `mark` - Marks a Task as done

Given the Task number based on the task list, mark the corresponding Task as done by the user.

**Syntax**:

`mark (task-index)`

**Example of usage**:

`mark 1`

**Expected outcome**:

Index 1 of the task list is now done, so it will update this task as done in the task list.

```
mark 1
    ____________________________________________________________
     Nice! I've marked this task as done:
      [E][X] shopping (from: 4pm to: 8:30pm)
    ____________________________________________________________
```

### `unmark` - Marks a Task as not done




**Syntax**:

`unmark (task-index)`

**Example of usage**:

`unmark 1`

**Expected outcome**:

Index 1 of the task list is now not done, so it will update this task as not done in the task list

```
unmark 1
    ____________________________________________________________
     Nice! I've marked this task as done:
      [E][X] shopping (from: 4pm to: 8:30pm)
    ____________________________________________________________
```

### `delete` - Deletes a Task

Given the Task number based on the task list, delete the corresponding Task by the user.

**Syntax**:

`delete (task-number)`

**Example of usage**:

`delete 1`

**Expected outcome**:

Index 1 of the task list is now deleted, so it will be deleted from the task list.

```
delete 1
    ____________________________________________________________
     Noted. I've removed this task:
       [E][ ] project meeting (from: Aug 6th 2pm to: 4pm)
     Now you have 4 tasks in the list.
    ____________________________________________________________
```

### `find` - Find Task by Keyword

Given a keyword, a list of all Tasks with a matching description will be shown.

**Syntax**:

`find (keyword)`

**Example of usage**:

`find book`

**Expected outcome**:

Task with a description containing the word 'book' is shown to the user.

```
find book
    ____________________________________________________________
     Here are the matching tasks in your list:
     1.[T][X] read book
     2.[D][X] return book (by: June 6th)
    ____________________________________________________________
```

### `bye` - Turns Program Off

The program will shut down.

**Example of usage**:

`bye`

**Expected outcome**:


```
    ____________________________________________________________
     Bye. Hope to see you again soon!
    ____________________________________________________________
```