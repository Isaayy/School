package pl.edu.pwsztar;

import connection.Connect;

import java.util.List;

public class Main {

    public static void main(String[] args) {
	    Connect connect = new Connect();
	    connect.readFile();
        connect.getConnection();

        List<String> tables = connect.getTables();
        tables.forEach(System.out::println);
    }
}
