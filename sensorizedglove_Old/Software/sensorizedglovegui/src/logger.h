#ifndef LOGGER_H
#define LOGGER_H


#include <QTextBrowser>

class Logger : public QTextBrowser
{
    Q_OBJECT
public:
    explicit Logger(QWidget *parent = 0);

    void message(QString msg);
    void alert(QString msg);
    void error(QString msg);
signals:

public slots:

private:
    QString buildLineWithTimestamp(QString color, bool bold, QString msg);
};

#endif // LOGGER_H
