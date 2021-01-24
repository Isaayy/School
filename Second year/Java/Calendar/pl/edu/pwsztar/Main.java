package pl.edu.pwsztar;

import java.util.*;


/**
 * Main class
 * Application of the program
 */
public class Main {

    public static void main(String[] args) {

        Calendar dateNew = Calendar.parse("17-01-2021");
        System.out.println(dateNew.getDateInFormat(Calendar.DATEFORMAT_SHORT));
        System.out.println(dateNew.getDayName());

        int size = 1004;
        for(int i = 0 ; i < size ; i++) dateNew.weekBackward();

        System.out.println(dateNew.getDateInFormat(Calendar.DATEFORMAT_SHORT));
        System.out.println(dateNew.getDayName());


        Calendar date1 = new Calendar(6,1,2023);
        Calendar date2 = new Calendar(13,2,2017);
        Calendar date3 = new Calendar(11,11,2020);
        Calendar date4 = new Calendar(9,5,2020);
        Calendar date5 = new Calendar(15,12,2019);
        Calendar date6 = new Calendar(12,1,2019);
        Calendar date7 = new Calendar(10,10,2021);
        Calendar date8 = new Calendar(16,11,2021);
        Calendar date9 = new Calendar(14,3,2018);
        Calendar date10 = new Calendar(29,8,2024);

//         ######################################################
//         SORTING ARRAY OF DATES

        Calendar dates[]={date1,date2,date3,date4,date5,date6,date7,date8,date9,date10};

        System.out.println("\nArray before sorting: ");
        System.out.println("-----------------------------------------------------------------------------------------------------");

        for(Calendar date: dates)
            System.out.print(date.getDateInFormat(Calendar.DATEFORMAT_CLASSIC)+"   ");

        System.out.println("\n \nArray after sorting: ");
        System.out.println("-----------------------------------------------------------------------------------------------------");

        Arrays.sort(dates);

        for(Calendar date: dates)
            System.out.print(date.getDateInFormat(Calendar.DATEFORMAT_CLASSIC)+"   ");

        System.out.println("\n\n-----------------------------------------------------------------------------------------------------");
        System.out.println("-----------------------------------------------------------------------------------------------------");

        // ######################################################
        // SORTING LIST OF DATES

        List<Calendar> datesList = Arrays.asList(date1,date2,date3,date4,date5,date6,date7,date8,date9,date10);

        System.out.println("\nList before sorting: ");
        System.out.println("-----------------------------------------------------------------------------------------------------");

        for (Calendar date : datesList) {
            System.out.print(date.getDateInFormat(Calendar.DATEFORMAT_CLASSIC)+"   ");
        }

        System.out.println("\n \nList after sorting: ");
        System.out.println("-----------------------------------------------------------------------------------------------------");

        Collections.sort(datesList);

        for (Calendar date : datesList) {
            System.out.print(date.getDateInFormat(Calendar.DATEFORMAT_CLASSIC)+"   ");
        }
//
//        System.out.println("\n\n-----------------------------------------------------------------------------------------------------");
//        System.out.println("-----------------------------------------------------------------------------------------------------");
//
//        System.out.println("\n \nCalendar string constructor: ");
//        System.out.println("-----------------------------------------------------------------------------------------------------");
//
//        Calendar date11 = new Calendar("15-01-2021");
//        System.out.println(date11.getDateInFormat(Calendar.DATEFORMAT_CLASSIC));
//
//        System.out.println("\n \nCalendar parse: ");
//        System.out.println("-----------------------------------------------------------------------------------------------------");
//
//        Calendar date12 = Calendar.parse("12-02-2021");
//        System.out.println(date12.getDateInFormat(Calendar.DATEFORMAT_CLASSIC));
//
//        System.out.println("\n \n------------------------------------------------------------------------------------------: ");
//        System.out.println("-----------------------------------------------------------------------------------------------------");


    }
}
