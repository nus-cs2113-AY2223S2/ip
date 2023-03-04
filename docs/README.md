# User Guide
___
██████╗░░█████╗░██████╗░    
██╔══██╗██╔══██╗██╔══██╗    
██████╦╝██║░░██║██████╦╝    
██╔══██╗██║░░██║██╔══██╗    
██████╦╝╚█████╔╝██████╦╝    
╚═════╝░░╚════╝░╚═════╝░ 

Bob is a _command-line interface_ based solution to scheduling your tasks

___

## Table of Contents
- [User Guide](#user-guide)
    * [Features](#features)
        + [Adding a `task`](#adding-a-task)
          1. [ToDo](#1-todo)
          2. [Deadline](#2-deadline)
          3. [Event](#3-event)
        + [Listing all `tasks`](#listing-all-tasks)
        + [Marking/Un-marking a `task` as done](#marking-or-un-marking-a-task-as-done)
        + [Deleting a `task`](#deleting-a-task)
        + [Finding a `task` using its name](#finding-a-task-using-its-name)
        + [Finding a `task` using its date](#finding-a-task-using-its-date)
        + [Clearing all entries](#clearing-all-entries)
        + [Exiting the application](#exiting-the-application)
        + [Saving the data](#saving-the-data)
        + [Editing the datafile](#editing-the-datafile)
    * [FAQ](#faq)
    * [Command Summary](#command-summary)

<br />

___
## Features 

### Adding a `task`
_There are three types of **`tasks`** supported:_

### 1. ToDo 
Format: `todo TASK_NAME`
- Adds a `task` of type `todo` to the application database
- `todo` is **_not_** case-sensitive
- `TASK_NAME` could be a **_String_** of multiple words

Examples:   
````
todo Complete CS2113 weekly quiz
tODo finish breakfast
Todo homework
````

### 2. Deadline
Format: `deadline TASK_NAME /by BY_DATE`
- Adds a `task` of type `deadline` to the application database
- `deadline` is **_not_** case-sensitive
- `TASK_NAME` could be a **_String_** of multiple words
- `/by` is **_compulsory_** or else the input is treated as **_invalid_**
- `BY_DATE` could be written as any **_String_** of multiple words
  - Alternatively, `BY_DATE` can be formatted as `YYYY-MM-DD` to enable `LocalDate` 
    based functions such as `date`
    - `YYYY` represents the year _(example : `2023` )_
    - `MM` represents the month _(example : `02` )_
    - `DD` represents the day _(example : `20` )_
    - Example : `2023-02-20` represents **20 February 2023**
  - `BY_DATE` can also be certain **_keywords_**:
    - `tomorrow` which sets `BY_DATE` to the next day the day you used this 
      command
    - `next TIME` which adds `TIME` to the day you used this command's date
    - `NUMBER TIME` which adds `NUMBER` of `TIME` to the day you used this 
      command's date
    - `TIME` includes:
      - `day`
      - `week`
      - `month`
      - `year`
      - `decade`

Examples:

_Below examples show `BY_DATE` as a random **_String_**_
````
Deadline homework /by asap
DeaDLinE Buy grocceries /by asap
````

_Below example show `BY_DATE` formatted as `YYYY-MM-DD`_
````
deadline assignment /by 2024-02-14
````
_Below example shows `BY_DATE` formatted as **_keyword_**_
````
deadline paint room /by tomorrow
````

### 3. Event
Format: `event TASK_NAME /from FROM_DATE /to TO_DATE`
- Adds a `task` of type `event` to the application database
- `task` is **_not_** case-sensitive
- `TASK_NAME` could be a **_String_** of multiple words
- `/from` and `/to` is **_compulsory_** or else the input is treated as **_invalid_**
- `FROM_DATE` and `TO_DATE` could be written as any **_String_** of multiple words
    - Alternatively, `FROM_DATE` and `TO_DATE` can be formatted as `YYYY-MM-DD` to enable `LocalDate`
      based functions such as `date`
        - `YYYY` represents the year _(example : `2023` )_
        - `MM` represents the month _(example : `02` )_
        - `DD` represents the day _(example : `20` )_
        - Example : `2023-02-20` represents **20 February 2023**
    - `FROM_DATE` and `TO_DATE` can also be certain **_keywords_**:
        - `tomorrow` which sets `BY_DATE` to the next day of the day you used this 
          command
        - `next TIME` which adds `TIME` to the day you used this command's date
        - `NUMBER TIME` which adds `NUMBER` of `TIME` to the day you used this
          command's date
        - `TIME` includes:
            - `day`
            - `week`
            - `month`
            - `year`
            - `decade`

Examples:

_Below example show `FROM_DATE` and `TO_DATE` as a random **_String_**_
````
Event meeting friends /from just now /to now
````

_Below example show `FROM_DATE` and `TO_DATE` formatted as `YYYY-MM-DD`_
````
eVeNT assignment /from 2023-02-14 /to 2
````
_Below examples shows `FROM_DATE` and `TO_DATE` formatted as **_keyword_**_
````
event paint room /from tomorrow /to next week
````
_Mixing of format for `FROM_DATE` and `TO_DATE` is possible_
````
event dance party /from now /to tomorrow
event play /from 2023-12-12 /to next decade
````

<br />

> **WARNING :** Leaving fields blank is accepted but removes `find` and `date` functionality
> *_Applies for **ALL** `todo`, `deadline`, `event`_

> **WARNING :** Leading and trailing whitespaces are **discarded**

<br />

### Listing all `tasks`

_Shows a list of all `tasks` recorded in the application_

Format: `list` 

<br />

### Marking or un-marking a `task` as done
_Marks a specific task as done_

Format: `mark NUMBER_ID` or `unmark NUMBER_ID`
- `NUMBER_ID` represents the order of the `task` in the list
  - `NUMBER_ID` can be displayed using `list`
    - Example output for `list`
    
    ````
        1.  [T][ ] this
        2.  [D][ ] that (by: Mar 2 2023)
    ````
    
    - `1` would then be the `NUMBER_ID` for `this`
- Only an `Integer` representing `NUMBER_ID` that already is in the list is accepted
- A `task` that is already `marked`/`unmarked` would cause an error message to be printed
- A `marked` `task` would display `[X]` when the `task` is printed
- A `unmarked` `task` would display `[X]` when the `task` is printed

Example:

_Below shows an example of the procedure of `marking` a `task`_
1. `list` is inputted into the **_command line_**
    ````
       1. [T][ ] this
    ````
2. A `task`, `this`, exists in the list with `NUMBER_ID` `1`
3. `mark 1` is then inputted into the **_command line_**
4. If `list` is now called, `this` would have its status updated
   ````
       1. [T][X] this
   ````

<br />

### Deleting a `task`
_Deletes a specific `task` stored in the application_

Format: `delete NUMBER_ID`

- `NUMBER_ID` represents the order of the `task` in the list
    - `NUMBER_ID` can be displayed using `list`
        - Example output for `list`
        
        ````
            1.  [T][ ] this
            2.  [D][ ] that (by: Mar 2 2023)
        ````
        
  - `1` would then be the `NUMBER_ID` for `this`
- Only an `Integer` representing `NUMBER_ID` that already is in the list is accepted
- After `delete` is successful, the `NUMBER_ID` of all the other `tasks` in the list would 
  be updated depending on the order of the `tasks` within the list

Example:

_Below shows an example of the procedure of `deleting` a `task`_
1. `list` is inputted into the **_command line_**
    ````
        1. [T][ ] this
        2. [T][X] that
        3. [E][ ] these (by: then)
    ````
2. A `task`, `that`, exists in the list with `NUMBER_ID` `2`
3. `delete 2` is then inputted into the **_command line_**
4. If `list` is now called, `that` would be gone from the list and `these` would have its
   `NUMBER_ID` updated from `3` to `2` as it is now the second `task` int the list
   ````
        1. [T][ ] this
        2. [E][ ] these (by: then)
   ````

<br />

### Finding a `task` using its name
_Searches the database of the application for a `task` which contains the entered input_

Format: `find KEYWORD`

- `KEYWORD` is case-sensitive
- `KEYWORD` can be a single `String` or multiple `Strings`
- Only the name of the `task` is searched
- Only exact matches would be found
- `Tasks` with a name which contains `KEYWORD` would be found
- The `NUMBER_ID` of the found `tasks` would correspond to the order of the `tasks` 
  entered and not the numbering in the returned list.

Example:

_Below shows an example of the procedure of using `find`_
1. `list` is inputted into the **_command line_**
    ````
        1. [T][ ] this thing
        2. [D][ ] these (by: then)
        3. [E][X] this thingy (from: now to: after)
    ````
2. `find this` is inputted into the **_command line_**
3. This would be shown
   ````
        1. [T][ ] this thing
        3. [E][ ] this thingy (from: now to: after)
   ````

<br />

### Finding a `task` using its date
_Searches the database of the application for a `task` which has the attribute `LocalDate`_

Format: `date DATE`

- Only the date of the `task` is searched
- Only exact matches would be found
- `Tasks` with a date of `DATE` would be found
- The `NUMBER_ID` of the found `tasks` would correspond to the order of the `tasks`
  entered and not the numbering in the returned list.
- `DATE` **_must_** be formatted as `YYYY-MM-DD` or certain **_keywords_**
  - `YYYY` represents the year _(example : `2023` )_
  - `MM` represents the month _(example : `02` )_
  - `DD` represents the day _(example : `20` )_
  - Example : `2023-02-20` represents **20 February 2023**
  - **_Keywords_** include:    
    - `tomorrow` which sets `DATE` to the next day of the day you used this 
      command's date
    - `next TIME` which adds `TIME` to the day you used this command's date
      - `TIME` includes:
        - `day`
        - `week`
        - `month`
        - `year`
        - `decade`
  - **_Keywords_** are **_not_** case-sensitive

Example:

_Below shows an example of the procedure of using `find`_
1. `list` is inputted into the **_command line_**
    ````
        1. [T][ ] this thing 
        2. [D][ ] these (by: Feb 22 2023)
        3. [E][X] this thingy (from: Feb 20 2023 to: Feb 22 2023)
    ````
2. `date 2023-02-22` is inputted into the **_command line_**
3. This would be shown
   ````
        2. [D][ ] these (by: Feb 22 2023)
        3. [E][X] this thingy (from: Feb 20 2023 to: Feb 22 2023)
   ````

<br />

### Clearing all entries
_Deletes all records stored within the application_

Format: `clear`

> **WARNING** : Doing this clears **_all_** the records stored in the datafile as well

<br />

### Exiting the application
_Says bye to Bob_

Format: `bye`

<br />

### Saving the data
_Data is saved automatically in a file at the path `[JAR FILE LOCATION]\\data\\tasks.txt`_
>**NOTE** : File and folder are generated automatically if the file and folder are not found

<br />

### Editing the datafile
_Data is stored in a file at the path `[JAR FILE LOCATION]\\data\\tasks.txt`_

Advanced users are welcome to directly edit the datafile

Format for `ToDo`: `T %   % TASK_NAME` or `T % X % TASK_NAME`

Format for `Deadline`: `T %   % TASK_NAME % BY_DATE` or `T % X % TASK_NAME % BY_DATE`

Format for `Event`: `T %   % TASK_NAME % FROM_DATE % TO_DATE` or 
                    `T % X % TASK_NAME % FROM_DATE % TO_DATE`

> **NOTE** : 
> - `BY_DATE`, `FROM_DATE`, `TO_DATE` should be formatted as `YYYY-MM-DD`
>   - `YYYY` represents the year _(example : `2023` )_
>   - `MM` represents the month _(example : `02` )_
>   - `DD` represents the day _(example : `20` )_
>   - Example : `2023-02-20` represents **20 February 2023**
> - `TASK_NAME` can be a single or multiple `Strings`
> - `X` represents `marked` while ` ` represents `unmarked`

> **! WARNING !** : If your changes to the datafile makes its format invalid, all previous data
>  would be discarded and the datafile would be emptied.

<br />

___

## FAQ

> Q: How do I transfer my data to another Computer?
> 
> A: Run the application once in the new Computer and overwrite 
> `[JAR FILE LOCATION]\\data\\tasks.txt` with the same file from your old Computer.

<br />

___

## Command Summary

| Command      | Format                                        |
|--------------|-----------------------------------------------|
| **todo**     | `todo TASK_NAME`                              |
| **deadline** | `deadline TASK_NAME /by BY_DATE`              |
| **event**    | `event TASK_NAME /from FROM_DATE /to TO_DATE` |
| **list**     | `list`                                        |
| **mark**     | `mark NUMBER_ID`                              |
| **unmark**   | `unmark NUMBER_ID`                            |
| **delete**   | `delete NUMBER_ID`                            |
| **find**     | `find KEYWORD`                                |
| **date**     | `date DATE`                                   |
| **clear**    | `clear`                                       |
| **exit**     | `bye`                                         |

<br />

___


                                                                 .###%%%%#%%###%#/                                      
                                                               %%&&&&&@&&&@&&%%%#%##%,                                  
                                                             %&@@@@&&%%%&&&&&@&&&&%%%%%(.                               
                                                            &&&&%#/,,...,,*(#%%&&@@@@@&%#,                              
                                                           %&%%%(*...........,,***(%&@@@%#.                             
                                                          %%%###*,.................,*/#@&%#.                            
                                                         %%%#/*,..................,,,,*%&&(.                            
                                                       .,%%&*....,,,*/##(***,,,....,,,,/%%(                             
                                                      (**#((...,,*((#(#((,(/////((/**,*(&#*                             
                                                      **(#**.......*(/(##/*,*(/((/*//(*(%/                              
                                                     .,/./.,.......,**,,,,..,(/(//(%#//%(                               
                                                      ,..,.......,,,**/.... .,,,.,,,,,#%                                
                                                       .,,,...,,**/*,,,/((((%//*,,.,,/(                                 
                                                         ..,,,,,,,*(*,,.,*****,*/***//                                  
                                                        ...,,,,,,,,,.***.*/%#//*//(/,                                   
                                                       ....,,*,,,,,.,**/*,,,,***//*                                     
                                                      ....,,****,,,,,,*////*,*///                                       
                                                    .......,,/(////******,,,*//                                         
                                                *((# ......,,*/((#((####(##(...                                         
                                        .(#######%%#. .....,,**//((######(,,.                                           
                                   ###(###########%%%......,,***//(((((((%#(                                            
                                ####################%%%.....,,,**///(((((#&                                             
                               %######################%%%.. ....,******/**(                                             
                              #%%&%%%%%######(((((######%%#    ......,,,..,                                             
                              %%%&%%%%%%####((((((((((###%%%#     .........#                                            
                              %%&&%%%%%%%#######(((((((####%%%%.        ...%                                            
                              %%&&&%%%%%%%###########((((#(###%%(###/    .%#                                            
                              &%%&&&%%%%%%%#%###########((#(####,,.%%&&&&%%(                                            
                              &&%&&&&&&&%%%%####%%############(.,,,#%%%%%%%                                             
                              &&&&&&&&&&&%%%%#%%%%%%#########/.,**####%%%##                                             
                              &&&&&&&&&&&&&%%%%%%%%%%%######..,**/#%###%%%#.                                            
                              &&&&&&&&&&&&&%%%%%%%#%%%%%,/*.......      ...*                                            
                             ,&&&&&@&&&&&&&&&%%%%%%#%%%/(**..............   .                                           
                             #&&&&&@@@&&&&&&&&%%%%%%#%%//(,..................                                           
                             #&@@@&@@@&&&&&&&&&%%&%%%%%%(/.,,,,....,,,,,,*,...                                          
                             %&@@@&@@@&&&&&&&&&&&&%%%%%(........,,,,,,,,,*,,,.                                          
                             &&&@@@@@@@@&&&&&&&&&&&#..........,,,,,,,**,,,,...                                          
                             &&&&@@@@@@@@@&&&&#.............,,,,,*,,,,*******,                                          
                             &@@@@@@@@@@@/..............,,,,,,**********///                                             
                             @&#/,,...............,..,..,,,,,******//,                                                  
                             ,.........,,,,,,,,,,*********//(#,                                                         
                             ,,,,,,,,,,*,*****/////((((%.                                                               
                             ******//////(((((((((#                                                                     
                            .((((((((###(((((##                                                                         
                            *##############.                                                                            
                            *#%%#%%####.                                                                                
                            /%%%#%(                                                                                     
