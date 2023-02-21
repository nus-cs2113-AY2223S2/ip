# Araxys Task Manager - User Guide

Araxys is a task manager that allows you to get your life together at just a fraction of your lifespan. The steal of a lifetime, wouldn't you say?

Table of contents:
1. [Quick Start](#quick_start)
2. [Features](#features) 
    1. [Command Format](#command_format)
    2. [Project Commands](#project_commands)
        1. [Todo](#todo)
        2. [Deadline](#deadline)
        3. [Event](#event)
        4. [Help](#help)
        5. [List](#list)
        6. [Mark](#mark)
        7. [Unmark](#unmark)
        8. [Find](#find)
        9. [Exit](#bye)
3. [FAQ](#faq)

## 1. Quick Start <a name = "quick_start"></a>
```
____________________________________________________________
    // | |     //   ) )  // | |  \\ / / \\    / / //   ) )
   //__| |    //___/ /  //__| |   \  /   \\  / / ((
  / ___  |   / ___ (   / ___  |   / /     \\/ /    \\
 //    | |  //   | |  //    | |  / /\\     / /       ) )
//     | | //    | | //     | | / /  \\   / / ((___ / /
____________________________________________________________
Welcome to Araxys Systems, the only system powered by LifeForce™
How may I help you today?
Type "help" to get help.
```

1. Make sure Java 11 is set up on your machine. Type java -version into your favourite terminal to see what version of Java is currently installed there (such as Command Prompt, Git Bash, or MacOS terminal) 
2. Download the latest .jar file from here: [```Releases```](https://github.com/ArtemiszenN/ip/releases)
3. Copy the .jar file to the desired location.
4. Run the java file using the command ```java -jar Araxys.jar```. You should see the welcome message shown above.
5. Enter your commands in the terminal and press Enter to execute them.

## 2. Features <a name = "features"></a>

### 2.1 Command Format <a name = "command_format"></a>
   * Parameters are defined in curly brackets and need to be defined by the user.
      * E.g. ```todo {description}``` where {description} is a parameter which can be defined, such as ```todo Read Remembrance of Earth's Past```
   * Date/time parameters are defined in square brackets and are automatically reformatted if in a valid format.
      * E.g. ```deadline {description} /by [end date/time]``` can be used as ```deadline Do Assignment 3 /by 2023-02-25 23:59``` and will appear as ```Do Assignment 3 (by: 11:59 PM, SATURDAY, FEBRUARY 25, 2023)```
     
### 2.2 Project Commands <a name = "project_commands"></a>

#### 2.2.1 Add a todo task: ```todo``` <a name = "todo"></a>
This command will create a new todo task with a given description, and then automatically save the task
* Format: ```todo {description}```
* Example:

Input:
```
todo Watch Black Adam
```

Output:
```
Command acknowledged. Reducing user lifespan by 30 minutes.
____________________________________________________________
New task has been added: [T][ ]//////Watch Black Adam
____________________________________________________________
```

#### 2.2.2 Add a deadline task: ```deadline``` <a name = "deadline"></a>
This command will create a new deadline task with a given description and end date/time, and then automatically save the task
* Format: ```todo {description} /by [end date/time]```
* Examples:

Input:
```
deadline Do Assignment 3 /by 2023-02-25 23:59
```

Output:
```
Command acknowledged. Reducing user lifespan by 30 minutes.
____________________________________________________________
New task has been added: [D][ ]//////Do Assignment 3 (by: 11:59 PM, SATURDAY, FEBRUARY 25, 2023)
____________________________________________________________
```

Input:
```
deadline Sleep /by 10pm
```

Output:
```
Command acknowledged. Reducing user lifespan by 30 minutes.
____________________________________________________________
New task has been added: [D][ ]//////Sleep (by: 10pm)
____________________________________________________________
```

#### 2.2.3 Add an event task: ```event``` <a name = "event"></a>
This command will create a new deadline task with a given description, start date/time and end date/time, and then automatically save the task
* Format: ```event {description} /from [start date/time] /to [end date/time]```
* Example:

Input:
```
event Cosfest 2023 /from 2023-04-08 12:00 /to 18:00
```

Output:
```
Command acknowledged. Reducing user lifespan by 30 minutes.
____________________________________________________________
New task has been added: [E][ ]//////Cosfest 2023 (from: 12:00 PM, SATURDAY, APRIL 8, 2023 to: 6:00 PM)
____________________________________________________________
```

#### 2.2.4 View the help menu: ```help``` <a name = "help"></a>
This command will show the list of commands and their syntaxes
* Format: ```help```
* Example:

Input:
```
help
```

Output:
```
____________________________________________________________
list: lists out all current items and their current status.
Syntax: list
todo: adds a todo task.
Syntax: todo {task}
deadline: adds a deadline task.
Syntax: deadline {task} /by [endDate]
event: adds an event task.
Syntax: event {task} /from [startDate] /to [endDate]
mark: marks a task as done.
Syntax: mark {index}
unmark: marks a task as not done.
Syntax: unmark {index}
delete: deletes an event.
Syntax: delete {index}
help: brings you here.
Syntax: help
bye: exits the program.
Syntax: bye
find: finds any item with the keywords in the description.
Syntax: find {keywords}
____________________________________________________________
```

#### 2.2.5 List all tasks and task statuses: ```list``` <a name = "list"></a>
* Format: ```list```
* Example:

Input:
```
list
```

Output:
```
____________________________________________________________
Command acknowledged. Reducing user lifespan by 30 minutes.
1:[T][ ]//////Watch Black Adam
2:[D][ ]//////Do Assignment 3 (by: 11:59 PM, SATURDAY, FEBRUARY 25, 2023)
3:[D][ ]//////Sleep (by: 10pm)
4:[E][ ]//////Cosfest 2023 (from: 12:00 PM, SATURDAY, APRIL 8, 2023 to: 6:00 PM)
____________________________________________________________
```

#### 2.2.6 Mark a task as done: ```mark``` <a name = "mark"></a>
* Format: ```mark {index}```
* Example:

Input:
```
mark 2
```

Output:
```
____________________________________________________________
Command acknowledged. Reducing user lifespan by 30 minutes.
Task has been marked as: completed
[D][X]//////Do Assignment 3 (by: 11:59 PM, SATURDAY, FEBRUARY 25, 2023)
____________________________________________________________
```

#### 2.2.7 Unmark a task as not done: ```unmark``` <a name = "unmark"></a>
* Format: ```unmark {index}```
* Example:

Input:
```
unmark 2
```

Output:
```
____________________________________________________________
Command acknowledged. Reducing user lifespan by 30 minutes.
Task has been marked as: not completed
[D][ ]//////Do Assignment 3 (by: 11:59 PM, SATURDAY, FEBRUARY 25, 2023)
____________________________________________________________
```

#### 2.2.8 Find a task by keywords in the description: ```find``` <a name = "find"></a>
* Format: ```find {keyword}```
* Note: {keyword} is case insensitive
* Example:

Input:
```
find cosfest
```

Output:
```
____________________________________________________________
Command acknowledged. Reducing user lifespan by 30 minutes.
Here are the tasks that we have found:
1: [E][ ]//////Cosfest 2023 (from: 12:00 PM, SATURDAY, APRIL 8, 2023 to: 6:00 PM)
____________________________________________________________
```
#### 2.2.9 Exit the program: ```bye``` <a name = "bye"></a>
* Format: ```bye```
* Example:

Input:
```
bye
```

Output:
```
____________________________________________________________
Goodbye. To reach customer service, just look outside your window.
____________________________________________________________
```

## 3. FAQ <a name = "faq"></a>
Q: How do I transfer data from one machine to another machine?

A: The application can be moved by copying the ```.jar``` file from the original machine to the new machine. The save data from ```./data/araxys.txt``` can then be transferred to the same directory as the ```.jar``` file.
