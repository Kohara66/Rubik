
import java.sql.*;
import java.util.Scanner;
import java.util.ArrayList;


/**
 * Created by Happy on 4/7/2017.
 */
public class CubesDB {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";        //Configure the driver needed
    static final String DB_CONNECTION_URL = "jdbc:mysql://localhost:3306/Cubes";     //Connection string – where's the database?

    static final String USER = "happy";   //TODO replace with your username
    static final String PASSWORD =  System.getenv("MYSQL_PW");
    static Scanner stringScanner = new Scanner(System.in);
    static Scanner numberScanner = new Scanner(System.in);
    static  final String TABLE_NAME = "Cubes";
    static final String CUBES_SOLVER_COLUMN = "Name";
    static  final  String TIME_TAKEN_COLUMN = "TimeSolver";

   // public static void main(String[] args)throws Exception {//TODO handle exceptions properly

      //  Class.forName(JDBC_DRIVER);   //Instantiate the driver class

      //  Connection conn = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORD);  //Create a connection to DB
      //  Statement statement = conn.createStatement();//A statement object is used to run SQL statements
        //Cubesexist(statement);
    CubesDB() {

        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Can't instantiate driver class; " +
                    "check you have drivers and classpath configured correctly?");
            cnfe.printStackTrace();
            System.exit(-1);
            //No driver? Need to fix before anything else will work. So quit the program

        }
    }
        // Connection conn = null;
        // ResultSet  rs = null;
    //Defining method
     void createTable() {
         try
            //You should have already created a database via terminal/command prompt OR MySQL Workbench


             (Connection connection = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORD);
            Statement statement = connection.createStatement()){


            // create table on mysql
            String createSQLTableTemplate = "CREATE TABLE if NOT EXISTS %s (%s varchar(70), %s double)";
            String createSQLTable = String.format(createSQLTableTemplate, TABLE_NAME, CUBES_SOLVER_COLUMN, TIME_TAKEN_COLUMN);
            statement.executeUpdate(createSQLTable);

            statement.close();
            connection.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }

     }
     void addRecord(Cubes cubes) {

         try (Connection connection = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORD)) {

             String addSQLcubes = "INSERT INTO " + TABLE_NAME + " VALUES ( ? , ? ) ";
             PreparedStatement addSQLcubesRecord = connection.prepareStatement(addSQLcubes);
             addSQLcubesRecord.setString(1, cubes.name);
             addSQLcubesRecord.setDouble(2, cubes.time);

             addSQLcubesRecord.execute();

             addSQLcubesRecord.close();
             connection.close();

         } catch (SQLException se) {
             se.printStackTrace();
         }

     }
            //deleting data from database method

         void delete(Cubes cubes){
             try(Connection connection = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORD)){
                 String deleteSQLcubes = "DELETE FROM %s WHERE %s = ? AND %s = ?";
                 String deleteSQLcubesRecord = String.format(deleteSQLcubes,TABLE_NAME,CUBES_SOLVER_COLUMN,TIME_TAKEN_COLUMN);
                 PreparedStatement deletePreparedStatement = connection.prepareStatement(deleteSQLcubesRecord);
                 deletePreparedStatement.setString(1,cubes.name);
                 deletePreparedStatement.setDouble(2,cubes.time);
                 deletePreparedStatement.execute();
                 deletePreparedStatement.close();
                 connection.close();
             }catch (SQLException sqle){
                 sqle.printStackTrace();
             }
         }
         //methode for updating database records

         void updateRecord(Cubes cubes){
             try(Connection connection = DriverManager.getConnection(DB_CONNECTION_URL,USER,PASSWORD)){
                 String updateSQLcubes = "UPDATE %s SET %s = ? WHERE %s = ?";
                 String updateSQLcubesRecord = String.format(updateSQLcubes,TABLE_NAME,TIME_TAKEN_COLUMN,CUBES_SOLVER_COLUMN);
                 PreparedStatement updatePreparedStatement = connection.prepareStatement(updateSQLcubesRecord);
                 updatePreparedStatement.setDouble(1,cubes.time);
                 updatePreparedStatement.setString(2,cubes.name);
                 updatePreparedStatement.execute();
                 updatePreparedStatement.close();
                 connection.close();
             }catch (SQLException se){
                 se.printStackTrace();
             }
         }

         //Method to display records

    ArrayList<Cubes> fetchAllRecords() {
        ArrayList<Cubes> allRecords = new ArrayList();
        try (Connection connection = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {
            String selectSQLtable = "SELECT * FROM " + TABLE_NAME;
            ResultSet rs = statement.executeQuery(selectSQLtable);
            while (rs.next()) {
                String name = rs.getString(CUBES_SOLVER_COLUMN);
                double time = rs.getDouble(TIME_TAKEN_COLUMN);
                Cubes cubeSolverRecord = new Cubes(name, time);
                allRecords.add(cubeSolverRecord);}
            rs.close();
            statement.close();
            connection.close();
            return allRecords;
        } catch (SQLException se) {
            se.printStackTrace();
            return null;
        }
    }
}


  /*          }

          //First assignment code
          //add some data
           System.out.println("Create Cubes table");
           String searchName = stringScanner.nextLine();
          System.out.println();
          String addSQL = "INSERT INTO Cubes VALUE('cubestomer II robot', 5.270) ";
           statement.executeUpdate(addSQL);
           addSQL = "INSERT INTO Cubes VALUE('" +"', 27.93) ";
          statement.executeUpdate(addSQL);
            addSQL = "INSERT INTO Cubes VALUE('Ruxin Liu (age(3)', 99.33) ";
            statement.executeUpdate(addSQL);
            addSQL = "INSERT INTO Cubes VALUE('Mats valk(human record holder)', 6.27) ";
            statement.executeUpdate(addSQL);
            }
           Scanner s = new Scanner(System.in);
           System.out.println("Do you have new time");
            double Timesolver = s.nextDouble();
           System.out.println("who had this time?");
            String Name = s.next();
           String addtable = "INSERT INTO Cubes VALUES(  '" + Name + "' , " + Timesolver + ")";
           statement.executeUpdate(addtable);


        }
        catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        while (true) {
            System.out.println("Enter the name of cube solver:");
            String InputName = stringScanner.nextLine();
            System.out.println("Enter the time:");
            Double InputTime = numberScanner.nextDouble();


            String prepStatInsert = "INSERT INTO cubes VALUES ( ? , ? )";
            PreparedStatement psInsert = conn.prepareStatement(prepStatInsert);
            psInsert.setString(1, InputName);
            psInsert.setDouble(2, InputTime);   //now this should be an int
            psInsert.executeUpdate();
            System.out.println("Do you have any more input (y for yes and n for no)");
            String decision = stringScanner.nextLine();
            if (decision.equalsIgnoreCase("n"))

                break;
        }


        String fetchAllDataSQL = "SELECT * FROM cubes";
        ResultSet rs = statement.executeQuery(fetchAllDataSQL);

        while (rs.next()) {
            String Things = rs.getString("Name");
            double solveTime = rs.getDouble("TimeSolver");
            System.out.println("Name = " + Things + " time = " + solveTime);
        }
        //statement.execute("DROP TABLE cube");
        statement.close();
        conn.close();


    }
    catch (SQLException se) {
       se.printStackTrace();
    }
}


       finally {
           //A finally block runs whether an exception is thrown or not.
           // Close resources and tidy up whether this code worked or not.

           try {
                if (statement != null) {
                    statement.close();
                   System.out.println("Statement closed");
                }
            } catch (SQLException se){
               //Closing the connection could throw an exception too

                se.printStackTrace();
           }
            try {
                if (conn != null) {
                    conn.close();
                   //Close connection to database

                    System.out.println("Database connection closed");
                }
            } catch (SQLException se) {
               se.printStackTrace();
           }
            System.out.println("End of program");

        }
    }
    private static boolean Cubesexist(Statement statement) throws SQLException {


        String checkTablePresentQuery = "SHOW TABLES LIKE 'Cubes'";   //Can query the database schema
        ResultSet tablesRS = statement.executeQuery(checkTablePresentQuery);
        if (tablesRS.next()) {    //If ResultSet has a next row, it has at least one row... that must be our table
            return true;
        }
        return false;

  }
*/






