package sample;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Vital on 19.11.2017.
 */
public class TableData {
    private int size;
    private ArrayList<String> namesOfChannels = new ArrayList<>();
    private DataModel dataModel;

    TableData(String[] data){
        String [] names;
        if ((data[0]+data[1]).equals("0400")) {
            names = Arrays.toString(NamesIdentificationChannel.values()).replaceAll("]", "").replaceAll("\\[", "").split(", ");
        } else if ((data[0]+data[1]).equals("0410")){
            names = Arrays.toString(NamesDataChannel.values()).replaceAll("]", "").replaceAll("\\[", "").split(", ");
        } else {
            String arr = "start, message";
            names = arr.split(", ");
        }

        for (String str : names){
            namesOfChannels.add(str);
        }

        dataModel = new DataModel(data);
    }

    public ArrayList<String> getNamesOfChannels() {
        return namesOfChannels;
    }

    public int[] getIntDataModel() {
        return dataModel.getIntData();
    }

    public void setDataModel(String[] arrayOfData){
        try {
            this.size = Integer.parseInt(arrayOfData[2]+arrayOfData[3],16) + 3; //3 потому, что к размеру посылки добавляем команду, размер и CRC16

            dataModel.setStrAndIntData(arrayOfData);
        } catch (NumberFormatException nfe){
            nfe.printStackTrace();
        }
    }

    public String[] getStringDataModel(){
        return dataModel.getStrData();
    }

    public void clear(){
        this.size = 0;
        this.namesOfChannels.clear();
        this.dataModel.clear();


    }
}
