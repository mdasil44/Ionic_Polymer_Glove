@echo off

::Uninstall versions prior to 1.3x

if exist "c:\Program Files (x86)\Xvid\unins000.exe" start "Uninstalling" /wait "c:\Program Files (x86)\Xvid\unins000.exe" /VERYSILENT

::Uninstall 1.3.x versions

if exist "c:\Program Files (x86)\Xvid\uninstall.exe" start "Uninstalling" /wait "c:\Program Files (x86)\Xvid\uninstall.exe" --unattendedmodeui none --mode unattended

::Install Xvid-1.3.2

start "Installing" /wait Xvid-1.3.2-20110601.exe --mode unattended --unattendedmodeui none --AutoUpdater no --decode_divx DIVX --decode_3ivx 3IVX --decode_other MPEG4