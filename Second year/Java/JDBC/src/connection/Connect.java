package connection;

import java.sql.*;
public class Connect {

    private String driver = "org.postgresql.Driver";

    private String host = "****";

    private String port = "****";

    private String dbname = "****";
    private String user = "****";

    private String url = "jdbc:postgresql://" + host+":"+port + "/" + dbname;
    private String pass = "****";
    private Connection connection;
    private Statement statement;

    public Connect () {
        connection = makeConnection(); }

    public Connection getConnection(){
        return(connection);
    }
    public void close() {
        try {

            connection.close(); }

        catch (SQLException sqle){
            System.err.println("Blad przy zamykaniu polaczenia: " + sqle);

        } }

    public Connection makeConnection(){
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, user, pass);
            System.out.println("Pomyslnie nawiazano polaczenie ");

//            statement = connection.createStatement();
//            ResultSet rs = statement.executeQuery( "select * from dziekanat.\"adresy\" ;" );
//
//            while ( rs.next() ) {
//
//                int idAdresu = rs.getInt("id_adresu");
//
//                String  ulica = rs.getString("ulica");
//
//                System.out.println(idAdresu);
//                System.out.println(ulica);
//
//            }

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
    } }