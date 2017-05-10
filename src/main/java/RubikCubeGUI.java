import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;



/**
 * Created by Happy on 4/30/2017.
 */
public class RubikCubeGUI extends JFrame {
    private JPanel rootPanel;
    private JLabel NameLabel;
    private JLabel TimeLabel;
    private JTextField NametextField;
    private JTextField TimetextField;
    private JLabel NewTimeLabel;
    private JTextField NewTimetextField;
    private JLabel SearchNameLabel;
    private JTextField SearchNametextField;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JList<Cubes> Displaylist;
    //private JList<Cubes>DisplayList;
    private DefaultListModel<Cubes> DisplayListModel;

    private DataBaseController dataBaseController ;


    RubikCubeGUI(DataBaseController dataBaseController) {

    super("Cube Rubic Solver");
    setPreferredSize(new Dimension(500,350));
       this.dataBaseController = dataBaseController; //store a reference to the DBController object to make request to the database.
    //Configures the list model of Cubes
    DisplayListModel = new DefaultListModel<Cubes>();
        Displaylist.setModel(DisplayListModel);
    setContentPane(rootPanel);
    pack();
    setVisible(true);
    addListeners();
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //setContentPane(rootPanel);
}



    private void addListeners() {
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cubes cubes = Displaylist.getSelectedValue();
                if(cubes ==null){
                    JOptionPane.showMessageDialog(RubikCubeGUI.this,"Please select the record to delete");
                }else {
                    dataBaseController.delete(cubes);
                    ArrayList<Cubes> cubes1 = dataBaseController.getAllData();
                    setListData(cubes1);
                }
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchName = SearchNametextField.getText();
                if(searchName.isEmpty()){
                    JOptionPane.showMessageDialog(RubikCubeGUI.this,"Please type the name to update time");
                    return;
                }
                double updateTime;
                try{
                    updateTime = Double.parseDouble(NewTimetextField.getText());
                }catch (NumberFormatException ne){
                    JOptionPane.showMessageDialog(RubikCubeGUI.this,"Enter the new time to update");
                    return;
                }
                Cubes cubesUpdateRecord = new Cubes(searchName,updateTime);
                dataBaseController.updateRecord(cubesUpdateRecord);
                SearchNametextField.setText("");
                NewTimetextField.setText("");
                ArrayList<Cubes> allData = dataBaseController.getAllData();
                setListData(allData);
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Read data, send message to database via controller

                String name = NewTimetextField.getText();

                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(RubikCubeGUI.this, "Enter the name");
                    return;
                }
                double time;

                try {
                    time = Double.parseDouble(TimetextField.getText());
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(RubikCubeGUI.this, "Enter the number for time");
                    return;
                }

                Cubes cubesRecord = new Cubes(name, time);
                dataBaseController.addRecordToDatabase(cubesRecord);

                //Clear input JTextFields
                NewTimetextField.setText("");
                TimetextField.setText("");

                //request all data from database to update list
                ArrayList<Cubes> allData = dataBaseController.getAllData();
                setListData(allData);
            }
        });
    }
    void setListData(ArrayList<Cubes> data) {
        DisplayListModel.clear();
        //add cubes object to display list model.
        for (Cubes cb : data) {
            DisplayListModel.addElement(cb);
        }
    }
}




