package pl.edu.pwsztar;

public class Main {

    public static void main(String[] args) {


        Calendar date0 = new Calendar(10,4,2050);
        System.out.println(date0.toString(1));



        Calendar date1 = new Calendar(13,2,2017);
        System.out.println(date1.toString(1));

        Calendar date2 = new Calendar(11,11,2020);
        System.out.println(date2.toString(0));
        System.out.println(date2.getDayName());

        Calendar date3 = new Calendar(6,1,2023);
        System.out.println(date3.toString(2));
        System.out.println(date3.getDayName());

        Calendar date4 = new Calendar(15,12,2020);
        System.out.println(date4.toString(2));
        System.out.println(date4.getDayName());
    }
}
