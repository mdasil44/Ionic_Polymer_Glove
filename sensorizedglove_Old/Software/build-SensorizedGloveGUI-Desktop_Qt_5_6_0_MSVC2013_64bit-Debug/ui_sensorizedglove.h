/********************************************************************************
** Form generated from reading UI file 'sensorizedglove.ui'
**
** Created by: Qt User Interface Compiler version 5.6.0
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_SENSORIZEDGLOVE_H
#define UI_SENSORIZEDGLOVE_H

#include <QtCore/QVariant>
#include <QtWidgets/QAction>
#include <QtWidgets/QApplication>
#include <QtWidgets/QButtonGroup>
#include <QtWidgets/QFormLayout>
#include <QtWidgets/QGraphicsView>
#include <QtWidgets/QGroupBox>
#include <QtWidgets/QHBoxLayout>
#include <QtWidgets/QHeaderView>
#include <QtWidgets/QLabel>
#include <QtWidgets/QLineEdit>
#include <QtWidgets/QMainWindow>
#include <QtWidgets/QMenu>
#include <QtWidgets/QMenuBar>
#include <QtWidgets/QProgressBar>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QSpacerItem>
#include <QtWidgets/QSpinBox>
#include <QtWidgets/QStatusBar>
#include <QtWidgets/QToolBar>
#include <QtWidgets/QVBoxLayout>
#include <QtWidgets/QWidget>
#include "logger.h"

QT_BEGIN_NAMESPACE

class Ui_SensorizedGlove
{
public:
    QAction *actionCalibrate;
    QAction *actionAssess_Calibration;
    QAction *actionBias_FSRs;
    QWidget *centralWidget;
    QVBoxLayout *verticalLayout_5;
    QHBoxLayout *horizontalLayout_3;
    QGraphicsView *graphicsView;
    QGroupBox *groupBox;
    QVBoxLayout *verticalLayout_6;
    QLabel *maxForceLabel;
    QLabel *label_7;
    QLabel *label_9;
    QVBoxLayout *verticalLayout_4;
    QVBoxLayout *verticalLayout;
    QGroupBox *ControlsGB;
    QHBoxLayout *horizontalLayout_4;
    QFormLayout *formLayout;
    QLabel *label;
    QLineEdit *trialIDText;
    QPushButton *StartRecordingB;
    QPushButton *StopRecordingB;
    QSpacerItem *horizontalSpacer_3;
    QGroupBox *statusGB;
    QFormLayout *formLayout_2;
    QLabel *label_2;
    QLabel *l_arduino;
    QLabel *label_4;
    QLabel *l_accelerometer;
    QGroupBox *calibrationGB;
    QVBoxLayout *verticalLayout_2;
    QLabel *label_5;
    QGroupBox *loadGB;
    QVBoxLayout *verticalLayout_3;
    QHBoxLayout *horizontalLayout_2;
    QLabel *currentLoadLabel;
    QSpacerItem *horizontalSpacer_2;
    QPushButton *loadCellBiasButton;
    QProgressBar *loadCellProgressBar;
    QHBoxLayout *horizontalLayout;
    QLabel *label_8;
    QSpacerItem *horizontalSpacer;
    QSpinBox *maxCalibForceSpinBox;
    QLabel *FSRConductanceLabel;
    QHBoxLayout *horizontalLayout_5;
    QPushButton *acceptCalibButton;
    QPushButton *skipCalibButton;
    QLabel *calibrationResultLabel;
    QLabel *label_3;
    Logger *logger;
    QMenuBar *menuBar;
    QMenu *CalibrateSensorsMenu;
    QToolBar *mainToolBar;
    QStatusBar *statusBar;

    void setupUi(QMainWindow *SensorizedGlove)
    {
        if (SensorizedGlove->objectName().isEmpty())
            SensorizedGlove->setObjectName(QStringLiteral("SensorizedGlove"));
        SensorizedGlove->resize(1261, 973);
        actionCalibrate = new QAction(SensorizedGlove);
        actionCalibrate->setObjectName(QStringLiteral("actionCalibrate"));
        actionCalibrate->setCheckable(true);
        actionAssess_Calibration = new QAction(SensorizedGlove);
        actionAssess_Calibration->setObjectName(QStringLiteral("actionAssess_Calibration"));
        actionAssess_Calibration->setCheckable(true);
        actionBias_FSRs = new QAction(SensorizedGlove);
        actionBias_FSRs->setObjectName(QStringLiteral("actionBias_FSRs"));
        actionBias_FSRs->setCheckable(false);
        centralWidget = new QWidget(SensorizedGlove);
        centralWidget->setObjectName(QStringLiteral("centralWidget"));
        verticalLayout_5 = new QVBoxLayout(centralWidget);
        verticalLayout_5->setSpacing(6);
        verticalLayout_5->setContentsMargins(11, 11, 11, 11);
        verticalLayout_5->setObjectName(QStringLiteral("verticalLayout_5"));
        horizontalLayout_3 = new QHBoxLayout();
        horizontalLayout_3->setSpacing(6);
        horizontalLayout_3->setObjectName(QStringLiteral("horizontalLayout_3"));
        graphicsView = new QGraphicsView(centralWidget);
        graphicsView->setObjectName(QStringLiteral("graphicsView"));
        QSizePolicy sizePolicy(QSizePolicy::Minimum, QSizePolicy::Expanding);
        sizePolicy.setHorizontalStretch(0);
        sizePolicy.setVerticalStretch(0);
        sizePolicy.setHeightForWidth(graphicsView->sizePolicy().hasHeightForWidth());
        graphicsView->setSizePolicy(sizePolicy);
        graphicsView->setMinimumSize(QSize(800, 750));

        horizontalLayout_3->addWidget(graphicsView);

        groupBox = new QGroupBox(centralWidget);
        groupBox->setObjectName(QStringLiteral("groupBox"));
        verticalLayout_6 = new QVBoxLayout(groupBox);
        verticalLayout_6->setSpacing(6);
        verticalLayout_6->setContentsMargins(11, 11, 11, 11);
        verticalLayout_6->setObjectName(QStringLiteral("verticalLayout_6"));
        maxForceLabel = new QLabel(groupBox);
        maxForceLabel->setObjectName(QStringLiteral("maxForceLabel"));
        maxForceLabel->setStyleSheet(QStringLiteral("font: 26pt \"MS Shell Dlg 2\";"));
        maxForceLabel->setAlignment(Qt::AlignBottom|Qt::AlignHCenter);

        verticalLayout_6->addWidget(maxForceLabel);

        label_7 = new QLabel(groupBox);
        label_7->setObjectName(QStringLiteral("label_7"));
        label_7->setPixmap(QPixmap(QString::fromUtf8(":/images/ForceScale.png")));

        verticalLayout_6->addWidget(label_7);

        label_9 = new QLabel(groupBox);
        label_9->setObjectName(QStringLiteral("label_9"));
        label_9->setStyleSheet(QStringLiteral("font: 26pt \"MS Shell Dlg 2\";"));
        label_9->setTextFormat(Qt::AutoText);
        label_9->setAlignment(Qt::AlignHCenter|Qt::AlignTop);

        verticalLayout_6->addWidget(label_9);


        horizontalLayout_3->addWidget(groupBox);

        verticalLayout_4 = new QVBoxLayout();
        verticalLayout_4->setSpacing(6);
        verticalLayout_4->setObjectName(QStringLiteral("verticalLayout_4"));
        verticalLayout = new QVBoxLayout();
        verticalLayout->setSpacing(6);
        verticalLayout->setObjectName(QStringLiteral("verticalLayout"));
        ControlsGB = new QGroupBox(centralWidget);
        ControlsGB->setObjectName(QStringLiteral("ControlsGB"));
        horizontalLayout_4 = new QHBoxLayout(ControlsGB);
        horizontalLayout_4->setSpacing(6);
        horizontalLayout_4->setContentsMargins(11, 11, 11, 11);
        horizontalLayout_4->setObjectName(QStringLiteral("horizontalLayout_4"));
        formLayout = new QFormLayout();
        formLayout->setSpacing(6);
        formLayout->setObjectName(QStringLiteral("formLayout"));
        label = new QLabel(ControlsGB);
        label->setObjectName(QStringLiteral("label"));

        formLayout->setWidget(0, QFormLayout::LabelRole, label);

        trialIDText = new QLineEdit(ControlsGB);
        trialIDText->setObjectName(QStringLiteral("trialIDText"));

        formLayout->setWidget(0, QFormLayout::FieldRole, trialIDText);

        StartRecordingB = new QPushButton(ControlsGB);
        StartRecordingB->setObjectName(QStringLiteral("StartRecordingB"));
        StartRecordingB->setEnabled(false);

        formLayout->setWidget(1, QFormLayout::LabelRole, StartRecordingB);

        StopRecordingB = new QPushButton(ControlsGB);
        StopRecordingB->setObjectName(QStringLiteral("StopRecordingB"));
        StopRecordingB->setEnabled(false);

        formLayout->setWidget(1, QFormLayout::FieldRole, StopRecordingB);


        horizontalLayout_4->addLayout(formLayout);

        horizontalSpacer_3 = new QSpacerItem(53, 20, QSizePolicy::Expanding, QSizePolicy::Minimum);

        horizontalLayout_4->addItem(horizontalSpacer_3);


        verticalLayout->addWidget(ControlsGB);

        statusGB = new QGroupBox(centralWidget);
        statusGB->setObjectName(QStringLiteral("statusGB"));
        formLayout_2 = new QFormLayout(statusGB);
        formLayout_2->setSpacing(6);
        formLayout_2->setContentsMargins(11, 11, 11, 11);
        formLayout_2->setObjectName(QStringLiteral("formLayout_2"));
        label_2 = new QLabel(statusGB);
        label_2->setObjectName(QStringLiteral("label_2"));

        formLayout_2->setWidget(0, QFormLayout::LabelRole, label_2);

        l_arduino = new QLabel(statusGB);
        l_arduino->setObjectName(QStringLiteral("l_arduino"));

        formLayout_2->setWidget(0, QFormLayout::FieldRole, l_arduino);

        label_4 = new QLabel(statusGB);
        label_4->setObjectName(QStringLiteral("label_4"));

        formLayout_2->setWidget(1, QFormLayout::LabelRole, label_4);

        l_accelerometer = new QLabel(statusGB);
        l_accelerometer->setObjectName(QStringLiteral("l_accelerometer"));

        formLayout_2->setWidget(1, QFormLayout::FieldRole, l_accelerometer);


        verticalLayout->addWidget(statusGB);


        verticalLayout_4->addLayout(verticalLayout);

        calibrationGB = new QGroupBox(centralWidget);
        calibrationGB->setObjectName(QStringLiteral("calibrationGB"));
        calibrationGB->setEnabled(false);
        verticalLayout_2 = new QVBoxLayout(calibrationGB);
        verticalLayout_2->setSpacing(6);
        verticalLayout_2->setContentsMargins(11, 11, 11, 11);
        verticalLayout_2->setObjectName(QStringLiteral("verticalLayout_2"));
        label_5 = new QLabel(calibrationGB);
        label_5->setObjectName(QStringLiteral("label_5"));

        verticalLayout_2->addWidget(label_5);

        loadGB = new QGroupBox(calibrationGB);
        loadGB->setObjectName(QStringLiteral("loadGB"));
        verticalLayout_3 = new QVBoxLayout(loadGB);
        verticalLayout_3->setSpacing(6);
        verticalLayout_3->setContentsMargins(11, 11, 11, 11);
        verticalLayout_3->setObjectName(QStringLiteral("verticalLayout_3"));
        horizontalLayout_2 = new QHBoxLayout();
        horizontalLayout_2->setSpacing(6);
        horizontalLayout_2->setObjectName(QStringLiteral("horizontalLayout_2"));
        currentLoadLabel = new QLabel(loadGB);
        currentLoadLabel->setObjectName(QStringLiteral("currentLoadLabel"));

        horizontalLayout_2->addWidget(currentLoadLabel);

        horizontalSpacer_2 = new QSpacerItem(40, 20, QSizePolicy::Expanding, QSizePolicy::Minimum);

        horizontalLayout_2->addItem(horizontalSpacer_2);

        loadCellBiasButton = new QPushButton(loadGB);
        loadCellBiasButton->setObjectName(QStringLiteral("loadCellBiasButton"));

        horizontalLayout_2->addWidget(loadCellBiasButton);


        verticalLayout_3->addLayout(horizontalLayout_2);

        loadCellProgressBar = new QProgressBar(loadGB);
        loadCellProgressBar->setObjectName(QStringLiteral("loadCellProgressBar"));
        loadCellProgressBar->setValue(0);
        loadCellProgressBar->setTextVisible(false);

        verticalLayout_3->addWidget(loadCellProgressBar);

        horizontalLayout = new QHBoxLayout();
        horizontalLayout->setSpacing(6);
        horizontalLayout->setObjectName(QStringLiteral("horizontalLayout"));
        label_8 = new QLabel(loadGB);
        label_8->setObjectName(QStringLiteral("label_8"));

        horizontalLayout->addWidget(label_8);

        horizontalSpacer = new QSpacerItem(40, 20, QSizePolicy::Expanding, QSizePolicy::Minimum);

        horizontalLayout->addItem(horizontalSpacer);

        maxCalibForceSpinBox = new QSpinBox(loadGB);
        maxCalibForceSpinBox->setObjectName(QStringLiteral("maxCalibForceSpinBox"));
        maxCalibForceSpinBox->setMaximum(20);
        maxCalibForceSpinBox->setValue(0);

        horizontalLayout->addWidget(maxCalibForceSpinBox);


        verticalLayout_3->addLayout(horizontalLayout);


        verticalLayout_2->addWidget(loadGB);

        FSRConductanceLabel = new QLabel(calibrationGB);
        FSRConductanceLabel->setObjectName(QStringLiteral("FSRConductanceLabel"));

        verticalLayout_2->addWidget(FSRConductanceLabel);

        horizontalLayout_5 = new QHBoxLayout();
        horizontalLayout_5->setSpacing(6);
        horizontalLayout_5->setObjectName(QStringLiteral("horizontalLayout_5"));
        acceptCalibButton = new QPushButton(calibrationGB);
        acceptCalibButton->setObjectName(QStringLiteral("acceptCalibButton"));

        horizontalLayout_5->addWidget(acceptCalibButton);

        skipCalibButton = new QPushButton(calibrationGB);
        skipCalibButton->setObjectName(QStringLiteral("skipCalibButton"));

        horizontalLayout_5->addWidget(skipCalibButton);


        verticalLayout_2->addLayout(horizontalLayout_5);

        calibrationResultLabel = new QLabel(calibrationGB);
        calibrationResultLabel->setObjectName(QStringLiteral("calibrationResultLabel"));
        calibrationResultLabel->setEnabled(false);

        verticalLayout_2->addWidget(calibrationResultLabel);


        verticalLayout_4->addWidget(calibrationGB);

        label_3 = new QLabel(centralWidget);
        label_3->setObjectName(QStringLiteral("label_3"));
        label_3->setPixmap(QPixmap(QString::fromUtf8(":/images/WearMeLogoSmall.jpg")));
        label_3->setAlignment(Qt::AlignCenter);

        verticalLayout_4->addWidget(label_3);


        horizontalLayout_3->addLayout(verticalLayout_4);


        verticalLayout_5->addLayout(horizontalLayout_3);

        logger = new Logger(centralWidget);
        logger->setObjectName(QStringLiteral("logger"));
        logger->setOpenExternalLinks(false);
        logger->setOpenLinks(false);

        verticalLayout_5->addWidget(logger);

        SensorizedGlove->setCentralWidget(centralWidget);
        menuBar = new QMenuBar(SensorizedGlove);
        menuBar->setObjectName(QStringLiteral("menuBar"));
        menuBar->setGeometry(QRect(0, 0, 1261, 21));
        CalibrateSensorsMenu = new QMenu(menuBar);
        CalibrateSensorsMenu->setObjectName(QStringLiteral("CalibrateSensorsMenu"));
        SensorizedGlove->setMenuBar(menuBar);
        mainToolBar = new QToolBar(SensorizedGlove);
        mainToolBar->setObjectName(QStringLiteral("mainToolBar"));
        SensorizedGlove->addToolBar(Qt::TopToolBarArea, mainToolBar);
        statusBar = new QStatusBar(SensorizedGlove);
        statusBar->setObjectName(QStringLiteral("statusBar"));
        SensorizedGlove->setStatusBar(statusBar);

        menuBar->addAction(CalibrateSensorsMenu->menuAction());
        CalibrateSensorsMenu->addAction(actionCalibrate);
        CalibrateSensorsMenu->addAction(actionAssess_Calibration);

        retranslateUi(SensorizedGlove);

        QMetaObject::connectSlotsByName(SensorizedGlove);
    } // setupUi

    void retranslateUi(QMainWindow *SensorizedGlove)
    {
        SensorizedGlove->setWindowTitle(QApplication::translate("SensorizedGlove", "SensorizedGlove", 0));
        actionCalibrate->setText(QApplication::translate("SensorizedGlove", "Calibrate", 0));
        actionAssess_Calibration->setText(QApplication::translate("SensorizedGlove", "Assess Calibration", 0));
        actionBias_FSRs->setText(QApplication::translate("SensorizedGlove", "&Bias FSRs", 0));
        groupBox->setTitle(QString());
        maxForceLabel->setText(QApplication::translate("SensorizedGlove", "0 N", 0));
        label_7->setText(QString());
        label_9->setText(QApplication::translate("SensorizedGlove", "0 N", 0));
        ControlsGB->setTitle(QApplication::translate("SensorizedGlove", "Record Sensor Data", 0));
        label->setText(QApplication::translate("SensorizedGlove", "Trial ID:", 0));
        StartRecordingB->setText(QApplication::translate("SensorizedGlove", "Record", 0));
        StopRecordingB->setText(QApplication::translate("SensorizedGlove", "Stop", 0));
        statusGB->setTitle(QApplication::translate("SensorizedGlove", "Status", 0));
        label_2->setText(QApplication::translate("SensorizedGlove", "Arduino:", 0));
        l_arduino->setText(QApplication::translate("SensorizedGlove", "Not connected", 0));
        label_4->setText(QApplication::translate("SensorizedGlove", "Accelerometer:", 0));
        l_accelerometer->setText(QApplication::translate("SensorizedGlove", "waiting...", 0));
        calibrationGB->setTitle(QApplication::translate("SensorizedGlove", "Sensors Calibration", 0));
        label_5->setText(QApplication::translate("SensorizedGlove", "- Check which sensor is bein calibrated.\n"
"- Steadily increase calibration load until max N are reached.\n"
"- Lift your finger away from the reference sensor quickly\n"
"and do not flex your fingers after applying the load.\n"
"- Sensors should be loaded during calibration similarly\n"
"to how they will be used in your experiment.\n"
"- The calibration result is displayed below.\n"
"- Repeat this process for all sensors. ", 0));
        loadGB->setTitle(QApplication::translate("SensorizedGlove", "Applied Load (PPS load cell)", 0));
        currentLoadLabel->setText(QApplication::translate("SensorizedGlove", "Current load: 0 N", 0));
        loadCellBiasButton->setText(QApplication::translate("SensorizedGlove", "Zero", 0));
        label_8->setText(QApplication::translate("SensorizedGlove", "0 N", 0));
        FSRConductanceLabel->setText(QApplication::translate("SensorizedGlove", "Current FSR conductance: 0 S", 0));
        acceptCalibButton->setText(QApplication::translate("SensorizedGlove", "Accept calibration", 0));
        skipCalibButton->setText(QApplication::translate("SensorizedGlove", "Skip calibration", 0));
        calibrationResultLabel->setText(QString());
        label_3->setText(QString());
        CalibrateSensorsMenu->setTitle(QApplication::translate("SensorizedGlove", "Calibrate Sensors", 0));
    } // retranslateUi

};

namespace Ui {
    class SensorizedGlove: public Ui_SensorizedGlove {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_SENSORIZEDGLOVE_H
