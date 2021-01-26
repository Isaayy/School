package pl.edu.pwsztar;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Biblioteka {

    List<Czytelnik> czytelnicy ;

    Biblioteka(List<Czytelnik> czytelnicy){

        this.czytelnicy = czytelnicy;

    }

    public void dodajCzytelnika(String imie, String naziwsko, int nr) {
        this.czytelnicy.add(new Czytelnik(imie,naziwsko,nr));
    }

    public void usunCzytelnika(char l){
        char currentChar;
        for (int i = 0 ; i < this.czytelnicy.size() ; i++) {
            int times = 0 ;
            for(int j = 0 ; j < this.czytelnicy.get(i).imie.length() ; j++){
                currentChar =  this.czytelnicy.get(i).imie.charAt(j);
                if(currentChar == l)
                    times++;
            }
            if(times == 2) this.czytelnicy.remove(i);
        }

    }

    public Czytelnik wyszukaj(String imie_nazwisko){
        for (Czytelnik item : this.czytelnicy) {
            if(item.imie.equals(imie_nazwisko) || item.nazwisko.equals(imie_nazwisko) )
                return item;
        }
        throw new dataError("Nie znaleziono czytelnika");
    }

    public void zapisz(int start , int end) throws IOException {
        File file = new File("czytelnicy.txt");

        file.createNewFile();

        FileWriter writer = new FileWriter(file);

        for (Czytelnik item : this.czytelnicy) {
            if( item.nr_czytelnika >= start && item.nr_czytelnika <= end)
                writer.write(item.imie+" "+ item.nazwisko+" "+item.nr_czytelnika + "\n");
        }

        writer.flush();
        writer.close();

    }



}
