@echo off
set /p testpath="Enter Path: "
cd %testpath%
@echo .
allure generate ./ --clean