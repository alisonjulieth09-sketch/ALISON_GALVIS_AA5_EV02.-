@echo off
title AYB Auth API - mvn spring-boot:run
cd /d "%~dp0"

rem ---- Java ----
if not defined JAVA_HOME (
  for /d %%D in ("C:\Program Files\Eclipse Adoptium\jdk-*" "C:\Program Files\Java\jdk-*" "C:\Program Files\Microsoft\jdk-*") do set "JAVA_HOME=%%~fD"
)
if defined JAVA_HOME set "PATH=%JAVA_HOME%\bin;%PATH%"
echo JAVA_HOME=%JAVA_HOME%

rem ---- Maven: busca uno instalado; si no hay, lo descarga en .maven ----
where mvn >nul 2>&1 && goto RUN
for /d %%M in ("%USERPROFILE%\Downloads\apache-maven-*" "%USERPROFILE%\Descargas\apache-maven-*" "C:\Program Files\apache-maven-*" "C:\apache-maven-*" "C:\maven*") do (
  if exist "%%~fM\bin\mvn.cmd" set "PATH=%%~fM\bin;%PATH%"
  for /d %%N in ("%%~fM\apache-maven-*") do if exist "%%~fN\bin\mvn.cmd" set "PATH=%%~fN\bin;%PATH%"
)
where mvn >nul 2>&1 && goto RUN

set "MVN_DIR=%~dp0.maven\apache-maven-3.9.9"
if not exist "%MVN_DIR%\bin\mvn.cmd" (
  echo.
  echo Maven no esta instalado. Descargando Maven 3.9.9 ^(solo la primera vez^)...
  powershell -NoProfile -ExecutionPolicy Bypass -Command "$ProgressPreference='SilentlyContinue'; [Net.ServicePointManager]::SecurityProtocol='Tls12'; New-Item -ItemType Directory -Force '.maven' | Out-Null; Invoke-WebRequest 'https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/3.9.9/apache-maven-3.9.9-bin.zip' -OutFile '.maven\maven.zip'; Expand-Archive '.maven\maven.zip' '.maven' -Force; Remove-Item '.maven\maven.zip'"
)
if not exist "%MVN_DIR%\bin\mvn.cmd" (
  echo.
  echo No se pudo descargar Maven. Revisa la conexion a internet ^(o si la red de la empresa lo bloquea^).
  pause
  exit /b 1
)
set "PATH=%MVN_DIR%\bin;%PATH%"

:RUN
echo.
echo ^> mvn spring-boot:run
call mvn spring-boot:run
pause
