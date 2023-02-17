# User Guide for Duke

Duke is a **task list manager application** for use via the **Command Line Interface** (CLI) that interacts with you like a personal assistant. 

- [Quick Start](#quick-start)
- [Features](#features)
  + [Add a To-Do task: `todo`](#add-a-to-do-task-todo)
  + [Add a Deadline task: `deadline`](#add-a-deadline-task-deadline)
  + [Add an Event task: `event`](#add-an-event-task-event)
  + [List all tasks: `list`](#list-all-tasks-list)
  + [Mark a task as done: `mark`](#mark-a-task-as-done-mark)
  + [Mark a task as not done: `unmark`](#mark-a-task-as-not-done-unmark)
  + [Delete a task: `delete`](#delete-a-task-delete)
  + [Search tasks with given keyword : `find`](#search-tasks-with-given-keyword-find)
  + [Exit the application: `bye`](#exit-the-application-bye)
  + [Loading/Saving the data file](#loadingsaving-the-data-file)
- [Command Summary](#command-summary)

## Quick Start
1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest `Duke.jar` available [here](https://github.com/denzelcjy/ip/releases).
3. Copy the file to the folder you want to use as the home folder for your Duke chatbot, e.g. `Desktop`.
4. Open the command terminal, `cd` into the folder that you copied `Duke.jar` into in the previous step, e.g. `cd Desktop`.
5. Once in the folder, type the command `java -jar Duke.jar` to run the application.

If successful, you should see this as the response output:
```
Hello from
 ____        _
|  _ \ _   _| | _____
| | | | | | | |/ / _ \
| |_| | |_| |   <  __/
|____/ \__,_|_|\_\___|

    ____________________________________________________________
     Hello! I'm Duke
     What can I do for you?
    ____________________________________________________________
```

## Features 

### Add a to-do task: `todo`

Adds a to-do task to the task list.

#### Usage:

`todo <description>`

#### Example:

`todo borrow book`

#### Sample Outcome:

```
    ____________________________________________________________
     Got it. I've added this task:
       [T][ ] borrow book
     Now you have 1 tasks in the list.
    ____________________________________________________________
```

### Add a deadline task: `deadline`

Adds a deadline task to the task list with a due date.

#### Usage:

`deadline <description> /by <due date>` 

+ `<due date>` details can contain `<date>` in the format of `<yyyy-mm-dd>` or <time> in the format of a string.
  
  + If both are included, `<date>` should be **before** the remaining string.

#### Example 1 using `<time>` only:

`deadline return book /by 6pm`
  
#### Example 2 using `<date> + <time>`:

`deadline submit assignment /by 2023-08-08 at 6pm`

#### Sample Outcome 1:

```
    ____________________________________________________________
     Got it. I've added this task:
       [D][ ] return book (by: 6pm)
     Now you have 2 tasks in the list.
    ____________________________________________________________
```

#### Sample Outcome 2:

```
    ____________________________________________________________
     Got it. I've added this task:
       [D][ ] submit assignment (by: 8 Aug 2023 at 6pm)
     Now you have 3 tasks in the list.
    ____________________________________________________________
```

### Add an event task: `event`

Adds an event task to the task list.

#### Usage:

`event <description> /from <start date/time> /to <end date/time>`

#### Example:

`event project meeting /from 9 Aug 2pm /to 4pm`

#### Sample Outcome:

```
    ____________________________________________________________
     Got it. I've added this task:
       [E][ ] project meeting (from: 9 Aug 2pm to: 4pm)
     Now you have 4 tasks in the list.
    ____________________________________________________________
```

### List all tasks: `list`

List all current tasks in the task list in order, along with the task type and done status.
  
#### Usage:

`list`

#### Example:

`list`

#### Sample Outcome:

```
    ____________________________________________________________
     Here are the tasks in your list:
     1.[T][ ] borrow book
     2.[D][ ] return book (by: 6pm)
     3.[D][ ] submit assignment (by: 8 Aug 2023 at 6pm)
     4.[E][ ] project meeting (from: 9 Aug 2pm to: 4pm)
    ____________________________________________________________
```

### Mark a task as done: `mark`

Marks the task as done according to given task number.
  
#### Usage:

`mark <task number>`

#### Example:

`mark 1`

#### Sample Outcome:

```
    ____________________________________________________________
     Nice! I've marked this task as done:
       [T][X] borrow book
    ____________________________________________________________
```

### Mark a task as <ins>not</ins> done: `unmark`

Marks the task as not done according to given task number.
  
#### Usage:

`unmark <task number>`

#### Example:

`unmark 1`

#### Sample Outcome:

```
    ____________________________________________________________
     OK, I've marked this task as not done yet:
       [T][ ] borrow book
    ____________________________________________________________
```

### Delete a task: `delete`

Removes the task from the task list according to given task number.
  
#### Usage:

`delete <task number>`

#### Example:

`delete 3`

#### Sample Outcome:

```
    ____________________________________________________________
     Noted. I've removed this task:
       [D][ ] submit assignment (by: 8 Aug 2023 at 6pm)
     Now you have 3 tasks in the list.
    ____________________________________________________________
```

### Search tasks with given keyword: `find`

Searches for tasks with where the given keyword is a substring of the task description.
  
#### Usage:

`find <keyword>`

#### Example:

`find book`

#### Sample Outcome:

```
    ____________________________________________________________
     Here are the matching tasks in your list:
     1.[T][ ] borrow book
     2.[D][ ] return book (by: 6pm)
    ____________________________________________________________
```

### Exit the application: `bye`

Exits the Duke chatbot.
  
#### Usage:

`bye`

#### Example:

`bye`

#### Sample Outcome:

```
    ____________________________________________________________
     Bye. Hope to see you again soon!
    ___________________________________________________________
```

### Loading/Saving the data file:

+ Task list data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.
  
+ Upon starting the Duke chatbot, saved data from the previous session will be loaded automatically.

## Command Summary
Command | Usage | Example
| ------------ | ------------- | ------------- |
todo | `todo <description>` | `todo borrow book` 
deadline | `deadline <description> /by <due date>` | `deadline submit assignment /by 2023-08-08 at 6pm`
event | `event <description> /from <start date/time> /to <end date/time>` | `event project meeting /from 9 Aug 2pm /to 4pm`
list | `list` | `list`
mark | `mark <task number>` | `mark 1`
unmark | `unmark <task number>` | `unmark 1`
delete | `delete <task number>` | `delete 3`
find | `find <keyword>` | `find book`
bye | `bye` | `bye`
