# Gilbert User Guide

Gilbert is the Individual Project Component of CS2113 AY22/23 Semester 2.
```
  ________.___.____   __________________________________________
 /  _____/|   |    |  \______   \_   _____/\______   \__    ___/
/   \  ___|   |    |   |    |  _/|    __)_  |       _/ |    |
\    \_\  \   |    |___|    |   \|        \ |    |   \ |    |
 \______  /___|_______ \______  /_______  / |____|_  / |____|
        \/            \/      \/        \/         \/
```
Gilbert is your personal task manager assistant!

## Table of Contents
- [Setting up Gilbert](#setup)
- [Features](#features)
  - [Addition of Tasks](#adding-tasks)
  - [List](#list-tasks)
  - [Deleting Tasks](#deleting-tasks)
  - [Mark done](#mark-as-done)
  - [Mark not done](#mark-as-not-done)
  - [Find Task](#find-task)
  - [Exit Gilbert](#exit)

### Setup
1. Ensure you have Java 11 or above installed.
2. Download the latest version `.jar` file [here](https://github.com/tzixi/ip/releases).
3. Type `cd` <file directory> into your terminal.
4. Run the command `java -jar duke.jar` to start Gilbert task manager.

## Features
## Adding Tasks

Gilbert allows you to add tasks that are due and helps you keep track of them!

Below are 3 different task types that Gilbert can help you track:
1. `Todo`       :   For any tasks that you have on hand
2. `Deadline`   :   For any tasks that have a deadline to follow- Gilbert can help you keep track of that too!
3. `Event`      :   For any events such as meetings and parties!

Adding a todo/event:
Use the format `todo` `<task>`
```
todo project
event birthday party
```
Adding a deadline:
Use the format `deadline` `<task>`/`due by`
```
deadline homework/6pm
```

## List tasks

Gilbert can also list out the existing tasks saved in the system!

Listing your tasks:
Use the command `list`
```
list
```
Example of expected outcome:
```
Here are the tasks in your list:
1.[T][ ] eat lunch
2.[D][ ] do homework by: (6pm)
3.[E][ ] Birthday party
```

## Deleting Tasks

Gilbert can delete previous entries to keep your list neat and tidy!

Deleting a task:
Use the format `delete` `<index>`, where index is the position of the task to be deleted.
```
delete 2
```
Example of expected outcome:
```
Noted. I've removed this task:
do homework
Now you have 2 tasks in your list.
```

## Mark as done

Gilbert can mark a task as done.

Marking a task:
Use the format `mark``<index>`, where index is the position of the task that is done.
```
mark 1
```
Example of expected outcome:
```
Nice! I've marked this task as done:

[T][X] eat lunch
```

## Mark as not done

Similarly, Gilbert can also mark a task as not done.

Unmarking a task:
Use the format `unmark``<index>`, where index is the position of the task that is not done.
```
unmark 1
```
Example of expected outcome:
```
OK, I've marked this task as not done yet:

[T][ ] eat lunch
```

## Find Task

Gilbert has a very useful function that allows the user to find previously inputted tasks.

Finding a task:
Use the format `find``<keyword>`, where keyword need not be just one word.
```
find birthday
```
Example of expected outcome:
```
Here are some of the tasks that matches your keyword:
1. [E][ ] Birthday party
```

## Exit

Finally, the command to exit Gilbert.

Exiting Gilbert:
Use the command `bye`
```
bye
```
Example of expected output:
```
Bye. Hope to see you again soon!
```
