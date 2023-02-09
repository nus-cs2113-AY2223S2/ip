@ECHO OFF
REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

FOR /L %%A IN (0,1,10) DO CALL COUNTER %%A%%

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT

FOR /L %%A IN (10,1,20) DO CALL COUNTER %%A%%

REM compile the code into the bin folder
javac  -cp "..\src\main\java;..\lib\gson-2.8.2.jar" -Xlint:none -d ..\bin ..\src\main\java\duke\main\*.java 

FOR /L %%A IN (20,1,30) DO CALL COUNTER %%A%%

IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

FOR /L %%A IN (30,1,70) DO CALL COUNTER %%A%%

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath "..\bin;..\lib\gson-2.8.2.jar" duke.main.Duke < input.txt > ACTUAL.TXT
FOR /L %%A IN (70,1,80) DO CALL COUNTER %%A%%

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT > nul
IF ERRORLEVEL 1 (
    ECHO TESTCASE FAILED
) ELSE (
    ECHO TESTCASE PASSED
)

FOR /L %%A IN (80,1,100) DO CALL COUNTER %%A%%

timeout /t 3 > NUL