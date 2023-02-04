REM Credits: Code adapted from: https://gist.github.com/Archigos/0e34219b4a8b82358bb0

@ECHO OFF

:ProgressMeter
SETLOCAL ENABLEDELAYEDEXPANSION
SET ProgressCnt=%1
SET /A ProgressPercent=ProgressCnt/10
SET /A NumBars=%ProgressPercent%
SET /A NumSpaces=10-%NumBars%

SET Meter=

FOR /L %%A IN (%NumBars%,-1,1) DO SET Meter=!Meter!I
FOR /L %%A IN (%NumSpaces%,-1,1) DO SET Meter=!Meter!.
TITLE Progress:  [%Meter%]  %ProgressCnt%%%
ENDLOCAL
GOTO :EOF