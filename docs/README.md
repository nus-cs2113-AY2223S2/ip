# User Guide
Duke acts as a productivity task manager tool that keeps track of your tasks through the CLI. It comes with meaningful features such as data persistence through auto-saving and auto-loading of data.
However, please note that Duke is only suitable for single-user usage as it does not have any online database feature.
## Features
### Adding different types of tasks
User is able to enter 3 types of tasks : 
- ***todo*** : comprising a description string
- ***deadline*** : comprising a description string, and a deadline time.
- ***event*** : comprising a description string, a starting and ending time.

### Modifying and listing existing tasks

User can list all the existing tasks that they currently have so that they can decide whether they need to add, mark, unmark or delete any of the task.
Afterwards, user can then edit existing tasks in 3 ways :
- ***mark*** : marks the task as done
- ***unmark*** : undo the mark on the task (not done)
- ***delete*** : removes the corresponding task from the task list



### Data-persistence (auto-save)
Upon loading of the program, it will check for any existing file ```./data/duke.txt```.  If it exists, it will load the data from the file.
After every adding or modifying of the task, the new task will be automatically saved to the ```./data/duke.txt```. If the file or the directory does not exist, it will create the directory and the file. Afterwards, it will save the updated list of tasks onto the file.

## Usage

### Initialization
Upon loading the program, if the ```./data/duke.txt``` does not exist in user's local machine, it will be created :
```agsl
Directory does not exist, creating a directory called data...
Directory data has been successfully created
File does not exist, creating a new file ./data/duke.txt
File duke.txt has been successfully created
``` 
Afterwards, the user will be greeted with :
```
         __________________________________________________
         Hello I'm Duke, your personal chatbot.
         Is there anything I can do for you
         __________________________________________________

```

### `todo` - adds a "todo" Task

This command will add a "todo" Task to the current list and save it to ```./data/duke.txt```

***Example of usage***: 

```todo study for midterms```

***Expected outcome***:
````
         __________________________________________________
         Got it. I've added this task:
          [T][ ] study for midterms
         Now you have 1 task(s) in your list.
         __________________________________________________
````

This shows that the ***todo*** Task has been added.

***Exception***  :
```
         __________________________________________________
Exception Occured: exception.IncompleteInputException: Please specify the description to the task that you wish to add
         __________________________________________________
```
 This exception handling will be displayed in case user did not specify any description.

### `deadline` - adds a "deadline" Task

This command will add a "deadline" Task to the current list and save it to ```./data/duke.txt```.

User needs to specify the description, and the deadline in the ****/by YYYY-MM-DD HH:mm**** format.

***Example of usage***:

```deadline submit CS2113 iP /by 2023-03-03 23:59```

***Expected outcome***:
````
         __________________________________________________
         Got it. I've added this task:
          [D][ ] submit CS2113 iP (by: Mar 03 2023 23:59)
         Now you have 2 task(s) in your list.
         __________________________________________________

````

This shows that the ***deadline*** Task has been added.

***Exceptions***  :
```
         __________________________________________________
Exception Occured: exception.IncompleteInputException: Please specify the description to the task that you wish to add
         __________________________________________________
```
This exception handling will be displayed in case user did not specify any description.
 ```
          __________________________________________________
Exception Occured: exception.IncompleteInputException: Please specify your deadline string /by YYYY-MM-DD HH:MM
         __________________________________________________
 ```
This exception will be displayed if the user does not enter the deadline in the correct   ****/by YYYY-MM-DD HH:mm**** format

### `event` - adds a "event" Task

This command will add a "event" Task to the current list and save it to ```./data/duke.txt```.

User needs to specify the description, and the starting and ending time in the ****/from YYYY-MM-DD HH:mm /to YYYY-MM-DD HH:mm**** format.

***Example of usage***:

```event NUS recess week /from 2023-02-20 00:00 /to 2023-02-24 23:59```

***Expected outcome***:
````
          __________________________________________________
         Got it. I've added this task:
          [E][ ] NUS recess week (from: Feb 20 2023 00:00 to: Feb 24 2023 23:59)
         Now you have 6 task(s) in your list.
         __________________________________________________
````

This shows that the ***event*** Task has been added.

***Exceptions***  :
```
         __________________________________________________
Exception Occured: exception.IncompleteInputException: Please specify the description to the task that you wish to add
         __________________________________________________
```
This exception handling will be displayed in case user did not specify any description.
 ```
         __________________________________________________
Exception Occured: exception.IncompleteInputException: Please specify the starting and ending time of your event
         __________________________________________________
 ```
This exception will be displayed if the user does not enter the starting and ending time in the correct   ****/by YYYY-MM-DD HH:mm**** format

### `find` - find the tasks that contain specific keyword
Displays all the tasks that match the specified keyword. Note that the keyword is ***case-sensitive***.

***Example of usage*** :

```find midterms```

***Expected outcome*** :
```
         __________________________________________________
         Here are the matching tasks for your list: 
         1.[T][ ] study for midterms
         __________________________________________________
```

***or***
```
         __________________________________________________
         There is no task matching your description
         __________________________________________________
```
if nothing matches the specified keyword.

### `list` - lists all existing tasks
Displays all the existing tasks to the CLI

***Example of usage*** : 

```list```

***Expected outcome*** :
```
        __________________________________________________
         Here are the tasks for your list: 
         1.[T][ ] study for midterms
         2.[D][ ] submit CS2113 iP (by: Mar 03 2023 23:59)
         3.[E][ ] NUS recess week (from: Feb 20 2023 00:00 to: Feb 24 2023 23:59)
        __________________________________________________
```
***or***
```
        __________________________________________________
        Seems like you do not have any task at the moment...
        __________________________________________________
```

if the user does not have any task to display.

### `mark` - marks the task as done
Marks the task based on the specified taskId ***(1-based)*** and displays it.

***Example of usage*** :

```mark 1```

***Expected outcome***
```
         __________________________________________________
         Nice, I have marked this task as done: 
         [T][X] study for midterms
         __________________________________________________
```
***Exception*** 
```agsl
Please specify the correct task to mark
Exception occured : exception.InvalidIndexException: Please ensure you enter the correct task number
```
If the taskId specified is invalid.

### `unmark` - unmarks the task (not done)
Unmarks the task based on the specified taskId ***(1-based)*** and displays it.

***Example of usage*** :

```unmark 1```

***Expected outcome***
```
         __________________________________________________
         Ouch, I have unmarked this task: 
         [T][ ] study for midterms
         __________________________________________________
```
***Exception***
```agsl
Please specify the correct task to unmark
Exception occured : exception.InvalidIndexException: Please ensure you enter the correct task number
```
If the taskId specified is invalid.

### `delete` - removes the task 
Removes the task based on the specified taskId ***(1-based)*** and displays it.

***Example of usage*** :

```delete 1```

***Expected outcome***
```
         __________________________________________________
         Noted. I've removed this task: 
         [T][ ] study for midterms
         Now you have 2 task(s) in your list
         __________________________________________________
```
***Exception***
```agsl
Please specify the correct task to unmark
Exception occured : exception.InvalidIndexException: Please ensure you enter the correct task number
```
If the taskId specified is invalid.

### `bye` - exits Duke
Exits the program (data has been saved).

***Example of usage*** :

```
bye
```

***Expected outcome*** :
```
         __________________________________________________
         Bye! Do let me know if you need any further assistance
         __________________________________________________
```

### Format of ```./data/duke.txt``` :
```
[D][ ] submit CS2113 iP (by: 2023-03-03 23:59)
[E][ ] NUS recess week (from: 2023-02-20 00:00 to: 2023-02-24 23:59)
```

