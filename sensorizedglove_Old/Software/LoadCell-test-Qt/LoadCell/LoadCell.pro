QT += core
QT -= gui

CONFIG += c++11

TARGET = LoadCell
CONFIG += console
CONFIG -= app_bundle

TEMPLATE = app

INCLUDEPATH += "F:\sensorized-glove\Code\PPS Load Cell\PPS API\inc"

LIBS += -L"F:\sensorized-glove\Code\PPS Load Cell\PPS API\lib\x64" -lPPSDaqAPI


SOURCES += main.cpp
