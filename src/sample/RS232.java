package sample;

import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

public class RS232 {
    private SerialPort serialPort;
    private SerialPort serialPort1;
    private boolean flagOpenCOMPort = false;

    public boolean openComPort(int numberOfCOMPort) throws SerialPortException {
        serialPort = new SerialPort("COM" + numberOfCOMPort);
        serialPort1 = new SerialPort("COM" + 3);
        String [] comPortOnSystem = SerialPortList.getPortNames();
        if (flagOpenCOMPort == false) {


            for (String str: comPortOnSystem) {
                System.out.println(str);
            }

            /*try {
                serialPort = new SerialPort("COM" + numberOfCOMPort);
                serialPort.openPort();
                serialPort.setParams(SerialPort.BAUDRATE_510416,
                        SerialPort.DATABITS_8,
                        SerialPort.STOPBITS_1,
                        SerialPort.PARITY_NONE);

                flagOpenCOMPort = true;
            } catch (SerialPortException spe){
                spe.printStackTrace();
                return false;
            }*/
        }
        return true;
    }
}
