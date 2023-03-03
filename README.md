# Nano User Guide

Nano is the chatbot for individual project for CS2113, AY22/23 Semester 2.

Nano is named after the nano machine from the fantasy wuxia novel Nano Machine!

## Table of Contents

- [Nano User Guide](#nano-user-guide)
   - [Features](#features)
      - [Task Creation](#task-creation)
      - [Task Management](#task-management)
  - [Commands](#commands)
    - [add - Add new task](#add---add-new-task)
    - [list - Display your Task List](#list---display-your-task-list)
    - [mark - Tag task as done](#mark---tag-task-as-done)
    - [unmark - Tag task as undone](#unmark---tag-task-as-undone)
    - [delete - Delete a Task](#delete---delete-a-task)
    - [find - Query for a task](#find---look-for-a-task)
    - [exit - Nano goes to sleep](#exit---nano-goes-to-sleep)

## Features

Nano has a variety of features for you to manage your tasks efficiently via CLI.

### Task Creation

Keep track of your tasks in a list.

Nano can manage up to three types of tasks:
1. Todo - regular todo tasks
2. Deadline - tasks that with a deadline
3. Event - tasks that have a start and end time

### Task Management

Nano can help you mark, unmark, list, find, and save your tasks easily!

- Nano marks your tasks to indicated that you have completed it.
- Nano can list out all your current tasks and its details.
- Nano allows you to look for specific tasks using a keyword.
- Nano can save your task list.

## Commands

### add - Add new task

Add a new task(Todo, Deadline, Event). Nano will automatically detect the type of task for you.

Syntax:

``/add <task_name>``

``/add <task_name> by/<due_date>``

``/add <task_name> from/<start_time> to/<end_time>``

### list - Display your task list

Nano will show you your list of tasks.  
The list contains all the task details as well

Syntax:

``/list``

### mark - Tag task as done
Nano marks a task in the task list as done

Syntax:  
``/mark <task_name>``


### unmark - Tag task as undone

Nano marks a task in the task list as not done.

Syntax:  
``/unmark <task_name>``


### delete - Delete a Task

Nano deletes a task from your task list.

Syntax:  
``delete <task_name>``

###  find - Look for a task

Nano will look for the tasks containing a keyword and display them to you.

Syntax:

``/find <keyword>``

### help - Displays all commands

Nano reminds you of all his features and their syntax

Syntax:
``/help``

### exit - Nano goes to sleep

Nano enters into sleeping mode.

Syntax:

``/exit``

