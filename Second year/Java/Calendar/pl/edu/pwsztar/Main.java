package pl.edu.pwsztar;

public class Main {

    public static void main(String[] args) {

        Calendar date0 = new Calendar(10,4,2050);
        System.out.println(date0.getDateInFormat(Calendar.DATEFORMAT_LONG));

        Calendar date1 = new Calendar(13,2,2017);
        System.out.println(date1.getDateInFormat(Calendar.DATEFORMAT_SHORT));

        Calendar date2 = new Calendar(11,11,2020);
        System.out.println(date2.getDateInFormat(Calendar.DATEFORMAT_CLASSIC));
        System.out.println(date2.getDayName());

        Calendar date3 = new Calendar(6,1,2023);
        System.out.println(date3.getDateInFormat(Calendar.DATEFORMAT_ROMAN));
        System.out.println(date3.getDayName());

        Calendar date4 = new Calendar(15,12,2020);
        System.out.println(date4.getDateInFormat(Calendar.DATEFORMAT_ROMAN));
        System.out.println(date4.getDayName());
    }
}
