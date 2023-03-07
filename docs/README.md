# User Guide

Duke is a desktop app for managing a todo list, optimized for user via a Command Line Interface (CLI).

Table of Contents

1. [Quick start](#quick-start)
2. [Features](#features)
    1. [Viewing help: `help`](#viewing-help)
    2. [Listing all tasks: `list`](#listing-all-tasks)
    3. [Exiting: `bye`](#exiting)
    4. [Find task by keyword: `find`](#find-task-by-keyword)
    5. [Add a ToDo: `todo`](#add-a-todo)
    6. [Add an Event: `event`](#add-an-event)
    7. [Add a Deadline: `deadline`](#add-a-deadline)
    8. [Mark a task as done: `mark`](#mark-a-task-as-done)
    9. [Unmark a task as done: `unmark`](#unmark-a-task-as-done)
    10. [Delete a task: `delete`](#delete-a-task)
3. [Saving the Data](#saving-the-data)
4. [Editing the data file](#editing-the-data-file)
5. [FAQ](#faq)
6. [Command summary](#command-summary)

## Quick start

1. Ensure you have Java 11 or above installed on your computer.
2. Download the latest duke.jar from here.
3. Copy the file to the folder you want to use as the *home folder* for your todo list.
4. Open the command line in that folder and run the command `java -jar ip.jar`.
5. When you first run the application, a greeting will be displayed.
```
Save file loaded!
 ____        _        
|  _ \ _   _| | _____ 
| | | | | | | |/ / _ \
| |_| | |_| |   <  __/
|____/ \__,_|_|\_\___|
Hello! I'm Duke
What can I do for you?
```
6. Type the command and press Enter to execute it.
7. Refer to the Features below for details of each command.

## Features

> Notes about the command format:
> - Words in UPPER_CASE are the parameters to be supplied by the user.
    e.g. in `event NAME /at DATE`, `NAME` and `DATE` are parameters which can be used as `event Christmas party /at 2022-12-25`.
> - Extraneous parameters for commands that do not take in parameters will be ignored.

### Viewing help

Displays the list of commands available.

Format: `help`

Sample:
```
help
1. todo (insert task description)
2. deadline (insert task description) /by (insert deadline)
3. event (insert task description) /from (insert start) /to (insert end)
4. find (insert search term)
5. mark (insert task number)
6. unmark (insert task number)
7. delete (insert task number)
8. list
9. help
10. bye
```

### Listing all tasks

Lists out the tasks in the task list. The letter in the first pair of [] refers to what type of task it is - T for a todo task, D for a deadline task and E for an event task. The letter in the second pair of [] refers to whether the task is done or not - \<space\> for a task that is not done and X for a task that is done. See [mark](#mark-a-task-as-done-mark).

Format: `list`

Sample:
```
list
Here are the tasks in your list:
1.[T][ ] borrow book
2.[D][ ] return book (by: Sunday)
3.[E][ ] project meeting (from: Mon 2 to: 4pm)
```

### Exiting

Exits the application gracefully.

Format: `bye`

Sample:
```
bye
See ya!
```

### Find task by keyword

Finds all tasks with names containing a given keyword.

Format: `find KEYWORD`

Sample:
```
find project
Here are the tasks in your list:
1.[E][ ] project meeting (from: Mon 2 to: 4pm)
```

### Add a ToDo

Creates a ToDo.

Format: `todo NAME`

Sample:
```
todo borrow book
Got it. I've added this task!
[T][ ] borrow book
Now you have 1 task(s) in the list.
Task list saved!
```

### Add an Event

Use this command to keep track of events you need to attend. If a date or date and time is specified in a clear format, then Duke will be able to understand it and display it in a more helpful form. See more at [understanding dates and times](#understanding-dates-and-times).

Format: `event NAME /at DATETIME`

Sample:
```
event project meeting /from Mon 2 /to 4pm
Got it. I've added this task!
[E][ ] project meeting (from: Mon 2 to: 4pm)
Now you have 1 task(s) in the list.
Task list saved!

event christmas party /from 2022-12-25 /to 2022-12-26
[E][ ] christmas party (from: 2022-12-25 to: 2022-12-26)
Now you have 2 task(s) in the list.
Task list saved!
```

### Add a Deadline

Use this command to keep track of deadlines you need to meet. If a date or date and time is specified in a clear format, then Duke will be able to understand it and display it in a more helpful form. See more at [understanding dates and times](#understanding-dates-and-times).

Format: `deadline NAME /by DATETIME`

Sample:
```
deadline return book /by Sunday
Got it. I've added this task!
[D][ ] return book (by: Sunday)
Now you have 1 task(s) in the list.
Task list saved!
```

### Mark a task as done

Marks a task in your list as completed.

Format: `mark INDEX`

Sample:
```
list
Here are the tasks in your list:
1.[T][ ] borrow book
2.[D][ ] return book (by: Sunday)
3.[E][ ] project meeting (from: Mon 2 to: 4pm)
4.[D][ ] do homework (by: no idea)

mark 2
Nice! I've marked this task as done:
[D][X] return book (by: Sunday)
Task list saved!
```

### Unmark a task as done

Marks a task in your list as incomplete.

Format: `unmark INDEX`

Sample:
```
list
Here are the tasks in your list:
1.[T][ ] borrow book
2.[D][X] return book (by: Sunday)
3.[E][ ] project meeting (from: Mon 2 to: 4pm)
4.[D][ ] do homework (by: no idea)

unmark 2
OK, I've marked this task as not done yet:
[D][ ] return book (by: Sunday)
Task list saved!
```

### Delete a task

Deletes a task from your list.

Format: `delete INDEX`

Sample:
```
list
Here are the tasks in your list:
1.[T][ ] borrow book
2.[D][ ] return book (by: Sunday)
3.[E][ ] project meeting (from: Mon 2 to: 4pm)

delete 2
Noted. I've removed this task:
[D][ ] return book (by: Sunday)
Now you have 2 task(s) in the list.
Task list saved!
```

## Saving the Data

Duke data is saved to the hard disk automatically after any command that modifies the data. There is no need to save manually.

## Editing the data file

Duke data is saved as a plaintext file at `[JAR file location]/data/duke.txt`. Advanced users are welcome tp update data directly by editing that data file.

> Caution: If your changes to the data file makes it format invalid, there may be data loss.

## FAQ

Q: How do I transfer my data to another computer?

A: Duke is designed for portability. Copy the JAR file and the data folder over to your other computer and you are good to go.

## Command summary

| Action                | Format                                   | Example                                                 |
|-----------------------|------------------------------------------|---------------------------------------------------------|
| View help             | `help`                                   | `help`                                                  |
| List tasks            | `list`                                   | `list`                                                  |
| Exit application      | `bye`                                    | `bye`                                                   |
| Find tasks by keyword | `find KEYWORD`                           | `find project`                                          | 
| Add a Todo            | `todo NAME`                              | `todo Do the team project`                              |
| Add an event          | `event NAME /from DATETIME /to DATETIME` | `event Xmas party /from 25 dec 12:00pm /to 26 dec 12am` |
| Add a deadline        | `deadline NAME /by DATETIME`             | `deadline iP /by tonight`                               |
| Mark a task as done   | `mark INDEX`                             | `mark 1`                                                |
| Unmark a task as done | `unmark INDEX`                           | `unmark 1`                                              |
| Delete a task         | `delete INDEX`                           | `delete 1`                                              |