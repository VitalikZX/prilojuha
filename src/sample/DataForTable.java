package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Vital on 19.11.2017.
 */
public class DataForTable {
    private StringProperty name;
    private StringProperty hex;
    private StringProperty dec;

    public DataForTable(String name, String hex, String dec) {
        this.name = new SimpleStringProperty(name);
        this.hex = new SimpleStringProperty(hex);
        this.dec = new SimpleStringProperty(dec);
    }

    public StringProperty getName() {
        return name;
    }

    public void setName(StringProperty name) {
        this.name = name;
    }

    public StringProperty getHex() {
        return hex;
    }

    public void setHex(StringProperty hex) {
        this.hex = hex;
    }

    public StringProperty getDec() {
        return dec;
    }

    public void setDec(StringProperty dec) {
        this.dec = dec;
    }
}
