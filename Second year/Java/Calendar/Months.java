package pl.edu.pwsztar;

class dataError extends RuntimeException {
    dataError (String message){
        super(message);
    }
}

public class Months {
    private int monthDays;
    private int monthOrder;
    private String monthName;

    Months(int days,int order,String name){
        this.monthDays = days;
        this.monthOrder = order;
        this.monthName = name;
    }

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

    public static Months getMonth(int monthNumber){
        try{
            return months[monthNumber-1];
        }
        catch (ArrayIndexOutOfBoundsException er){
            throw new dataError("Index out of bounds - wrong month number");
        }
    }

    public String getName() {
        return monthName;
    }

    public int getDays() {
        return monthDays;
    }

    public int getOrder() {
        return monthOrder;
    }

}