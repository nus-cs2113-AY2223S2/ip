# Duke Chatbot User Guide
Duke is a ChatBot designed to help you to track your tasks via a command line interface. It could be just a normal task, an event or even an important deadline. Use it now!
## Setting Up Duke Chatbot
1. Ensure you have Java 11 or above installed.
2. Download the latest version `.jar` file [here](https://github.com/hrithie/ip/releases).
3. Type `cd` into your terminal and go to the directory where `.jar` file exists.
4. Run this command `java -jar duke.jar` to start Duke chatbot.

If the setup is correct, the following should be the output when you execute the file:
```
____________________________________________________________
Hola! I'm Duke
Let me know your tasks for the day!
____________________________________________________________
☹ OOPS!!! Currently creating file as it does not exist.
File created successfully! Please proceed with Duke.
```
After launching it the first time, this is the expected output:
```
____________________________________________________________
Hola! I'm Duke
Let me know your tasks for the day!
____________________________________________________________
Data that was saved previously has been loaded.
____________________________________________________________
```
## Features
1. Add Todo
2. Add Deadline
3. Add Event
4. List Tasks
5. Delete Task
6. Mark As Done
7. Mark As Not Done
8. Find Task 
9. Exit

### 1. Add Todo

Add a todo to task list. This task does not have any date/time attributes.

### Usage:

`todo <description>`

### Example: 

`todo return book`

### Expected outcome:

```
____________________________________________________________
Adding this task:
[T][ ] return book
You currently have 1 task(s)
____________________________________________________________
```
### 2. Add Deadline

Add a deadline to task list. This task has a due date attribute.

### Usage:

`deadline <description> /by <due date>`

### Example:

`deadline return book /by 18 Jan`

### Expected outcome:

```
____________________________________________________________
Adding this task:
[D][ ] return book  (by: 18 Jan)
You currently have 2 task(s)
____________________________________________________________

```
### 3. Add Event

Add an event to task list. An event has a start and end day/time attribute.

### Usage:

`event <description> /from <start day/time /to <end day/time>`

### Example:

`event First Aid Course /from Monday 8am /to 6pm`

### Expected outcome:

```
____________________________________________________________
Adding this task:
[E][ ] First Aid Course (from: Monday 8am to: 6pm)
You currently have 3 task(s)
____________________________________________________________

```
### 4. List Tasks

List all the tasks in task list.

### Usage:

`list`

### Example:

`list`

### Expected outcome:

```
____________________________________________________________
Tasks in list:
1.[T][ ] return book
2.[D][ ] return book  (by: 18 Jan)
3.[E][ ] First Aid Course (from: Monday 8am to: 6pm)
____________________________________________________________
```
### 5. Delete Task

Delete a task from task list. Use the task number to specify task to delete. 

### Usage:

`delete <task number>`

### Example:

`delete 2`

### Expected outcome:

```
____________________________________________________________
Successfully deleted task:
[D][ ] return book  (by: 18 Jan)
You currently have 2 task(s) left
____________________________________________________________
```
### 6. Mark As Done

Mark a task as done. Use the task number to specify task to mark as done. 

### Usage:

`mark <task number>`

### Example:

`mark 1`

### Expected outcome:

```
____________________________________________________________
Marking task as done:
[T][X] return book
____________________________________________________________
```
### 7. Mark As Not Done

Mark a task as not done. Use the task number to specify task to unmark. 

### Usage:

`unmark <task number>`

### Example:

`unmark 1`

### Expected outcome:

```
____________________________________________________________
Marking task as undone:
[T][ ] return book
____________________________________________________________
```
### 8. Find Task

Find a task in the list. Use a keyword to specify task to find.

### Usage:

`find <keyword>`

### Example:

`find Course`

### Expected outcome:

```
____________________________________________________________
The keyword is found in these tasks:
1.[E][ ] First Aid Course (from: Monday 8am to: 6pm)
____________________________________________________________
```
### 9. Exit

Exit Duke chatbot.

### Usage:

`bye`

### Example:

`bye`

### Expected outcome:

```
____________________________________________________________
Bye, cya soon!
____________________________________________________________
```
