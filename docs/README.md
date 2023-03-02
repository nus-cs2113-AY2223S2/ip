# User Guide
Duke is a **desktop app for managing tasks, optimized for use via a Command Line Interface** (CLI).
If you can type fast, Duke can get your task management done faster than traditional GUI apps. Duke is capable of
helping you **keep track of your tasks, deadlines and events.**

## Table of Contents
1. [Quick Start](#quick-start)
2. [Features](#features)
    1. [Viewing help: `help`](#viewing-help-help)
    2. [Listing all tasks: `list`](#listing-all-tasks-list)
    3. [Adding a todo task: `todo`](#adding-a-todo-task-todo)
    4. [Adding a deadline task: `deadline`](#adding-a-deadline-task-deadline)
    5. [Adding an event task: `event`](#adding-an-event-task-event)
    6. [Marking a task as done: `mark`](#marking-a-task-as-done-mark)
    7. [Marking a task as undone: `unmark`](#marking-a-task-as-undone-unmark)
    8. [Deleting a task: `delete`](#deleting-a-task-delete)
    9. [Finding a task by keyword: `find`](#finding-a-task-by-keyword-find)
    10. [Exiting the program: `bye`](#exiting-the-program-bye)
3. [FAQ](#faq)

## Quick Start

1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest ip.jar from [here](https://github.com/yixuann02/ip/releases/tag/A-Release).
3. Copy the file to the folder you want to use as the home folder for your Duke.
4. Open a command terminal, cd into the folder you put the jar file in, and use the java -jar
   ip.jar command to run the application.
5. Type the command in the command box and press Enter to execute it.
   e.g. typing `help` and pressing Enter will display the list of commands available in Duke.
6. Refer to the [Features](#features) below for details of each command.

## Features

### Viewing help: `help`

Shows a message explaining the usage of each command and the required format of each command.

Format: `help`

### Listing all tasks: `list`

Displays a list of all current tasks in the task list as well their status. The task List is saved and updated from
the user's hard disk each time Duke is started.

Format: `list`

### Adding a todo task: `todo`

Adds a todo task to the task list.

Format: `todo <TASK_DESCRIPTION>`
- Adds a Task with the description `<TASK_DESCRIPTION>` to the task list.
- The `<TASK_DESCRIPTION>` should not be empty.

Examples:
- `todo read book`
- `todo finish homework`

### Adding a deadline task: `deadline`

Adds a deadline task to the task list.

Format: `deadline <TASK_DESCRIPTION> /by <DEADLINE>`

- Adds a Task with the description `<TASK_DESCRIPTION>` and deadline `<DEADLINE>` to the task list.
- The `<TASK_DESCRIPTION>` should not be empty.
- The `/by` keyword is required.

Examples:
- `deadline return book /by 15 March`
- `deadline finish homework /by 2pm`

### Adding an event task: `event`

Adds an event task to the task list.

Format: `event <TASK_DESCRIPTION> /from <START_TIME> /to <END_TIME>`

- Adds a Task with the description `<TASK_DESCRIPTION>`, start time `<START_TIME>`
  and end time `<END_TIME>` to the task list.
- The `<TASK_DESCRIPTION>` should not be empty.
- The `/from` and `/to` keywords are required.

Examples:
- `event project meeting /from 2023-03-15 1400 /to 2023-03-15 1500`
- `event family dinner /from 10 March 7pm /to 9pm`

### Marking a task as done: `mark`

Marks a task as done.

Format: `mark <TASK_INDEX>`

- Marks the task at the specified `<TASK_INDEX>` of the list as done.
- The `<TASK_INDEX>` should be a positive integer between 1 to the latest task count.

Examples:
- `mark 1` marks the first task in the list as done.
- `mark 2` marks the second task in the list as done.

### Marking a task as undone: `unmark`

Marks a task as undone.

Format: `unmark <TASK_INDEX>`

- Marks the task at the specified `<TASK_INDEX>` of the list as not done.
- The `<TASK_INDEX>` should be a positive integer between 1 to the latest task count.

Examples:
- `unmark 1` marks the first task in the list as not done.
- `unmark 2` marks the second task in the list as not done.

### Deleting a task: `delete`

Deletes a task from the task list.

Format: `delete <TASK_INDEX>`

- Deletes the task at the specified `<TASK_INDEX>` of the list.
- The `<TASK_INDEX>` should be a positive integer between 1 to the latest task count.

Examples:
- `delete 1` deletes the first task in the list.
- `delete 2` deletes the second task in the list.

### Finding a task by keyword: `find`

Find tasks whose description contains the given keyword.

Format: `find <KEYWORD>`

- Find tasks whose description contains the given `<KEYWORD>`.
- The `<KEYWORD>` should not be empty.
- The `<KEYWORD>` should only be a single word.

Examples:
- `find book` returns tasks with descriptions containing the word "book".

### Exiting the program: `bye`

Exits the program.

Format: `bye`

## FAQ

**Q**: How do I save my data in my hard disk?  
**A**: Duke saves your data in your hard disk automatically after each command.