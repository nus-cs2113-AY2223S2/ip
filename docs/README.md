<img src="/img/archduke-black.svg" alt="Archduke" height="60px">

Welcome to the Archduke user documentation.

Archduke is a CLI tool that can help you keep track of various things. It is a derivation of [Project Duke](https://nus-cs2113-ay2223s2.github.io/website/schedule/week2/project.html#project-duke), the [CS2113](https://nus-cs2113-ay2223s2.github.io) individual project (school project).

## Features

- Handle 3 different types of tasks

  - _TODO_ tasks (`T`) are, well, TODO tasks. You have an event that you need to do, and can either mark it as done or not done. That's it.

  - _Deadline_ tasks (`D`) are similar to TODO tasks, but it requires a deadline timestamp. We call this timestamp the _due date_.

  - _Event_ tasks (`E`) are similar to TODO tasks, but it requires the start time and the end time of said event. We call these the _from date_ and the _to date_ respectively.

- Persist task list for different sessions (so you can exit the program and go back and your data will be saved).

## Installation

- **Prerequisite:** Since this app is a CLI app, ensure you are familiar with the command line interface of your operating system (cmd.exe or Powershell for Windows, the shell/terminal for Unix-based OS). You are not required to know anything about the built-in terminal commands, but you need to not be scared of the terminal to use this app.

- Ensure you have Java 11 or above installed in your computer. You can check whether Java installation is in order by typing the following in the terminal and check its output.

  ```
  java --version
  ```

- Download the `.jar` file from the latest release in [here](https://github.com/joulev/ip/releases). It should be named like so `archduke-x.y.jar` where `x.y` is the version number.

- Now you can run the app by typing the following in the terminal

  ```
  java -jar path/to/archduke-x.y.jar
  ```

## Usage

### Add tasks (C)

#### TODO task

```
todo TASK_DESCRIPTION
```

- Add a TODO task with the description `TASK_DESCRIPTION`.

##### Example

```
todo read tenten kakumei
```

#### Deadline task

```
deadline TASK_DESCRIPTION /by DUE_DATE
```

- Add a deadline task with the given description and the given due date.
- **Important:** The date must be of format `DD/MM/YYYY HH:MM:SS`.

##### Example

```
deadline watch tenten kakumei #5 /by 08/02/2023 21:00:00
```

#### Event task

```
event TASK_DESCRIPTION /from FROM_DATE /to TO_DATE
```

- Add an event task with the given description, from date and to date.
- **Important:** Similar to `deadline`, the date must also be of the format `DD/MM/YYYY HH:MM:SS`.

##### Example

```
event tenten kakumei #6 /from 08/02/2023 21:00:00 /to 08/02/2023 21:30:00
```

### Get tasks (R)

#### Get all tasks

```
list
```

- Print the list of all tasks. Yes, the command is that simple.

#### Query tasks by description

```
find QUERY
```

- Print the list of all tasks that match the provided query.

* A task is said to match a string query if that task's description contains that string query. Typos are not handled and word order must be correct, so please be careful.

##### Example

```
find tenten kakumei
```

### Update tasks (U)

#### Mark tasks as done

```
mark INDEX
```

- Each task has an index, counted from 1. To get the index of a task, print the whole task list with `list`.
- This command marks the task with the index `INDEX` as done.

##### Example

```sh
# mark the 5th task as printed by `list` as completed
mark 5
```

#### Mark tasks as undone

```
unmark INDEX
```

- Similar to `mark`, except the task is marked as not completed.

### Delele tasks (D)

#### Delete tasks

```
delete INDEX
```

- Similar to `mark` and `unmark`, except the task is deleted.

### Miscellaneous

#### Quit

```
bye
```

- Quit the application. Data is still persisted.

### For power users

- The persisted data is stored in `.archduke/tasks.json`. Edit it if you know what you are doing. Keep a backup if needed, because Archduke is unforgiving for broken storage files. If you corrupt the file in any way, shape or form, the program will just discard it the next time you use it.
