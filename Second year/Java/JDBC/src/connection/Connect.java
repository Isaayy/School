package connection;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Connect {

    private String driver = "org.postgresql.Driver";

    private String host;
    private String port;
    private String dbname;
    private String user;

    private String url ;
    private String pass;

    private List<String> tables = new ArrayList();


    public void readFile() {
        try {
            File myObj = new File("C:\\Users\\Jakub\\Desktop\\data.txt");
            Scanner myReader = new Scanner(myObj);
            List<String> data = new ArrayList();

            while (myReader.hasNextLine()) {
                data.add(myReader.nextLine()) ;
            }
            myReader.close();

            this.host = data.get(0);
            this.port = data.get(1);
            this.dbname = data.get(2);
            this.user = data.get(3);
            this.pass = data.get(4);
            this.url =  "jdbc:postgresql://" + this.host+":"+this.port + "/" + this.dbname;


        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private Connection connection;
    private Statement statement;

    public Connect () {}

    public Connection getConnection(){
        if(this.url == null) return null;
        connection = makeConnection();

        return(connection);
    }

    public void close() {
        try {
            connection.close(); }

        catch (SQLException sqle){
            System.err.println("Blad przy zamykaniu polaczenia: " + sqle);

        }
    }

    public Connection makeConnection(){
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, user, pass);
            System.out.println("Pomyslnie nawiazano polaczenie ");

            DatabaseMetaData md = connection.getMetaData();
            ResultSet rs = md.getTables(null, null, "%", null);
            while (rs.next()) {
                this.tables.add(rs.getString(3));
            }
            return(connection);

        }
        catch(ClassNotFoundException cnfe) {
            System.err.println("Blad ladowania sterownika: " + cnfe);

            return(null);
        }
        catch(SQLException sqle) {
            System.err.println("Blad przy nawiązywaniu polaczenia: " + sqle);

            return(null);
        }
    }

    public List<String> getTables() {
        return this.tables;
    }

    public ArrayList<ArrayList<String>> getDataFromTable(String table) throws SQLException {

        Statement stmt = connection.createStatement();
        String query = "select * from " + table;
        ResultSet rs = stmt.executeQuery(query);

        ResultSetMetaData rsmd;
        ArrayList<ArrayList<String>> cellData = new ArrayList<>();

        while (rs.next()) {
            rsmd = rs.getMetaData();
            ArrayList<String> innerList = new ArrayList<String>();

            for (int i = 1 ; i <= rsmd.getColumnCount() ; i++){
                innerList.add(rs.getString(i));
            }
            cellData.add(innerList);
        }
        return cellData;
   }
}