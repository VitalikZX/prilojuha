package sample;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import jssc.SerialPortList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*****************************************************************
 * При работе с библиотекой jssc помимо включения исходного кода *
 * нужно в src создать директорию lib и положить туда jssc.jar   *
 *****************************************************************/

public class Controller {
    private final int [] GIVE_ME_DATA = {0x1e, 0xa1};
    private String[] initArray = {"04", "10", "00", "18", "00", "00", "00", "00", "00", "00", "00", "00", "00", "00", "00", "00", "00", "00", "00", "00", "00", "00", "00", "00", "00", "00", "00", "00", "00", "00", "00", "00", "00", "00", "00", "00", "00", "00", "00", "00", "00", "00", "00", "00", "00", "00", "00", "00", "A9", "E7", "00", "00", "00", "00"};
    private TableData tableData = new TableData(initArray);
    private ObservableList<DataForTable> data = FXCollections.observableArrayList();

    private boolean isCOMPortOpen = false;

    RS232 rs232 = new RS232();

    @FXML
    private Button bttnStart;

    @FXML
    private Button bttnStop;

    @FXML
    private Label labelInfo;

    @FXML
    private ToggleButton bttnOpenPort;

    @FXML
    private ChoiceBox portsChoiceBox;

    @FXML
    private TableView dataTable;

    @FXML
    private TableColumn<DataForTable, String> name;
    @FXML
    private TableColumn<DataForTable, String> hex;
    @FXML
    private TableColumn<DataForTable, String> dec;

    @FXML
    private void initialize() {//запускается только при старте
        ObservableList<String> ports = FXCollections.observableArrayList(SerialPortList.getPortNames());
        portsChoiceBox.setItems(ports);

        initData();
        name.setCellValueFactory(cellData -> cellData.getValue().getName());
        hex.setCellValueFactory(cellData -> cellData.getValue().getHex());
        dec.setCellValueFactory(cellData -> cellData.getValue().getDec());

        dataTable.getItems().addListener(listener);

        dataTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> initData());
        // заполняем таблицу данными
        dataTable.setItems(data);
    }

    ListChangeListener listener = new ListChangeListener() {

        @Override
        public void onChanged(Change c) {
            System.out.println("консоль");
        }
    };

    @FXML
    private void openOrCloseCOMPort() {
        int numberOfCOMPort;

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Ошибка");
        alert.setHeaderText(null);
        alert.setContentText("COM порт не открыт... причины не ясны...");

        if (isCOMPortOpen == false) {
            try {
                numberOfCOMPort = Integer.parseInt(((String) portsChoiceBox.getValue()).substring(3));

                if (rs232.openComPort(numberOfCOMPort)) {
                    isCOMPortOpen = true;
                    bttnStart.setDisable(false);
                    portsChoiceBox.setDisable(true);
                    bttnOpenPort.setText("Закрыть порт");
                } else {
                    bttnOpenPort.setSelected(false);
                    isCOMPortOpen = false;
                    alert.showAndWait();
                }
            } catch (NullPointerException | NumberFormatException e) {
                alert.showAndWait();
                isCOMPortOpen = false;
            }
        } else {
            bttnStart.setDisable(true);
            bttnStop.setDisable(true);
            if (rs232.closeCOMPort()) {
                portsChoiceBox.setDisable(false);
                bttnOpenPort.setText("Открыть порт");
                bttnOpenPort.setSelected(false);
                isCOMPortOpen = false;
            } else {
                bttnOpenPort.setSelected(true);
                isCOMPortOpen = true;
            }
        }
    }

    @FXML
    private void startExchange(){
        bttnStart.setDisable(true);
        bttnOpenPort.setDisable(true);
        bttnStop.setDisable(false);
        rs232.sendCommand(GIVE_ME_DATA);
    }

    @FXML
    private void stopExchange(){
        bttnStart.setDisable(false);
        bttnOpenPort.setDisable(false);
        bttnStop.setDisable(true);
    }

    private List<String> getCOMPortsList(){
        String [] comPortOnSystem = SerialPortList.getPortNames();
        List<String> listOfCOMPortInSystem = Arrays.asList(comPortOnSystem);

        for (String str: comPortOnSystem) {
            System.out.println(str);
        }
        return listOfCOMPortInSystem;
    }

    private void initData() {
        ArrayList<String> name = tableData.getNamesOfChannels();
        String[] hex = tableData.getStringDataModel();
        int[] dec = tableData.getIntDataModel();
        for(int i=0; i<name.size(); i++){
            data.add(new DataForTable(name.get(i), hex[i], Integer.toString(dec[i])));
        }
    }
}
