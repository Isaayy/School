package org.example;

import javafx.stage.FileChooser;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

/**
 * Klasa odpowiedzialna za wszystkie funkcjonalnosci zachodzace w programie
 * @author Jakub Serwin
 */

public class Connect {

    private String driver = "org.postgresql.Driver";
    private String host;
    private String port;
    private String dbname;
    private String user;
    private String url ;
    private String pass;

    private List<String> tables = new ArrayList();

    /**
     * Metoda odczytująca dane z pliku oraz wstawienie ich do zmiennych uzytych później do nawiazania polaczenia
     * wywolywana po zalaczeniu pliku txt przez uzytkownika
     */
    public void readFile(){
        try {
            FileChooser fc = new FileChooser();
            fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("TEXT files (*.txt)", "*.txt"));
            File file = fc.showOpenDialog(null);

            File myObj = new File(file.getAbsolutePath());
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

            System.out.println(this.dbname );
            System.out.println(this.url );
            System.out.println(this.host );
            System.out.println(this.port );


        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private Connection connection;
    private Statement statement;

    public Connect () {}

    /**
     * Metoda zwracajaca nawiazane polaczenie z baza danych
     * @return      connection
     */
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

    /**
     * Metoda nawiązująca polaczenie z baza danych
     * Zwraca polaczenie jesli udało się polaczyc lub null w przeciwnym wypadku
     * @return      connection
     */
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

    /**
     * Metoda zwracjaca wszystkie tabele z bazy danych
     * @return      tables
     */
    public List<String> getTables() {
        return this.tables;
    }

    /**
     * Metoda pobierajaca dane z podanej tabeli
     * Sa one wstawiane do tablicy 2D
     * @param  table  tabela z ktorej mają zostac pobrane dane
     * @return      cellData
     */
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

    /**
     * Metoda wysylajaca email z listy adresow które znajduja sie w podanej przez uzytkownika tabeli
     * Email zostaje wyslany gdy adresowi przypada wartosc true
     * @param  username  login klienta pocztowego
     * @param  password  haslo klienta pocztowego
     * @param  table     tabela z adresami
     */
    public void SendingEmail(String username, String password, String table) throws SQLException {

        Properties prop = new Properties();
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");


        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            ArrayList<ArrayList<String>> emailList = this.getDataFromTable(table);

            for (ArrayList<String> column: emailList) {
                for (String row: column) {
                    if (column.equals("true")) {

                        Message message = new MimeMessage(session);
                        message.setFrom(new InternetAddress(username));
                        message.setRecipients(
                                Message.RecipientType.TO,
                                InternetAddress.parse(row)
                        );
                        message.setSubject("Auto mail");
                        message.setText("You recieved auto message");

                        Transport.send(message);
                    }
                }
            }
            } catch(MessagingException e) {
            e.printStackTrace();
        }

    }
}