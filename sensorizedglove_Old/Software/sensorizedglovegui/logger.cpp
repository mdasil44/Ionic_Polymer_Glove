#include "logger.h"
#include <sstream>
#include <QTime>

Logger::Logger(QWidget *parent) :
    QTextBrowser(parent)
{
}

void Logger::message(QString msg)
{
    QString text = buildLineWithTimestamp("#0000B0", false, msg);
    append(text);
}

void Logger::alert(QString msg)
{
    QString text = buildLineWithTimestamp("#809800", true, msg);
    append(text);
}

void Logger::error(QString msg)
{
    QString text = buildLineWithTimestamp("#F10000", true, msg);
    append(text);
}

QString Logger::buildLineWithTimestamp(QString color, bool bold, QString msg)
{
    std::stringstream text;
    text << "<span style=\"color: " << color.toStdString() << "; font-weight: bold;\">";
    //text << "[" << QTime::currentTime().toString("HH:mm:ss.zzz").toStdString() << "] ";
    text << "</span><span style=" << (bold ? "\"font-weight: bold;\"" : "") << ">";
    text << msg.toStdString();
    text << "</span>";

    return QString(text.str().c_str());
}

