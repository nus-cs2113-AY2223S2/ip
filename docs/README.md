# User Guide

Buddy is a very helpful tool in helping you manage your daily tasks
and helping you be more organised. It also helps you improve your day to day productivity!
Get immersed in this cli-optimised application!

## Features 

### Add tasks to your task list
Add various types of tasks - todo, deadline and event.

### Delete tasks from your task list
Delete tasks from the list.

### Keep track of the progress of your tasks
Mark/Unmark tasks from the list to keep track of what you have completed.

### Find tasks in your task list
Find tasks that match a keyword

## Usage

### `todo` - Adds a Todo task

Format: `todo <TASK_DESCRIPTION>`

- Adds a todo with `<TASK_DESCRIPTION>`
- `<TASK_DESCRIPTION>` should not be empty

Examples of usage:
- `todo cs2113 project`
- `todo cs2102 assignment`

### `deadline` - Adds a Deadline task
Format: `deadline <TASK_DESCRIPTION> /by <deadlinedate>`

- Adds a task with a `<TASK_DESCRIPTION>` to be completed by `<deadlinedate>`
- Deadline date should be in `<YYYY-MM-DD>` format
- Deadline date cannot be before today's date
- Deadline date should not be empty

Examples of usage:
- `deadline cs2113 ip /by 2023-03-03`
- `deadline cs1010 assignment /by 2024-04-04`

### `event` - Adds a Event task
Format: `event <TASK_DESCRIPTION> /from <Start> /to <End>`

- Adds a task with a `<TASK_DESCRIPTION>` with a start `<Start>` to end `<End>`
- `<TASK_DESCRIPTION>` should not be empty
- `<Start>` and `<End>` should not be empty

Examples of usage:
- `event cs2113 exam /from 4pm /to 6pm`
- `event NUS Open House /from 24 Dec 2023 6am /to 6pm`

### `mark` - Marks a task as done
Format: `mark <INDEX_OF_TASK>`

- Marks a task as done
- `<INDEX_OF_TASK>` should be within the task list

Examples of usage:
- `mark 2`
- `mark 1`

### `unmark` - Marks a task as not done
Format: `unmark <INDEX_OF_TASK>`

- Marks a task as not done
- `<INDEX_OF_TASK>` should be within the task list

Examples of usage:
- `unmark 2`
- `unmark 4`

### `delete` - Deletes a task from the task list
Format: `delete <INDEX_OF_TASK>`

- Deletes a task
- `<INDEX_OF_TASK>` should be within the task list

Examples of usage:
- `delete 2`
- `delete 1`

### `find` - Finds a task by keyword
Format: `find <keyword>`

- Finds matching tasks
- `<keyword>` should be one word

Examples of usage:
- `find cs2113`
- `find todo`

### `bye` - Exits the program
Format: `bye`

- Exits the program.

### HOW DO I SAVE MY TASK LIST?

- The program auto saves your task list and loads it when you run the program!