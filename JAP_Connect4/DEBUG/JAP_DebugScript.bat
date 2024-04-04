@echo off

cd..

::===========================================================::
:: CLEARING BIN
::===========================================================::
rd /s /q DEBUG\bin
mkdir DEBUG\bin
mkdir DEBUG\bin\saves


::===========================================================::
:: COMPILING .JAVA FILES TO .CLASS FILES IN BIN (Parallel Compilation)
::===========================================================::
cd .\src
for /r %%i in (*.java) do (
    echo Compiling %%i...
    javac "%%i" -d ..\DEBUG\bin
  )
cd ..


::===========================================================::
:: UPDATING IMAGELIB IN BIN
::===========================================================::
mkdir DEBUG\bin\imageLib
xcopy /y /e imageLib DEBUG\bin\imageLib


::===========================================================::
:: UPDATING LOCALES IN BIN
::===========================================================::
xcopy /y /e localeLib DEBUG\bin


::===========================================================::
:: BLOCK - WAIT FOR PARALLEL COMPILATION TO COMPLETE
::===========================================================::
rem Function to wait for processes to finish
:WAIT
timeout /t 1 /nobreak >nul


::===========================================================::
:: COMPILE INTO RUNNABLE JAR
::===========================================================::
cd DEBUG\bin
jar cfe Connect4.jar CST8221.Menu .
cd ..


::===========================================================::
:: RUNNING JAR FILE
::===========================================================::
start runJar.bat
start runJar.bat