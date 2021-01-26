package pl.edu.pwsztar;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        // Stworzenie czytelnikow
        Czytelnik c1=  new Czytelnik("Jan","Kowalski",1);
        Czytelnik c2=  new Czytelnik("Kamil","Nowak",2);
        Czytelnik c3=  new Czytelnik("Klaudia","Rzeka",3);
        List<Czytelnik> czytelnicy= new ArrayList<Czytelnik>();
        czytelnicy.add(c1);
        czytelnicy.add(c2);
        czytelnicy.add(c3);


        // Stworzenie obiektu biblioteka
        Biblioteka biblioteka = new Biblioteka(czytelnicy);

        // Dodanie nowych czytelnikow
        biblioteka.dodajCzytelnika("Marta","Zamek",4);
        biblioteka.dodajCzytelnika("Xavierv","Kamien",5);


        // Usuniecie czytelnika
        biblioteka.usunCzytelnika('v');

        // wyszukanie czytelnika
        System.out.println(biblioteka.wyszukaj("Marta").imie);

        // zapisanie czytelnikow do pliku
        biblioteka.zapisz(1,3);


    }

}
