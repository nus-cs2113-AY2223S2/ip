@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT

REM compile the code into the bin folder
javac -cp ..\src\main\java;C:\Users\USER\Desktop\NUS\Year_2_Sem_2\CS2113\Individual_Project\lib\gson-2.10.1.jar -Xlint:none -d ..\bin ..\src\main\java\luke\*.java ..\src\main\java\luke\command\*.java ..\src\main\java\luke\exception\*.java ..\src\main\java\luke\icon\*.java ..\src\main\java\luke\task\*.java

IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ..\bin;C:\Users\USER\Desktop\NUS\Year_2_Sem_2\CS2113\Individual_Project\lib\gson-2.10.1.jar luke.Luke < input.txt > ACTUAL.TXT


REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT
