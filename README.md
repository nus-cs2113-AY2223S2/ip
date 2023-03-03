# User Guide
## Contents
- **[[Quick Start](#quick-start)]**
- **[[Features](#features)]**
- **[[Command Summary](#command-summary)]**

## Quick Start
1. Ensure you have Java ```11``` or above installed in your Computer.

2. Download the latest ```ip.jar``` from [here](https://github.com/Ng-YZ/ip/releases/tag/A-jar).

3. Copy the file to the folder you want to use as the home folder for your ```Duke```.

4. Open a command terminal, ```cd``` into the folder you put the jar file in, 
and use the ```java -jar ip.jar``` command to run the application.

5. Type the command in the command box and press Enter to execute it. 
   Some example commands you can try:
   - ```list```: Lists all tasks that you have.
   - ```todo TASK DESCRIPTION```: Adds a to-do task to your task list.
   - ```delete 2```: Deletes the 2nd task in your current list.
   - ```bye```: Exits the program.
6. Refer to the [```features```](#features) below for details of each command.

## Features
   - [Adding a To-do task: ```todo```](#adding-a-to-do-task--todo)
   - [Adding a Deadline: ```deadline```](#adding-a-deadline--deadline)
   - [Adding an Event: ```event```](#adding-an-event--event)
   - [Deleting a task: ```delete```](#deleting-a-task--delete)
   - [Marking a task: ```mark```](#marking-a-task--mark)
   - [Unmarking a task: ```unmark```](#unmarking-a-task--unmark)
   - [Finding a task: ```find```](#finding-a-task--find)
   - [Listing all tasks: ```list```](#listing-all-tasks--list)
   - [Exiting the program: ```bye```](#exiting-the-program--bye)

#### Notes about the command format
- Words in ```UPPER_CASE``` are the parameters to be supplied by user.
e.g. in ```todo TASK_DESCRIPTION```, ```TASK_DESCRIPTION``` is a parameter which can be used as 
```todo assignment```.
- Words after the slash, e.g. ```/by```, is expected for the command to be interpreted correctly.
- Extraneous parameters for commands that do not take in parameters will not be accounted.
e.g. if the command specifies ```bye asdf```, it will not be interpreted as ```bye```.


### Adding a To-do task: ```todo```
Adds a to-do task to the task list.

Format: ```todo TASK_DESCRIPTION```

Examples:
- ```todo tutorial worksheet```

Sample output:
```
__________________________________________________________
Gotcha, this task has been added to the list: 
[T][ ] tutorial worksheet
There are 1 tasks in your list :)
__________________________________________________________
```

### Adding a Deadline: ```deadline```
Adds a deadline task to the task list with the due date.

Format: ```deadline TASK_DESCRIPTION /by DATE_TIME```
- ```DATE_TIME``` can be specified as a date, day, or time depending on your preference.

Examples:
- ```deadline assignment 1 /by Thursday 10pm```
- ```deadline work report /by 2359```

Sample output:
```
__________________________________________________________
Gotcha, this deadline has been added to the list: 
[D][ ] assignment 1 (by: Thursday 10pm)
There are 2 tasks in your list :)
__________________________________________________________
```
```
__________________________________________________________
Gotcha, this deadline has been added to the list:
[D][ ] work report (by: 2359)
There are 3 tasks in your list :)
__________________________________________________________
```

### Adding an Event: ```event```
Adds an event to the task list with the start and end time.

Format: ```event TASK_DESCRIPTION /from START_DATE_TIME /to END_DATE_TIME```
- ```START_DATE_TIME``` can be specified as a starting date, day, or time of an event depending on your preference.
- ```END_DATE_TIME``` can be specified as an ending date, day, or time of an event depending on your preference.

Examples:
- ```event birthday party /from Friday 3pm /to 6pm```
- ```event lecture /from 2pm /to 5pm```

Sample output:
```
__________________________________________________________
Gotcha, this event has been added to the list: 
[E][ ] birthday party (from: Friday 3pm to: 6pm)
There are 4 tasks in your list :)
__________________________________________________________
```
```
__________________________________________________________
Gotcha, this event has been added to the list: 
[E][ ] lecture (from: 2pm to: 5pm)
There are 5 tasks in your list :)
__________________________________________________________
```

### Deleting a task: ```delete```
Deletes the specified task from the task list.

Format: ```delete INDEX```
- Deletes the task at the specified ```INDEX```. The index refers to the index number shown in the displayed task list.
- The index must be a positive integer 1, 2, 3...

Examples:
- ```delete 1```
- ```delete 5```
- ```delete 100```

Sample output: 
```
__________________________________________________________
Okay, I have deleted this task: 
[T][ ] tutorial worksheet
There are 4 tasks in your list :)
__________________________________________________________
```
```
__________________________________________________________
Okay, I have deleted this task: 
[D][ ] assignment 1 (by: Thursday 10pm)
There are 3 tasks in your list :)
__________________________________________________________
```
```
__________________________________________________________
Oops.. I don't know what this means :(
__________________________________________________________
```

### Marking a task: ```mark```
Marks the specified task from the task list as completed.

Format: ```mark INDEX```
- Marks the task at the specified ```INDEX```. The index refers to the index number shown in the displayed task list.
- The index must be a positive integer 1, 2, 3...

Examples:
- ```mark 1```
- ```mark 3```

Sample output:
```
__________________________________________________________
Gotcha, this task has been marked done: 
[D][X] work report (by: 2359)
__________________________________________________________
```
```
__________________________________________________________
Gotcha, this task has been marked done: 
[E][X] lecture (from: 2pm to: 5pm)
__________________________________________________________
```

### Unmarking a task: ```unmark```
Unmarks the specified task from the task list, indicating that task is uncompleted.

Format: ```unmark INDEX```
- Marks the task at the specified ```INDEX``` as incomplete. The index refers to the index number 
shown in the displayed task list.
- The index must be a positive integer 1, 2, 3...

Examples:
- ```unmark 1```
- ```unmark 3```

Sample output:
```
__________________________________________________________
Gotcha, this task has been unmarked. Remember to complete it: 
[D][ ] work report (by: 2359)
__________________________________________________________
```

### Finding a task: ```find```
Finds the tasks that contains the given keywords.

Format: ```find KEYWORDS [MORE_KEYWORDS]```
- Finds the tasks with the ```KEYWORDS```. 
- ```[MORE_KEYWORDS]``` is optional. There can be more than one keyword.
- Alphabetical characters and words can all be matched. 
- If tasks containing the keywords are not found, an empty list will be displayed.

Examples:
- ```find cs2113```
- ```find eat acai```

Sample output:
```
__________________________________________________________
Here are the matching tasks in your list:
[D][ ] cs2113 user guide (by: 2359 tomorrow)
[D][ ] cs2113 team project MVP (by: next tutorial)
__________________________________________________________
```
```
__________________________________________________________
Here are the matching tasks in your list:
__________________________________________________________
```

### Listing all tasks: ```list```
Shows a list of all tasks in the task list.

Format: ```list```

### Exiting the program: ```bye```
Exits the program.

Format: ```bye```

## Command Summary 
| **Action**   | **Format, Examples**                                                                                            |
|--------------|-----------------------------------------------------------------------------------------------------------------|
| Add To-do    | ```todo TASK_DESCRIPTION```<br/>e.g.,```todo read book```                                                       |
| Add Deadline | ```deadline TASK_DESCRIPTION /by DATE_TIME```<br/> ```deadline assignment 1 /by Thursday 10pm```                |
| Add Event    | ```event TASK_DESCRIPTION /from START_DATE_TIME /to END_DATE_TIME```<br/> ```event lecture /from 2pm /to 5pm``` |
| Delete Task  | ```delete INDEX```<br/>e.g.,```delete 5```                                                                      |
| Mark Task    | ```mark INDEX```<br/>e.g.,```mark 1```                                                                          |
| Unmark Task  | ```unmark INDEX```<br/>e.g.,`- ```unmark 3```                                                                   |
| Find Task    | ```find KEYWORDS [MORE_KEYWORDS]```<br/>e.g.,```find cs2113```                                                  |
| List         | ```list```                                                                                                      |
| Exit         | ```bye```                                                                                                       |
