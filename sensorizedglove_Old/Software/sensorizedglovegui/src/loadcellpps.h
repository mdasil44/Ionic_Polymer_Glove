#ifndef LOADCELLPPS_H
#define LOADCELLPPS_H

#include <QObject>
#include "PPSDaq.h"
#include <Qstring>
#include "logger.h"




class LoadCellPPS : public QObject
{
public:
    LoadCellPPS();
    ~LoadCellPPS();
    int initialize(Logger *log);
    int start();
    int stop();
    void biasLoadCell();

signals:
    int acquireData(double &cellForce);

    //variables
    string_t ConfigFileDef;
    int const LogLevel;// 0 to disable
    int const BufferSize;
    bool cellInitialized;

    Logger *log;
};

#endif // LOADCELLPPS_H
