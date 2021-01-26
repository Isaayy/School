package pl.edu.pwsztar;

class dataError extends RuntimeException {
    dataError (String message){
        super(message);
    }
}

public class Czytelnik {
    String imie;
    String nazwisko;
    int nr_czytelnika;

    Czytelnik(String i, String n ,int nr){
        if(i.isEmpty() || n.isEmpty()) throw new dataError("Puste imie lub nazwisko");
        this.imie = i;
        this.nazwisko = n;
        this.nr_czytelnika = nr;
    }

}
