# User Guide

## Introduction

This is an assistant named Duke that can help you manage your tasks.

## Content

- [User Guide](#user-guide)
  - [Introduction](#introduction)
  - [Content](#content)
  - [Features](#features)
  - [Usage](#usage)
    - [`todo` - Add a todo task](#todo---add-a-todo-task)
    - [`deadline` - Add a deadline task](#deadline---add-a-deadline-task)
    - [`event` - Add an event task](#event---add-an-event-task)
    - [`mark` - Mark a task as done](#mark---mark-a-task-as-done)
    - [`unmark` - Mark a task as not done](#unmark---mark-a-task-as-not-done)
    - [`list` - Show all tasks](#list---show-all-tasks)
    - [`find` - Find tasks](#find---find-tasks)
    - [`delete` - Delete a task](#delete---delete-a-task)
    - [`bye` - Exit the program](#bye---exit-the-program)

## Features

You can add, mark, unmark, delete tasks in Duke. Generally, there are three types of tasks that Duke can process: `todo`(no start time and end time), `deadline`(no start time but has a deadline) and `event`(has both start time and end time).

Please refer to `Usage` for detailed description of commands and their usage.

<!-- ## Usage

### `Keyword` - Describe action

Describe the action and its outcome.

Example of usage: 

`keyword (optional arguments)`

Expected outcome:

Description of the outcome.

```
expected output
``` -->

## Usage 

### `todo` - Add a todo task

You can add a `todo` task by entering:

``` 
todo <task description>
```

- **Example of usage**

``` 
todo Return books
```

- **Expected output**

``` 
    ____________________________________________________________
    Got it. I've added this task:
      [T] [ ] Return books
    Now you have 1 task in the list.
    ____________________________________________________________
```

### `deadline` - Add a deadline task

You can add a `deadline` task by entering:

``` 
deadline <task description> /by <end time>
```

We recommend you to write time in format of "yyyy/MM/dd HH:mm" so Duke can understand the time.

- **Example of usage**

``` 
deadline Return books /by 2023/03/31 18:00
```

- **Expected output**

``` 
    ____________________________________________________________
    Got it. I've added this task:
      [D] [ ] Return books (by: Mar 31 2023 06:00 PM)
    Now you have 2 tasks in the list.
    ____________________________________________________________
```

### `event` - Add an event task

You can add an `event` task by entering:

``` 
event <task description> /from <start time> /to <end time>
```

- **Example of usage**:

``` 
event CS2113 class /from 2023/03/03 16:00 /to 2023/03/03 18:00
```

- **Expected output**

```
    ____________________________________________________________
    Got it. I've added this task:
      [E] [ ] CS2113 class (from: Mar 03 2023 04:00 PM to: Mar 03 2023 06:00 PM)
    Now you have 3 tasks in the list.
    ____________________________________________________________
```

### `mark` - Mark a task as done

You can mark a task as done by entering:

``` 
mark <number of the task>
```

Here "number of the task" is the index number of the task you want to mark when you run `list` command.

- **Example of usage**

``` 
mark 2
```

- **Expected output**

```
    ____________________________________________________________
    Nice! I've marked this task as done:
      [D] [X] Return books (by: Mar 31 2023 06:00 PM)
    ____________________________________________________________
```

### `unmark` - Mark a task as not done

You can mark a task as not done by entering:

``` 
unmark <number of the task>
```

- **Example of usage**

```
unmark 3
```

- **Expected output**

```
    ____________________________________________________________
    OK, I've marked this task as not done yet:
      [E] [ ] CS2113 class (from: Mar 03 2023 04:00 PM to: Mar 03 2023 06:00 PM)
    ____________________________________________________________
```

### `list` - Show all tasks

Use `list` to show all tasks.

- **Example of usage**

```
list
```

- **Expected output**

```
    ____________________________________________________________
    1. [T] [ ] Return books
    2. [D] [X] Return books (by: Mar 31 2023 06:00 PM)
    3. [E] [ ] CS2113 class (from: Mar 03 2023 04:00 PM to: Mar 03 2023 06:00 PM)
    ____________________________________________________________
```

### `find` - Find tasks

- You can find tasks that has certain pattern string in their description by entering:

``` 
find <pattern>
```

- **Example of usage**

```
find books
```

- **Expected output**

```
    ____________________________________________________________
     Here are the matching task(s) in your list:
     1. [T] [ ] Return books
     2. [D] [X] Return books (by: Mar 31 2023 06:00 PM)
     (Total 2)
    ____________________________________________________________
```

- You can find tasks that end before certain date/time by entering:

``` 
find /by <time>
```

Here time can be either form of `yyyy/MM/dd` or `yyyy/MM/dd HH:mm`.


- **Example of usage**

```
find /by 2023/03/31
```

- **Expected output**

```
    ____________________________________________________________
     Here are the task(s) ending before Mar 31 2023 11:59 PM in your list:
     1. [D] [X] Return books (by: Mar 31 2023 06:00 PM)
     2. [E] [ ] CS2113 class (from: Mar 03 2023 04:00 PM to: Mar 03 2023 06:00 PM)
     (Total 2)
    ____________________________________________________________
```

### `delete` - Delete a task

You can delete a task by entering:

``` 
delete <number of the task>
```

- **Example of usage**

```
delete 2
```

- **Expected output**

```
    ____________________________________________________________
    Noted. I've removed this task:
      [D] [X] Return books (by: Mar 31 2023 06:00 PM)
    Now you have 2 tasks in the list.
    ____________________________________________________________
```

### `bye` - Exit the program

Use `bye` to exit the program.

Your tasks will be recorded and will not lose. So next time you run Duke assistant, all tasks will be restored!

- **Example of usage**

```
bye
```

- **Expected output**

```
    ____________________________________________________________
     Bye. Hope to see you again soon!
    ____________________________________________________________
```
> program terminate