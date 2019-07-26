package sample;

/**
 * Created by Vital on 19.11.2017.
 */
public class DataModel {
    private int size;
    private int[] intData;
    private String[] strData;

    DataModel(String[] data) throws NumberFormatException{
        try {
            this.size = Integer.parseInt(data[2]+data[3],16) + 3; //3 потому, что к размеру посылки добавляем команду, размер и CRC16
            intData = new int[size];
            strData = new String[size];
            for (int i=0; i<data.length-1; i=i+2){
                strData[i/2] = data[i] + data[i+1];
                intData[i/2] = Integer.parseInt(data[i] + data[i+1],16);
            }
        } catch (NumberFormatException nfe){
            nfe.printStackTrace();
        }
    }

    public int getSize() {
        return size;
    }

    public int[] getIntData() {
        return intData;
    }

    public String[] getStrData() {
        return strData;
    }

    public void setStrAndIntData(String[] strData) {
        for(int i=0; i<strData.length-1; i=i+2) {
            this.strData[i/2] = strData[i]+strData[i+1];
            this.intData[i/2] = Integer.parseInt(strData[i]+strData[i+1],16);
        }
    }

    public void clear(){
        this.size = 0;
        for (int i = 0; i < intData.length; i++) {
            intData[i] = 0;
            strData[i] = "";
        }
    }
}
