# User Guide
Welcome to R.I.C.A, a chatbot that provides todo list management through a text-based interface! R.I.C.A. is short for Really-Intelligent-Chat-Assistant, and I hope she'll be helpful for you. Or as Rica would say:
```
    ____________________________________________________________
     Hello! I'm
     .______              __          ______             ___          
     |   _  \            |  |        /      |           /   \         
     |  |_)  |           |  |       |  ,----'          /  ^  \        
     |      /            |  |       |  |              /  /_\  \       
     |  |\  \----.  __   |  |  __   |  `----.  __    /  _____  \   __ 
     | _| `._____| (__)  |__| (__)   \______| (__)  /__/     \__\ (__)
     That's Really-Intelligent-Chat-Assistant for you!
     How may I be of assistance?
    ____________________________________________________________
```

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
    * [`bye` - Turns Rica Off](#bye---turns-rica-off)
<!-- TOC -->

## Features 

### Task Type 1: `Todos`

Most basic Task supported by Rica. Todo Tasks are either done or not done(commands are explained below), but are considered not done when first created. <br><br>
Each Todo also has a Task **description** of what work needs to be done exactly.

### Task Type 2: `Events`

A kind of Todo that represents an Event. Event Tasks have an associated **start date and/or time** and **end date and/or time** that, accordingly, indicates when that Event will begin and end. <br><br>
Like Todos, they can be marked done or not done(default state when first created), and each have an Event description as well.

### Task Type 3: `Deadlines`

Another kind of Todo that represents a Deadline(scary stuff, eh?). Deadline Tasks have a specified **due date** that refers to the date and/or time before which they must be completed(or suffer the consequences! oh dear). <br><br>
Anyway, like Todos, they can also be marked done or not done(default state when first created), and each have a Deadline description.

### Find Tasks By Description

After creating a long list of Tasks(bless your eyes, by the way), you may request for Rica to return a list of _only those Tasks_ that have _descriptions matching a certain **keyword**_ specified by you(see command description below for details). Handy right?

### Task Completion Status

All Tasks can be marked **done** or **not done**. Rica will help you remember this state for every Task you create, and you may mark any Task as done or not done(oh no) with commands explained further below. <br><br>
This status is indicated by a cross `[X]` if a Task is done or an empty box `[ ]` if not done when Rica is told to list all Tasks she remembers. See `list` command below for an example.

### Task Deletion

**Deletes** a Task remembered by Rica. So your Task list has _one less Task_! My personal favourite action in life, and Rica's favourite(I believe, ask her some day).

### Task Data Storage

Rica has a fantastic memory, so she will _record your list of Tasks_ in your computer's storage and _restore her previous memories_ the next time she is awakened. <br><br>
Her memories will be stored in the data\tasks.txt file(relative to Rica's jar file). Don't delete this, unless you love an amnesiac Rica. <br><br>
And don't touch her memories please, unless you speak Rica-language fluently, I don't want your Task list disappearing!

## Usage

### `todo` - New Todo Task

Creates a new Todo Task with the given description and a default state of not done.

**Syntax**:

`todo (your-task-description)`

**Example of usage**: 

`todo Remember to be kind to yourself`

**Expected outcome**:

Rica will remember a new Todo Task with a description 'Remember to be kind to yourself' for you, which will be added to your list of Tasks.

```
todo Remember to be kind to yourself
    ____________________________________________________________
     New todo I'll remember: 
       [T][ ] Remember to be kind to yourself
     You have 1 task for now, all the best!
    ____________________________________________________________
```

### `deadline` - New Deadline Task

Creates a new Deadline Task with the given description and due date.

**Syntax**:

`deadline (your-task-description) /by (your-deadline)`

**Example of usage**:

`deadline Fall asleep /by 10pm`

**Expected outcome**:

Rica will remember that you have a deadline called 'Fall asleep' to meet by 10pm, which is added to your list of Tasks.

```
deadline Fall asleep /by 10pm
    ____________________________________________________________
     New deadline I'll remember: 
       [D][ ] Fall asleep (by: 10pm)
     You have 1 task for now, all the best!
    ____________________________________________________________
```

### `event` - New Event Task

Creates a new Event Task with the given description, start date/time and end date/time.

**Syntax**:

`event (your-tassk-description) /from (your-start-date) /to (your-end-date)`

**Example of usage**:

`event Watch sunset /from 7pm /to 7.15pm`

**Expected outcome**:

Rica will keep in mind that you want to 'Watch sunset' from 7pm to 7.15pm, and this Deadline is added to your list of Tasks.

```
event Watch sunset /from 7pm /to 7.15pm
    ____________________________________________________________
     New event I'll remember: 
       [E][ ] Watch sunset (from: 7pm to: 7.15pm)
     You have 1 task for now, all the best!
    ____________________________________________________________
```

### `list` - List all Tasks

Lists all Tasks that Rica currently remembers along with their completion status.

**Example of usage**:

`list`

**Expected outcome**:

Rica-language description of all the Tasks you told her to remember for you as well as which Tasks are done or not done.

```
list
    ____________________________________________________________
     I think you have these tasks:
     1.[E][ ] Watch sunset (from: 7pm to: 7.15pm)
    ____________________________________________________________
```

### `mark` - Marks a Task as done

Given the Task number(according to Rica's list), mark the corresponding Task as done by the user(hooray!).

**Syntax**:

`mark (your-task-number)`

**Example of usage**:

`mark 1`

**Expected outcome**:

Task number 1 is now done, and the next time you tell Rica to `list`, Task number 1 is indicated as done.

```
mark 1
    ____________________________________________________________
     Shall remember that this task is done:
        [E][X] Watch sunset (from: 7pm to: 7.15pm)
    ____________________________________________________________
```

### `unmark` - Marks a Task as not done

Given the Task number(according to Rica's list), indicate that the corresponding Task is not done.

**Syntax**:

`unmark (your-task-number)`

**Example of usage**:

`unmark 1`

**Expected outcome**:

Task number 1 is now not done, and the next time you tell Rica to `list`, Task number 1 is labelled as not done.

```
unmark 1
    ____________________________________________________________
     (Why??) Anyway, I've marked this task as not done yet:
        [E][ ] Watch sunset (from: 7pm to: 7.15pm)
    ____________________________________________________________
```

### `delete` - Deletes a Task

Given the Task number within Rica's Task list, Rica will forget that you have that particular Task(I hope you're not skipping duty!).

**Syntax**:

`delete (your-task-number)`

**Example of usage**:

`delete 1`

**Expected outcome**:

Task number 1 will be deleted, so it will no longer be listed when you tell Rica to `list`.

```
delete 1
    ____________________________________________________________
     I have removed this task for you:
       [E][ ] Watch sunset (from: 7pm to: 7.15pm)
     Let's see... You now have 0 tasks left. Keep going!
    ____________________________________________________________
```

### `find` - Find Task by Keyword

Given a keyword, Rica will look through her memory and provide you a list of all Tasks with a matching description.

**Syntax**:

`find (your-keyword)`

**Example of usage**:

`find sunset`

**Expected outcome**:

Task with a description containing the word 'sunset' is shown to the user.

```
find sunset
    ____________________________________________________________
     I found some matching tasks for 'sunset':
     1.[E][ ] Watch sunset (from: 7pm to: 7.15pm)
    ____________________________________________________________
```

### `bye` - Turns Rica Off

Rica will bid you adieu and shut down. Hope you know what you're doing!

**Example of usage**:

`bye`

**Expected outcome**:

Rica is gone!

```
    ____________________________________________________________
     Leaving so soon? Come back anytime, I'll be happy to help!
    ____________________________________________________________
```
