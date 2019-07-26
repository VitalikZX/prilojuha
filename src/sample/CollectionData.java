package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Created by 412 on 20.11.2017.
 */
public class CollectionData {
    private ObservableList<DataForTable> data = FXCollections.observableArrayList();

    public void add(DataForTable dataForTable){
        data.add(dataForTable);
    }

    public void update(DataForTable dataForTable){

    }

    public void delete(DataForTable dataForTable){
        data.remove(dataForTable);
    }

    public ObservableList<DataForTable> getDataList(){
        return data;
    }
}
