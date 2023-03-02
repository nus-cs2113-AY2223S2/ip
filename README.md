# Duke assistant

This is an assistant named Duke that can help you manage your tasks!

## Install

Go to [Release Page](https://github.com/Zemdalk/ip/releases) and download the latest version of JAR file. Then you can run Duke assistant by running `java -jar Duke.jar`!

## Usage

You can add, mark, unmark, delete tasks in Duke! Generally, there are three types of tasks that Duke can process: `todo`(no start time and end time), `deadline`(no start time but has a deadline) and `event`(has both start time and end time).

### Add a task

- You can add a `todo` task by entering:

``` shell
todo <task description>
```

E.g. `todo Return books`.

- You can add a `deadline` task by entering:

``` shell
deadline <task description> /by <end time>
```

We recommend you to write time in format of "yyyy/MM/dd HH:mm" so Duke can understand the time, e.g. `deadline Return books /by 2023/03/31 18:00`.

- You can add an `event` task by entering:

``` shell
event <task description> /from <start time> /to <end time>
```

E.g. `event CS2113 class /from 2023/03/03 16:00 /to 2023/03/03 18:00`.

### Show all tasks

Use `list` to show all tasks.

### Mark a task as done

You can mark a task as done by entering:

``` shell
mark <number of the task>
```

Here "number of the task" is the index number of the task you want to mark when you run `list` command.

### Mark a task as not done

You can mark a task as not done by entering:

``` shell
unmark <number of the task>
```

### Find a task

- You can find tasks that has certain pattern string in their description by entering:

``` shell
find <pattern>
```

For example, use `find class` to find all tasks with the word "class" in their description.

- You can find tasks that end before certain date/time by entering:

``` shell
find /by <time>
```

Here time can be either form of `yyyy/MM/dd` or `yyyy/MM/dd HH:mm`.

For example, use `find /by 2023/03/31` to find all tasks ending before March 31, 2023 and use `find /by 2023/03/31 20:00` to find all tasks ending before March 31, 2023 8pm.

### Delete a task

You can delete a task by entering:

``` shell
delete <number of the task>
```

### Exit the program

Use `bye` to exit the program.

Your tasks will be recorded and will not lose. So next time you run Duke assistant, all tasks will be restored!