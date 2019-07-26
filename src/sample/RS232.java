package sample;

import jssc.SerialPort;
import jssc.SerialPortException;

/*****************************************************************
 * При работе с библиотекой jssc помимо включения исходного кода *
 * нужно в src создать директорию lib и положить туда jssc.jar   *
 *****************************************************************/

public class RS232 {
    private SerialPort serialPort;
    private boolean flagOpenCOMPort = false;

    public boolean isOpened(){
        return serialPort.isOpened();
    }

    public boolean openComPort(int numberOfCOMPort){
        if (flagOpenCOMPort == false) {
            try {
                serialPort = new SerialPort("COM" + numberOfCOMPort);
                serialPort.openPort();
                serialPort.setParams(   SerialPort.BAUDRATE_510416,
                                        SerialPort.DATABITS_8,
                                        SerialPort.STOPBITS_1,
                                        SerialPort.PARITY_NONE);
                flagOpenCOMPort = true;
            } catch (SerialPortException e) {
                e.printStackTrace();
                flagOpenCOMPort = false;
                return false;
            }
        }
        return true;
    }

    public boolean closeCOMPort(){
        try{
            serialPort.closePort();
            flagOpenCOMPort = false;
        } catch (SerialPortException e) {
            e.printStackTrace();
            flagOpenCOMPort = true;
            return false;
        }
        return true;
    }

    public boolean sendCommand(int[] command){
        try {
            serialPort.writeIntArray(command);
        } catch (SerialPortException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
