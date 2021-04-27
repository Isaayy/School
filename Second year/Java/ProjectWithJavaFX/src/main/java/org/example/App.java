package org.example;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Glowna klasa programu, sluzy do interakcji z uzytkownikiem, ktory moze zalaczyc plik z danymi do bazy danych i korzystac z dalszych funkcjonalnosci
 * @author Jakub Serwin
 */

public class App extends Application {

    @Override
    public void start(Stage stage) {
        var javaVersion = SystemInfo.javaVersion();
        var javafxVersion = SystemInfo.javafxVersion();

        Connect connection = new Connect();

        var label = new Label("Aby połączyć się z bazą danych załącz plik z ustawieniami dotyczącymi połączenia");
        label.setLayoutX(100);
        label.setLayoutY(150);

        var label2 = new Label("Tabele z podanej bazy danych zostały wypisane w konsoli");
        label2.setLayoutX(140);
        label2.setLayoutY(240);
        label2.setVisible(false);

        var label3 = new Label("Podaj tablice której zawartość chcesz wypisać (np. dziekanat.oceny)");
        label3.setLayoutX(100);
        label3.setLayoutY(310);
        label3.setVisible(false);

        TextField textField = new TextField();
        textField.setLayoutX(100);
        textField.setLayoutY(330);
        textField.setVisible(false);

        Button btn = new Button("Wypisz");
        btn.setLayoutX(255);
        btn.setLayoutY(330);
        btn.setVisible(false);
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                ArrayList<ArrayList<String>> lista = null;
                try {
                    lista = connection.getDataFromTable(textField.getText());
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                lista.forEach(System.out::println);
            }
        });

        Button button = new Button("Załącz plik");
        button.setLayoutX(280);
        button.setLayoutY(190);
        button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                connection.readFile();
                connection.getConnection();

                // Wypisanie wszystkich tabeli z polaczonej bazy danych
                List<String> tables = connection.getTables();
                tables.forEach(System.out::println);
                label2.setVisible(true);

                label3.setVisible(true);
                textField.setVisible(true);
                btn.setVisible(true);

            }
        });

        Pane pane = new Pane();
        pane.getChildren().add(label);
        pane.getChildren().add(label2);
        pane.getChildren().add(label3);
        pane.getChildren().add(textField);
        pane.getChildren().add(button);
        pane.getChildren().add(btn);

        Scene scene = new Scene(pane, 640, 480);
        stage.setScene(scene);
        stage.show();


    }

    public static void main(String[] args) {
        launch();
    }

}