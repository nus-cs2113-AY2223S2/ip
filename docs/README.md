# User Guide

## Features 

### Feature-Greet

Once you start the program, it will greet you at first.

Description of the feature.

## Usage

## Run the jar file
Put the jar file in some directory in your computer, and use java -jar "your jar file path" to run it in your terminal

1. If you want to add task, you can type:
   - todo
   - deadline
   - event<br>
And your task status will be shown as T, D, E respectively.<br>
e.g., event do homework /by no idea :wuwu: [E][ ] do homework (by: no idea :wuwu)<br>
If you want to add time to your task, you can do so:<br>
e.g., deadline return book /by 2/12/2019 1800: [D][ ] return book (by: Dec 2 2019 18:00)<br>
or deadline return book /by 2/12/2019: [D][ ] return book (by: Dec 2 2019)<br>

2. If you want to list all the task you have added before, you can type:
   - list
Your tasks will be displayed in the chronological order in which you add them

3. If you want to delete one task based on its position, you can type:
   - delete "The task position"
Then this task will be deleted, and the system will tell you which one is deleted

4. If you want to mark one of your tasks, or you want to remove the mark of one of your tasks, you can type:
   - mark "The task position"
   - unmark "The task position"

5. If you want to find the task position that contain some specific information, you can type:
   - find "The information you want to find"
e.g., <br>
Your task list have tasks: <br>
1. [D][ ] return book (by: Dec 2 2019)<br>
2. [E][X] dinner<br>
If you type "- find book"<br>
It will print "1. [D][ ] return book (by: Dec 2 2019)"<br>

6. If you want to terminate your program, you can type:
   - bye
And you tasks will be stored in a txt file in your computer.

7. If you type some illegal command that the system cannot understand, it will respond to you, and require you to type in another legal command.

## Enjoy Duke!

