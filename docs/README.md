# Duke User Guide
Duke is a Personal Assistant Chatbot that helps you to keep track of various things via Command Line Interface (CLI).

## Setting Up Duke
1. Ensure you have Java 11 or above installed in your computer.
2. Download the latest `Duke.jar` from [here] (https://github.com/saintzaw/ip/releases).
3. Open your terminal and `cd` into the folder where `Duke.jar` exists.
4. Once you are in the correct folder, run this command `java -jar Duke.jar` to run Duke.

If the setup is correct, you should see the below as the output when you run Duke for the first time:
```
Hello from
 ____        _        
|  _ \ _   _| | _____ 
| | | | | | | |/ / _ \
| |_| | |_| |   <  __/
|____/ \__,_|_|\_\___|

____________________________________________________________
Hello! I'm Duke
What can I do for you?
____________________________________________________________
☹ OOPS!!! The file does not exist. Creating a new file.
Now your data will be saved in the file. Please proceed!
____________________________________________________________
```
In future usage of Duke, you can expect to see this instead:
```
Hello from
 ____        _        
|  _ \ _   _| | _____ 
| | | | | | | |/ / _ \
| |_| | |_| |   <  __/
|____/ \__,_|_|\_\___|

____________________________________________________________
Hello! I'm Duke
What can I do for you?
____________________________________________________________
Saved data has been loaded!
____________________________________________________________
```
Enjoy! 
## Features
- Add Todo
- Add Deadline
- Add Event
- List Tasks
- Delete Task
- Mark As Done
- Mark As Not Done
- Find Task 
- Exit

### Add Todo

Add a todo to task list. A todo is a task that does not have any 
date or time attached to it.

### Usage:

`todo <description>`

### Example: 

`todo read book`

### Expected outcome:

```
____________________________________________________________
Got it. I've added this task:
[T][ ] read book
Now you have 1 tasks in the list.
____________________________________________________________

```
### Add Deadline

Add a deadline to task list. A deadline is a task that has a specific due date.

### Usage:

`deadline <description> /by <due date>`

### Example:

`deadline return book /by 3 March`

### Expected outcome:

```
____________________________________________________________
Got it. I've added this task:
[D][ ] return book (by: 3 March)
Now you have 2 tasks in the list.
____________________________________________________________

```
### Add Event

Add an event to task list. An event is a task that that starts at a specific 
date or time and ends at a specific date or time.

### Usage:

`event <description> /from <start date/time> /to <end date/time>`

### Example:

`event training /from Thursday 1900 /to 2100`

### Expected outcome:

```
____________________________________________________________
Got it. I've added this task:
[E][ ] training (from: Thursday 1900 to: 2100)
Now you have 3 tasks in the list.
____________________________________________________________

```
### List Tasks

List all the tasks in the current task list.

### Usage:

`list`

### Example:

`list`

### Expected outcome:

```
____________________________________________________________
Here are the tasks in your list:
1.[T][ ] read book
2.[D][ ] return book (by: 3 March)
3.[E][ ] training (from: Thursday 1900 to: 2100)
____________________________________________________________

```
### Delete Task

Delete a task from task list by specifying the task number in the list.

### Usage:

`delete <task number>`

### Example:

`delete 1`

### Expected outcome:

```
____________________________________________________________
Noted. I've removed this task:
[T][ ] read book
Now you have 2 tasks in the list.
____________________________________________________________

```
### Mark As Done

Mark a task as done by specifying the task number in the list.

### Usage:

`mark <task number>`

### Example:

`mark 1`

### Expected outcome:

```
____________________________________________________________
Nice! I've marked this task as done:
[D][X] return book (by: 3 March)
____________________________________________________________

```
### Mark As Not Done

Mark a task as not done by specifying the task number in the list.

### Usage:

`unmark <task number>`

### Example:

`unmark 1`

### Expected outcome:

```
____________________________________________________________
OK, I've marked this task as not done yet:
[D][ ] return book (by: 3 March)
____________________________________________________________

```
### Find Task

Find a task in the list by providing a keyword that matches the
task description or part of it.

### Usage:

`find <keyword>`

### Example:

`find book`

### Expected outcome:

```
____________________________________________________________
Here are the matching tasks in your list:
1.[D][ ] return book (by: 3 March)
____________________________________________________________

```
### Exit

Exit the program.

### Usage:

`bye`

### Example:

`bye`

### Expected outcome:

```
____________________________________________________________
Bye. Hope to see you again soon! :)
____________________________________________________________

```
