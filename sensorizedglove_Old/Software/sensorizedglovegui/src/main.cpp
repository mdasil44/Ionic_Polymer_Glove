#include "sensorizedglove.h"
#include <QApplication>

int main(int argc, char *argv[])
{
    QApplication a(argc, argv);
    SensorizedGlove w;    
    w.setWindowTitle("Sensorized Glove");
    w.show();

    return a.exec();
}
