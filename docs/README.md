# User Guide

## Quick Start

- Ensure you have `Java 11` installed on your computer. You can check your Java version using the following command in the terminal.

`java --version`

- Download the `.jar` file from here: https://github.com/nikkiDEEE/ip/releases
- To run `ThomasShelby`, open the terminal and change the current directory to same one as the jar file.
- Run the following command in the terminal:

`java -jar ip.jar`

## Features 

### Task Manager

Core features of `ThomasShelby` include adding tasks of the following 3 types to a list:

- `todo`: `[T]`
  - Simple tasks with a `description`
- `deadline`: `[D]`
  - Tasks with a `description` and (do) `by`
- `event`: `[E]` 
  - Tasks with a `description` that have a `start` and `end` date

You can also `add`, `delete`, `mark`, `unmark`, and `find` by keyword.

### Save data

- Any changes to tasks are saved locally in the relative file path `/data/data.txt` upon exiting the program. 
- These saved tasks will be loaded automatically upon starting the program again.


