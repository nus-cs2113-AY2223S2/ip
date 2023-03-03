# CLoM User Guide
CLoM is a tool to help you keep track of tasks, aiding you in your daily life. It is a CLI application which aims to improve your everyday life!
## Features 

### Storing of Tasks

Tasks can be added and stored locally to be viewed each time the user opens the application

### View all of your tasks

See everything that is stored in the task list

### Mark tasks as Done/Not Done

Tasks can be marked as done/not done, and this will be shown visually on the CLI as well

### Find tasks by using a keyword

Find all the tasks in the list which match the keyword the user specified

### Delete task

Delete the task specified by the index

### Add Task - Todo

Add a todo task into the task list

### Add Task - Deadline

Add a deadline task into the task list

### Add Task - Event

Add an event task into the task list

## Usage

### `list` - Displays the entire task list.

Displays the entire task list, with the task type, status and dates as well.

Format: 

`list`

Example of usage: 

`list`

Expected outcome: Task list is displayed

Description of the outcome.

```
        ==========================================
        Here are the tasks in your list:
        1.[T][ ] desc
        2.[D][ ] desc (by:  date)
        3.[E][ ] desc (from: fromdate to: todate) 
        ==========================================
```
### `todo` - Adds a new todo task in the task list

Adds a new task of type todo. Task description is specified by the user after the keyword "todo".

Format: 

`todo <task_desc>`

Example of usage: 

`todo Assignment 3`

Expected outcome: Todo task is added to the task list. 

Description of the outcome.

```
        ==========================================
        Got it. I've added this task:
        Assignment 3
        You have a total of 4 tasks in the list   
        ==========================================
```
### `deadline` - Adds a new deadline task in the task list

Adds a new task of type deadline. Task description is specified by the user after the keyword "deadline".

Format: 

`deadline <task_desc> /by <by_date>`

Example of usage: 

`deadline Eat Buffet /by 4 Jan`

Expected outcome: Deadline task is added to the task list. 

Description of the outcome.

```
        ==========================================
        Got it. I've added this task:
        Eat buffet (by: thursday)
        You have a total of 5 tasks in the list   
        ==========================================
```
### `event` - Adds a new event task in the task list

Adds a new task of type event. Task description is specified by the user after the keyword "event".

Format: 

`event <task_desc> /from <from_date> /to <to_date>`

Example of usage: 

`event Celebrate holidays /from 3 Jan /to 5 Feb`

Expected outcome: Event task is added to the task list. 

Description of the outcome.

```
        ==========================================        
        Got it. I've added this task:
        [E][ ] Celebrate holidays (from: today to: thursday)
        You have a total of 4 tasks in the list
        ========================================== 
```
### `mark` - Marks a task in the task list as done

Marks a task as done. Task number is specified after the keyword "mark".

Format: 

`mark <task_number>`

Example of usage: 

`mark 3`

Expected outcome: Task Number 3 is marked as done. 

Description of the outcome.

```
        ==========================================
        Nice! I've marked this task as done:      
        [E][X] Celebrate holidays (from: today to: thursday)
        ==========================================
```
### `unmark` - Marks a task in the task list as not done

Marks a task as not done. Task number is specified after the keyword "unmark".

Format: 

`unmark <task_number>`

Example of usage: 

`unmark 3`

Expected outcome: Task Number 3 is marked as not done.  

Description of the outcome.

```
        ==========================================
        OK, I've marked this task as not done yet:
        [E][ ] Celebrate holidays (from: today to: thursday)
        ==========================================
```
### `delete` - Deletes a task in the task list

Deletes a task from the task list. Task number is specified after the keyword "delete"

Format: 

`delete <task_number>`

Example of usage: 

`delete 2`

Expected outcome: Task Number 2 is deleted from the task list.   

Description of the outcome.

```
        ==========================================
        Woosh! This task is now gone:
        [D][ ] study CS2113 (by:  Friday)
        ==========================================
```
### `find` - Finds all tasks in the task list which match the specified keyword

Finds all tasks in the task list which match the specified keyword. Keyword to be searhed is specified after the keyword "find". Keyword to be searched is case sensitive.

Format: 

`find <keyword>`

Example of usage: 

`find assignment`

Expected outcome: Task Number 3 is marked as done.  

Description of the outcome.

```
        ==========================================
        Here's what I found :
        1. [T][ ] Assignment 1
        2. [D][X] Assignment 6 (by: 4 March)
        ==========================================
```
### `bye` - Exits the application

Exits the application. Tasks added or deleted are saved locally.

Format: 

`bye`

Example of usage: 

`bye`

Expected outcome: Application exits.  

Description of the outcome.

```
        ==========================================
        Bye. Hope to see you again soon!
        ==========================================
```