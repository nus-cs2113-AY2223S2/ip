# User Guide

## Contents
- Quick Start
- Features
  - Adding a Todo: [```todo```](#adding-a-todo-todo)
  - Adding a Deadline: [```deadline```](#adding-a-deadline-deadline)
  - Adding an Event: [```event```](#adding-an-event-event)
  - Listing all tasks: All tasks: [```list```](#listing-all-tasks-list) and Tasks on specific date: [```list```](#listing-all-tasks-occurring-on-a-specific-date)
  - Finding tasks: [```find```](#finding-tasks-find)
  - Marking a task: [```mark```](#marking-a-task-mark)
  - Unmarking a task: [```unmark```](#unmarking-a-task-unmark)
  - Deleting a task: [```delete```](#deleting-a-task-delete)
  - Exiting the program: [```bye```](#exiting-the-program-exit)
  - Saving the data

## Quick Start
1. Ensure you have Java ```11``` installed in your Computer.
2. Download the latest ```ip.jar``` from [here](https://github.com/dsicol/ip/releases/tag/A-Jar).
3. Copy the file to the folder you want to use as the home folder for your Inu.
4. Follow the file path in your OS command prompt, and run Inu with ```java -jar ip.jar```
5. Type the command in the input box and press Enter to execute it.
6. Refer to the [```features```](#features) below for details of each command.

## Features

### Viewing help: ```help```

Shows the list of all available commands.

Format: ```help```

### Adding a Todo: ```todo```

Adds a todo to the task list.

Format: ```todo <description>```
- Description cannot be empty.

Example: ```todo submit iP```

<br>

### Adding a Deadline: ```deadline```

Adds a Deadline to the task list.

Format: ```deadline <description> /by <due date and time>```
- Description and due date and time cannot be empty.
- Format of time is dd/mm/yyyy HH:mm.

Example: ```deadline submit iP /by 07/03/2023 23:59```

<br>

### Adding an Event: ```event```

Adds an Event to the task list.

Format: ```event <description> /from <from date and time> /to <to date and time>```
- Description, from date and time and to date and time cannot be empty.
- Format of time is dd/mm/yyyy HH:mm.

Example: ```event complete next iP increment /from 01/03/2023 10:30 /to 02/03/2023 17:30```

<br>

### Listing all tasks: ```list```

Lists all the task in the tasks list.

Format: ```list```

<br>

### Listing all tasks occurring on a specific date: ```list```

Lists all the task occurring on a specific date in the tasks list.

Format: ```list <date>```.
- Format of date is dd/mm/yyyy.
- No provision of date lists all tasks instead.

<br>

### Finding tasks: ```find```

Find tasks with a keyword.

Format: ```find <keyword>```
- Keyword cannot be empty.

Example: ```find book```

<br>

### Marking a task: ```mark```

Marks a task as done.

Format: ```mark <index>```
- Marks the task at the specified ```<index>```
- The index refers to the index number shown in the displayed task list.
- The index must be a positive integer 1, 2, 3, ...


Example: ```mark 1```

<br>

### Unmarking a task: ```unmark```

Unmarks a task, set is as not done.

Format: ```unmark <index>```
- Unmarks the task at the specified ```<index>```
- The index refers to the index number shown in the displayed task list.
- The index must be a positive integer 1, 2, 3, ...


Example: ```unmark 1```

<br>

### Deleting a task: ```delete```

Deletes a task from the task list.

Format: ```delete <index>```
- Deletes the task at the specified ```<index>```
- The index refers to the index number shown in the displayed task list.
- The index must be a positive integer 1, 2, 3, ...

Example: ```delete 1```

<br>

### Exiting the program: ```bye```

Exits the program.

Format: ```bye```

<br>

### Saving the data
Inu data are saved in the hard disk automatically after exiting the application. There is no need to save manually.