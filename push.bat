@echo off
setlocal

rem Obtener la fecha y hora actuales
for /f "tokens=1-3 delims= " %%a in ('date /t') do (
    set currentDate=%%a %%b %%c
)
for /f "tokens=1 delims= " %%d in ('time /t') do set currentTime=%%d

rem Combinar la fecha y la hora
set formattedDate=%currentDate% %currentTime%

rem Comandos de git
git add .
git status
git commit -m "%formattedDate%"
git push

