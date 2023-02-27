# Duke User Guide
Duke is a Command Line Interface (CLI) Personal Assistant Chatbot that helps you to keep track of tasks.

## Content Page
<!-- TOC -->
* [Duke User Guide](#duke-user-guide)
  * [Content Page](#content-page)
  * [Quick start](#quick-start)
  * [Features](#features)
    * [Viewing help: `help`](#viewing-help--help)
    * [Adding todo task: `todo` \<task>](#adding-todo-task--todo-task-)
    * [Adding deadline task: `deadline` \<task> `/by` \<date>](#adding-deadline-task--deadline-task--by-date-)
    * [Adding event task: `event` \<task> `/from` \<start date> `/to` \<end date>](#adding-event-task--event-task--from-start-date--to-end-date-)
    * [Delete a task: `delete` \<task number>](#delete-a-task--delete-task-number-)
    * [Saving the task list and exiting the program: `bye`](#saving-the-task-list-and-exiting-the-program--bye)
    * [Search task by date: `date` \<yyyy/mm/dd>](#search-task-by-date--date-yyyymmdd-)
    * [Find tasks by keyword(s): `find` \<keyword(s)>](#find-tasks-by-keyword--s---find-keyword--s--)
    * [View all tasks: `list`](#view-all-tasks--list)
    * [Marking a task done: `mark` \<task number>](#marking-a-task-done--mark-task-number-)
    * [Marking a task not done: `unmark` \<task number>](#marking-a-task-not-done--unmark-task-number-)
  * [An Example Walkthrough](#an-example-walkthrough)
  * [Command Summary](#command-summary)
<!-- TOC -->

## Quick start
1. Ensure that you have Java 11 or above installed in your computer </br> Click [here](https://www.java.com/en/download/help/version_manual.html) for steps on how to check your Java version </br> Click [here](https://docs.oracle.com/en/java/javase/11/install/overview-jdk-installation.html#GUID-8677A77F-231A-40F7-98B9-1FD0B48C346A) for the guide on installing Java 11
2. Download Duke [here](https://github.com/lil1n/ip/releases)
3. Move the Duke.jar file to the file directory of your choice </br> You can move the jar file by right-clicking on Duke.jar, select cut, then go to the directory of choice and right-clicking and pasting it
4. Right-click on the directory with Duke.jar and select "open in terminal", type `java -jar Duke.jar` and press Enter on your keyboard to start the program
5. Start using the program by typing `help` and pressing Enter on your keyboard to get the list of commands supported

## Features 

### Viewing help: `help`
Displays a message showing the commands available, and their usage.

### Adding todo task: `todo` \<task>
Adds a todo task to the task list. </br> A task type of todo only keeps track of:
- Task name
- Status of task: done or not done

Example:
- `todo go supermarket`
- `todo charge phone`

### Adding deadline task: `deadline` \<task> `/by` \<date>
Adds a deadline task to the task list. </br> A task type of deadline only keeps track of:
- Task name
- Status of task: done or not done
- Date to be tagged with task

Example:
- `deadline eat bread /by tomorrow`
- `deadline submit proposal /by 2023/01/30`
- `deadline bread sale /by 2023/01/25 1800`

```
Tip: Duke understands valid date in the format of yyyy/mm/dd, hence supplying a date in such format allows you to filter deadline tasks by the date
```

### Adding event task: `event` \<task> `/from` \<start date> `/to` \<end date>
Adds an event task to the task list. </br> A task type of event only keeps track of:
- Task name
- Status of task: done or not done
- Start date to be tagged with task
- End date to be tagged with task

Example:
- `event visit friends /from today /to tomorrow`
- `event closing down sale /from today /to 2023/01/30`
- `event opera performance /from 2023/01/27 1600 /to 2023/01/27 1900`

```
Tip: Duke understands valid date in the format of yyyy/mm/dd, hence supplying a date in such format allows you to filter event tasks by the date
```

### Delete a task: `delete` \<task number>
Deletes a task by the task number in the task list. </br> Example:
- `delete 1`
- `delete 2`

### Saving the task list and exiting the program: `bye`
Exits the program and saves the data in the list into a text file that is stored in a file named data that is stored in the file where Duke.jar is stored. </br> Example:
- `bye`

### Search task by date: `date` \<yyyy/mm/dd>
Shows the tasks that contains the date in its task information. </br> Example:
- `date 2023/01/25`
- `date 2023/02/01`

### Find tasks by keyword(s): `find` \<keyword(s)>
Shows the tasks that contains the keyword(s) in the task name of the task. </br> Example:
- `find bread`
- `find sale`

### View all tasks: `list`
Shows all the tasks in the list. </br> Example: 
- `list`

### Marking a task done: `mark` \<task number>
Marks a task as done by the task number in the task list. </br> Example:
- `mark 1`
- `mark 2`

### Marking a task not done: `unmark` \<task number>
Marks a task as not done by the task number in the task list. </br> Example:
- `unmark 1`
- `unmark 2`

## An Example Walkthrough
```
java -jar Duke.jar
________________________________________________________________________________
 Hello! I'm Duke
 What can I do for you?
________________________________________________________________________________

todo go supermarket
________________________________________________________________________________
 The following task has been added:
   [T][ ] go supermarket
 There is now 1 task(s) in total.
________________________________________________________________________________

todo charge phone
________________________________________________________________________________
 The following task has been added:
   [T][ ] charge phone
 There is now 2 task(s) in total.
________________________________________________________________________________


deadline eat bread /by tomorrow
________________________________________________________________________________
 The following task has been added:
   [D][ ] eat bread (by: tomorrow)
 There is now 3 task(s) in total.
________________________________________________________________________________

deadline submit proposal /by 2023/01/30
________________________________________________________________________________
 The following task has been added:
   [D][ ] submit proposal (by: 30 Jan 2023)
 There is now 4 task(s) in total.
________________________________________________________________________________


deadline bread sale /by 2023/01/25 1800
________________________________________________________________________________
 The following task has been added:
   [D][ ] bread sale (by: 25 Jan 2023 1800)
 There is now 5 task(s) in total.
________________________________________________________________________________

event visit friends /from today /to tomorrow
________________________________________________________________________________
 The following task has been added:
   [E][ ] visit friends (from: today to: tomorrow)
 There is now 6 task(s) in total.
________________________________________________________________________________

event closing down sale /from today /to 2023/01/30
________________________________________________________________________________
 The following task has been added:
   [E][ ] closing down sale (from: today to: 30 Jan 2023)
 There is now 7 task(s) in total.
________________________________________________________________________________

event opera performance /from 2023/01/25 1600 /to 2023/01/25 1900
________________________________________________________________________________
 The following task has been added:
   [E][ ] opera performance (from: 25 Jan 2023 1600 to: 25 Jan 2023 1900)
 There is now 8 task(s) in total.
________________________________________________________________________________

list
________________________________________________________________________________
 Here are the tasks in your lists:
 1.[T][ ] go supermarket
 2.[T][ ] charge phone
 3.[D][ ] eat bread (by: tomorrow)
 4.[D][ ] submit proposal (by: 30 Jan 2023)
 5.[D][ ] bread sale (by: 25 Jan 2023 1800)
 6.[E][ ] visit friends (from: today to: tomorrow)
 7.[E][ ] closing down sale (from: today to: 30 Jan 2023)
 8.[E][ ] opera performance (from: 25 Jan 2023 1600 to: 25 Jan 2023 1900)
________________________________________________________________________________

delete 6
________________________________________________________________________________
 Noted. Task removed: 
   [E][ ] visit friends (from: today to: tomorrow)
 You now have 7 task(s) in the list.
________________________________________________________________________________

delete 2
________________________________________________________________________________
 Noted. Task removed: 
   [T][ ] charge phone
 You now have 6 task(s) in the list.
________________________________________________________________________________

delete 2
________________________________________________________________________________
 Noted. Task removed: 
   [T][ ] charge phone
 You now have 6 task(s) in the list.
________________________________________________________________________________

mark 1
________________________________________________________________________________
 Awesome! I've marked this task as done:
   [T][X] go supermarket
________________________________________________________________________________

mark 4
________________________________________________________________________________
 Awesome! I've marked this task as done:
   [D][X] bread sale (by: 25 Jan 2023 1800)
________________________________________________________________________________


unmark 1
________________________________________________________________________________
 OK, I've marked this task as not done yet:
   [T][ ] go supermarket
________________________________________________________________________________

list
________________________________________________________________________________
 Here are the tasks in your lists:
 1.[T][ ] go supermarket
 2.[D][ ] eat bread (by: tomorrow)
 3.[D][ ] submit proposal (by: 30 Jan 2023)
 4.[D][X] bread sale (by: 25 Jan 2023 1800)
 5.[E][ ] closing down sale (from: today to: 30 Jan 2023)
 6.[E][ ] opera performance (from: 25 Jan 2023 1600 to: 25 Jan 2023 1900)
________________________________________________________________________________

date 2023/01/25
________________________________________________________________________________
 The following tasks are due on 25 Jan 2023:
 1. [D][X] bread sale (by: 25 Jan 2023 1800)
 2. [E][ ] opera performance (from: 25 Jan 2023 1600 to: 25 Jan 2023 1900)
________________________________________________________________________________

date 2023/01/25
________________________________________________________________________________
 The following task(s) occur on 25 Jan 2023:
 1. [D][X] bread sale (by: 25 Jan 2023 1800)
 2. [E][ ] opera performance (from: 25 Jan 2023 1600 to: 25 Jan 2023 1900)
________________________________________________________________________________


find sale
________________________________________________________________________________
 Here are the matching tasks in your list:
 1. [D][X] bread sale (by: 25 Jan 2023 1800)
 2. [E][ ] closing down sale (from: today to: 30 Jan 2023)
________________________________________________________________________________

bye
________________________________________________________________________________
 Bye! Hope to see you again soon!
________________________________________________________________________________
```

## Command Summary
| **Action**             | **Format<br/>Example command**                                                                                          |
|------------------------|-------------------------------------------------------------------------------------------------------------------------|
| Add todo task          | `todo` task<br/>**Example:** `todo buy bread`                                                                           |
| Add deadline task      | `deadline` task `/by` date<br/>**Example:** `deadline submit proposal /by 2023/02/28`                                   |
| Add event task         | `event` task name `/from` date `/to` date<br/>**Example:** `event make bread /from 2023/02/28 1400 /to 2023/02/28 1600` |
| Delete task            | `delete` task number<br/>**Example:** `delete 1`                                                                        |
| Saving and exiting     | `bye`                                                                                                                   |
| Filter task given date | `date` date<br/>**Example:** `date 2023/02/28`                                                                          |
| Find tasks             | `find` keyword(s)<br/>**Example:** `find bread`                                                                         |
| Help                   | `help`                                                                                                                  |
| List                   | `list`                                                                                                                  |
| Mark task as done      | `mark` task number<br/>**Example:** `mark 1`                                                                            |
| Mark task as not done  | `unmark` task number<br/>**Example:** `unmark 1`                                                                        |
