
import java.util.ArrayList;
/**
 * Created by Happy on 5/9/2017.
 */
//This class is responsible for connecting GUI and database
public class DataBaseController {

    static RubikCubeGUI rubikCubeGUI;
    static CubesDB cubesDB;

    public static void main(String[] args) {

        DataBaseController dataBaseController = new DataBaseController();
        dataBaseController.startApp();

    }

    private void startApp() {

        cubesDB = new CubesDB();
        cubesDB.createTable();
        ArrayList<Cubes> allData = cubesDB.fetchAllRecords();
        rubikCubeGUI = new RubikCubeGUI(this);
        rubikCubeGUI.setListData(allData);
    }
    void delete(Cubes cubes){
        cubesDB.delete(cubes);
    }
    void updateRecord(Cubes cubes){
        cubesDB.updateRecord(cubes);
    }


    ArrayList<Cubes> getAllData() {
        return cubesDB.fetchAllRecords();
    }

    void addRecordToDatabase(Cubes cubes) {
        cubesDB.addRecord(cubes);
    }
}






