# User Guide of Your Very Cool Bot Keqing

## Introduction

Keqing is a bot that helps you to save tasks and manage them. 
It is optimized for use via a Command Line Interface (CLI). 
If you can type fast, Keqing can get your tasks management done faster than traditional GUI apps.

At the same time, Keqing is a highly personalised bot that can be easily adopted and used by anyone. 
The instructions are pretty simple and easy to follow. 
With the help of Keqing, you can manage your tasks more efficiently.

## Quick Start

1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest `keqing.jar`.
3. Copy the file to the folder you want to use as the home folder for your bot.
4. Double-click the file to start the bot.
5. Type the command in the command box and press Enter to execute it. 
e.g. typing `menu` and pressing Enter will return you the list of instructions you can use.

## Features 

### Feature - Add Tasks

Keqing is able to store three types of tasks, namely `todo`, `deadline` and `event`. 
You can add them to the list of tasks.

### Feature - List Tasks

Keqing is able to list all the tasks you have added.

### Feature - Mark Tasks as Done/Undone

Keqing is able to mark tasks as done or undone.

### Feature - Delete Tasks

Keqing is able to delete tasks from the list of tasks that you have added.

### Feature - Find Tasks

By typing in a keyword, Keqing is able to find and list down all the tasks that contain the keyword.

### Feature - Storage

Keqing is able to store the list of tasks in a text file locally.
And the list of tasks will be loaded when you start the bot next time.

## Usage

### `todo` - Add a ToDo task

This command adds a ToDo task to the list of tasks.

Example of usage: 

`todo sleep more`

Expected outcome:

This adds a ToDo task with the description `sleep more` to the list of tasks.

Expected output from the bot:
```
____________________________________________________________

Got it. I've added this task:
  added: [T][ ] sleep more
Now you have 5 tasks in your list.
____________________________________________________________
```

### `deadline` - Add a Deadline task

This command adds a Deadline task to the list of tasks.

Example of usage:

`deadline return the guitar pick to Jack /by 2020-09-06`

Expected outcome:

This adds a Deadline task with the description 
`return the guitar pick to Jack` and the deadline `2020-09-06` to the list of tasks.

Expected output from the bot:
```
____________________________________________________________
Got it. I've added this task:
  added: [D][ ] return the guitar pick to Jack (by: 2020-09-06)
Now you have 5 tasks in your list.
____________________________________________________________
```

### `event` - Add an Event task

This command adds an Event task to the list of tasks.

Example of usage:

`event project meeting /from 10am /to 11pm`

Expected outcome:

This adds an Event task with the description 'project meeting' and the time period 
`10am` to `11pm` to the list of tasks.

Expected output from the bot:
```
____________________________________________________________

Got it. I've added this task:
  added: [E][ ] project meeting (from: 10am to: 11pm)
Now you have 6 tasks in your list.
____________________________________________________________
```

### `list` - List all tasks

This command lists all the tasks you have added.

Example of usage:

`list`

Expected outcome:

This lists all the tasks you have added.

Expected output from the bot:
```
____________________________________________________________

1.[T][ ] eat more   
2.[E][ ] project meeting (from: 10am to: 11pm)
____________________________________________________________
```

### `done` - Mark a task as done

This command marks a task as done.

Example of usage:

`mark 1`

Expected outcome:

This marks the first task in the list of tasks as done.
When the task is originally done, this command will not have any effect on it.

Expected output from the bot:
```
____________________________________________________________

Nice! I've marked this task as done:
   [T][X] eat more   
____________________________________________________________
```

### `undone` - Mark a task as undone

This command marks a task as undone.
When the task is originally undone, this command will not have any effect on it.

Example of usage:

`unmark 1`

Expected outcome:

This marks the first task in the list of tasks as undone.

Expected output from the bot:
```
____________________________________________________________

OK, I've marked this task as not done yet:
   [T][ ] eat more   
____________________________________________________________
```

### `delete` - Delete a task

This command deletes a task.

Example of usage:

`delete 1`

Expected outcome:

This deletes the first task in the list of tasks.

Expected output from the bot:
```
____________________________________________________________

Got it. I've deleted this task:
  deleted: [T][ ] eat more
Now you have 1 tasks in your list.
____________________________________________________________
```

(Additional usage: `delete all`, this deletes all the tasks in the list of tasks.)

### `find` - Find tasks

This command finds tasks that contain the keyword.

Example of usage:

`find eat`

Expected outcome:

This finds all the tasks that contain the keyword `eat`.

Expected output from the bot:
```
____________________________________________________________

Keqing tried very hard! Here are the matching tasks in your list:
2.[T][ ] eat more
____________________________________________________________
```

### `menu` - List all instructions

This command lists all the instructions you can use.

Example of usage:

`menu`

Expected outcome:

This lists all the instructions you can use.

Expected output from the bot:
```
____________________________________________________________

Try the following commands:
1. list: show the list of tasks;
2. todo: add a task without starting time/deadline to the list;
3. deadline: add a task with deadline to the list;
4. event: add a task with specific starting and ending time
5. mark: mark a task as 'done' state;
6. unmark: unmark a task from 'done' state;
7. delete: delete a task from the list;
8. find: find a task from the list by keyword;
____________________________________________________________
```

### `bye` - Exit the bot

This command exits the bot.

Example of usage:

`bye`

Expected outcome:

Keqing goes to rest.

Expected output from the bot:
```
____________________________________________________________

Bye. Hope to see you again soon!
____________________________________________________________
```

## Summary

This is a summary of the instructions you can use.

| Instruction | Description |

| ----------- | ----------- |

| `list` | List all tasks |

| `todo` | Add a ToDo task |

| `deadline` | Add a Deadline task |

| `event` | Add an Event task |

| `done` | Mark a task as done |

| `undone` | Mark a task as undone |

| `delete` | Delete a task |

| `find` | Find tasks |

| `menu` | List all instructions |

| `bye` | Exit the bot |