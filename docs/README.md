Adapted from [AB3 User Guide](https://se-education.org/addressbook-level3/UserGuide.html).

# User Guide
Welcome to Duke, an organiser to help you keep track of your tasks!

## Table of contents
- [User Guide](#user-guide)
    * [Set-up](#set-up)
    * [Command summary](#command-summary)
    * [Features](#features)
        + [`help` - Viewing help](#help---viewing-help)
        + [`list` - Listing all saved tasks](#list---listing-all-saved-tasks)
        + [`todo` - Adding a ToDo](#todo---adding-a-todo)
        + [`deadline` - Adding a Deadline](#deadline---adding-a-deadline)
        + [`event` - Adding an Event](#event---adding-an-event)
        + [`mark` - Marking done](#mark---marking-done)
        + [`unmark` - Marking not done](#unmark---marking-not-done)
        + [`delete` - Deleting a task](#delete---deleting-a-task)
        + [`find` - Finding a task](#find---finding-a-task)
        + [`date` - Find tasks on date](#date---find-tasks-on-date)
        + [`bye` - Exiting the program](#bye---exiting-the-program)
        + [Loading and saving of data](#loading-and-saving-of-data)

<small><i><a href='http://ecotrust-canada.github.io/markdown-toc/'>Table of contents generated with markdown-toc</a></i></small>


## Set-up
1. Ensure that you have Java `11` or above installed on your Computer. 
2. Download the latest `ip.jar` from [here](https://github.com/honglinshang/ip/releases/tag/v2.0). 
3. Copy the file to the folder you want to use as the *home folder* for Duke.
4. Open a command terminal, cd into the folder you put the jar file in, and use the 
`java -jar ip.jar` command to run the application
5. On opening, Duke should greet you with this
```
Hello from
 ____        _        
|  _ \ _   _| | _____ 
| | | | | | | |/ / _ \
| |_| | |_| |   <  __/
|____/ \__,_|_|\_\___|
```
6. Type the command in the command box and press Enter to execute it. 
e.g. typing `help` and pressing Enter will show a list of all available commands.
7. Refer to [Features](#features) below for details of each command.  

## Command summary
|  Action  | Format                           |
|:--------:|----------------------------------|
|   Help   | `help`                           |
|   List   | `list`                           |
|   Todo   | `todo TASK`                      |
| Deadline | `deadline TASK /by DATE`         |
|  Event   | `event TASK /from DATE /to DATE` |
|   Mark   | `mark IDX`                       |
|  Unmark  | `unmark IDX`                     |
|  Delete  | `delete IDX`                     |
|   Find   | `find KEYWORD`                   |
|   Date   | `date DATE`                      |
|   Bye    | `bye`                            |

## Features
> Notes:
> - Words in `UPPER_CASE` are parameters to be supplied by the user.  
>   e.g. in 'todo TASK', `TASK` is a parameter that can be used as `todo read book`. 
> - Extraneous parameters for commands that do not take in parameters will be ignored.
>   e.g. `help 123` will be interpreted as `help`. 
> - `date` only considers Deadlines and Events with `DATE`s input as `year-month-dayThour:minute`.
>   e.g. `deadline read book /by 2023-10-30T23:59` sets a deadline for Oct 20 2023, 11:59PM
> - By default, all newly added tasks are not completed. 

### `help` - Viewing help
Shows a list of all commands available in Duke.

Format: `help`

### `list` - Listing all saved tasks
Shows a numbered list of all tasks in Duke. 

Format: `list`

### `todo` - Adding a ToDo
Adds a normal task to Duke. 

Format: `todo TASK`  

Example:
```
>> todo eat lunch

Got it. I've added this todo:
  [T][ ] eat lunch
```

### `deadline` - Adding a Deadline
Adds a task with a due date to Duke.

Format: `deadline TASK /by DATE`
- Enter `DATE` in `yyyy-MM-ddThh:mm` format to use `date` on this task.

Example:
```
>> deadline submit tutorial /by 2023-03-03T23:59

Got it. I've added this deadline:
  [D][ ] submit tutorial (by: Mar 03 2023, 11:59PM)
```

### `event` - Adding an Event
Adds a task with a start and end date to Duke.

Format: `event TASK /from DATE /to DATE`
- Enter `DATE` in `yyyy-MM-ddThh:mm` format to use `date` on this task.

Example:
```
>> event holiday /from 2023-02-25T00:00 /to 2023-03-04T23:59

Got it. I've added this event:
  [E][ ] holiday (from: Feb 25 2023, 12:00AM to: Mar 04 2023, 11:59PM)
```

### `mark` - Marking done
Marks the specified task as completed. 

Format: `mark IDX`
- `IDX` can be obtained by using `list` to find the task's index.  

Example:
```
>> mark 4

Nice!, I've marked this task as done:
  [T][X] eat lunch
```

### `unmark` - Marking not done
Marks the specified task as yet to be completed.

Format: `unmark IDX`
- `IDX` can be obtained by using `list` to find the task's index.

Example:
```
>> unmark 4

OK, I've marked this task as not done yet:
  [T][ ] eat lunch
```

### `delete` - Deleting a task
Deletes the specified task from Duke.

Format: `delete IDX`
- `IDX` can be obtained by using `list` to find the task's index.

Example:
```
>> delete 4

Noted, I've removed this task:
  [T][ ] eat lunch
Now you have 5 tasks in the list
```

### `find` - Finding a task
Shows all tasks in Duke that contain the specified keyword.

Format: `find KEYWORD`

Example:
```
>> find book

Here are the matching tasks in your list:
1.[T][X] read book
2.[D][ ] return book (by: June 6th)
```

### `date` - Find tasks on date
Shows all tasks in Duke that occur on the specified date.

Format: `date DATE`
- `DATE` should be entered in the format `yyyy-MM-dd`.

Example:
```
>> date 2023-03-03

Here are the tasks happening on Mar 03 2023:
1.[D][ ] submit tutorial (by: Mar 03 2023, 11:59PM)
2.[E][ ] holiday (from: Feb 25 2023, 12:00AM to: Mar 04 2023, 11:59PM)
```

### `bye` - Exiting the program
Exits the program. 

Format: `exit`

### Loading and saving of data
Duke automatically loads up your data on start-up.  
After any command that changes the data, Duke will save the changes into your hard disk automatically.  
No worries about saving manually!  

The default path for the save file is defined in `Duke.java` should you wish to change it.  

