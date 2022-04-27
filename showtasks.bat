call runcrud.bat
if "%ERRORLEVEL%" == "0" goto browser
echo.
echo Cannot run script runcrud.bat - breaking work.
goto fail

:browser
start firefox https://www.google.com
if "%ERRORLEVEL%" == "0" goto url
echo.
echo Cannot run browser.
goto fail

:url
start firefox http://localhost:8080/crud/v1/task/getTasks
if "%ERRORLEVEL%" == "0" goto end
echo.
echo Cannot open url.
goto fail

:fail
echo.
echo There were errors.

:end
echo.
echo Work is finished.