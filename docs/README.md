# User Guide

Duke is a command-line app for managing your own list of tasks to do. 
It allows you to store your todo, deadlines, and events for various tasks and give you a quick glance of your task list. 
You can also keep track of all the tasks you have done by marking them. 
Moreover, it allows you to filter with specific keywords to easily find the task you’re looking for.

## Features
### View Help: help
It will print all the commands that the user can use.
The list of tasks will be shown in sequence.
In addition, it will be shown in [Type][Done(X)/Not done( )] <description> <time> format.


### Add A todo to the task List: todo
Adds a todo to the list of tasks.

### Add A Deadline to the task list: deadline
The deadline function allows you to add a deadline to your task list.


### Add An Event to the task list: event
The event function allows you to add an event to your task list.

### Mark A Task: mark
Mark a task in the list.

### Unmark A Task: unmark
Mark a task in the list.

### Filter the tasks: find
The find function will display tasks that contain the keyword entered by the user.


### Delete task: delete
The delete function will allow you to delete the task in your list.

## Usage

### `help` - It will print all the commands that the user can use.
The list of tasks will be shown in sequence.
In addition, it will be shown in [Type][Done(X)/Not done( )] <description> <time> format.
Format: help
```
>> help
First time using Duke? Below is the quick guide for you to get to know about how to use the app …
```

### `list` - List all tasks will in sequence.
In addition, it will be shown in [Type][Done(X)/Not done( )] <description> <time> format.
Format: list
```
>> list
1. [T][ ] return book
2. [D][ ] finish ip (by: May 02 2023)
3. [E][ ] CG2023 midterm (from: Mar 02 2023, to: Mar 02 2023)
```

### `todo` - add a todo to the list of tasks
Format: todo <todo description>
```
>> list
[T][ ] read book
>> todo play game
>> todo return book
>> list
[T][ ] read book
[T][ ] play game
[T][ ] return book
```

### `deadline` - add a deadline to the list of tasks
Format: deadline <deadline description> by: <when>
```
>> list
[T][ ] read book
>> deadline return book by: 2022-02-02
>> list
[T][ ] read book
[D][ ] return book (by: Feb 02 2022)
```

### `event` - add an event to the list of tasks.
Format: event <event description> from: <when> to: <when>
```
>> list
[T][ ] read book
>> event Malaysia Trip from 2023-03-04 to: 2023-03-10
>> list
[T][ ] read book
[D][ ] Malaysia Trip (from: Mar 04 2023, to: Mar 10 2023)
```
### `mark` - mark a task in the list.
Format: mark <Task Index>
```
>> list
1. [T][ ] read book
2. [T][ ] play game
3. [T][ ] return book
>> mark 1
>> list
1. [T][X] read book
2. [T][ ] play game
3. [T][ ] return book
```
### `unmark` - unmark a task in the list.
Format: unmark <Task Index>
```
>> list
1. [T][X] read book
2. [T][ ] play game
3. [T][ ] return book
>> unmark 1
>> list
1. [T][ ] read book
2. [T][ ] play game
3. [T][ ] return book
```

### `find` - display tasks that contain the keyword entered by the user.
Format: find <Keyword>
```
>> list
1. [T][X] read book
2. [T][ ] play game
3. [T][ ] return book
>> find book
[T][X] read book
[T][ ] return book
```
### `delete` - delete the task in your list.
Format: delete <Task Index>
```
>> list
1. [T][X] read book
2. [T][ ] play game
3. [T][ ] return book
>> delete 1
>> list
1. [T][ ] play game
2. [T][ ] return book
```