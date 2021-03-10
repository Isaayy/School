package pl.edu.pwsztar;

public class Main {

    public static void main(String[] args) {
        JavaMailApi app = new JavaMailApi();

        app.setUsername("aa@gmail.com");
        app.setDestinationAddress("aa@gmail.com");
        app.setSubject("Zadanie JavaMail");
        app.setMsg("Szanowny Panie"
                + "\n\nEmail wyslany przy pomocy JavaMail"
                + "\nJakub Serwin");
        app.sendMail();
    }
}
