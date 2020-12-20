package pl.edu.pwsztar;

class dataError extends RuntimeException {
    dataError (String message){
        super(message);
    }
}

/**
 * Months class
 * Store information about days in each month, name of it and order
 */

public class Months {
    private int monthDays;
    private int monthOrder;
    private String monthName;

    /**
     * Class constructor.
     */
    Months(int days,int order,String name){
        this.monthDays = days;
        this.monthOrder = order;
        this.monthName = name;
    }

    /**
     * Array of the months in one year
     */
    private static Months[] months = {
            new Months(31,1, "January"),
            new Months(28,2,"February"),
            new Months(31,3,"March"),
            new Months(30,4,"April"),
            new Months(31,5,"May"),
            new Months(30,6,"June"),
            new Months(31,7,"July"),
            new Months(31,8,"August"),
            new Months(30,9,"September"),
            new Months(31,10,"October"),
            new Months(30,11,"November"),
            new Months(31,12,"December")
    };

    /**
     * Method which return month object by given int
     * @param  monthNumber  integer number from 1-12
     * * * @return      month object
     */
    public static Months getMonth(int monthNumber){
        try{
            return months[monthNumber-1];
        }
        catch (ArrayIndexOutOfBoundsException er){
            throw new dataError("Index out of bounds - wrong month number");
        }
    }

    /**
     * Get name of the month
     * * * @return      month name
     */
    public String getName() {
        return monthName;
    }

    /**
     * Get number of days of the month
     * * * @return      month days
     */
    public int getDays() {
        return monthDays;
    }


    /**
     * Get the month order
     * * * @return      month order
     */
    public int getOrder() {
        return monthOrder;
    }

    /**
     * Get Roman Name of the Month Method
     * Switch that transform int moth number to roman number
     * * * @return      month order in roman
     */
    public String getRomanName(){
        switch (monthOrder){
            case 1:
                return "I";
            case 2:
                return "II";
            case 3:
                return "III";
            case 4:
                return "IV";
            case 5:
                return "V";
            case 6:
                return "VI";
            case 7:
                return "VII";
            case 8:
                return "VIII";
            case 9:
                return "IX";
            case 10:
                return "X";
            case 11:
                return "XI";
            case 12:
                return "XII";
        }
        return "";
    }

}