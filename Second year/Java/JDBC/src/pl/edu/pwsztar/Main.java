package pl.edu.pwsztar;

import connection.Connect;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException {
	    Connect connection = new Connect();
        connection.readFile();
        connection.getConnection();

        // Wypisanie wszystkich tabeli z polaczonej bazy danych
        List<String> tables = connection.getTables();
        tables.forEach(System.out::println);


        // Zawartosc podanej tabeli
        ArrayList<ArrayList<String>> lista = connection.getDataFromTable("dziekanat.oceny");
        lista.forEach(System.out::println);

    }
}
