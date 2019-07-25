package sample;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import jssc.SerialPort;
import jssc.SerialPortList;


public class Controller {
    RS232 rs232 = new RS232();
    public final int DEFAULT_COM_PORT_NUMBER = 1;

    @FXML
    private Button bttnStart;

    @FXML
    private Button bttnStop;

    @FXML
    private Label labelInfo;

    @FXML
    private ToggleButton bttnOpenPort;

    @FXML
    private TextField textFieldCOMPortNumber;

    @FXML
    private void openComPort() {

        SerialPort serialPort;
        SerialPort serialPort1;


        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Ошибка");
        alert.setHeaderText(null);
        alert.setContentText("COM порт не открыт... причины не ясные...");

        serialPort = new SerialPort("COM" + 1);
        serialPort1 = new SerialPort("COM" + 3);
        String [] comPortOnSystem = SerialPortList.getPortNames();

        if (comPortOnSystem.length == 0) {
            System.out.println("com is not found");
        } else {
            for (String str : comPortOnSystem) {
                System.out.println(str);
            }
        }

        /*int numberOfCOMPort = DEFAULT_COM_PORT_NUMBER;

        try {
            numberOfCOMPort = Integer.parseInt(textFieldCOMPortNumber.getText());
        } catch (NullPointerException | NumberFormatException e) {
            textFieldCOMPortNumber.setText(String.valueOf(DEFAULT_COM_PORT_NUMBER));
        }
        try {
            if (rs232.openComPort(numberOfCOMPort)) {
                bttnStart.setDisable(false);
            } else {

                alert.showAndWait();
            }
        } catch (RuntimeException|SerialPortException e) {
            alert.showAndWait();
        }*/

    }
}
