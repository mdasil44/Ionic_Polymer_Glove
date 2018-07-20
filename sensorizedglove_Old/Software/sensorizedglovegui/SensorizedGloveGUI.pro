#-------------------------------------------------
#
# Project created by QtCreator 2015-09-01T11:50:28
#
#-------------------------------------------------

QT       += core gui serialport

greaterThan(QT_MAJOR_VERSION, 4): QT += widgets

TARGET = SensorizedGloveGUI
TEMPLATE = app

INCLUDEPATH += "F:\sensorized-glove\Code\PPS Load Cell\PPS API\inc"

LIBS += -L"F:\sensorized-glove\Code\PPS Load Cell\PPS API\lib\x64" -lPPSDaqAPI


SOURCES += src/main.cpp\
        src/sensorizedglove.cpp \
    src/loadcellpps.cpp \
    src/logger.cpp

HEADERS  += src/sensorizedglove.h \
    src/loadcellpps.h \
    src/logger.h \
    src/PPSDaq.h

FORMS    += src/sensorizedglove.ui

RESOURCES += \
    resources/Resources.qrc

