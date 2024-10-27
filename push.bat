@echo off
setlocal

rem Obtener la fecha y hora actual
for /f "tokens=2 delims= " %%a in ('date /t') do set currentDate=%%a
for /f "tokens=1 delims= " %%b in ('time /t') do set currentTime=%%b

rem Formatear la fecha y hora
set formattedDate=%currentDate% %currentTime%

rem Comandos de git
git add .
git status
git commit -m "%formattedDate%"
git push
